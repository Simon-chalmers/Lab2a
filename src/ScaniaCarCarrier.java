import java.awt.*;
import java.util.ArrayList;

public class ScaniaCarCarrier extends Scania implements ILoadable<AbstractVehicle>{

    /**
     * Number of cars the carrier can hold.
     */
    private final static int maxCars = 6;
    /**
     * Loadable class containing list of cars.
     */
    private final Loadable<AbstractVehicle> carrylist;

    public ScaniaCarCarrier(Point position, int maxCars) {
        super(position);
        carrylist = new Loadable<>(maxCars);
        nrDoors = 2;
        color = Color.white;
        enginePower = 600;
        weight = 12000;
        modelName = "Scania";
        direction = 0;
        currentTilt = 0;
        maxTilt = 70;
        changeTilt = maxTilt;
        stopEngine();
    }

    /**
     * @param vehicle Adds a vehicle to the carrier.
     * @return
     */
    public boolean addVehicle(AbstractVehicle vehicle) {
        if (isFlatbedAtMaxTilt() && !this.getClass().equals(vehicle.getClass()) && vehicle.weight <= 3000) {
            if (isWithinDistance(vehicle)) {
                if (carrylist.load(vehicle)) {
                    vehicle.position.x = position.x;
                    vehicle.position.y = position.y;
                    weight += vehicle.weight;
                    vehicle.isLoaded = true;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return Unloads a vehicle from the carrier (First in last out) and sets coordinates for unload position.
     */
    public boolean unloadVehicle() {
        if (isFlatbedAtMaxTilt()) {
            weight -= carrylist.getObjectArrayList().get(carrylist.getObjectArrayList().size()).weight;
            return carrylist.unload( carrylist.getObjectArrayList().get(carrylist.getObjectArrayList().size()));
        }
        return false;
    }

    /**
     * @param vehicle Checks if a vehicle is close enough to be added to the carrier.
     * @return
     */
    private boolean isWithinDistance(AbstractVehicle vehicle) {
        if (position.x - vehicle.position.x < 5 && position.x - vehicle.position.x >= 0) {
            return position.y - vehicle.position.y < 5 && position.y - vehicle.position.y >= 0;
        }
        return false;
    }


    @Override
    public void move() {
        super.move();
        for (AbstractVehicle v : getCarryList()) {
            v.position.x = position.x;
            v.position.y = position.y;
        }
    }

    public ArrayList<AbstractVehicle> getCarryList() {
        return carrylist.getObjectArrayList();
    }

    @Override
    public boolean load(AbstractVehicle object) {
        return false;
    }

    @Override
    public boolean unload(AbstractVehicle object) {
        return false;
    }
}
