import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;


public interface Graph {
	public static final int INFINITY = Integer.MAX_VALUE;
	public static final int FAIL = -1;
	
	public boolean marked (int node);
	public void mark(int node);
	public void clear(int node);
	
	public int size ();
	
	//hasNode
	public Object value (int node);
	
	//hasEdge
	public int find(Object value);
	
	public Iterator iterator (int node);
	
	//weight
	public int cost (int src, int dest, Double time);
	public void setCost(int src, int dest, int cost);
	
	public boolean getConnections (int src);
	
	public int degree (int node);
	
	public int addNode (Object o);
	public int removeNode (Object o);
	
	public void addEdge (int src, int dest, int cost);
	public void removeEdge (int src, int dest, int cost);
	
	public void read (BufferedReader in) throws IOException;
	public void read () throws IOException;
	
	public void write (PrintStream out);
	public void write ();
	
	
}
