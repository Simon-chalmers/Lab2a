public class Saab95Garage implements ILoadable<Saab95> {

    private final Loadable<Saab95> loadable;

    public Saab95Garage(int maximumCars) {
        loadable = new Loadable<>(maximumCars);
    }

    @Override
    public boolean load(Saab95 saab95) {
        return loadable.load(saab95);
    }

    @Override
    public boolean unload(Saab95 saab95) {
        return loadable.unload(saab95);
    }

}
