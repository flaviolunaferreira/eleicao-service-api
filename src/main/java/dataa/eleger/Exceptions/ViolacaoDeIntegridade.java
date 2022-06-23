package dataa.eleger.Exceptions;

public class ViolacaoDeIntegridade extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ViolacaoDeIntegridade(String message, Throwable cause) {
		super(message, cause);
	}

	public ViolacaoDeIntegridade(String message) {
		super(message);
	}
}
