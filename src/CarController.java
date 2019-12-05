import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 1;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    List<AbstractVehicle> vehicles = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.vehicles.add(new Volvo240(new Point(100, 160)));
        cc.vehicles.add(new Saab95(new Point(100, 345)));
        cc.vehicles.add(new Scania(new Point(100, 480)));

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            for (AbstractVehicle vehicle : vehicles) {

                /*if (!isVehicleInFrame(vehicle)) {
                    vehicle.turnLeft();
                    vehicle.turnLeft();

                }*/
                withinBoundsHandler(vehicle, frame.getWidth() - 115);
                vehicle.move();
            }

            frame.drawPanel.moveit(vehicles);
            frame.drawPanel.repaint();

        }
    }

    private void withinBoundsHandler(AbstractVehicle vehicle, int width) {
        if (vehicle.position.x > width) {
            vehicle.direction = 2;
        } else if (vehicle.position.x < 0) {
            vehicle.direction = 0;
        }
    }

    boolean isVehicleInFrame(AbstractVehicle vechicle) {
        if (vechicle.position.x > 0 && vechicle.position.x < frame.getWidth() - vechicle.getWidth()) {
            if (vechicle.position.y > 0 && vechicle.position.y < frame.getHeight() - frame.controlPanel.getHeight() - vechicle.getWidth()) {
                return true;
            }
        }

        return false;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (AbstractVehicle car : vehicles) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (AbstractVehicle car : vehicles) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (AbstractVehicle car : vehicles) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (AbstractVehicle car : vehicles) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftBed() {
        for (AbstractVehicle car : vehicles) {
            if (car instanceof Scania) {
                ((Scania) car).raiseFlatbed();
            }
        }
    }

    void lowerBed() {
        for (AbstractVehicle car : vehicles) {
            if (car instanceof Scania) {
                ((Scania) car).lowerFlatbed();
            }
        }
    }

    void startEngine() {
        for (AbstractVehicle car : vehicles) {
            car.startEngine();
        }
    }

    void stopEngine() {
        for (AbstractVehicle car : vehicles) {
            car.stopEngine();
        }
    }
}
