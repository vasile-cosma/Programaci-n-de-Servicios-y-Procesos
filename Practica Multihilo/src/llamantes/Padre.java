package llamantes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import llamados.HijoConsumer;
import llamados.HijoProducer;

public class Padre {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		String nombreFich;
		String ruta;
		int nrTransferencias;
		int nrHilos;
		HijoConsumer hc;
		HijoProducer hp;
		int salidaProductor = 100000;
		int salidaConsumidor = 100000;

		System.out.println("Dime el nombre del fichero a generar:");
		nombreFich = sc.nextLine();
		System.out.println("Dime la ruta donde lo guardo (acábalo en barra)");
		ruta = sc.nextLine();
		System.out.println("Dime el número de transferencias a generar");
		nrTransferencias = Integer.parseInt(sc.nextLine());
		System.out.println("Dime el número de hilos con el que procesar el fichero");
		nrHilos = Integer.parseInt(sc.nextLine());

		ProcessBuilder pb1 = new ProcessBuilder("java", "-cp", "bin", "llamados.HijoProducer");
		ProcessBuilder pb2 = new ProcessBuilder("java", "-cp", "bin", "llamados.HijoConsumer");

		File lock = new File("lock.tmp");
		lock.createNewFile();
		try {
			Process p2 = pb2.start();
			OutputStream osLeerFichero = p2.getOutputStream();
			BufferedWriter bwLeerFichero = new BufferedWriter(new OutputStreamWriter(osLeerFichero));
			bwLeerFichero.write(nombreFich + "\n" + ruta + "\n" + nrTransferencias + "\n" + nrHilos + "\n");
			bwLeerFichero.flush();
			bwLeerFichero.close();

			Process p1 = pb1.start();
			OutputStream osGenerarFichero = p1.getOutputStream();
			BufferedWriter bwGenerarFichero = new BufferedWriter(new OutputStreamWriter(osGenerarFichero));
			bwGenerarFichero.write(nombreFich + "\n" + ruta + "\n" + nrTransferencias + "\n");
			bwGenerarFichero.flush();
			bwGenerarFichero.close();

			salidaProductor = p1.waitFor();
			lock.delete();
			System.out.println("Valor de salida del generador: " + salidaProductor);
			if (salidaProductor == -2) {
				System.err.println("ERROR: No ha llegado el fichero de transferencias");
			} else if (salidaProductor == 0) {
				InputStream isProductor = p1.getInputStream();
				Scanner scProductor = new Scanner(isProductor);
				while (scProductor.hasNext()) {
					System.out.println(scProductor.nextLine());
				}
				scProductor.close();
			}

			salidaConsumidor = p2.waitFor();
			lock.delete();
			System.out.println("Valor de salida del lector: " + salidaConsumidor);
			if (salidaConsumidor != 0) {
				System.err.println("ERROR");
			} else if (salidaConsumidor == 0) {
				InputStream isConsumidor = p2.getInputStream();
				Scanner scConsumidor = new Scanner(isConsumidor);
				while (scConsumidor.hasNext()) {
					System.out.println(scConsumidor.nextLine());
				}
				scConsumidor.close();
			}

		} catch (IOException e) {
			System.err.println("ERROR con los ficheros");
		} catch (InterruptedException e) {
			System.err.println("ERROR: Ejecución interrumpida");
		}

	}

}
