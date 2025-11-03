package ejercicios;

import java.lang.Thread.State;

public class Ejercicio05 implements Runnable {
	// Atributos de la clase
	private int tiempoDesactivar;
	private boolean desactivar;

	// Constructor
	public Ejercicio05(boolean desactivar) {
		super();
		// El tiempo que tarda el artificiero en desactivar se genera aleatoriamente
		this.tiempoDesactivar = (int) (Math.random() * (9500 - 10500 + 1)) + 9500;
		this.desactivar = desactivar;
	}

	@Override
	public void run() {
		/*
		 *  Si el parámetro "desactivar" recibido es true, inicia el método
		 *  desactivarBomba(). De lo contrario, se inicia cuentaAtras()
		 */
		
		if (desactivar)
			this.desactivarBomba();
		else
			this.cuentaAtras();
	}
	
	public int getTiempoDesactivar() {
		return tiempoDesactivar;
	}

	public void cuentaAtras() {
		try {
			//Cuenta atrás de 9 segundos
			for (int x = 9; x != 0; x--) {
				Thread.sleep(1000);
				System.out.println(x);
				
			}
			// Si llega al final, la bomba explota
			System.out.println("BOOM!");

		} catch (InterruptedException e) {
			// Si el proceso se interrumpe, la bomba ha sido desactivada
			System.out.println("La bomba ha sido desactivada");
			return;
		}

	}

	public void desactivarBomba() {
		try {
			System.out.println("El artificiero comienza a desactivar la bomba");
			// El proceso duerme durante el tiempo que tarde el artificiero
			Thread.sleep(tiempoDesactivar);

		} catch (InterruptedException e) {
			// Si se interrumpe el proceso, es porque la bomba ha explotado
			System.out.println("El artificiero ha muerto");
			return;
		}

	}

	public static void main(String[] args) throws InterruptedException {
		// Instanciamos dos hilos, cada uno con uno de los métodos
		Thread artificiero = new Thread(new Ejercicio05(true));
		Thread bomba = new Thread(new Ejercicio05(false));
		// Los iniciamos
		artificiero.start();
		bomba.start();

		State estadoArtificiero;
		State estadoBomba;
		
		// Nota: isAlive() puede ser más eficiente
		
		// Decidimos qué proceso se interrumpe
		do {
			estadoArtificiero = artificiero.getState();
			estadoBomba = bomba.getState();
			Thread.sleep(10);
			if (estadoArtificiero == State.TERMINATED)
				bomba.interrupt();
			else if (estadoBomba == State.TERMINATED)
				artificiero.interrupt();
		} while (estadoArtificiero != State.TERMINATED);

	}

}
