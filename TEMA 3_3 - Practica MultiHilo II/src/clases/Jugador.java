package clases;

public class Jugador extends Thread {
	private int identificador;
	private Arbitro arbitro;

	public Jugador(int identificador, Arbitro arbitro) {
		super();
		this.identificador = identificador;
		this.arbitro = arbitro;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public Arbitro getArbitro() {
		return arbitro;
	}

	public void setArbitro(Arbitro arbitro) {
		this.arbitro = arbitro;
	}

	@Override
	public void run() {
		/*
		 * Si el turno coincide con el identificador del jugador, generamos un nº
		 * aleatorio y le pedimos al árbitro que lo compruebe. El bucle se interrumpe
		 * cuando el juego es acabado
		 */
		do {
			if (arbitro.getTurno() == this.getIdentificador()) {
				arbitro.comprobar(identificador, (1 + (int) (10 * Math.random())));
			}
			//Jugador.yield();
			// Si el yield() falla, reemplazar por Thread.sleep(1):

			/*
			 * try { Thread.sleep(1); } catch (InterruptedException e) { //
			 * e.printStackTrace(); }
			 */
		} while (!arbitro.isAcabado());

	}

}
