package llamantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ejercicio01 {
	public static Scanner sc = new Scanner(System.in);
	public static String msjExito = "Has escrito un entero positivo";
	public static String msjError1 = "El entero debe ser positivo";
	public static String msjError2 = "No has escrito un entero";
	
	public static void main(String[] args) {
		File directorio = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\bin");
		ProcessBuilder pb = new ProcessBuilder("java", "llamados.Ejercicio01");
		File dInp = new File("C:\\Users\\Vespertino\\Desktop\\PSP\\EJERCICIOS\\Practica Multiproceso III\\Ficheros\\Ejercicio 01", "entradaEj01.txt");
		
		pb.directory(directorio);
		pb.redirectInput(dInp);
		
		try {
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));	
		
			
			
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
