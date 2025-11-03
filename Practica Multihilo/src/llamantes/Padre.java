package llamantes;

import java.util.Scanner;

public class Padre {
public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String nombreFich;
		String ruta;
		int nrTransferencias;
		int nrHilos;
		
		System.out.println("Dime el nombre del fichero a generar:");
		nombreFich = sc.nextLine();
		System.out.println("Dime la ruta donde lo guardo (acábalo en barra)");
		ruta = sc.nextLine();
		System.out.println("Dime el número de transferencias a generar");
		nrTransferencias = Integer.parseInt(sc.nextLine());
		System.out.println("Dime el número de hilos con el que procesar el fichero");
		nrHilos = Integer.parseInt(sc.nextLine());
		
		for (int x=0; x<100; x++) {
			System.out.println((Math.random()*(2700-1500+1)+1500));
		}
				

	}

}
