package clases;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		CadenaMontaje cm = new CadenaMontaje();
		Productor productor = new Productor(cm);
		Consumidor tipo1 = new Consumidor(1, cm);
		Consumidor tipo2 = new Consumidor(2, cm);
		Consumidor tipo3 = new Consumidor(3, cm);
		
		ExecutorService executor = Executors.newFixedThreadPool(4);
		executor.submit(productor);
		executor.submit(tipo1);
		executor.submit(tipo2);
		executor.submit(tipo3);
		executor.shutdown();
		try {
			if (executor.awaitTermination(60, TimeUnit.DAYS)) {
				System.out.println("*************************************");
				System.out.println("Quedan en la cinta: " + cm.mostrarColocados());
				System.out.println("Puestos en total: " + cm.getPuestos());
				System.out.println("Recogidos por tipo: " + cm.mostrarRecogidos());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
