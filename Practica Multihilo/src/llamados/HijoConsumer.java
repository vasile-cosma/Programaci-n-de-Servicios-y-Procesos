package llamados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.tree.FixedHeightLayoutCache;

import common.Banco;

public class HijoConsumer {
	public static Scanner sc = new Scanner(System.in);
	private Banco banco;
	
	public HijoConsumer(Banco banco) {
		super();
		this.banco = banco;
	}
	
	public static void main(String[] args) {
		File fichero;
		File externas;
		File internas;
		File sinSaldo;
		String nombreFich;
		String ruta;
		int nrTransferencias;
		int nrHilos;
		double saldoCuenta;
		double sumatorio;

		System.out.println("Dime el nombre del fichero a generar:");
		nombreFich = sc.nextLine();
		System.out.println("Dime la ruta donde lo guardo (acábalo en barra)");
		ruta = sc.nextLine();
		System.out.println("Dime el número de transferencias a generar");
		nrTransferencias = Integer.parseInt(sc.nextLine());
		saldoCuenta = (Math.random()*(2700-1500+1)+1500) * nrTransferencias;
		System.out.println("Dime el número de hilos con el que procesar el fichero");
		nrHilos = Integer.parseInt(sc.nextLine());
		
		ExecutorService es = Executors.newFixedThreadPool(nrHilos);
		for (int i = 0; i<nrHilos; i++) {
			Procesador p= new Procesador();
			es.submit(p);
		}
		
		
		fichero = new File(ruta, nombreFich);
		externas = new File(ruta, "transferenciasInt.txt");
		internas = new File(ruta, "transferenciasExt.txt");
		sinSaldo = new File(ruta, "transferenciasSinSaldo.txt");
		
		try {
			Scanner scLector = new Scanner(fichero);
			BufferedWriter bwInt = new BufferedWriter(new FileWriter(internas));
			BufferedWriter bwExt= new BufferedWriter(new FileWriter(externas));
			BufferedWriter bwSS = new BufferedWriter(new FileWriter(sinSaldo));
			
			while (scLector.hasNext()) {
				String transferencia = scLector.nextLine();

				if (transferencia.charAt(0) == '1') {
					Thread.sleep(1000);
					System.out.println(transferencia + " : Interna");
				} else if (transferencia.charAt(0) == '2') {
					System.out.println(transferencia + " : Externa");
				}
			}
			scLector.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		saldoCuenta = (Math.random() * (2700 - 1500 + 1) + 1500) * nrTransferencias;

	}

}
