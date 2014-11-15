import java.util.ArrayList;

public class Vertex implements Comparable<Vertex>{
	public ArrayList<Link> neighbors;
	public double minDistance = Integer.MAX_VALUE ;
	public Vertex previous;
	public boolean mark;
	public int SRC;
	public Vertex(int src){
		SRC = src;
		mark = false;
		neighbors = new ArrayList<Link>();
	}	
	public int getSrc() {
		return SRC;
	}
	public void mark(){
		mark = true;
	}
	public void unmark(){
		mark = false;
	}
	public boolean marked(){
		return !mark;
	}
	public int compareTo(Vertex other){
		return Double.compare(minDistance, other.minDistance);
	}
	public void addNeighbors(Link link) {
		neighbors.add(link);	
	}

	public String toString(){
		return  SRC + "";
	}
/*	
 * 
 * public void updateLoad(){
		for (Link l: this.neighbors){
			l.updateLoad();
		}
	}
	public void decLoad() {
		for (Vertex v: this.neighbors){
			l.decLoad();
		}
		
	}*/
}