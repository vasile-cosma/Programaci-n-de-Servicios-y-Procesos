package llamados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HijoProducer {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int num;

		try {
			Thread.sleep((int) (Math.random() * (5 - 1 + 1) + 1)*1000);
			num = (int) (Math.random() * (101));

			if (num < 30) {
				System.err.println("ERROR: No ha llegado el fichero de transferencias");
				System.exit(-2);
			}

			String nombreFich;
			String ruta;
			int nrTransferencias;
			String cuentaEmpleado;
			File fichero;
			double importeNomina;

			System.out.println("Dime el nombre del fichero a generar:");
			nombreFich = sc.nextLine();
			System.out.println("Dime la ruta donde lo guardo (acábalo en barra)");
			ruta = sc.nextLine();
			fichero = new File(ruta, nombreFich);
			System.out.println("Dime el número de transferencias a generar");
			nrTransferencias = Integer.parseInt(sc.nextLine());

			BufferedWriter bf = new BufferedWriter(new FileWriter(fichero));
			for (int x = 0; x < nrTransferencias; x++) {
				cuentaEmpleado = generarCuenta();
				importeNomina = (Math.random() * (3000 - 1500 + 1) + 1500);
				bf.write(cuentaEmpleado + ";" + String.format("%.2f", importeNomina) + "\n");
				Thread.sleep(10);
				;
			}
			bf.close();
			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	public static String generarCuenta() {
		int primerDigito = (int) (Math.random() * (2 - 1 + 1) + 1);
		int resto = (int) (Math.random() * (99999999 - 10000000 + 1) + 10000000);
		String cuenta = Integer.toString(primerDigito) + Integer.toString(resto);
		return cuenta;
	}



}
