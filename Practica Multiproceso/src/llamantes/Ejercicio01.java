package llamantes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import excepciones.ProcesoException;

public class Ejercicio01 {

	public static void main(String[] args) throws ProcesoException {

		// Declaramos las variables que vamos a utilizar
		int num;
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

		// Asignamos la ruta a los .bin con cada proceso
		pb1.directory(fichero);
		pb2.directory(fichero);

		try {

			/*
			 * Iniciamos los procesos y declaramos las herramientas de entrada/salida
			 * necesarias
			 */
			Process p1 = pb1.start();
			Process p2 = pb2.start();

			/*
			 * Dos Scanners que conectan los canales de salida de cada oroceso hijo
			 * respectivamente con el canal de entrada del padre
			 */
			Scanner sc1 = new Scanner(p1.getInputStream());
			Scanner sc2 = new Scanner(p2.getInputStream());

			/*
			 * BufferedWriter conectado el canal de salida del padre al canal de entrada del
			 * segundo hijo
			 */
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));

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
				sc1.close();
				sc2.close();
				bw.close();
				throw new ProcesoException("El primer proceso hijo no terminó de la forma esperada");

			} else {
				System.out.println("Printando la salida del programa de generación de impares");

				/*
				 * Bucle para obtener todos los números que el primer hijo deja en el canal de
				 * salida.
				 */
				while (sc1.hasNextLine()) {

					// Guardamos cada número devuelto por el primer hijo en la variable num
					num = Integer.parseInt(sc1.nextLine());

					// Imprimimos cada número devuelto por el primer hijo
					System.out.println(num);

					// Escribimos cada número en el buffered writer
					bw.write(num + "\n");
				}
				System.out.println("Fin de la  salida del programa de generación de impares");

				// "Empujamos" todo lo que tenemos escrito en el bufferedWriter
				bw.flush();

				/*
				 * Cerramos el BufferedWriter y el primer Scanner, ya que hemos terminado de
				 * utilizarlo
				 */
				bw.close();
				sc1.close();
				
				/*
				 * Obtenemos y mostramos el valor de salida del segundo proceso hijo (esperando
				 * a que termine de ejecutarse)
				 */
				valorSalida2 = p2.waitFor();
				
				// Imprimimos tal como se indica en el enunciado
				System.out.println("Valor de salida programa 2: " + valorSalida2);

				/*
				 * Si el segundo programa no termina de la forma esperada, avisamos con una
				 * excepción y no imprimimos su salida
				 */
				if (valorSalida2 != 0) {
					sc2.close();
					throw new ProcesoException("El segundo proceso hijo no terminó de la forma esperada");
					
					// De lo contrario, continuamos su ejecución de manera normal
				} else {
					System.out.println("Salida ordenada del segundo programa");
					
					/*
					 * Mientras el segundo Scanner (conectado al output del segundo hijo) tenga
					 * líneas, las imprimimos en consola
					 */
					while (sc2.hasNextLine()) {
						num = Integer.parseInt(sc2.nextLine());
						System.out.println(num);
					}
					System.out.println("Fin de la salida ordenada del segundo programa");
					
					// Cerramos el Scanner restante
					sc2.close();
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
