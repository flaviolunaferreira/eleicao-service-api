package dataa.eleger.Exceptions;

public class ViolacaoDeRegra extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ViolacaoDeRegra(String message, Throwable cause) {
        super(message, cause);
    }

    public ViolacaoDeRegra(String message) {
        super(message);
    }
}
