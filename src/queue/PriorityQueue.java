package queue;

public class PriorityQueue implements Queue{

	private Comparable values[];
			private int count;
	private boolean descending;
	
	public PriorityQueue(int maxsize){
		this(maxsize, true);
	}
	public PriorityQueue (int maxsize, boolean descending){
		this.values = new Comparable[maxsize];
		this.count=0;
		this.descending = descending;
	}
	private static int left(int i){
		return 2 * i + 1;
	}
	private static int right (int i){
		return 2 * i + 2;
	}
	private static int parent (int i){
		return (i-1) / 2;
	}
	@Override
	public boolean isEmpty() {
		return this.count==0;
	}
	@Override
	public boolean isFull() {
		return this.count==this.values.length;
	}
	@Override
	public Object next() {
		return this.isEmpty() ? null: this.values[0];
	}
	@Override
	public boolean enter(Object o) {
		if (this.isFull()) return false;
		Comparable d = (Comparable) o;
		int p;
			for (p = this.count;
					p > 0 && ((descending ? 1 : -1) * d.compareTo(values[parent(p)])) > 0;
					p = parent(p))
				this.values[p] = this.values[parent(p)];
			this.values[p] = d;
			this.count++;
		return true;
	}
	@Override
	public Object leave() {
		int multiplier = this.descending ? 1: -1;
		if (this.isEmpty()) return null; 
		Comparable result = this.values[0];
		Comparable d = values[--count];
		int larger; int p=0;
		while (p < this.count/2){
			larger = right(p) < this.count &&
					(multiplier * this.values[right(p)].compareTo(this.values[left(p)])) > 0 ?
							right(p) : left(p);
			if (multiplier * d.compareTo(this.values[larger]) > 0) {
				this.values[p] = this.values[larger];
				return result;
			}else{
				this.values[p] = this.values[larger];
				p = larger;
			}
		}
		this.values[p] = d;
		return result;
	}
	@Override
	public int length() {
		return this.count;
	}
	
}
