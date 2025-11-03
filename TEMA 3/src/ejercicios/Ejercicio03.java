package ejercicios;

public class Ejercicio03 implements Runnable {
	// Metros de cada vuelta
	public final static int METROS_OBJETIVO = 100;
	//Atributos de la clase
	private String nombre;
	private int milisegundos10M;

	// Constructor de la clase
	public Ejercicio03(String nombre) {
		super();
		this.nombre = nombre;
		// Los milisegundos que se tarda en recorrer 10 metros se generan de forma aleatoria
		this.milisegundos10M = (int) (Math.random() * (1050 - 950 + 1)) + 950;
	}

	// Getters y setters para encapsulamiento
	public String getNombre() {
		return nombre;
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
		System.out.println(this.getNombre() + " comienza su relevo");
		// Bucle que se repite hasta que se hayan completado los metros de la vuelta
		while (metros > 0) {
			Thread.sleep(milisegundos10M);
			// El acumulador va guardando los milisegundos que se tarda en recorrer los metros totales de la vuelta (de 10 en 10)
			acum += milisegundos10M;
			// Los metros recorridos se van restando a los metros totales de la vuelta
			metros = metros - 10;
		}
		System.out.println(this.getNombre() + " ha acabado su relevo - Ha tardado: " + acum/1000 +  " segundos");
		
	}

	public static void main(String[] args) {
		// Instanciamos los competidores
		Thread pepe = new Thread(new Ejercicio03("Pepe"));
		Thread maria = new Thread(new Ejercicio03("Maria"));
		Thread juan = new Thread(new Ejercicio03("Juan"));
		Thread marta = new Thread(new Ejercicio03("Marta"));
		
		// Iniciamos los procesos, seguido de un join() para que el siguiente proceso no comience antes de que termine el otro
		try {
			pepe.start();
			pepe.join();
			maria.start();
			maria.join();
			juan.start();
			juan.join();
			marta.start();
			marta.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("FINAL DEL PROGRAMA");

	}

}
