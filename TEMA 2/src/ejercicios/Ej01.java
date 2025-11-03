package ejercicios;

import java.util.Scanner;
import java.io.IOException;
import java.lang.*;

public class Ej01 {
/*
 * Crea una nuevo proyecto Java. Usando la línea de comandos, pide al usuario qué acción
 * quiere realizar (apagar, reiniciar o suspender) y cuánto tiempo quiere dejar antes de realizar
 * la acción.
 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		
		System.out.println("¿Qué acción quiere realizar? \n 1. Apagar \n 2. Reiniciar \n 3. Suspender");
		int respuesta = Integer.parseInt(sc.nextLine());
		while (respuesta != 1 && respuesta != 2 && respuesta != 3) {
		System.out.println("Por favor, seleccione una opción correcta: \n 1. Apagar \n 2. Reiniciar \n 3. Suspender");
		respuesta = Integer.parseInt(sc.nextLine());
		}  
		
		// NOTA: el tiempo no funciona
		
//		int respuesta2 = 0;
//		try {
//			System.out.println("¿Cuánto tiempo quiere dejar antes de realizar la acción? (Expresado en segundos)");
//			respuesta2 = Integer.parseInt(sc.nextLine());
//		} catch (NumberFormatException e) {
//			System.out.println("ERROR: El valor introducido no es válido. Por favor, introduzca los segundos expresados en cifras \n" + e.getMessage());
//		}
		sc.close();
		
		try {
			switch (respuesta) {
			case 1:
				ProcessBuilder pb = new ProcessBuilder("C:\\Windows\\System32\\shutdown", "-s");
				//NO FUNCIONA:
				//ProcessBuilder pb = new ProcessBuilder("C:\\Windows\\System32\\shutdown", "-s", "-t " + respuesta2); 
					pb.start();
				break;
			case 2:
				ProcessBuilder pb2 = new ProcessBuilder("C:\\Windows\\System32\\shutdown", "-r");
				// NO FUNCIONA:
				//ProcessBuilder pb2 = new ProcessBuilder("C:\\Windows\\System32\\shutdown", "-r", "-t " + respuesta2); 
					pb2.start();
				break;
			case 3:
				ProcessBuilder pb3 = new ProcessBuilder("C:\\Windows\\System32\\shutdown", "-h");
				// NO FUNCIONA:
				//ProcessBuilder pb3 = new ProcessBuilder("C:\\Windows\\System32\\shutdown", "-h", "-t" + respuesta2); 
					pb3.start();
				break;
			default:
				System.out.println("No se reconoce la opción introducida");
				break;
			}
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

}
