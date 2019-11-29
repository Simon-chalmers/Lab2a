public interface ILoadable<T> {

    /**
     * @param object Adds the object to the list .
     * @return True if successfully added the object.
     */
    boolean load(T object);

    /**
     * @param object Removes the object from the list.
     * @return True if successfully removed the object.
     */
    boolean unload(T object);

}
