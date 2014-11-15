import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import list.List;


public class NetworkGraph extends AbstractGraph {

	/**
	 * @param args
	 * must include specialized list class in program
	 */
	ArrayList<Link> links;

	public NetworkGraph (){		
		//number of links
		links = new ArrayList<Link>();
		this.size = 0;
		//list of links this.mark[i] = false; 	this.mark = new boolean[max];
		this.values = new ArrayList<Vertex>();
	}
	public void setValues(int src, Vertex[] temp) {
		//ensure the uniqueness of added nodes
		Boolean add = true;  

		for (int i=0; i < temp.length && temp[i] != null; i++){
			int t = temp[i].getSrc();
			if(t == (src)){ add = false; break;}	
		}
		if (add){
			Vertex v = new Vertex(src);
			temp[v.getSrc()] =  v;
		}		
	}


	public void readTopology(String contents) throws IOException {
		String[] lines = contents.split(",");
		Vertex[] t = new Vertex[lines.length];
		ArrayList<Link> allLinks = new ArrayList<Link>();
		for (int i=0; i < lines.length; i++){
			String[] info = lines[i].split("\\s");
			int src = (info[0].toCharArray())[0] - 65 ;
			int dest = (info[1].toCharArray())[0] - 65 ;
			int prop = Integer.parseInt(info[2]);
			int capacity = Integer.parseInt(info[3]);

			Link temp = new Link(src, dest, prop, capacity);
			Link temp2 = new Link(dest, src, prop, capacity);
			allLinks.add(temp); allLinks.add(temp2);
			this.setValues(src, t);
		}
		for (int j =0; j < t.length && t[j] != null; j++){
			this.values.add(t[j]);
		}

		this.getLinks(allLinks);

	}

	//prints the list in string format 
	public String toString(int src){
		return this.values.get(src).toString();

	}
	public Link find(Vertex v, Vertex prev){
		for (Link l: links){
			if (l.getSrc() == v.getSrc() && l.getDest() == prev.getSrc())
				return l;
		}
		return null;

	}
	public void getLinks(ArrayList<Link> allLinks) {
		links = allLinks;
		for (int i=0; i < this.values.size(); i++){
			Vertex v = (Vertex) this.values.get(i);
			for (Link l: allLinks){
				if (l.getSrc() == v.getSrc()){
					v.addNeighbors(l);
				}
			}	
		}

	}

}
