public class GenericGarage implements ILoadable<AbstractVehicle> {

    private final Loadable<AbstractVehicle> loadable;

    public GenericGarage(int maximumVehicles) {
        loadable = new Loadable<>(maximumVehicles);
    }

    @Override
    public boolean load(AbstractVehicle object) {
        return loadable.load(object);
    }

    @Override
    public boolean unload(AbstractVehicle object) {
        return loadable.unload(object);
    }
}



