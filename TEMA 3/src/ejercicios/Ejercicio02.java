package ejercicios;

public class Ejercicio02 extends Thread{
	public final static int KM_BICI = 180;
	public final static int KM_CORRER = 42;
	public final static int M_NADAR = 4000;
	private String nombre;
	private int velocidadCorrer;
	private int velocidadBici;
	private int velocidadNadar;
	
	
	public Ejercicio02(String nombre, int velocidadCorrer, int velocidadBici, int velocidadNadar) {
		this.nombre = nombre;
		this.velocidadCorrer = velocidadCorrer;
		this.velocidadBici = velocidadBici;
		this.velocidadNadar = velocidadNadar;
	}
	
	public void iniciarCarrera() throws InterruptedException {
		int kmBiciRestantes = KM_BICI;
		int kmCorrerRestantes = KM_CORRER;
		int mNadarRestantes = M_NADAR;
		System.out.println(nombre + " comienza la prueba en bici");
		do {
			kmBiciRestantes = (kmBiciRestantes - velocidadBici);
			Thread.sleep(1000);
			
			if (kmBiciRestantes > 0) {
				//System.out.println("Km de bici restantes para : " + nombre + ": " + kmBiciRestantes);
			} else {
				System.out.println(nombre + " ha acabado la prueba en bici");
			}
			
		} while (kmBiciRestantes > 0);
		
		
		System.out.println(nombre + " comienza la maratón");
		do {
			kmCorrerRestantes = (kmCorrerRestantes - velocidadCorrer);
			Thread.sleep(1000);
			
			if (kmCorrerRestantes > 0) {
				//System.out.println("Km de maratón restantes para : " + nombre + ": " + kmCorrerRestantes);
			} else {
				System.out.println(nombre + " ha acabado la maratón");
			}
			
		} while (kmCorrerRestantes > 0);
		
		
		System.out.println(nombre + " comienza la prueba de natación");
		do {
			mNadarRestantes = (mNadarRestantes - velocidadNadar);
			Thread.sleep(1000);
			
			if (mNadarRestantes > 0) {
				//System.out.println("Metros de natación restantes para " + nombre + ": " + mNadarRestantes);
			} else {
				System.out.println(nombre + " ha terminado la prueba de IRON MAN. ¡ENHORABUENA!");
			}

		} while (mNadarRestantes > 0);
		
	}
	
	@Override
	public void run() {
		try {
			this.iniciarCarrera();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		Ejercicio02 pedro = new Ejercicio02("Pedro", 6, 20, 1000);
		Ejercicio02 juan = new Ejercicio02("Juan", 7, 18, 2000);
		Ejercicio02 vicente = new Ejercicio02("Vicente", 6, 30, 1000);
		pedro.start();
		juan.start();
		vicente.start();
		
	}

}
