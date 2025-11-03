package llamados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Ejercicio01Hijo2 {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		// Declaramos las variables que vamos a utilizar
		int num;
		ArrayList<Integer> numeros = new ArrayList<Integer>();

		try {
			/*
			 *  Recibe 10 números, uno a uno, por el canal de entrada y los agrega al Array
			 *	list
			 */

			for (int x = 0; x < 10; x++) {
				num = Integer.parseInt(sc.nextLine());
				numeros.add(num);
			}

			// Ordena el array list de menor a mayor
			numeros.sort(Comparator.naturalOrder());

			// Muestra los números, ya ordenados, por el canal de salida
			for (Integer numero : numeros) {
				System.out.println(numero);
			}
			// Si el programa termina correctamnete, devolverá el valor de salida "0"
			System.exit(0);
			// Si ocurre cualquier error, el valor de salida devuelto será "-1"
		} catch (Exception e) {
			System.err.println("ERROR: Se han cometido errores.");
			e.printStackTrace();
			System.exit(-1);
		}

	}

}
