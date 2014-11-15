import java.util.ArrayList;
import java.util.Collections;

import list.List;


public class Link {

	private  int delay;
	private  int capacity;
	private  int load;
	public  int SRC;
	public  int DEST;
	public boolean marked;
	public final Vertex target;
	/**
	 * @param args
	 */
	public Link(int src, int dest, int prop, int cap){
		delay = prop;
		capacity = cap;
		SRC = src;
		DEST = dest;
		target = new Vertex(dest);
		marked = false;
		load = 0;
	}

	public double getLoad(){
		return (this.load/(double)(this.capacity));
	}

	//free circuit
	public boolean isNotBlocked(Double time) {
		return (this.getLoad() < this.capacity);
	}
	public int getSrc(){
		return this.SRC;
	}
	public int getDest(){
		return this.DEST;
	}
	public int getCapacity(){
		return this.capacity;
	}
	public int getProp(){
		return this.delay;
	}
	public void updateLoad(){
		this.load++;
	}
	public void decLoad(){
		this.load--;
	}
	public void unmark(){
		this.marked = false;
	}
	public void mark(){
		this.marked = true;
	}
	public boolean isMarked(){
		return this.marked;
	}
	public String toString(){
		return this.SRC + "-"+ this.DEST;
	}
	public boolean Blocked() {
		return !(capacity - load > 0);
	}
}
