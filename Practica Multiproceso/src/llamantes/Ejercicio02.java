package llamantes;


import java.io.File;
import java.io.IOException;
import excepciones.ProcesoException;

public class Ejercicio02 {

	public static void main(String[] args) throws ProcesoException {
		
		// Declaramos las variables que vamos a utilizar
		int valorSalida1;
		int valorSalida2;
		ProcessBuilder pb1 = new ProcessBuilder("java", "llamados.Ejercicio01Hijo1");
		ProcessBuilder pb2 = new ProcessBuilder("java", "llamados.Ejercicio01Hijo2");
		
		/*
		 * Instanciamos un objeto File con la ruta al .bin de los hijos (no hace falta
		 * que sea la ruta completa, con la palabra "bin", Java ya sabe a qué directorio
		 * nos referimos)
		 */
		File fichero = new File("bin");
		
		/*
		 * Instanciamos más variabes de tipo File con el nombre de los ficheros .txt que
		 * pide el enunciado. Si estos no existen en la carpeta del proyecto, se crearán
		 * ahí automáticamnente (ya que no hemos introducido ruta)
		 */
		File fImpares = new File("impares.txt");
		File fOrden = new File("orden.txt");
		
		// Ficheros adicionales para guardar un registro de errores en caso de que se
		// produzcan
		File fErrores1 = new File("errores1.txt");
		File fErrores2 = new File("errores2.txt");
		
		// Asignamos la ruta a los .bin con cada proceso
		pb1.directory(fichero);
		pb2.directory(fichero);
		
		// Redirigimos los canales según lo indicado en el enunciado
		pb1.redirectOutput(fImpares);
		pb2.redirectInput(fImpares);
		pb2.redirectOutput(fOrden);
		
		// Redigirimos los errores, aunque el enunciado no lo pida:
		pb1.redirectError(fErrores1);
		pb2.redirectError(fErrores2);

		try {
			
			/*
			 * Iniciamos el primer proceso 
			 * (en este caso no necesitamos herramientas para la
			 * entrada/salida, ya que utilizaremos los ficheros .txt)
			 */
			Process p1 = pb1.start();

			/*
			 * Obtenemos y mostramos el valor de salida del primer proceso hijo (esperando a
			 * que termine de ejecutarse)
			 */
			valorSalida1 = p1.waitFor();

			// Imprimimos tal como indica el enunciado
			System.out.println("Valor de salida programa 1: " + valorSalida1);

			/*
			 * Si el primer proceso no termina de la forma esperada, no mostramos su salida
			 * y evitamos que el segundo programa se ejecute (utilizando nuestra propia
			 * excepción)
			 * 
			 */
			if (valorSalida1 != 0) {
				throw new ProcesoException("El primer proceso hijo no terminó de la forma esperada");

			} else {
				System.out.println("Printando la salida del programa de generación de impares en el fichero...");
				System.out.println("Salida del programa de generación de impares guardada en el fichero correspondiente");
				
				/*
				 * Comenzamos el segundo proceso, ahora que hemos controlado la salida del
				 * primero (si lo iniciásemos junto al p1, como en el primer ejercicio, el
				 * archivo "ordenados.txt" se llenaría de contenido a pesar de la expeción
				 */
				Process p2 = pb2.start();
				
				/*
				 * Obtenemos y mostramos el valor de salida del segundo proceso hijo (esperando
				 * a que termine de ejecutarse)
				 */
				valorSalida2 = p2.waitFor();
				
				// Imprimimos tal como se indica en el enunciado
				System.out.println("Valor de salida programa 2: " + valorSalida2);

				/*
				 * Si el segundo programa no termina de la forma esperada, avisamos con una
				 * excepción
				 */
				if (valorSalida2 != 0) {
					throw new ProcesoException("El segundo proceso hijo no terminó de la forma esperada");
					
					// De lo contrario, continuamos de manera normal
				} else {
					System.out.println("Salida ordenada del segundo programa");
					System.out.println("Fin de la salida ordenada del segundo programa");
				}

			}

			// Tratamiento de excepciones necesario
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}