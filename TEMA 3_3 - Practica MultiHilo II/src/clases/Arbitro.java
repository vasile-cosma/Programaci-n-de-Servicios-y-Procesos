package clases;

public class Arbitro {
	private int jugadores;
	private int turno;
	private int num;
	private boolean acabado;

	public Arbitro(int jugadores) {
		super();
		this.jugadores = jugadores;
		this.turno = 1;
		this.num = 1 + (int) (10 * Math.random());
		this.acabado = false;
	}

	public int getJugadores() {
		return jugadores;
	}

	public void setJugadores(int jugadores) {
		this.jugadores = jugadores;
	}

	public int getTurno() {
		return turno;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public boolean isAcabado() {
		return acabado;
	}

	public void setAcabado(boolean acabado) {
		this.acabado = acabado;
	}

	public synchronized void comprobar(int identificador, int num) {
		System.out.println("\t Jugador" + identificador + " dice: " + num);
		/*
		 * Si el número recibido como parámetro coincide con el número generado en el
		 * atributo del árbitro, el juego se da por acabado
		 */
		if (num == this.num) {
			System.out.println("Jugador " + identificador + " gana, adivinó el número!!!");
			acabado = true;
		//De lo contrario, avanza el turno 
		} else {
			turno++;
		// Si el turno es superior al nº de jugadores, se resetea al valor inicial y continúa el juego
			if (turno > jugadores)
				turno = 1;
			System.out.println("Le toca a Jug" + turno);
		}

	}

}
