package dataa.eleger.Exceptions;

public class ValorDuplicado extends Exception {

    @Override
    public String getMessage() {
        return "Já existe um campo com esse nome";
    }
}
