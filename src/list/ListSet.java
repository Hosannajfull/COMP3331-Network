package list;

public class ListSet implements list.Set{
	List immutableList;
	public ListSet(List n){
		immutableList= n;
	}
	public ListSet(){
		immutableList=List.NIL;
	}

	/*** Returns the {@link Iterator} that will iterate through the elements of
	 * this set.
	 * 
	 * @return {@link Iterator} for this set
	 */
	
	public ListIterator iterator() {
		return new ListIterator(this);
		
	}

	public Object get(int index) {
		if (index >= this.size()) {
			return null;
		}
		return immutableList.nth(index);
	}
	//takes the iterator class and uses the hasNext method to return the size
	public int size() {	
		return immutableList.length();	
	}
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	@Override
	public boolean isMember(Object object) {
		int count=0;
		for (ListIterator iterator = this.iterator(); iterator.hasNext(); iterator.next()) {
			if (object.equals(iterator.next())) {
				return true;
				}
		}
		return false;
	}
	//uses the iterator class to add one method to another
	public Set union(Set otherSet) {
		Set unionSet = this.copy();
		Object object =null;
		for (ListIterator iterator = (((ListSet) otherSet).iterator()); iterator.hasNext(); object = iterator.next()) {
			unionSet.add(object);
		}
		return unionSet;
	}
	@Override
	public Set intersection(Set otherSet) {
		Set intersectionSet = this.empty();
		Object object = null;
		for (ListIterator iterator = (((ListSet) otherSet).iterator()); iterator.hasNext(); object = iterator.next()) {
			if (this.isMember(object)) {
				intersectionSet.add(object);
			}
		}   return intersectionSet;
	}
	@Override
	public Set copy() {
		Set copySet = this.empty();
		for (ListIterator iterator = this.iterator(); iterator.hasNext();) {
			copySet.add(iterator.next());
		}
		return copySet;
	}
	@Override
	public void add(Object o) {
		immutableList.push(o);
	}
	@Override
	public void remove(Object o) {
		immutableList.delete(o);
	}
	@Override
	public ListSet empty() {
		return new ListSet(List.NIL);
	}
	public String toString(){
		return immutableList.toString();
	}
}