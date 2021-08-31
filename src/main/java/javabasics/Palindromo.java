package javabasics;

public class Palindromo {
	
	
	public static void main(String[] args) {
		String[] cadenas = { "La ruta natural", "Esto no es", "Parzibyte", "Hola", "Sol", "Ana", "Oro", "Oso",
				"A ti no, bonita","Mom",
				"Adivina ya te opina, ya ni miles origina, ya ni cetro me domina, ya ni monarcas, a repaso ni mulato carreta, acaso nicotina, ya ni cita vecino, anima cocina, pedazo gallina, cedazo terso nos retoza de canilla goza, de p�nico camina, �nice vaticina, ya ni tocino saca, a terracota luminosa pera, sacra n�mina y �nimo de mortecina, ya ni giros elimina, ya ni poeta, ya ni vida",
				"A mam�, Roma le aviva el amor a pap� y a pap�, Roma le aviva el amor a Mam�",
				"Me gusta programar en Java" };
		for (String cadena : cadenas) {
			System.out.println("�'" + cadena + "' es pal�ndromo? " + esPalindromo(cadena));
		}
	}

	/**
	 * Comprobar si es pal�ndromo en Java
	 * 
	 * @author parzibyte
	 */
	public static boolean esPalindromo(String cadena) {
		// Convertir a min�scula y quitar espacios " ", puntos "." y comas ","
		// Tambi�n remplazar palabras con acento p.ej. � => i

		cadena = cadena.toLowerCase().replace("�", "a").replace("�", "e").replace("�", "i").replace("�", "o")
				.replace("�", "u").replace(" ", "").replace(".", "").replace(",", "");
		// Invertir la cadena, y si es igual que la original entonces
		// son pal�ndromos
		String invertida = new StringBuilder(cadena).reverse().toString();
		// Para invertir recomiendo ver
		// https://parzibyte.me/blog/2019/02/20/invertir-cadena-string-java/
		return invertida.equals(cadena);
	}
}
