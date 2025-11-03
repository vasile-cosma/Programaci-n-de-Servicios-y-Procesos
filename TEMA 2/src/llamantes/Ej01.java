package llamantes;


import java.util.Scanner;

//Recibe cadena. Si tiene menos de 3 caracteres, error si tiene mas de 3 carracteres cuenta vocales.
// El segundo programa dice "el nº de vocales es... X" y, si no "el texto mandado es muy corto".


public class Ej01 {
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		
		//System.out.println("Introduzca una cadena:");
		String cadena = sc.nextLine();
		int acum = 0;
		
		if (cadena.length()<3) { 
				System.exit(-1);
			} else {
				for (int n = 0; n < cadena.length(); n++) {
					char caracter = cadena.toLowerCase().charAt(n);
					if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u' || caracter == 'á'|| caracter == 'é' || caracter == 'í'|| caracter == 'ó'|| caracter == 'ú') {
						acum++;
					}
				}
				System.out.println(acum);
				System.exit(0);
			}
	}
}
