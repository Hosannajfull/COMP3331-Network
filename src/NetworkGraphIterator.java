import java.util.Iterator;
import java.util.NoSuchElementException;

import list.List;


public class NetworkGraphIterator implements Iterator<Object> {

	private List edges;
	public NetworkGraphIterator(list.List edges) {
		// TODO Auto-generated constructor stub
		this.edges = edges;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !this.edges.isEmpty();
	}

	@Override
	public Object next() {
		// TODO Auto-generated method stub
		if (this.edges.isEmpty())
			throw new NoSuchElementException();
		int[] result = (int[]) this.edges.head();
		this.edges = this.edges.tail();
		return result[0];
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub
		
	}

}
