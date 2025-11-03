package ejercicios;

public class Ejercicio04 implements Runnable {
	// Metros de cada vuelta
	public final static int METROS_OBJETIVO = 100;
	// Atributos de la clase
	private String nombre;
	private int milisegundos10M;
	private String equipo;

	// Constructor de la clase
	public Ejercicio04(String nombre, String equipo) {
		super();
		this.nombre = nombre;
		this.equipo = equipo;
		// Los milisegundos que se tarda en recorrer 10 metros se generan de forma
		// aleatoria
		this.milisegundos10M = (int) (Math.random() * (1050 - 950 + 1)) + 950;
	}

	// Getters y setters para encapsulamiento
	public String getNombre() {
		return nombre;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMilisegundos10M() {
		return milisegundos10M;
	}

	public void setMilisegundos10M(int milisegundos10m) {
		milisegundos10M = milisegundos10m;
	}

	@Override
	public void run() {
		try {
			this.correr();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void correr() throws InterruptedException {
		int metros = METROS_OBJETIVO;
		double acum = 0;
		System.out.println(this.getEquipo() + ":" + this.getNombre() + " comienza su relevo");
		// Bucle que se repite hasta que se hayan completado los metros de la vuelta
		while (metros > 0) {
			Thread.sleep(milisegundos10M);
			// El acumulador va guardando los milisegundos que se tarda en recorrer los
			// metros totales de la vuelta (de 10 en 10)
			acum += milisegundos10M;
			// Los metros recorridos se van restando a los metros totales de la vuelta
			metros = metros - 10;
		}
		System.out.println(this.getEquipo() + ":" + this.getNombre() + " ha acabado su relevo - Ha tardado: "
				+ acum / 1000 + " segundos");

	}

	public static void main(String[] args) {
		// Instanciamos todos los competidores
		Thread pepe = new Thread(new Ejercicio04("Pepe", "España"));
		Thread maria = new Thread(new Ejercicio04("Maria", "España"));
		Thread juan = new Thread(new Ejercicio04("Juan", "España"));
		Thread marta = new Thread(new Ejercicio04("Marta", "España"));
		Thread john = new Thread(new Ejercicio04("John", "EEUU"));
		Thread kim = new Thread(new Ejercicio04("Kim", "EEUU"));
		Thread mike = new Thread(new Ejercicio04("Mike", "EEUU"));
		Thread sarah = new Thread(new Ejercicio04("Sarah", "EEUU"));

		/*
		 *  Creamos un hilo para el equipo de España, con una clase abstracta que se
		 *  encargue de iniciar el hilo de cada participante del equipo España (cada
		 * .start() seguido de un .join() para que no comience el siguiente proceso sin
		 * que haya acabado el anterior)
		 */
		
		Thread españa = new Thread(() -> {
			try {
				pepe.start();
				pepe.join();
				maria.start();
				maria.join();
				juan.start();
				juan.join();
				marta.start();
				marta.join();
				//Cuando acaben, se imprime:
				System.out.println("FINAL DE PROGRAMA - España");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		/*
		 *  Creamos otor hilo para el equipo de España, con una clase abstracta que se
		 *  encargue de iniciar el hilo de cada participante del equipo España (cada
		 * .start() seguido de un .join() para que no comience el siguiente proceso sin
		 * que haya acabado el anterior)
		 */
		Thread eeuu = new Thread(() -> {
			try {
				john.start();
				john.join();
				kim.start();
				kim.join();
				mike.start();
				mike.join();
				sarah.start();
				sarah.join();
				//Cuando acaben, se imprime:
				System.out.println("FINAL DE PROGRAMA - EEUU");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		// Iniciamos los hilos de los equipos, que se encargaran de iniciar los hilos de cada competidor
		españa.start();
		eeuu.start();

	}

}
