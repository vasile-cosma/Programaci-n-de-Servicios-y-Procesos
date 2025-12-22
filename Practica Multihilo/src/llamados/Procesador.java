package llamados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import common.Banco;

public class Procesador implements Runnable {
	private int id;
	private File ficheroLeer;
	private File ficheroInt;
	private File ficheroExt;
	private File ficheroSS;
	private Banco banco;
	private double procesadas;

	public Procesador(int id, File ficheroLeer, File ficheroExt, File ficheroInt, File ficheroSS, Banco banco) {
		super();
		this.id = id;
		this.ficheroLeer = ficheroLeer;
		this.ficheroInt = ficheroInt;
		this.ficheroExt = ficheroExt;
		this.ficheroSS = ficheroSS;
		this.banco = banco;
		this.procesadas = 0;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public double getProcesadas() {
		return procesadas;
	}

	public void setProcesadas(int procesadas) {
		this.procesadas = procesadas;
	}

	@Override
	public void run() {
		String linea;
		int primerNum;
		double importe;

		try {
			Scanner scLector = new Scanner(ficheroLeer);
			BufferedWriter bw;
			while (scLector.hasNext()) {
				linea = scLector.nextLine();
				// Comprobamos que la línea no haya sido procesada
				if (banco.comprobarProcesados(linea)) {
					// Sacamos el primer número para clasificar transferencias internas y externas
					primerNum = Integer.parseInt(String.valueOf(linea.charAt(0)));
					// Separamos la información después del ";" (cuenta e importe).
					String[] lineas = linea.split(";");
					String nuevaLinea;

					// Reemplazamos comas por puntos para que java lo pueda convertir a decimales
					nuevaLinea = lineas[1].replaceAll(",", ".");
					importe = Double.parseDouble(String.valueOf(nuevaLinea));

					// Dependiendo del caso, grabamos en un fichero o en otro.
					if (banco.getSaldoCuenta() < importe) {
						bw = new BufferedWriter(new FileWriter(ficheroSS, true));
					} else {
						if (primerNum == 1) {
							bw = new BufferedWriter(new FileWriter(ficheroInt, true));
							banco.setSaldoCuenta(banco.getSaldoCuenta() - importe);
							Thread.sleep(1000);
						} else {
							bw = new BufferedWriter(new FileWriter(ficheroExt, true));
							banco.setSaldoCuenta(banco.getSaldoCuenta() - importe);
						}
					}

					bw.write(linea + "\n");
					// Llevamos la cuenta del importe
					procesadas += importe;

					bw.close();
				}

			}
			scLector.close();
			System.out
					.println("En este hilo: Hilo " + id + " ha hecho transferencias por valor de: " + procesadas + "€");
		} catch (FileNotFoundException e) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			System.err.println("ERROR: No se encontró el fichero a leer");
		} catch (IOException e) {
			System.err.println("ERROR con el fichero");
		} catch (InterruptedException e) {
			System.err.println("ERROR: ejecución interrumpida");
		}

	}

}
