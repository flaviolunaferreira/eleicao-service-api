package dataa.eleger.uteis;

public enum PermissoesEnum {

    USUARIO("usuario"),
    ADMINISTRADOR("administrador");

    private final String descricao;

    PermissoesEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
