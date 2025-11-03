package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		// Instanciamos a los chicos
		Alumno harry = new Alumno(Constantes.HARRY_POTTER, Constantes.CASA_GRYFFINDOR, true);
		Alumno draco = new Alumno(Constantes.DRACO_MALFOY, Constantes.CASA_SLYTHERIN, true);
		Alumno cedric = new Alumno(Constantes.CEDRIC_DIGORY, Constantes.CASA_HUFFLEPUFF, true);
		Alumno terry = new Alumno(Constantes.TERRY_BOOT, Constantes.CASA_RAVENCLAW, true);
		// Instanciamos a las chicas
		Alumno hermione = new Alumno(Constantes.HERMIONE_GRANGER, Constantes.CASA_GRYFFINDOR, Constantes.HARRY_POTTER);
		Alumno pansy = new Alumno(Constantes.PANSY_PARKINSON, Constantes.CASA_SLYTHERIN, Constantes.DRACO_MALFOY);
		Alumno tamsin = new Alumno(Constantes.TAMSIN_APPLEBEE, Constantes.CASA_HUFFLEPUFF, Constantes.CEDRIC_DIGORY);
		Alumno luna = new Alumno(Constantes.LUNA_LOVEWOOD, Constantes.CASA_RAVENCLAW, Constantes.TERRY_BOOT);
		// Los recogemos en ArrayList
		ArrayList<Alumno> chicos = new ArrayList<Alumno>(Arrays.asList(harry, draco, cedric, terry));
		ArrayList<Alumno> chicas = new ArrayList<Alumno>(Arrays.asList(hermione, pansy, tamsin, luna));
		/*
		 * Declaramos la pista (información sincronizada y compartida por todos los
		 * hilos)
		 */
		PistaBaile pista = new PistaBaile(chicos, chicas);
		// Instanciamos al director
		Director dumbledore = new Director(pista, chicos);
		/*
		 * Insertamos la pista de baile a las chicas. Esto no se puede hacer antes dado
		 * que las chicas necesitan pista y la pista necesita ArrayList de chicas
		 */
		chicas.forEach(x -> x.setPistaBaile(pista));

		ExecutorService executor = Executors.newFixedThreadPool(5);
		executor.submit(hermione);
		executor.submit(pansy);
		executor.submit(tamsin);
		executor.submit(luna);
		executor.submit(dumbledore);
		executor.shutdown();
		
		// PRUEBA PARA COMPROBAR QUE EL FILTRO FUNCIONA BIEN:
//		ArrayList<Alumno> parejas = pista.filtrarParejas(hermione);
//		parejas.forEach(x -> System.out.println(x.getNombre()));

	}

}
