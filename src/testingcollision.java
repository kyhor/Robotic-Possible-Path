import java.util.*;

public class testingcollision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		Map<Integer, Integer> map = new HashMap<Integer,Integer>();
		Graph g = new Graph();
		
		String p = "(8,8)->(7,8)->(6,8)->(6,7)->(5,7)->(4,7)->(3,7)->(3,6)";
		
		String[] ps = p.split("->");
		System.out.println(ps[0]);
		System.out.println(ps[1]);
		System.out.println(Character.getNumericValue(ps[1].charAt(1)));
		*/
		
		
		System.out.println(convert("PA",1));
		
		
	}
	public static String convert(String s, int numRows) {
        
        String[] rows = new String[numRows];
        int row = -1;
        boolean up = true;
        String end = "";
        if (numRows == 1){
        	
        	return s;
        	
        }
        else{
	        for (int i =0; i<numRows; i++){
	            
	            rows[i]= "";
	        }
	        
	        for (int j =0; j< s.length(); j++){
	            
	            if(up  && row<numRows-1){
	                
	                row ++;          
	                
	            }
	            else if (numRows-1 == row){
	                
	                up = false;
	                row--;
	            }
	            else if (row == 0){
	                up = true;
	                row++;
	            }
	            else{
	                row--;
	            }
	            rows[row]+= s.charAt(j);
	            
	        }
	        
	        for (int i =0; i<numRows; i++){
	            
	            end += rows[i];
	        }
	        
	        return end;
        }        
	}
}
