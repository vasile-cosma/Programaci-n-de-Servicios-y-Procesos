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
		File lock = new File("lock.tmp");

		try {
			// Espera entre 1 y 5 segundos
			Thread.sleep((int) (Math.random() * (5 - 1 + 1) + 1) * 1000);
			num = (int) (Math.random() * (101));

			// Simulamos que el fichero no ha llegado
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

			// Recoge los datos de su stream de entrada
			nombreFich = sc.nextLine();
			ruta = sc.nextLine();
			fichero = new File(ruta, nombreFich);
			nrTransferencias = Integer.parseInt(sc.nextLine());

			// Genera las transferencias y las escribe en el nuevo fichero
			BufferedWriter bf = new BufferedWriter(new FileWriter(fichero));
			for (int x = 0; x < nrTransferencias; x++) {
				cuentaEmpleado = generarCuenta();
				importeNomina = (Math.random() * (3000 - 1500 + 1) + 1500);
				bf.write(cuentaEmpleado + ";" + String.format("%.2f", importeNomina) + "\n");
				Thread.sleep(10);
			}
			bf.close();

			System.out.println("Fichero " + nombreFich + " generado exitosamente");
			System.exit(0);

			// Indicamos los errores
		} catch (IOException e) {
			System.out.println("ERROR con los ficheros");
			System.exit(-1);
		} catch (InterruptedException e) {
			System.out.println("ERROR: ejecución interrumpida");
			System.exit(-1);
		} finally {
			/*
			 * Eliminamos el fichero "lock.tmp" que nos sirve de semáforo, indicando a los
			 * demás procesos que ya pueden continuar su ejecución (ya hay fichero para
			 * procesar).
			 */
			lock.delete();
		}

	}

	// Método para generar las cuentas de forma aleatoria.
	public static String generarCuenta() {
		int primerDigito = (int) (Math.random() * (2 - 1 + 1) + 1);
		int resto = (int) (Math.random() * (99999999 - 10000000 + 1) + 10000000);
		String cuenta = Integer.toString(primerDigito) + Integer.toString(resto);
		return cuenta;
	}

}
