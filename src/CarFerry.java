import java.util.ArrayList;

public class CarFerry extends AbstractVehicle implements ILoadable<AbstractVehicle> {

    private final Loadable<AbstractVehicle> loadable;

    public CarFerry(int maximumVehicles) {
        loadable = new Loadable<>(maximumVehicles);
        weight = 100000;
    }

    @Override
    double speedFactor() {
        return enginePower * 0.01;
    }

    @Override
    public boolean load(AbstractVehicle vehicle) {
        if (!vehicle.getClass().equals(this.getClass())) {
            weight += vehicle.weight;
            return loadable.load(vehicle);
        }
        return false;
    }

    @Override
    public boolean unload(AbstractVehicle vehicle) {
        if (loadable.getObjectArrayList().indexOf(vehicle) == 0) {
            weight -= vehicle.weight;
            return loadable.unload(vehicle);
        }
        return false;
    }

    public ArrayList getVehicles() {
        return loadable.getObjectArrayList();
    }
}
