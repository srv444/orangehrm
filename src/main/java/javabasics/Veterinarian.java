package javabasics;

public class Veterinarian {
		String petName;
	
		public void accept(String petName) {
			this.petName=petName;
		}
		
		
		public String heal() {
			String temp = petName;
			petName=null;
			return temp;
		}
		
		
		public static void main(String[] args) {
			Veterinarian veterinarian = new Veterinarian();
			Veterinarian veterinarian2 = new Veterinarian();
			veterinarian.accept("Barkley");
			veterinarian2.accept("Mittens");
			System.out.println(veterinarian.heal());
			System.out.println(veterinarian2.heal());
		}
	
	
}
