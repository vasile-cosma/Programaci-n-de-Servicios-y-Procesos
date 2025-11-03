package llamantes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ejercicio03 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\bin");
		File dirEntrada = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 03", "entradaEj03.txt");
		File dirSalida = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 03", "Palindromo.txt");
		File dirError = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 03", "errorEj03.txt");
		ProcessBuilder pb = new ProcessBuilder("java", "llamados.Ejercicio03");
		
		pb.directory(directorio);
		pb.redirectInput(dirEntrada);
		pb.redirectError(dirError);
		pb.redirectOutput(dirSalida);
		
		try {
			Process p = pb.start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			Scanner sc2 = new Scanner(p.getInputStream());
			
			
			int valorSalida = p.waitFor();
			while (sc2.hasNext()) {
				System.out.println(sc2.nextLine());
			}
			
			if (valorSalida == 0) {
				System.out.println("Es palíndormo");
			}else  if (valorSalida == -1) {
				System.out.println("La cadena introducida NO es palíndromo");
			}else {
				System.err.println("ERROR: se han producido errores.");
				System.out.println("ERROR: se han producido errores");
			}
				
			bw.close();
			sc2.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
