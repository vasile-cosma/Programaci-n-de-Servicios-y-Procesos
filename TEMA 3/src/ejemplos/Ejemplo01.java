package ejemplos;

public class Ejemplo01 extends Thread {
	private String cadena;
	// Recibe en el constructor una cadena. Tiene un método "escritura" en el que
	// repita lo del constructor. Espera un segundo y vuelta al bucle. Desde un main
	// arrancamos ese hilo dos veces, una con "tic" y otra con "tac"

	public Ejemplo01(String cadena) {
		super();
		this.cadena = cadena;
	}

	@Override
	public void run() {
		try {
			this.escribir();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void escribir() throws InterruptedException {
		while (true) {
			System.out.println(cadena);
			Thread.sleep(1000);
		}
		
	}

	public static void main(String[] args) {
		Ejemplo01 tic = new Ejemplo01("tic");
		Ejemplo01 tac = new Ejemplo01("tac");
		tic.start();
		tac.start();

	}

}
