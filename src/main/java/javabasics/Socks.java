package javabasics;

import java.io.IOException;
import java.util.Scanner;

public class Socks {

	public static void main(String[] args) throws IOException {

		Scanner s = new Scanner(System.in);
		int[] freq = new int[200];
		int n = s.nextInt();

		for (int i = 0; i < n; i++) {
			int x = s.nextInt();
//			freq[x]++;
			System.out.println(freq[x]);
			freq[x]=freq[x]+1;
		}
		s.close();
		int total = 0;

		for (int i = 1; i < 200; i++) {
//			total += freq[i] / 2;
			total = total + freq[i] / 2;
		}

		System.out.println(total);

	}

}
