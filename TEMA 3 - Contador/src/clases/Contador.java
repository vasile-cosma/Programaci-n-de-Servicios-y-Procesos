package clases;

public class Contador {
	private int contador=0;

	public Contador() {
		super();
	}
	
	public int getContador() {
		return contador;
	}

	public void sumar() {
		contador++;
		
	}

	@Override
	public String toString() {
		return "" + contador;
	}
	


	
	
}
