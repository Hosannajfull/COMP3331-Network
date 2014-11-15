package queue;
import list.List;
public class ListQueue implements Queue{
	private List front;
	private List back;

public ListQueue(){
	this.front=List.NIL;
	this.back=List.NIL;
}
public boolean isEmpty(){
	return this.front.isEmpty();
}
public boolean isFull(){
	return false;
}
public int length(){
	return front.length();
}
public Object next(){
	return !this.isEmpty() ? 
			this.front.head() :
				null;
}
public Object leave (){
	Object o = this.next();
	if (!this.isEmpty())
		this.front = this.front.tail();
	return o;
}

public boolean enter(Object o){
	try{
		List p = List.list(o);
		if (this.isEmpty()){
			this.front = p;
			this.back=p;}
		else{
			this.back.append(p);
			this.back=p; 
			}
		return true;
		}
	catch (OutOfMemoryError e){
		return false;
		}
		}
	public void setTail(Object o) {
		// TODO Auto-generated method stub
		
	}
}
