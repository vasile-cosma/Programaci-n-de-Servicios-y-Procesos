package clases;

public class Repartidor implements Runnable {
	private String nombre;
	private Restaurante restaurante;
	private int pizzasRepartidas;
	private int hamburguesasRepartidas;

	public Repartidor(String nombre, Restaurante restaurante) {
		super();
		this.nombre = nombre;
		this.restaurante = restaurante;
		this.pizzasRepartidas = 0;
		this.hamburguesasRepartidas = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPizzasRepartidas() {
		return pizzasRepartidas;
	}

	public void setPizzasRepartidas(int pizzasRepartidas) {
		this.pizzasRepartidas = pizzasRepartidas;
	}

	public int getHamburguesasRepartidas() {
		return hamburguesasRepartidas;
	}

	public void setHamburguesasRepartidas(int hamburguesasRepartidas) {
		this.hamburguesasRepartidas = hamburguesasRepartidas;
	}

	@Override
	public void run() {
		char producto;
		while (!restaurante.isHornoVacio() || !restaurante.isTerminado()) {
			try {
				producto = restaurante.repartir();
				Thread.sleep((int) (Math.random() * (601)));
				if (producto == 'P') {
					pizzasRepartidas += 1;
					System.out.println("El repartidor " + nombre + " ha repartido una pizza");
				} else if (producto == 'H') {
					hamburguesasRepartidas += 1;
					System.out.println("El repartidor " + nombre + " ha repartido una hamburguesa");
				} else {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("=======================================================================\nEl repartidor "
				+ nombre + " ha repartido " + pizzasRepartidas + " pizzas\nEl repartidor " + nombre + " ha repartido "
				+ hamburguesasRepartidas
				+ " hamburugesas\n=======================================================================");
		System.out.println();
	}

}
