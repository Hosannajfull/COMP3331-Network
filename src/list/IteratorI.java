package list;
	

	public interface IteratorI {
	    /**
	     * Checks if there is a next element.
	     * 
	     * @return true if a next element exists, false otherwise
	     */
	    public boolean hasNext();
	    
	    /**
	     * Gets the next element.
	     * 
	     * @return next element
	     */
	    public Object next();
	}

