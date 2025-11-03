package clases;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		// Inicializamos árbitro
		Arbitro arbi = new Arbitro(3);

		//Inicializamos jugadores
		Jugador jug1 = new Jugador(1, arbi);
		Jugador jug2 = new Jugador(2, arbi);
		Jugador jug3 = new Jugador(3, arbi);
		
		// Inicializamos executor para crear un pool de hilos acorde al número de jugadores
		ExecutorService executor = Executors.newFixedThreadPool(3);
		System.out.println("NÚMERO A ADIVINAR: " + arbi.getNum());
		
		// Comenzamos a ejecutar los jugadores por orden
		executor.submit(jug1);
		executor.submit(jug2);
		executor.submit(jug3);
		// Una vez terminado el proceso, podemos cerrar executor
		executor.shutdown();
		
		
	}

}
