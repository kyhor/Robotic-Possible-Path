
import java.util.*;
import java.util.Comparator;

public class Vertex implements Comparable<Vertex>{
	int x,y;
	int actfromroot;
	String facing;
	Vertex prev;
	ArrayList<Vertex>neigbours;
	
	public Vertex (Vertex v){
		
		this.x = v.x;
		this.y = v.y;
		this.actfromroot = 0;
		this.facing = null;
		this.neigbours = new ArrayList<Vertex>(v.neigbours);	
		this.prev = null;
	}
	public Vertex (int x, int y){
		this.x = x;
		this.y = y;
		this.actfromroot = 0;
		this.facing = null;
		this.neigbours = new ArrayList<Vertex>();
		this.prev = null;
	}
	public int compareTo(Vertex others){
		
		int tarx = 3;
		int tary = 3;
		
		int x = Math.abs(others.x - tarx);
		int y = Math.abs(others.y - tary);
		
		
		return x+y;
		
		
	}
	public static Comparator<Vertex> VertexComparator = new Comparator<Vertex>(){
		
		public int compare (Vertex v, Vertex w){
			
			
			return v.compareTo(w);
		}
		
	};
	
}
