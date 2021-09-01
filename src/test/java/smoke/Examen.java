package smoke;

import java.util.Arrays;

public class Examen {
	
	
	public int solution(int[] A) {
        int smallestInt = 1;
       
        if(A.length ==0){
            return smallestInt;
        }//if
        
        Arrays.sort(A);


      if(A[0]>1){
          return smallestInt;
      }
      if(A[A.length-1]<=0){
          return smallestInt;
      }

      for(int i=0; i<A.length; i++){
          if(A[i]==smallestInt){
              smallestInt++;
          }
      }
        return smallestInt;
    }// end method
	
	
	public String seRepiteDosVces(String S){
		 String l=S.toLowerCase();
		    int count=0;
	        String temp="";
	        
		    for(int i= 0; i<l.length()-1;i++){
		        for(int j=i+1;j<l.length();j++){

		            if(l.charAt(i)==l.charAt(j)){
	                    temp= temp + l.charAt(i);
	                    count++;
	                } 
		        }

		    }
		 

	        return temp;
		
	}
	

}
