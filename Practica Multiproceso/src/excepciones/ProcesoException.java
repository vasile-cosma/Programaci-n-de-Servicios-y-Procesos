package excepciones;

public class ProcesoException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public ProcesoException(String msg) {
		super(msg);
	}
}
