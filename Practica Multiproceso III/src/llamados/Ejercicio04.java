package llamados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio04 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String asignatura;
		String nombreFichero;
		int aprobados = 0;
		double acumNotas = 0;
		int acum = 0;
		double media;
		
		
		
		do {
			asignatura = sc.nextLine();
			nombreFichero = sc.nextLine();
			
			if (!asignatura.equalsIgnoreCase("*") && !nombreFichero.equalsIgnoreCase("*")) {
				//TODO: cambiar ruta a la del ordenador de clase
				File fichero = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 04", nombreFichero);
				
				try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
					while (br.ready()) {
						int nota = Integer.parseInt(br.readLine());
						acumNotas += nota;
						acum++;
						if (nota >= 5)
							aprobados++;
					}
					media = acumNotas / acum;
					System.out.println("En la asignatura " + asignatura);
					System.out.println("Han aprobado " + aprobados + " alumnos");
					System.out.println("La media es " + media);
					System.out.println("**************************************************************************");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					System.err.println("ERROR: Fichero no encontrado");
					System.exit(-1);
				} catch (IOException e) {
					System.err.println("ERROR: Se han producido errores");
					System.exit(-2);
					e.printStackTrace();
				} catch (Exception e) {
					System.err.println("ERROR: Se han producido errores");
					System.exit(-2);
				}
			}
		} while (!asignatura.equalsIgnoreCase("*") && !nombreFichero.equalsIgnoreCase("*"));
		System.exit(0);
		
		
		
		
	}

}
