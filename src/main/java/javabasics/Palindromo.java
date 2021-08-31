package javabasics;

public class Palindromo {
	
	
	public static void main(String[] args) {
		String[] cadenas = { "La ruta natural", "Esto no es", "Parzibyte", "Hola", "Sol", "Ana", "Oro", "Oso",
				"A ti no, bonita","Mom",
				"Adivina ya te opina, ya ni miles origina, ya ni cetro me domina, ya ni monarcas, a repaso ni mulato carreta, acaso nicotina, ya ni cita vecino, anima cocina, pedazo gallina, cedazo terso nos retoza de canilla goza, de pánico camina, ónice vaticina, ya ni tocino saca, a terracota luminosa pera, sacra nómina y ánimo de mortecina, ya ni giros elimina, ya ni poeta, ya ni vida",
				"A mamá, Roma le aviva el amor a papá y a papá, Roma le aviva el amor a Mamá",
				"Me gusta programar en Java" };
		for (String cadena : cadenas) {
			System.out.println("¿'" + cadena + "' es palíndromo? " + esPalindromo(cadena));
		}
	}

	/**
	 * Comprobar si es palíndromo en Java
	 * 
	 * @author parzibyte
	 */
	public static boolean esPalindromo(String cadena) {
		// Convertir a minúscula y quitar espacios " ", puntos "." y comas ","
		// También remplazar palabras con acento p.ej. í => i

		cadena = cadena.toLowerCase().replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o")
				.replace("ú", "u").replace(" ", "").replace(".", "").replace(",", "");
		// Invertir la cadena, y si es igual que la original entonces
		// son palíndromos
		String invertida = new StringBuilder(cadena).reverse().toString();
		// Para invertir recomiendo ver
		// https://parzibyte.me/blog/2019/02/20/invertir-cadena-string-java/
		return invertida.equals(cadena);
	}
}
