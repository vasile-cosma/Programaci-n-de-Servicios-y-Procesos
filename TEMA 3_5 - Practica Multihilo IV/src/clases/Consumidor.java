package clases;

public class Consumidor implements Runnable {
	private int tipoProducto;
	private CadenaMontaje cadenaMontaje;

	public Consumidor() {
		super();
	}

	public Consumidor(int tipoProducto, CadenaMontaje cadenaMontaje) {
		super();
		this.tipoProducto = tipoProducto;
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
		int tiempoEmpaquetar;

		try {
			while (!cadenaMontaje.isTerminado() || !cadenaMontaje.cadenaVacia()) {
				tiempoEmpaquetar = (int) (Math.random() * 501);
				cadenaMontaje.recogerProducto(tipoProducto);
				Thread.sleep(tiempoEmpaquetar);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}
