package clases;

import java.util.ArrayList;

public class PistaBaile {
	private boolean cambio;
	private boolean terminado;
	private ArrayList<Alumno> chicos;
	private ArrayList<Alumno> chicas;

	public PistaBaile(ArrayList<Alumno> chicos, ArrayList<Alumno> chicas) {
		super();
		this.cambio = false;
		this.terminado = false;
		this.chicos = chicos;
		this.chicas = chicas;
	}

	public boolean isCambio() {
		return cambio;
	}

	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public ArrayList<Alumno> getChicos() {
		return chicos;
	}

	public void setChicos(ArrayList<Alumno> chicos) {
		this.chicos = chicos;
	}

	public ArrayList<Alumno> getChicas() {
		return chicas;
	}

	public void setChicas(ArrayList<Alumno> chicas) {
		this.chicas = chicas;
	}

	public synchronized void iniciarRotacion() {
		// Al comienzo de cada rotación, ninguna chica ha cambiado
		chicas.forEach(x -> x.setHaCambiado(false));
		chicos.forEach(x -> x.setOcupado(false));
	}

	public synchronized void cambiarPareja(Alumno chica) {
		/*
		 * Filtramos las parejas con el método para ello
		 * 
		 */
		ArrayList<Alumno> posiblesParejas = filtrarParejas(chica);

		// Si no hay posibles parejas, descansa
		if (posiblesParejas.isEmpty()) {
			chica.setPareja(null);
			chica.setUltimaPareja(null);
			System.out.println(chica.getNombre() + " descansa esta vez");

			// Si hay posibles parejas, elegimos una al azar
		} else {
			int maxIndice = posiblesParejas.size() - 1;
			int indexPareja = (int) (Math.random() * (maxIndice + 1));
			Alumno nuevaPareja = posiblesParejas.get(indexPareja);
			chica.setPareja(nuevaPareja.getNombre());
			nuevaPareja.setOcupado(true);
			System.out.println(chica.getNombre() + " bailará con " + nuevaPareja.getNombre());
		}
		/*
		 * Indicamos que la chica ha cambiado de pareja en esta rotación y cambiamos a
		 * su última pareja
		 */
		chica.setHaCambiado(true);
		chica.setUltimaPareja(chica.getPareja());

	}

	public synchronized ArrayList<Alumno> filtrarParejas(Alumno chica) {
		/*
		 * Si la chica tiene pareja, liberamos al chico para que lo puedan escoger otras
		 * chicas
		 */
//		if (chica.getPareja() != null)
//			for (Alumno alumno : chicos) {
//				if (alumno.getNombre().equalsIgnoreCase(chica.getPareja()))
//					alumno.setOcupado(false);
//			}
		
		// Filtramos para que no salga la última pareja de la chica ni los chicos ocupados
		ArrayList<Alumno> nuevaLista = new ArrayList<Alumno>(chicos.stream()
																	.filter(x -> !x.isOcupado())
																	.filter(x -> chica.getUltimaPareja() == null || !chica.getUltimaPareja().equalsIgnoreCase(x.getNombre()))
																	.toList());
		// Declaramos lista definitiva de posibles parejas
		ArrayList<Alumno> listaDefinitiva;

		// Filtramos gryffindor y slytherin
		if (chica.getCasa().equalsIgnoreCase(Constantes.CASA_GRYFFINDOR)) {
			listaDefinitiva = new ArrayList<Alumno>(
					nuevaLista.stream().filter(x -> !x.getCasa().equalsIgnoreCase(Constantes.CASA_SLYTHERIN)).toList());
		} else if (chica.getCasa().equalsIgnoreCase(Constantes.CASA_SLYTHERIN)) {
			listaDefinitiva = new ArrayList<Alumno>(nuevaLista.stream()
					.filter(x -> !x.getCasa().equalsIgnoreCase(Constantes.CASA_GRYFFINDOR)).toList());
		} else {
			listaDefinitiva = new ArrayList<Alumno>(nuevaLista);
		}

		return listaDefinitiva;
	}
}
