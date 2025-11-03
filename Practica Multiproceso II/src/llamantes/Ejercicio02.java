package llamantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio02 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		String devuelto;
		ArrayList<String> devueltos = new ArrayList<String>();
		//TODO cambia la siguiente línea para que funcione en clase:
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso II\\bin");
		ProcessBuilder pb = new ProcessBuilder("java","llamados.Ejercicio02");
		pb.directory(directorio);
		
		try {
			Process p = pb.start();
			ObjectOutputStream oos = new ObjectOutputStream(p.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			do {
				System.out.println("Escribe un número:");
				devuelto = sc.nextLine();
				devueltos.add(devuelto);
			} while (!devuelto.equalsIgnoreCase("*"));
			
			oos.writeObject(devueltos);
			oos.flush();
			p.waitFor();
			
			if (p.exitValue() == 0) {
				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
			} else if (p.exitValue() == -1) {
				System.out.println("ERROR: se han introducido cadenas de texto.");
			} else {
				System.out.println("ERROR: se han producido errores");
			}
			
			oos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}

}
