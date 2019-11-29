
public class Volvo240Garage implements ILoadable<Volvo240> {

    private final Loadable<Volvo240> loadable;

    public Volvo240Garage(int maximumCars) {
        loadable = new Loadable<>(maximumCars);
    }

    @Override
    public boolean load(Volvo240 volvo240) {
        return load(volvo240);
    }

    @Override
    public boolean unload(Volvo240 volvo240) {
        return unload(volvo240);
    }


}
