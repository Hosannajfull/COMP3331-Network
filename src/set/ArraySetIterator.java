package set;

public class ArraySetIterator implements Iterator {

    private int nextIndex;
    private ArraySet set;

    /**
     * Constructor. Sets the {@link ArraySet} to iterate over and sets the
     * <code>nextIndex</code> to 0.
     * 
     * @param set
     *            set to iterate over
     */
    public ArraySetIterator(ArraySet set) {
        this.nextIndex = 0;
        this.set = set;
    }

    @Override
    public boolean hasNext() {
        return this.nextIndex < this.set.size();
    }

    @Override
    public Object next() {
        // Returns the element at nextIndex and increments nextIndex
        return this.set.get(this.nextIndex++);
    }
}