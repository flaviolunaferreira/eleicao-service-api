package dataa.eleger.modelos.permissoes;

import dataa.eleger.entidades.PermissoesEntidade;

import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoDtoResposta {

    private Long idPermissao;
    private String usuario;
    private String permissoesEnum;

    public PermissaoDtoResposta(PermissoesEntidade permissoes) {
        this.setIdPermissao(permissoes.getIdPermissao());
        this.setUsuario(permissoes.getUsuarioEntidade().getNome());
        this.setPermissoesEnum(permissoes.getPermissoesEnum().getDescricao());
    }
}
