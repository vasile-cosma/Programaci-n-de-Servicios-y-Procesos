package common;

import java.util.ArrayList;

public class Banco {
	double saldoCuenta;
	ArrayList<String> procesados = new ArrayList<String>();

	public Banco() {
		super();
	}

	public double getSaldoCuenta() {
		return saldoCuenta;
	}

	public void setSaldoCuenta(double saldoCuenta) {
		this.saldoCuenta = saldoCuenta;
	}

	public ArrayList<String> getProcesados() {
		return procesados;
	}

	public synchronized void addProcesado(String procesado) {
		procesados.add(procesado);
	}

	public synchronized boolean comprobarProcesados(String linea) {
		if (procesados.contains(linea)) {
			return false;
		} else {
			procesados.add(linea);
			return true;
		}

	}

}
