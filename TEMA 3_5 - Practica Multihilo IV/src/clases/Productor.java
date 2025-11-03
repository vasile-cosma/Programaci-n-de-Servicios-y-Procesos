package clases;

public class Productor implements Runnable {
	private int tipoProducto;
	private CadenaMontaje cadenaMontaje;

	public Productor() {
		super();
	}

	public Productor(CadenaMontaje cadenaMontaje) {
		super();
		this.cadenaMontaje = cadenaMontaje;
	}

	public int getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(int tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public CadenaMontaje getCadenaMontaje() {
		return cadenaMontaje;
	}

	public void setCadenaMontaje(CadenaMontaje cadenaMontaje) {
		this.cadenaMontaje = cadenaMontaje;
	}

	@Override
	public void run() {
		int tiempoColocar;
		try {
			for (int i = 0; i < 20; i++) {
				tipoProducto = (int) (Math.random() * (3 - 1 + 1) + 1);
				tiempoColocar = (int) (Math.random() * 251);
				cadenaMontaje.colocarProducto(tipoProducto);
				Thread.sleep(tiempoColocar);
			}
			cadenaMontaje.setTerminado(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
