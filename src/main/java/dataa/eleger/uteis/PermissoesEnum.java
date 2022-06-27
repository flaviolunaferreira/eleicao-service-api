package dataa.eleger.uteis;

public enum PermissoesEnum {

    ELEITOR("ELEITOR"),
    ADMINISTRADOR("ADMINISTRADOR");

    private final String descricao;

    PermissoesEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
