package clases;

public class Restaurante {
	private char[] horno;
	private boolean hornoVacio;
	private boolean hornoLleno;
	private int terminado;
	private int pizzasCocinadas;
	private int hamburguesasCocinadas;

	public Restaurante() {
		super();
		this.horno = new char[] { 'X', 'X', 'X', 'X', 'X' };
		this.hornoVacio = true;
		this.hornoLleno = false;
		this.terminado = 0;
		this.pizzasCocinadas = 0;
		this.hamburguesasCocinadas = 0;
	}

	public char[] getHorno() {
		return horno;
	}

	public void setHorno(char[] horno) {
		this.horno = horno;
	}

	public boolean isHornoLleno() {
		return hornoLleno;
	}

	public synchronized void setHornoLleno(boolean hornoLleno) {
		this.hornoLleno = hornoLleno;
	}

	public synchronized boolean isHornoVacio() {
		return hornoVacio;
	}

	public synchronized void setHornoVacio(boolean hornoVacio) {
		this.hornoVacio = hornoVacio;
	}

	public synchronized boolean isTerminado() {
		if (terminado == 2)
			return true;
		else
			return false;
	}

	public synchronized void setTerminado() {
		this.terminado++;
		notifyAll();
	}

	public int getPizzasCocinadas() {
		return pizzasCocinadas;
	}

	public void setPizzasCocinadas(int pizzasCocinadas) {
		this.pizzasCocinadas = pizzasCocinadas;
	}

	public synchronized int getHamburguesasCocinadas() {
		return hamburguesasCocinadas;
	}

	public synchronized void setHamburguesasCocinadas(int hamburguesasCocinadas) {
		this.hamburguesasCocinadas = hamburguesasCocinadas;
	}

	public synchronized int getTerminado() {
		return terminado;
	}

	public String mostrarHorno() {
		String cadena = "[";
		for (int i = 0; i < horno.length; i++) {

			if (i == (horno.length - 1))
				cadena += horno[i] + "]";
			else
				cadena += horno[i] + ", ";
		}

		return cadena;
	}

	public synchronized void cocinar(char tipoComida) {
		while (isHornoLleno()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < horno.length; i++) {

			if (horno[i] == 'X') {
				horno[i] = tipoComida;
				break;
			}

		}
		comprobarHornoLleno();
		comprobarHornoVacio();
		notifyAll();
	}

	public synchronized char repartir() {
		char producto = 'X';
		boolean repartido = false;

		while (isHornoVacio() && !isTerminado()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (isHornoVacio() && isTerminado()) {
			return 'X';
		}

		int indice = comprobarHamburguesas();

		if (indice != -1) {
			horno[indice] = 'X';
			producto = 'H';
			repartido = true;

		} else {
			for (int i = 0; i < horno.length; i++) {
				if (horno[i] == 'P' && !repartido) {
					horno[i] = 'X';
					producto = 'P';
					repartido = true;
				}
			}
		}

		comprobarHornoLleno();
		comprobarHornoVacio();
		notifyAll();
		return producto;

	}

	public synchronized void comprobarHornoVacio() {
		boolean flagVacio = true;

		for (int i = 0; i < horno.length; i++) {
			if (horno[i] != 'X')
				flagVacio = false;
		}

		this.hornoVacio = flagVacio;
	}

	public synchronized void comprobarHornoLleno() {
		boolean flagLleno = true;

		for (int i = 0; i < horno.length; i++) {
			if (horno[i] == 'X')
				flagLleno = false;
		}

		this.hornoLleno = flagLleno;
	}

	public synchronized int comprobarHamburguesas() {
		int indice = -1;

		for (int i = 0; i < horno.length; i++) {
			if (horno[i] == 'H')
				indice = i;
		}

		return indice;
	}

}
