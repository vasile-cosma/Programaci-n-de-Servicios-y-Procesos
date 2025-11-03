package clases;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import excepciones.ExcepcionTipoComida;

public class Main {

	public static void main(String[] args) {
		Restaurante dominos = new Restaurante();
		try {
			Cocinero izar = new Cocinero('H', dominos);
			Cocinero miguel = new Cocinero('P', dominos);
			Repartidor vasile = new Repartidor("vasile", dominos);
			Repartidor david = new Repartidor("david", dominos);
			Repartidor brian = new Repartidor("brian", dominos);
			Repartidor isma = new Repartidor("isma", dominos);
			
			ExecutorService es = Executors.newFixedThreadPool(6);
			es.submit(izar);
			es.submit(miguel);
			es.submit(vasile);
			es.submit(david);
			es.submit(brian);
			es.submit(isma);
			es.shutdown();
			es.awaitTermination(60, TimeUnit.DAYS);
		} catch (ExcepcionTipoComida e) {
			System.err.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Pizzas cocinadas en total: " + dominos.getPizzasCocinadas());
		System.out.println("Hamburguesas cocinadas en total: " + dominos.getHamburguesasCocinadas());
		System.out.println("Estado del horno: " + dominos.mostrarHorno());
	}

}
