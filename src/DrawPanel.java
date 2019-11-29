import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

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
        try {
            BufferedImage bufferedImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/background.png"));
            backgroundImage = bufferedImage.getScaledInstance(800, 800, Image.SCALE_SMOOTH);

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                    bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/redcar.png")).getScaledInstance(100, 60, Image.SCALE_SMOOTH));
                    vehiclePoints.add(new Point(vehicle.position.x, vehicle.position.y));
                } else if (vehicle.getClass().equals(Volvo240.class)) {
                    bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/bluecar.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                    vehiclePoints.add(new Point(vehicle.position.x, vehicle.position.y));
                } else {
                    bufferedImages.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/truck.png")).getScaledInstance(150, 200, Image.SCALE_SMOOTH));
                    vehiclePoints.add(new Point(vehicle.position.x, vehicle.position.y));
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
