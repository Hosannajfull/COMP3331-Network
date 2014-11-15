package set;

public class ArraySet extends AbstractSet implements Set {
    private Comparable[] values;
    private int count;

    /**
     * Constructor. Instantiates the values array and sets the count to 0.
     * 
     * @param max
     *            maximum length of the values array
     */
    public ArraySet(int max) {
        this.values = new Comparable[max];
        this.count = 0;
    }

    /**
     * Checks if the values array is full or not.
     * 
     * @return true if the values array is full, false otherwise
     */
    private boolean isFull() {
        // Returns whether the size is equal to the max length of the values
        // array
        return this.count == this.values.length;
    }

    /**
     * Finds the index of an object.
     * 
     * @param object
     *            object to find
     * @param minIndex
     *            minimum index to look from
     * @param maxIndex
     *            maximum index to look to
     * @return index of the object or the index where the object is supposed to
     *         exist in the values array
     */
    private int findIndex(Comparable object, int minIndex, int maxIndex) {
        // There's nowhere to look, so just return the index we're currently at
        if (minIndex == maxIndex) {
            return minIndex;
        }

        // Get the middle index
        int midIndex = (minIndex + maxIndex) / 2;

        // Get the comparison of the parameter object and the object at the
        // midIndex
        int comparison = object.compareTo(this.values[midIndex]);

        // Checks if the object at midIndex is equal to the parameter object
        if (comparison == 0) {
            return midIndex;
        }

        // If the comparison is > 0, then check the range between the midIndex
        // and the maxIndex
        if (comparison > 0) {
            return this.findIndex(object, midIndex + 1, maxIndex);
        }

        // If the comparison is < 0, then check the range between minIndex and
        // midIndex
        else {
            return this.findIndex(object, minIndex, midIndex);
        }
    }

    /**
     * Shifts the elements of the values array down (towards to end of the
     * array), leaving an empty spot at the index.
     * 
     * @param index
     *            index to shift the elements from
     */
    private void shiftDown(int index) {
        // Start pointer at the count
        // While pointer is >= index, decrement pointer per loop
        // Increment the count after all of this is done
        for (int pointer = this.count++; pointer >= index; pointer--) {
            // Set the entry at pointer + 1 as the value of the space below it
            this.values[pointer + 1] = this.values[pointer];
        }
    }

    /**
     * Shifts the elements of the values array up (towards the beginning of the
     * array), effectively removing the element at the index.
     * 
     * @param index
     *            index to shift the elements from
     */
    private void shiftUp(int index) {
        // Decrement the count
        this.count--;

        // Replace each entry with the entry above it
        for (int pointer = index; pointer < this.count; pointer++) {
            this.values[pointer] = this.values[pointer + 1];
        }
    }

    /**
     * Overriding the {@link AbstractSet} <code>size()</code> method with an
     * O(1) implementation.
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * Overriding the {@link AbstractSet} <code>isMember()</code> method with an
     * O(log n) implementation
     */
    @Override
    public boolean isMember(Object object) {
        // Check if the object is a Comparable object. If not then return false.
        if (!(object instanceof Comparable)) {
            return false;
        }

        // Cast to a Comparable object
        Comparable comparableObject = (Comparable) object;

        // Get the index of this object (or the supposed index if it doesn't
        // exist in the set)
        int index = this.findIndex(comparableObject, 0, this.count);

        // Return whether the object at this index is equal to the parameter
        // object
        return comparableObject.compareTo(this.values[index]) == 0;
    }

    /**
     * Gets an object at a given index.
     * 
     * @param index
     *            index to get the object from
     * @return object at the given index or null if no object exists at that
     *         index
     */
    public Comparable get(int index) {
        if (index >= this.values.length) {
            // Index out of bounds, so return null
            return null;
        }
        
        return this.values[index];
    }

    @Override
    public void add(Object object) {
        if (this.isFull()) {
            // Throw an exception if our array is full and we cannot add any
            // more objects
            throw new ArrayIndexOutOfBoundsException("Array Full");
        }

        // Check if the object is a Comparable object
        if (object instanceof Comparable) {
            Comparable comparableObject = (Comparable) object;

            // Get the index of this object (or the supposed index if it doesn't
            // exist in the set)
            int index = this.findIndex(comparableObject, 0, this.count);

            // Make sure that this object does not already exist within the set
            if (index >= this.count || comparableObject.compareTo(this.values[index]) != 0) {
                // Shift the array down to create an empty spot to put this
                // object
                this.shiftDown(index);

                // Insert the object in the newly empty spot
                this.values[index] = comparableObject;
            }
        }
    }

    @Override
    public void remove(Object object) {
        // Check if the object is a Comparable object
        if (object instanceof Comparable) {
            Comparable comparableObject = (Comparable) object;

            // Get the index of this object
            int index = this.findIndex(comparableObject, 0, this.count);

            // Remove this object if it exists at the index
            if (comparableObject.compareTo(this.values[index]) == 0) {
                // Shift the array up to remove the object
                this.shiftUp(index);
            }
        }
    }

    @Override
    public ArraySetIterator iterator() {
        return new ArraySetIterator(this);
    }

    /**
     * Override the copy method to suit this set.
     */
    @Override
    public Set copy() {
        // Create a new ArraySet with this set's max length
        ArraySet copySet = new ArraySet(this.values.length);

        // Copy the values from this set to the copy set
        for (int i = 0; i < this.count; i++) {
            copySet.values[i] = this.values[i];
        }

        // Set the count for the copy set
        copySet.count = this.count;

        return copySet;
    }

    @Override
    public Set empty() {
        // Return a new ArraySet with this set's max length
        return new ArraySet(this.values.length);
    }
}