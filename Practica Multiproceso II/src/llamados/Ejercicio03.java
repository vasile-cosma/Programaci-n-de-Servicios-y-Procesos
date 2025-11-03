package llamados;

import java.util.ArrayList;
import java.util.Scanner;



public class Ejercicio03 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String cadena = sc.nextLine().trim();
		StringBuilder sb = new StringBuilder(cadena);
		String cadena2 = sb.reverse().toString();
		
		if (cadena.equalsIgnoreCase(cadena2)) {
			System.out.println("Valor de salida: 0");
			System.exit(0);
		} else {
			System.out.println("Valor de salida: -1");
			System.exit(-1);
		}
			
	}
			
		

}
