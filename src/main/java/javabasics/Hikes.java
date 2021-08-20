package javabasics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Hikes {
	
	public static int countingValleys(int step, String path) {
	       int level = 0; // Start at sea level
	        int valleys = 0;
	        boolean belowSea = false;
	   
	   for (int i = 0; i < step; i++) {
	            char slope = path.charAt(i);
	            if (slope == 'U')// Uphill
	                level++;
	            
	            else// Downhill
	                level--;
	            
	            // Handle transitions
	            if (!belowSea && level < 0) {
	                valleys++;
	                belowSea = true;
	            }

	            if (level >= 0)// We are back above sea level
	                belowSea = false;
	        }
	        System.out.println(valleys);
	        return valleys;
	    }
	    
	    


	public static void main(String[] args) throws IOException {
		
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = countingValleys(steps, path);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}
