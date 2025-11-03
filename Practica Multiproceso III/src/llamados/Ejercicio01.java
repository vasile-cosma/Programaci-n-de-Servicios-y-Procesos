package llamados;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Escribe un programa que reciba un argumento y que retorne (System.exit()) los siguientes
valores
- Devolverá -1 si el argumento viene vacío
- Devolverá -2 si el argumento no es un entero
- Devolverá -3 si el argumento es un entero positivo
- Devolverá 0 si el argumento es un entero negativo
Escribe otro programa que pida por consola que se introduzca un número entero positivo.
Con el dato introducido llamará al programa anterior y a partir del valor de salida del
programa le confirmará al usuario si el dato introducido es correcto.
 */
public class Ejercicio01 {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		try {
			int argumento = sc.nextInt();
			
			if (argumento > 0) {
				System.out.println(-3);
				System.exit(-3);
			}
			else if (argumento < 0) {
				System.out.println(0);
				System.exit(0);
			}
			else {
				System.out.println(-1);
				System.exit(-1);
			}
				
			
		} catch (NullPointerException e) {
			System.out.println(-1);
			System.exit(-1);
		} catch (NumberFormatException e) {
			System.out.println(-2);
			System.exit(-2);
		}  catch (InputMismatchException e) {
			System.out.println(-1);
			System.exit(-1);
		}
	
	}

}
