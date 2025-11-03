package llamantes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ejercicio04 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String asignatura;
		String nombreFichero;
		//TODO: cambiar ruta a la del ordenador de clase
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "llamados.Ejercicio04");
		pb.directory(directorio);
		int valorSalida;
		try {
			Process p = pb.start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader brErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			do {
				System.out.println("Escribe la asignatura:");
				asignatura = sc.nextLine();
				System.out.println("Escribe el nombre del fichero:");
				nombreFichero = sc.nextLine();
			//	if (!asignatura.equalsIgnoreCase("*") && !nombreFichero.equalsIgnoreCase("*")) {
				bw.write(asignatura + "\n");
				bw.write(nombreFichero + "\n");		
				bw.flush();
			//	}
				
			} while (!asignatura.equalsIgnoreCase("*") && !nombreFichero.equalsIgnoreCase("*") && !asignatura.isEmpty() && !nombreFichero.isEmpty());
			
			
			
			valorSalida = p.waitFor();
			System.out.println("Valor de salida: " + valorSalida);
			
			String error;
			if (valorSalida != 0) {
				while((error = brErr.readLine()) != null) {
					System.err.println(error);
				}
			}
			
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
			
			
			br.close();
			bw.close();
			
			
		} catch (IOException e) {
			System.err.println("ERROR de IO");
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
