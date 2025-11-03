package llamados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejercicio02 {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayList<String> recibidos = new ArrayList<String>();
		int num;
		int acum = 0;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(System.in);
			recibidos = (ArrayList<String>) ois.readObject();
			
			for (String recibido : recibidos) {
				if (recibido.equals("*"))
					break;
				num = Integer.parseInt(recibido);
				acum += num; 
			}
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.exit(-1);
		}
		for (String recibido : recibidos) {
			System.out.println("Escrito " + recibido);
		}
			System.out.println("Suma: " + acum);
			System.exit(0);
		
		

	}

}
