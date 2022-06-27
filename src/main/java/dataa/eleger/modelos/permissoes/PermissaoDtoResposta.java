package dataa.eleger.modelos.permissoes;

import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.uteis.PermissoesEnum;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
