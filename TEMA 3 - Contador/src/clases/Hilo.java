package clases;

public class Hilo extends Thread{
	Contador contador;
	
	public Hilo(Contador contador) {
		super();
		this.contador=contador;
	}

	@Override
	public void run() {
		for (int x = 0; x>1000; x++) {
			contador.sumar();
		}
	}
	
}
