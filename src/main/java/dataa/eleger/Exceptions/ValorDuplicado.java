package dataa.eleger.Exceptions;

public class ValorDuplicado extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ValorDuplicado(String message, Throwable cause) {
            super(message, cause);
        }

    public ValorDuplicado(String message) {
            super(message);
    }
}
