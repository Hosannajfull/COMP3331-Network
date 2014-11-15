package set;

public interface Set {

    /**
     * Calculates the size of the set.
     * 
     * @return number of elements in the set
     */
    public int size();

    /**
     * Checks if the set is empty or not.
     * 
     * @return true if the set is empty, false if the set is not empty
     */
    public boolean isEmpty();

    /**
     * Checks whether an object is a member of the set or not.
     * 
     * @param object
     *            object to check if is a member
     * @return true if the object is a member, false if the object is not a
     *         member
     */
    public boolean isMember(Object object);

    /**
     * Creates a new set with the union of this set and another set. Does not
     * modify the current set.
     * 
     * @param otherSet
     *            set to union with
     * @return union of this set and the other set
     */
    public Set union(Set otherSet);

    /**
     * Creates a new set with the intersection of this set and another set. Does
     * not modify the current set.
     * 
     * @param otherSet
     *            set to intersect with
     * @return intersection of this set and the other set
     */
    public Set intersection(Set otherSet);

    /**
     * Creates a copy of the current set. Does not modify the current set.
     * 
     * @return the copy of this set
     */
    public Set copy();

    /**
     * Adds an object to this set.
     * 
     * @param object
     *            object to add
     */
    public void add(Object object);

    /**
     * Removes an object from this set.
     * 
     * @param object
     *            object to remove
     */
    public void remove(Object object);

    /**
     * Returns the {@link Iterator} that will iterate through the elements of
     * this set.
     * 
     * @return {@link Iterator} for this set
     */
    public Iterator iterator();

    /**
     * Creates an empty copy of this set. Does not modify the current set.
     * 
     * @return an empty copy of this set
     */
    public Set empty();
}