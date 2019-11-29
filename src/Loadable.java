import java.util.ArrayList;

public class Loadable<T> implements ILoadable<T> {

    /**
     * The maximum capacity.
     */
    private final int maximumCapacity;

    /**
     * List containing the objects.
     */
    private final ArrayList<T> objectArrayList;

    Loadable(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        objectArrayList = new ArrayList<>();
    }

    @Override
    public boolean load(T object) {
        if (objectArrayList.size() < maximumCapacity) {
            if (!objectArrayList.contains(object)) {
                if (object instanceof AbstractVehicle) {
                    ((AbstractVehicle) object).isLoaded = true;
                }
                return objectArrayList.add(object);
            }
        }
        return false;
    }

    @Override
    public boolean unload(T object) {
        if (object instanceof AbstractVehicle) {
            ((AbstractVehicle) object).isLoaded = false;
        }
        return objectArrayList.remove(object);
    }

    public ArrayList<T> getObjectArrayList() {
        return objectArrayList;
    }
}