import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

/*
S-Single Responsibility Principle

    Loadable - Only focus to add and remove items from list.

O-Open Closed Principle

    AbstractVehicle - Can extend and add methods for specific subclasses.

L-Liskovs Substitutions Principle

    Volvo240/Saab95 and Scania can do just as much as AbstractVehicle

I-Interface Segregation Principle

    ILoadable and IMovable does different things.
    So we can use ILoadable in Garage and not IMovable because
    garage should not move.

D-Dependancy Inversion Principle

    We use as wide variable as possible to store objects.
    ex, List<AbstractVehicle> instead of ArrayList<Volvo250>
    and return AbstractVehicle.


Refactor Plan

    Apply decomposition on DrawPanel to implement better SoC
    Fix HashMap to bind AbstractVehicle with BufferedImage
    Check references between CarView, CarController, CarModel.
    Implement MVC design pattern.
    Implement Factory design pattern.
    AbstractVehicleFactory

 */


public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    List<Image> bufferedImages = new ArrayList<>();
    List<Point> vehiclePoints = new ArrayList<>();

    //Background Image
    Image backgroundImage;

    // TODO: Make this general for all cars
    void moveit(List<AbstractVehicle> vehicles){
        for(int i=0; i<vehicles.size(); i++){
            vehiclePoints.set(i, new Point(vehicles.get(i).position.x, vehicles.get(i).position.y));
        }
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        // Print an error message in case file is not found with a try/catch block

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, null);
        for(int i=0; i<vehiclePoints.size(); i++) {
            g.drawImage(bufferedImages.get(i), vehiclePoints.get(i).x, vehiclePoints.get(i).y, null); // see javadoc for more info on the parameters
        }
    }

    public void setBufferedImages(List<AbstractVehicle> vehicles){
        for(AbstractVehicle vehicle : vehicles){
            try {
                if (vehicle.getClass().equals(Saab95.class)) {
                    bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")).getScaledInstance(100, 60, Image.SCALE_SMOOTH));
                    vehiclePoints.add(new Point(vehicle.position.x, vehicle.position.y));
                    vehicle.setWidth(bufferedImages.get(bufferedImages.size()-1).getWidth(null));
                } else if (vehicle.getClass().equals(Volvo240.class)) {
                    bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    vehiclePoints.add(new Point(vehicle.position.x, vehicle.position.y));
                    vehicle.setWidth(bufferedImages.get(bufferedImages.size()-1).getWidth(null));
                } else {
                    bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
                    vehiclePoints.add(new Point(vehicle.position.x, vehicle.position.y));
                    vehicle.setWidth(bufferedImages.get(bufferedImages.size()-1).getWidth(null));
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
