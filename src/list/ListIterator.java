package list;

import java.util.Iterator;

public class ListIterator implements Iterator{
private ListSet set;
private int nextIndex;

	public ListIterator(ListSet a){
		  this.nextIndex = 0;
	      this.set = a;
	}
	public boolean hasNext() {
        return this.nextIndex < this.set.size();
	}
	
	public Object next() {
        return (Object) this.set.get(this.nextIndex++);
	}
	@Override
	public void remove() {
		
	}

	
}
