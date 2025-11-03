package clases;


public class CadenaMontaje {
	private int[] productos = new int[3];
	private int[] recogidos = new int[3];
	private int colocados;
	private boolean terminado;
//	donde meterlo para imprimir la parte final? private boolean terminado;

	public CadenaMontaje() {
		super();
		this.colocados = 0;
		//this.terminado = false;

	}

	public int[] getProductos() {
		return productos;
	}

	public void setProductos(int[] productos) {
		this.productos = productos;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public int getPuestos() {
		return colocados;
	}

	public void setProductos(int puestos) {
		this.colocados = puestos;
	}

	public String mostrarColocados() {
		String cadena = "[";
		for (int x = 0; x < productos.length; x++) {
			if (x == 2) {
				cadena += productos[x] + "]";
			} else {
				cadena += productos[x] + ", ";
			}

		}
		return cadena;
	}

	public String mostrarRecogidos() {
		String cadena = "[";
		for (int x = 0; x < recogidos.length; x++) {
			if (x == 2) {
				cadena += recogidos[x] + "]";
			} else {
				cadena += recogidos[x] + ", ";
			}

		}
		return cadena;
	}

	public synchronized void colocarProducto(int tipoProducto) {
		while (cadenaLlena()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Produciendo tipo " + tipoProducto);
		
		for (int x = 0; x < productos.length; x++) {
			if (productos[x] == 0) {
				productos[x] = tipoProducto;
				colocados++;
				System.out.println("Coloco un producto " + tipoProducto + " en la posición " + (x+1));
				System.out.println(mostrarColocados());
				notifyAll();
				break;
			}
		}
	}

	public synchronized void recogerProducto(int tipoProducto) {
		while (this.cadenaVacia() && !terminado) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("Recogiendo producto " + tipoProducto);
		int indiceRecogidos = tipoProducto - 1;

		for (int x = 0; x < productos.length; x++) {

				if (productos[x] == tipoProducto) {
					recogidos[indiceRecogidos] += 1;
					productos[x] = 0;
					System.out.println("Recogido producto " + tipoProducto);
					x = 0;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					notifyAll();
			}
		}
	}
	
	public boolean cadenaLlena() {
		for (int producto : productos) {
			if (producto == 0)
				return false;
		}
		return true;
	}
	
	public boolean cadenaVacia() {
		for (int producto : productos) {
			if (producto != 0)
				return false;
		}
		return true;
	}

}
