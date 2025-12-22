package llamados;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import common.Banco;

public class HijoConsumer {
	public static Scanner sc = new Scanner(System.in);

	public HijoConsumer() {
		super();
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		File lock = new File("lock.tmp");
		boolean isBlocked = true;
		while (isBlocked == true) {
			Thread.sleep(100);
			if (!lock.exists())
				isBlocked = false;
		}

		File ficheroLeer;
		File ficheroExt;
		File ficheroInt;
		File ficheroSS;
		String nombreFich;
		String ruta;
		int nrTransferencias;
		int nrHilos;
		double saldoCuenta;
		Banco banco;

		banco = new Banco();
		nombreFich = sc.nextLine();
		ruta = sc.nextLine();
		nrTransferencias = Integer.parseInt(sc.nextLine());
		// Generamos saldo de cuenta aleatorio en base a las transferencias
		saldoCuenta = (Math.random() * (2700 - 1500 + 1) + 1500) * nrTransferencias;
		// Asiganmos el saldo al banco
		banco.setSaldoCuenta(saldoCuenta);
		nrHilos = Integer.parseInt(sc.nextLine());
		ficheroLeer = new File(ruta, nombreFich);

		// Generamos ficheros y borramos si ya existían de ejecuciones anteriores
		ficheroExt = new File(ruta, "transferenciasExt.txt");
		if (ficheroExt.exists())
			ficheroExt.delete();
		ficheroInt = new File(ruta, "transferenciasInt.txt");
		if (ficheroInt.exists())
			ficheroInt.delete();
		ficheroSS = new File(ruta, "transferenciasSinSaldo.txt");
		if (ficheroSS.exists())
			ficheroSS.delete();

		// Si el fichero no existe, paramos el programa esperando a que HijoProduccer lo
		// genere.
		// Doble medida de seguridad.
		while (!ficheroLeer.exists()) {
			Thread.sleep(100);
		}

		// Sacamos el importe total:
		Scanner scLector = new Scanner(ficheroLeer);
		String linea;
		String[] lineas;
		double total = 0;
		while (scLector.hasNextLine()) {
			linea = scLector.nextLine();
			lineas = linea.split(";");
			total += Double.parseDouble(lineas[1].replace(",", "."));
		}
		scLector.close();
		System.out.println("Importe total transferencias = " + total);
		System.out.println("El saldo de la cuenta es: " + saldoCuenta);

		// Ejecutamos los procesos indicados por el usuario
		ExecutorService es = Executors.newFixedThreadPool(nrHilos);
		for (int i = 0; i < nrHilos; i++) {
			Procesador p = new Procesador((i + 1), ficheroLeer, ficheroExt, ficheroInt, ficheroSS, banco);
			es.submit(p);
		}
		es.shutdown();

		try {
			es.awaitTermination(60, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			System.err.println("ERROR: La ejecución ha sido interrumpida");
			lock.delete();
			System.exit(-1);
		}
		System.out.println("Quedan " + banco.getSaldoCuenta() + "€ en la cuenta");
		System.out.println("Fin del proceso.");

	}

}
