package queue;

import list.List;

public class Stack implements Queue{
private List values;

public Stack(){
	this.values = List.NIL;
}

@Override
public boolean isEmpty() {
	return this.values.isEmpty();
}

@Override
public boolean isFull() {
	return false;
}
@Override
public Object next() {
	return this.values.isEmpty() ? 
			null :
				this.values.head();
}

@Override
public boolean enter(Object o) {
	try{
		this.values = this.values.push(o);
	}catch (OutOfMemoryError e){
		return false;
	}
	return true;
}

@Override
public Object leave() {
	Object result = this.next();
	if (!this.values.isEmpty())
		this.values = this.values.tail();
	return result;
}

@Override
public int length() {
	return this.values.length();
}

public String toString(){
	String s = this.values.toString();
	return "stack:"+s.substring(1,s.length()-1)+": ";
}
}