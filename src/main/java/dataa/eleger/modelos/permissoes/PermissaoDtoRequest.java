package dataa.eleger.modelos.permissoes;

import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.repositorios.UsuarioRepositorio;
import dataa.eleger.uteis.PermissoesEnum;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoDtoRequest {

    private Long usuario;
    private PermissoesEnum permissao;

    public PermissoesEntidade novaPermissao(UsuarioRepositorio usuarioRepositorio) {
        UsuarioEntidade usuarioEntidade = usuarioRepositorio.findById(usuario).get();
        return new PermissoesEntidade(usuarioEntidade, permissao);
    }
}
