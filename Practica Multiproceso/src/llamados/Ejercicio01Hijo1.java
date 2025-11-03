package llamados;

public class Ejercicio01Hijo1 {

	public static void main(String[] args) {
		// Declaramos las variables que vamos a utilizar
		int numero;
		int cont = 0;
		try {
			// Bucle que genera números aleatorios y filtra los 10 primeros impares
			do {
				numero = (int) (Math.random() * 100);
				if (numero % 2 != 0) {
					// Cada número impar es escrito por el canal de salida
					System.out.println(numero);
					cont++;
				}
			} while (cont < 10);
			// Si el programa termina correctamente, devuelve un valor de salida "0"
			System.exit(0);
		} catch (Exception e) {
			/*
			 *  Si el programa no acaba de la forma esperada, el valor de salida devuelto
			 *  será "-1"
			 */
			System.err.println("ERROR: Se han producido errores");
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
