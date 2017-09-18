import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.Queue;

public class SimpleVersion2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[]oriPt = {8,8};
		int[]tarPt = {3,6};
		int maxAct = 10;
		int totalpaths = 0;
		Map<Integer,Queue <Vertex>> q_map = 
				new HashMap<Integer,Queue<Vertex>>();
		
		// initialize all empty arraylist to ready for storing Vertex
		for (int i = 1;i<=maxAct; i++){
			
			q_map.put(i, new LinkedList<Vertex>());
			
		}
		Graph g = new Graph();
		
		Vertex expvert = new Vertex(g.locateVertex(oriPt[0],oriPt[1]));
		
		if (expvert.x == tarPt[0] && expvert.y == tarPt[1]){
			
			totalpaths++;
			System.out.print(totalpaths+":");
			printPath(expvert);
		}
		
		//initial step 
		for (Vertex v : expvert.neigbours){
				
			Vertex copyvert = new Vertex(v);
			copyvert.prev = expvert;
			copyvert.actfromroot = (expvert.actfromroot) + 1;
				
			if (copyvert.actfromroot <= maxAct){
					
				if (copyvert.x == tarPt[0] && copyvert.y == tarPt[1]){
						
					totalpaths++;
					System.out.print(totalpaths+":");
					printPath(copyvert);
						
				}
				q_map.get(copyvert.actfromroot).add(copyvert);
											
			}										
		}
		
		int current_act = 1;
		// run from first level until maxAct level
		while (current_act < maxAct){
			
			Queue <Vertex>current_level =  q_map.get(current_act);

			// run through all vertex in this level first
			while (current_level.peek() != null){
				
				expvert = current_level.poll();
				
				for (Vertex v : expvert.neigbours){
					
					int requiremove = Math.abs(v.x - tarPt[0]) + Math.abs(v.y - tarPt[1]);
					
					
					
					if(requiremove <= maxAct - current_act){
						
						Vertex copyvert = new Vertex(v);
						//copyvert.facing = changeFace(expvert,v);
						copyvert.prev = expvert;
						copyvert.actfromroot = (expvert.actfromroot) + 1;
						
						if (copyvert.x == tarPt[0] && copyvert.y == tarPt[1]){
							
							
							totalpaths++;
							System.out.print(totalpaths+":");
							printPath(copyvert);
								
						}
						
						q_map.get(copyvert.actfromroot).add(copyvert);
							
					} 
											
				}
				
			}
			current_act ++;	
		}
		System.out.println("all "+ totalpaths +" Paths is found!!!");
	}

	public static void printPath(Vertex end){
		
		Stack <Vertex> reverse = new Stack <Vertex>();
		
		Vertex current = end;
		
		String path = "("+current.x +","+ current.y+ ")";
		
		while (current.prev != null){
			
			current = current.prev;
			
			path = "("+current.x +","+ current.y+ ")->" +path;
			
			
		}
		
		
		
		System.out.println(path);
		
	}
	
	public static void pathToAct(String path){
		
		String[]ps= path.split("->");
		
		for (int i = 1; i < ps.length; i++){
			
			int oldx = Character.getNumericValue(ps[i-1].charAt(1));
			int oldy = Character.getNumericValue(ps[i-1].charAt(3));

			int newx = Character.getNumericValue(ps[i].charAt(1));
			int newy = Character.getNumericValue(ps[i].charAt(3));
	
			
		}
		
		
	}
	
}
