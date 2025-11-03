package clases;

import java.util.ArrayList;

public class Director extends Thread {
	private int rotaciones;
	private double duracion;
	private PistaBaile pistaBaile;
	private ArrayList<Alumno> chicos;

	public Director(PistaBaile pistaBaile, ArrayList<Alumno> chicos) {
		super();
		this.rotaciones = (int) (Math.random() * (8 - 4)) + 4;
		this.duracion = (Math.random() * (6 - 3)) + 3;
		this.pistaBaile = pistaBaile;
		this.chicos = chicos;
	}

	public int getRotaciones() {
		return rotaciones;
	}

	public void setRotaciones(int rotaciones) {
		this.rotaciones = rotaciones;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public PistaBaile getPistaBaile() {
		return pistaBaile;
	}

	public void setPistaBaile(PistaBaile pistaBaile) {
		this.pistaBaile = pistaBaile;
	}

	@Override
	public synchronized void run() {
		try {
			/*
			 * Sin esta linea, el director comienza antes que los alumnos impriman que
			 * comienzan a bailar
			 */
			Thread.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("### El baile va a durar: " + rotaciones + " vueltas");
		for (int i = rotaciones; i > 0; i--) {
			/*
			 * Reseteamos todas las chicas al estado
			 * "no han cambiado de pareja en esta rotación"
			 */
			pistaBaile.iniciarRotacion();
			for (double j = duracion; j > 0; j--) {
				System.out.println("+++++++Nananana, na, na,+++++++");
			}
			this.setDuracion((Math.random() * (6 - 3)) + 3);
			if (i > 1) {
				System.out.println("====================================\n" + "=======Cambio de Pareja=============\n"
						+ "====================================");
				// Invocamos al cambio de pareja
				pistaBaile.setCambio(true);

				try {
					// Esperamos a que todos cambien de pareja antes de seguir
					Thread.sleep(500);
					pistaBaile.setCambio(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		System.out.println("***********************************\n" + "******El baile ha concluido********\n"
				+ "***********************************");
		pistaBaile.setTerminado(true);
		pistaBaile.setCambio(false);
	}
}
