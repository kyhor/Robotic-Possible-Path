
import java.util.*;
import java.lang.Math;

public class version2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// given input
		String ori_face = "N";
		String tar_face = "S";
		int[]oriPt = {1,2};
		int[]tarPt = {3,3};
		int maxAct = 4;
		
		
		String current_face = ori_face;
		int current_act = 0;
	
		Map<Integer,ArrayList <Vertex>> al_map = 
				new HashMap<Integer,ArrayList<Vertex>>();
		
		// initialize all empty arraylist to ready for storing Vertex
		for (int i = 1;i<=maxAct; i++){
			
			al_map.put(i, new ArrayList<Vertex>());
			
		}
		Graph g = new Graph();
		
		Vertex expvert = new Vertex(g.locateVertex(oriPt[0],oriPt[1]));
		expvert.facing = current_face;
		
		do{
			for (Vertex v : expvert.neigbours){
				
				System.out.println("after for");
				Vertex copyvert = new Vertex(v);
				copyvert.facing = changeFace(expvert,v);
				copyvert.prev = expvert;
				copyvert.actfromroot += 
						(expvert.actfromroot) + costofact(copyvert,expvert);
				if (copyvert.actfromroot <= maxAct){
					
					if (copyvert.x == tarPt[0] && copyvert.y == tarPt[1] && copyvert.facing == tar_face){
						
						printPath(copyvert);
						
					}
					else if (copyvert.x == tarPt[0] && copyvert.y == tarPt[1] && 
							(copyvert.actfromroot + costChangeFace(copyvert.facing,tar_face)<= maxAct)){
						// to print action path only based on cv.face to tar_face,
						// no value modify in printPath
						printPath(copyvert,tar_face);  
						
						Vertex newFaceVert = new Vertex(copyvert);
						
						newFaceVert.facing = tar_face;
						
						newFaceVert.prev = copyvert;
						
						newFaceVert.actfromroot += 
								(copyvert.actfromroot) + costofact(newFaceVert,copyvert);
					}
					else{
													
						pqs_map.get(copyvert.actfromroot).add(copyvert);
											
					}								
				}							
			}	
			current_act ++;	
			System.out.println("before while");
		}while (current_act < maxAct);
	}
	
	
	public static void printPath(Vertex v){	
		Stack<Vertex> st = new Stack<Vertex>();
		String actions = "";
		st.push(v);
		while (v.prev != null){
			st.push(v);
		}
		Vertex pre = st.pop();
		while (!st.isEmpty()){
			Vertex next = st.pop();
			actions += direction (pre,next);
			pre = next;
		}
		System.out.println("Possible acts: "+ actions); 
	}
	
	public static String direction (Vertex prev, Vertex next){
		
		//no change in position but facing
		if (prev.x - next.x == 0 && prev.y - next.y == 0){
			
			if (Math.abs(faceMap(prev.facing)-faceMap(next.facing))== 1){
				return "L";
			}
			else {
				
				return "LL";
			}
			
		}
		// go down 1 position
		else if (prev.x - next.x == 0 && next.y - prev.y  == -1){
			
			String m = "";
			if (faceMap(prev.facing)== 0){
				m = "RR";
			}
			else if (faceMap(prev.facing)== 1){
				m = "R";
			}
			else if (faceMap(prev.facing)== 2){
				m = "";
			}
			else {
				m = "L";
			}
			
			m+="M";
	
			return m;
		}
		//go up 1 pos
		else if (prev.x - next.x == 0 && next.y - prev.y  == 1){
			
			String m = "";
			if (faceMap(prev.facing)== 0){
				m = "";
			}
			else if (faceMap(prev.facing)== 1){
				m = "L";
			}
			else if (faceMap(prev.facing)== 2){
				m = "LL";
			}
			else {
				m = "R";
			}
			
			m+="M";
	
			return m;
		}
		//go left 1 pos
		else if (prev.x - next.x == 1 && next.y - prev.y  == 0){
		
			String m = "";
			if (faceMap(prev.facing)== 0){
				m = "L";
			}
			else if (faceMap(prev.facing)== 1){
				m = "LL";
			}
			else if (faceMap(prev.facing)== 2){
				m = "R";
			}
			else {
				m = "";
			}
			
			m+="M";
	
			return m;
		}
		//go right 1 pos
		else{
			String m = "";
			if (faceMap(prev.facing)== 0){
				m = "R";
			}
			else if (faceMap(prev.facing)== 1){
				m = "";
			}
			else if (faceMap(prev.facing)== 2){
				m = "L";
			}
			else {
				m = "RR";
			}
			
			m+="M";
	
			return m;
					
		}
	}
		
	public static int faceMap(String face){
		
		if (face == "N"){return 0;}
		else if (face == "E"){return 1;}
		else if (face == "S"){return 2;}
		else {return 3;}
		
	}
	public static void printPath(Vertex v, String tar_face){
		
		Stack<Vertex> st = new Stack<Vertex>();
		String actions = "";
		st.push(v);
		while (v.prev != null){
			st.push(v);
		}
		Vertex pre = st.pop();
		while (!st.isEmpty()){
			Vertex next = st.pop();
			actions += direction (pre,next);
			pre = next;
		}
		int switchface = 0;
		while(switchface < Math.abs(faceMap(pre.facing)- faceMap(tar_face))){
			
			actions+="L";
			switchface ++;
			
		}
		System.out.println("Possible acts: "+ actions); 
		
	}
	public static int costofact(Vertex next, Vertex prev){
		
		//no change in position but facing
				if (prev.x - next.x == 0 && prev.y - next.y == 0){
					
					if (Math.abs(faceMap(prev.facing)-faceMap(next.facing))== 1){
						return 1;
					}
					else {
						
						return 2;
					}
					
				}
				// go down 1 position
				else if (prev.x - next.x == 0 && next.y - prev.y  == -1){
					
					int m = 0;
					if (faceMap(prev.facing)== 0){
						m = 2;
					}
					else if (faceMap(prev.facing)== 1){
						m = 1;
					}
					else if (faceMap(prev.facing)== 2){
						m = 0;
					}
					else {
						m = 1;
					}
					
					m++;
			
					return m;
				}
				//go up 1 pos
				else if (prev.x - next.x == 0 && next.y - prev.y  == 1){
					
					int m = 0;
					if (faceMap(prev.facing)== 0){
						m = 0;
					}
					else if (faceMap(prev.facing)== 1){
						m = 1;
					}
					else if (faceMap(prev.facing)== 2){
						m = 2;
					}
					else {
						m = 1;
					}
					
					m++;
			
					return m;
				}
				//go left 1 pos
				else if (prev.x - next.x == 1 && next.y - prev.y  == 0){
				
					int m = 0;
					if (faceMap(prev.facing)== 0){
						m = 1;
					}
					else if (faceMap(prev.facing)== 1){
						m = 2;
					}
					else if (faceMap(prev.facing)== 2){
						m = 1;
					}
					else {
						m = 0;
					}
					
					m++;
			
					return m;
				}
				//go right 1 pos
				else{
					int m = 0;
					if (faceMap(prev.facing)== 0){
						m = 1;
					}
					else if (faceMap(prev.facing)== 1){
						m = 0;
					}
					else if (faceMap(prev.facing)== 2){
						m = 1;
					}
					else {
						m = 2;
					}
					
					m++;
			
					return m;
							
				}
		
	}
	public static int costChangeFace(String oldface, String newface){
		
		return Math.abs(faceMap(oldface)-faceMap(newface));
		
	}
	public static String changeFace(Vertex prev, Vertex next){
				// go down 1 position
				if (prev.x - next.x == 0 && next.y - prev.y  == -1){
			
					return "S";
				}
				//go up 1 pos
				else if (prev.x - next.x == 0 && next.y - prev.y  == 1){
			
					return "N";
				}
				//go left 1 pos
				else if (prev.x - next.x == 1 && next.y - prev.y  == 0){
		
					return "W";
				}
				//go right 1 pos
				else{
					
					return "E";
							
				}
	}
}
