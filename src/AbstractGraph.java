import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;


public class AbstractGraph implements Graph {

	/**
	 * @param args
	 * using linked list graph data structure because of low connectivity of relative nodes and 
	 * to avoid O(l^2) ordering 
	 */
	
	protected int size;
	protected int maxsize;
	protected ArrayList values;
	protected boolean[] mark;
	

	@Override
	public boolean marked(int node) {
		// TODO Auto-generated method stub
		return this.mark[node];
	}

	@Override
	public void mark(int node) {
		// TODO Auto-generated method stub
		this.mark[node] = true;
	}

	@Override
	public void clear(int node) {
		// TODO Auto-generated method stub
		this.mark[node] = false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	@Override
	public Object value(int node) {
		// TODO Auto-generated method stub
		return this.values.get(node);
	}

	@Override
	public int find(Object o) {
		// TODO Auto-generated method stub
		for (int i =0; i < this.size; i++){
			if (this.values.get(i).equals(o)) return i;
		}
		return Graph.FAIL;
	}

	@Override
	public Iterator iterator(int node) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setCost(int src, int dest, int cost) {
		
	}

	@Override
	public int degree(int node) {
		int count = 0;
		for (	Iterator i = this.iterator(node);
				i.hasNext();
				i.next())
				count++;
		return count;
	}

	public int addNode(Object o) {
		if (this.size == this.maxsize)
			return Graph.FAIL;;
		this.values.add(o);
		return size;
	}
	
	public int removeNode(Object o){
		if (this.size == 0) return Graph.FAIL;;
		this.values.remove(o);
		return size;
	}

	public void addEdge(int src, int dest, int cost) {
		setCost(src,dest,cost);
	}

	public void removeEdge(Object o){
		this.values.remove(o);
	}


//*******************DO NOT IMPLEMENT ***************************//
	
	@Override
	public void read(BufferedReader in) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read() throws IOException {
		// TODO Auto-generated method stub
		//BufferedReader in = new BufferedReader()
		//read(in);
	}
	
	@Override
	public void write(PrintStream out) {
		// TODO Auto-generated method stub
		//PrintStream out = new PrintStrea
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int cost(int src, int dest, Double time) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getConnections(int src) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeEdge(int src, int dest, int cost) {
		// TODO Auto-generated method stub
		
	}

}
