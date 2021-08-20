package javabasics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RepeatedString {

	/*
	 * Complete the 'repeatedString' function below.
	 *
	 * The function is expected to return a LONG_INTEGER. The function accepts
	 * following parameters: 1. STRING s 2. LONG_INTEGER n
	 */

	public static long repeatedString(String s, long n) {
		long size = s.length(), repeated = n / size;
		long left = n - (size * repeated);
		int extra = 0;

		int count = 0;
		for (int i = 0; i < size; i++) {
			if (s.charAt(i) == 'a') {
				++count;
			}
		}

		for (int i = 0; i < left; i++) {
			if (s.charAt(i) == 'a') {
				++extra;
			}
		}

		repeated = (repeated * count) + extra;

		return repeated;
	}




    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    

        String s = bufferedReader.readLine();

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = RepeatedString.repeatedString(s, n);

      System.out.println(result);
        bufferedReader.close();
    
    }


}




