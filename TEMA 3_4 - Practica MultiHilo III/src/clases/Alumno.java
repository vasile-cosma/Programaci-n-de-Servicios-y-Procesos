package clases;

public class Alumno extends Thread {
	private String nombre;
	private String casa;
	private String pareja;
	// Indica si el chico está ocupado (tiene pareja)
	private boolean ocupado;
	private PistaBaile pistaBaile;
	// Indica si las chicas han cambiado de  pareja en la rotación actual
	private boolean haCambiado;
	private String ultimaPareja;

	// Constructor para chicas
	public Alumno(String nombre, String casa, String pareja) {
		super();
		this.nombre = nombre;
		this.casa = casa;
		this.pareja = pareja;
		this.ocupado = true;
		this.haCambiado = false;
		this.ultimaPareja = pareja;
	}

	// Constructor para chicos
	public Alumno(String nombre, String casa, boolean ocupado) {
		super();
		this.nombre = nombre;
		this.casa = casa;
		this.ocupado = ocupado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCasa() {
		return casa;
	}

	public void setCasa(String casa) {
		this.casa = casa;
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public PistaBaile getPistaBaile() {
		return pistaBaile;
	}

	public void setPistaBaile(PistaBaile pistaBaile) {
		this.pistaBaile = pistaBaile;
	}

	public String getPareja() {
		return pareja;
	}

	public void setPareja(String pareja) {
		this.pareja = pareja;
	}

	public boolean isHaCambiado() {
		return haCambiado;
	}

	public void setHaCambiado(boolean haCambiado) {
		this.haCambiado = haCambiado;
	}

	public String getUltimaPareja() {
		return ultimaPareja;
	}

	public void setUltimaPareja(String ultimaPareja) {
		this.ultimaPareja = ultimaPareja;
	}

	@Override
	public synchronized void run() {
		System.out.println(this.nombre + " empieza bailando con " + this.pareja);
		do {
			try {
				/*
				 * Cambiamos de pareja solamente si toca (lo decide el director) y si la chica
				 * todavía no ha cambiado en esta rotación
				 */
				if (pistaBaile.isCambio() && !this.haCambiado) {
					// Invocamos a cambiarPareja() con la instancia de este objeto como parámetro
					pistaBaile.cambiarPareja(this);
					// Indicamos que esta chica ya ha cambiado de pareja para que no siga buscando
					this.haCambiado = true;
				}
				// Sin este sleep, nadie descansa
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} while (!pistaBaile.isTerminado());
		/*
		 * Imprimimos todas las parejas que han terminado de bailar, excepto aquellos
		 * que estén descansando
		 */
		if (this.pareja != null)
			System.out.println(this.nombre + " y " + this.pareja + " han acabado de bailar.");
	}
}
