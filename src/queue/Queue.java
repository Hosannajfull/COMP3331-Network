package queue;

public interface Queue {
	public boolean isEmpty();
	public boolean isFull();
	public Object next();
	public boolean enter(Object o);
	public Object leave();
	public int length();
}
