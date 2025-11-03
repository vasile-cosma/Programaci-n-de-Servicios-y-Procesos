package clases;

public class Main {

	public static void main(String[] args) {
		Contador contador = new Contador();
		Hilo t1 = new Hilo(contador);
		Hilo t2 = new Hilo(contador);
		Hilo t3 = new Hilo(contador);
		Hilo t4 = new Hilo(contador);
		Hilo t5 = new Hilo(contador);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		do {
			
		} while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive() || t5.isAlive());
		
		if (!t1.isAlive() || !t2.isAlive() || !t3.isAlive() || !t4.isAlive() || !t5.isAlive())
			System.out.println(contador);
		
	}

}
