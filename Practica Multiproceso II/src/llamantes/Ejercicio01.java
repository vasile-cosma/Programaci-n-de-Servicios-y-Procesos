package llamantes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio01 {
	public static Scanner sc = new Scanner(System.in);
	public static String msjExito = "Has escrito un entero positivo";
	public static String msjError1 = "El entero debe ser positivo";
	public static String msjError2 = "No has escrito un entero";
	
	public static void main(String[] args) {
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso II\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "llamados.Ejercicio01");
		
		System.out.println("Introduzca un número entero postivio:");
		String respuesta;
		respuesta = sc.nextLine();
		
		
		pb.directory(directorio);
		try {
			Process p = pb.start();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));	
			
			bw.write(respuesta + "\n");
			bw.flush();
			bw.close();
			
			while (br.ready()) {
				br.readLine();
			}
			
			int exitVal = 0;
			exitVal = p.waitFor();
			System.out.println("Valor devuelto:" + exitVal);
			
			switch (exitVal) {
			case -3:
				System.out.println(msjExito);
				break;
			case -2:
				System.out.println(msjError1);
				break;
			case -1:
				System.out.println(msjError2);
				break;
			case 0:
				System.out.println(msjError1);
				break;
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
			System.out.println(msjError2);
		} 

	}

}
