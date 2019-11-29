import java.awt.*;
import java.util.ArrayList;

public class ScaniaCarCarrier extends Scania {

    /**
     * Number of cars the carrier can hold.
     */
    private final static int maxCars = 6;
    /**
     * List containing the loaded vehicles.
     */
    private final ArrayList<AbstractVehicle> carryList;

    public ScaniaCarCarrier(Point position) {
        super(position);
        carryList = new ArrayList<>();
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
        if (isFlatbedAtMaxTilt() && !this.equals(vehicle) && vehicle.weight <= 3000) {
            if (isWithinDistance(vehicle)) {
                if (carryList.size() < maxCars) {
                    vehicle.position.x = position.x;
                    vehicle.position.y = position.y;
                    weight += vehicle.weight;
                    if (carryList.add(vehicle)) {
                        vehicle.isLoaded = true;
                        return true;
                    }
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
        if (carryList.size() > 0 && isFlatbedAtMaxTilt()) {
            carryList.get(carryList.size() - 1).position.x = position.x + carryList.size();
            weight -= carryList.get(carryList.size() - 1).weight;
            carryList.get(carryList.size() - 1).isLoaded = false;
            carryList.remove(carryList.size() - 1);
            return true;
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
        for (AbstractVehicle v : carryList) {
            v.position.x = position.x;
            v.position.y = position.y;
        }
    }

    public ArrayList<AbstractVehicle> getCarryList() {
        return carryList;
    }
}
