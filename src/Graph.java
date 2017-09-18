import java.util.HashMap;
import java.util.Map;

public class Graph {
	
	Map<Integer, Vertex> vert_map;
	int size;
	
	public Graph(){
		vert_map = new HashMap<Integer, Vertex>();
		//create all vertex
		for(int x = 1;x<=8; x++){
			for(int y = 1;y<=8;y++){
				Vertex v = new Vertex(x,y);
				int location = (x*y)*10-y;
				vert_map.put(location, v);
			}
		}
		
		//connection step
		connectVert();
		
	}
	private void connectVert(){
		
		for(int x = 1;x<=8; x++){
			for(int y = 1;y<=8;y++){
				int location = (x*y)*10-y;
				Vertex v = vert_map.get(location);
				if (x-1 >= 1){
					
					int addlocation = ((x-1)*y)*10-y;
					v.neigbours.add(vert_map.get(addlocation));
					
				}
				if (y-1 >= 1){
					
					int addlocation = (x*(y-1))*10-(y-1);
					v.neigbours.add(vert_map.get(addlocation));
				
				}
				if (x+1 <= 8){
					int addlocation = ((x+1)*y)*10-y;
					v.neigbours.add(vert_map.get(addlocation));
					
				}
				if (y+1 <= 8){
					int addlocation = (x*(y+1))*10-(y+1);
					v.neigbours.add(vert_map.get(addlocation));
				}
				
			}
		}
		
	}
	
	public Vertex locateVertex(int x, int y){
		
		int location = (x*y)*10-y;
		return vert_map.get(location);
	}
	
	public int getsize(){
		return vert_map.size();
	}
	
	
	
}