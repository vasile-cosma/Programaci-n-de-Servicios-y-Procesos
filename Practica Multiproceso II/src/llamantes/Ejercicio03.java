package llamantes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Ejercicio03 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso II\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "llamados.Ejercicio03");
		pb.directory(directorio);
		
		try {
			Process p = pb.start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			Scanner sc2 = new Scanner(p.getInputStream());
			
			System.out.println("Escribe un texto:");
			String respuesta = sc.nextLine();
			
			bw.write(respuesta);
			bw.flush();
			bw.close();
			
			int valorSalida = p.waitFor();
			while (sc2.hasNext()) {
				System.out.println(sc2.nextLine());
			}
			
			if (valorSalida == 0) {
				System.out.println("Es palíndormo");
			}else  if (valorSalida == -1) {
				System.out.println("NO es palíndormo");
			}else
				System.out.println("ERROR: se han producido errores");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
