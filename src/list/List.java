package list;
//all of the methods except for purge, sort, isSingleton, and merge are taken
//from Professor Pasik's slides on courseworks

public final class List {
	private final Object data; 
	private final List next;
	public static final List NIL = new List(null, null);
	public List(Object d, List n){
		data = d;
		next = n;
	}

	public static List list(Object d){
		return new List(d, List.NIL);	}
	public Object head(){
		return data;
	}
	public List tail(){
		return next;
	}
	public boolean isEmpty(){
		return this == List.NIL;
	}
	public List push (Object d){
		return new List(d, this);
	}
	public String toString(){
		return this.isEmpty() ?
				"()" :
					"(" + this.head().toString()
					+ (this.tail().isEmpty() ? "" : " ")
					+ this.tail().toString().substring(1);
	}
	public List find (Object d){
		return this.isEmpty() || d.equals(this.head()) ? 
				this :
					this.tail().find(d);
	}
	public int length(){
		return this.isEmpty() ? 0 : 1 + this.tail().length();
	}
	public List reverse(){
		return this.isEmpty() ?
				this :
					this.tail().reverse().append(List.list(this.head()));
	}
	public List delete(Object d){
		return this.isEmpty() ? this : 
			d.equals(this.head()) ? this.tail().delete(d) :
				this.tail().delete(d).push(this.head());
	}
	// if the head item is present in the tail objects then the object is deleted
	public List purge(Object d) {
		return this.isEmpty() ? this :
			this.tail().delete(this.head()).purge(this.head()).push(this.head());	
	}
	public Object nth(int n){
		return this.isEmpty() || n < 0 ? null :
			n ==0 ? this.head() :
				this.tail().nth(n - 1);
	}
	//checks to see if the next list is NIL
	public boolean isSingleton() {
		return this.tail().isEmpty();
	}
	public List sort() {
		List left=NIL;
		List right=NIL;
		boolean addLeft = false;
		// Base Case
		if (this.isEmpty() || this.isSingleton()) {
			return this;
		}
		// Split
		for (List current = this; !(current.isEmpty()); current=current.tail()) {
			if (addLeft)
			{
				left=new List(current.head(), left); 
			}
			else
			{
				right=new List(current.head(), right);
			}
			addLeft = !addLeft;
		}
		// Recursion and merge
		return merge(left.sort(), right.sort());
	}
	public List merge(List left, List right){
		//base case
		if (left.isEmpty()){ 
			return right;
		}
		if (left.isEmpty()){ 
			return left;
		}
		//recursive comparison step stores the vales in return
		if (((Integer) left.head()).compareTo((Integer) right.head()) < 0){
			return new List(left.head(),merge(left.tail(), right));
		}
		else{
			return new List(right.head(), merge (right.tail(), left)); 
		}
	}
	//append a list to another by continuously pushing the objects in the list
	public List append(List that){
		return this.isEmpty() ? 
				that :
					this.tail().append(that).push(this.head());
	}

	
}
