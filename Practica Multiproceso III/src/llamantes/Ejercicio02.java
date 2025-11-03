package llamantes;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio02 {
	public static Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\bin");
		File dirEntrada = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 02","entradaEj02.txt");
		File dirSalida = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 02","sumaEj02.txt");
		File dirError = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 02","errorEj02.txt");
		
		
		ProcessBuilder pb = new ProcessBuilder("java","llamados.Ejercicio02");
		pb.directory(directorio);
		pb.redirectInput(dirEntrada);
		pb.redirectOutput(dirSalida);
		pb.redirectError(dirError);
		
		
		try {
			Scanner scError = new Scanner(dirError);
			Process p = pb.start();
			int valorSalida = p.waitFor();
			
			if (valorSalida == 0) {
				System.out.println("Todo ha ido bien");
			} else {
				System.err.println("ERROR: se han producido errores. Valor salida:" + valorSalida);
				while (scError.hasNextLine()) {
					System.err.println(scError.nextLine());
				}
			}
			scError.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
