package dataa.eleger.Exceptions;

public class NaoEncontrado extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NaoEncontrado(String message, Throwable cause) {
		super(message, cause);
	}

	public NaoEncontrado(String message) {
		super(message);
	}
}
