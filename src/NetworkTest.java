import io.IO;

import io.IO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TimerTask;


import list.List;
import java.util.Timer;


public class NetworkTest {
	public static long totalProp;
	public static final int INFINITY = Integer.MAX_VALUE ;
	public static void main(String[] args) throws InterruptedException {
		//1) initialize variables

		/***************************************/
		Float startTime; 
		Float endTime; 
		int src; int dest;
		 totalProp = 0;
		long totalPackets = 0;
		int blockedPackets = 0;
		int totalHops = 0;
		int requests = 0;
		
		String NETWORK_SCHEME = args[0];
		String ROUTING_SCHEME = args[1];
		ArrayList<Link> links = new ArrayList<Link>();
		int PACKET_RATE = Integer.parseInt(args[4]);
		/*Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TaskUnMark(), 0, //initial delay
				1 * 1000); //subsequent rate
*/
		//2) initialize the graph		
		NetworkGraph graph = new NetworkGraph();
		try {
			graph.readTopology(IO.readFile(args[2]));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("no such file exists");
		}
		String[] lines =  readFile(IO.readFile(args[3]));
		//3) get the workload

		System.out.println(lines.length);
		for (String line: lines){
			requests++;
			/******************************PARSE LINE *****************************************/
			String[] inputs = line.split("\\s");
			startTime = Float.parseFloat(inputs[0]);
			endTime = Float.parseFloat(inputs[3]);
			src = (inputs[1].toCharArray())[0] - 65 ;
			dest = (inputs[2].toCharArray())[0] - 65 ;
			/*********************************************************************************/
			//4) determine the number of iterations necessary			
			if (NETWORK_SCHEME.contains("CIRCUIT")){
				totalPackets++;
				//currentTime += (1/PACKET_RATE)*j;
				findPath(src, ROUTING_SCHEME, graph);
				ArrayList<Vertex> results = getShortestPath(dest, graph);
				int size = results.size();
				//if (size == 0){ blockedPackets++;	}
				/*	for (Link ls: results){
					if (ls.getLoad() == 0 || ls.isMarked()){
						blockedPackets++;
					}
				}*/
				totalHops += size;

				
				/*for (Link ls: results){
					if (ls.getLoad() == 0 || ls.isMarked()){
						blockedPackets++;
					}
					((Link) graph.values.get(ls.getSrc())).unmark(); ((Link) graph.values.get(ls.getDest())).unmark();
				}
				totalHops += size;
				for (Link ls: results){
					ls.unmark();
				}*/
				for (Vertex v: results){
					v.unmark();
				} 
			}
			else{
				int instancePackets =  (int) Math.ceil((endTime)*PACKET_RATE);
				totalPackets +=  instancePackets; 
				
				//double currentTime =startTime;
				for ( int j =0; j < instancePackets ; j++){
					
					//currentTime += (1/PACKET_RATE)*j;
					findPath(src, ROUTING_SCHEME, graph);
					ArrayList<Vertex> results = getShortestPath(dest, graph);
					int size = results.size();
					//if (size == 0){ blockedPackets++;	}
					/*	for (Link ls: results){
						if (ls.getLoad() == 0 || ls.isMarked()){
							blockedPackets++;
						}
					}*/
					totalHops += size;
					for (Vertex v: results){
						v.unmark();
					}
				}
				
			}

		}
		if (NETWORK_SCHEME.contains("PATH")){
			blockedPackets = blockedPackets/3;
		}
		/*****WORK CALCULATIONS******/

		requests = (int) totalPackets;
		long success =  requests - blockedPackets; 
		double hopAv = totalHops/success; 
		//5) Print Final Statistics: int requests, int totalPackets, int success, int blockedPackets, int hopAv
		printStats(requests, totalPackets, success, blockedPackets, hopAv);
	}
	private static String[] readFile(String contents)  {
		
		String[] lines = contents.split(",");
		return lines;
	}

	/*************************************************Establish paths*********************************/
	public static void findPath(int s, String input,  NetworkGraph graph){
		PriorityQueue<Vertex> PQ = new PriorityQueue<Vertex>();
		Vertex source  = ((Vertex) graph.values.get(s));
		source.minDistance = 0;
		PQ.add(source);
		while (!PQ.isEmpty() ){
			Vertex top = PQ.poll();
			for (int i = 0; i < top.neighbors.size(); i++){
				Link j = top.neighbors.get(i); 				
				Vertex v = (Vertex) graph.values.get(j.getDest());
				double weight = (input.contains("SHP")) ?  1 : (input.contains("SPD")) ? j.getProp() : j.getLoad();
				double totaldistance =  weight + top.minDistance;
				if (totaldistance < v.minDistance){
					PQ.remove(v);
					v.minDistance = totaldistance; v.previous = top;
					PQ.add(v);
					totalProp+=j.getProp();
				}	
			}
		}
	}
	/********************************SHORTEST PATH*******************************************************************/	
	public static ArrayList<Vertex> getShortestPath(int dest, NetworkGraph graph){
		ArrayList<Vertex> temp = new ArrayList<Vertex>();
		for (Vertex v = ((Vertex)graph.values.get(dest)); v != null && v.marked(); v = v.previous ){
			temp.add(v);
			/*if (v.previous != null){
				Link ls = v.neighbors.get(0);
				if (ls != null){
					
					System.out.println("h");
					ls.updateLoad(); 
					totalProp += ls.getProp();
					v.mark();
				}
			}
*/		
			v.mark();
			totalProp += v.neighbors.get(0).getProp();
			}
		return temp;
	}
	/********************************PRINT STATS**********************************************************************/
	private static void printStats(int requests, long totalPackets, long success, int blockedPackets, double hopAv) {

		System.out.println("total number of virtual circuit requests: " +requests);
		System.out.println("total number of packets: "+ totalPackets);
		System.out.println("number of successfully routed packets: " + success);
		System.out.println("percentage of successfully routed packets: " + (double) ((double) success/totalPackets)*100);
		System.out.println("number of blocked packets: " + (double) blockedPackets);
		System.out.println("percentage of blocked packets: " + (double) ((double)blockedPackets/totalPackets)*100);
		System.out.println("average number of hops per circuit: " +   hopAv);
		System.out.println("average cumulative propagation delay per circuit: " +   (totalProp/(double)success));
	}
}


