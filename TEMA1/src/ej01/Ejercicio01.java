package ej01;

import java.io.IOException;
import java.util.Scanner;

public class Ejercicio01 {
	static int retorno = -2;
	public static void main(String[] args) throws IOException, InterruptedException{
		Scanner sc = new Scanner(System.in);
		ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\Mozilla Firefox\\firefox.exe", sc.nextLine());
		
		Process p = pb.start();
		
		retorno = p.waitFor();
		
		System.out.println("Resultado: " + retorno);
		
		sc.close();
	}
}
