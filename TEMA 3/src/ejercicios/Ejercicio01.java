package ejercicios;

public class Ejercicio01 extends Thread {
	private boolean pares;

	public Ejercicio01(boolean pares) {
		super();
		this.pares = pares;
	}

	public void generarNumeros() throws InterruptedException {
		for (int x = 1; x <= 10; x++) {
			if (pares) {
				if (x % 2 == 0) {
					System.out.println(x);
					Thread.sleep(1000);
				}
					
			} else {
				if (x % 2 != 0) {
					System.out.println(x);
					Thread.sleep(1000);
				}
			}
		}
	}
	
	@Override
	public void run() {
		try {
			this.generarNumeros();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Ejercicio01 pares = new Ejercicio01(true);
		Ejercicio01 impares = new Ejercicio01(false);
		impares.start();
		pares.start();
	}

}
;