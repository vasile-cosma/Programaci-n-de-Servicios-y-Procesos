package llamados;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ej01 {
	public static String MSJ_ERROR = "ERROR: La cadena debe tener al menos 3 caracteres";
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Recibe cadena. Si tiene menos de 3 caracteres, error si tiene mas de 3 carracteres cuenta vocales.
		// El segundo programa dice "el nº de vocales es... X" y, si no "el texto mandado es muy corto".
		
		System.out.println("Introduza una cadena:");
		String respuesta = sc.nextLine();
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\TEMA 2\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "llamantes.Ej01");
		pb.directory(directorio);
		
		try {
			Process p = pb.start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			bw.write(respuesta); //"Ponerlo en la tubería"
			bw.flush(); //"Emoujarlo"
			bw.close();
			
			Scanner sc2 = new Scanner(p.getInputStream());
			while (sc2.hasNextLine()) {
				System.out.println(sc2.nextLine());
			}
			sc2.close();
			
			int exitVal = -1;
			exitVal = p.waitFor();
			
			if (exitVal == -1) 
				System.out.println(MSJ_ERROR);
				
			
			
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			System.out.println(MSJ_ERROR);
		}
		
		
		
		
		
	}

}
