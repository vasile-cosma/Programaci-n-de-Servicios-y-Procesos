package clases;

import excepciones.ExcepcionTipoComida;

public class Cocinero implements Runnable {
	private char especialidad;
	private Restaurante restaurante;

	public Cocinero(char especialidad, Restaurante restaurante) throws ExcepcionTipoComida {
		super();
		this.especialidad = especialidad;
		this.restaurante = restaurante;
		if (especialidad != 'P' && especialidad != 'H') {
			throw new ExcepcionTipoComida("ERROR: Solo se producen pizzas (P) y hamburguesas (H)");
		}

	}

	public int getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(char especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public void run() {
		int tiempoCocina;
		try {

			for (int i = 0; i < 15; i++) {
				if (especialidad == 'H') {
					System.out.println("Preparando una hamburguesa...");
					tiempoCocina = (int) (Math.random() * 301);
					Thread.sleep(tiempoCocina);
					restaurante.cocinar(especialidad);
					restaurante.setHamburguesasCocinadas(restaurante.getHamburguesasCocinadas() + 1);
					System.out.println("Hamburguesa preparada para reparto. " + restaurante.mostrarHorno());
				} else {
					System.out.println("Preparando una pizza...");
					tiempoCocina = (int) (Math.random() * 201);
					Thread.sleep(tiempoCocina);
					restaurante.cocinar(especialidad);
					restaurante.setPizzasCocinadas(restaurante.getPizzasCocinadas() + 1);
					System.out.println("Pizza preparada para reparto. " + restaurante.mostrarHorno());
				}
			}
			restaurante.setTerminado();

			if (especialidad == 'H') {
				System.out.println("El cocinero que hace hamburguesas ha acabado su turno");
			} else {
				System.out.println("El cocinero que hace pizzas ha acabado su turno");
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
