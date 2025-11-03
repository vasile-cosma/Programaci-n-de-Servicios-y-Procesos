package llamados;


import java.util.Scanner;

public class Ejercicio02 {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String recibido;
		int num;
		int acum = 0;
		
		try {
			do {
				recibido = sc.nextLine();
				if (recibido.equals("*"))
					break;
				System.out.println("Escrito " + recibido);
				num = Integer.parseInt(recibido);
				acum += num; 
			} while (!recibido.equalsIgnoreCase("*"));
		} catch (NumberFormatException e) {
			System.err.println("Error: se han introducido cadenas");
			//e.printStackTrace();
			System.exit(-1);
		} catch (Exception e) {
			System.err.println("Error: fichero de entrada vacío o no encontrado");
			System.exit(-2);
		}
			System.out.println("Suma: " + acum);
			System.exit(0);

	}

}
