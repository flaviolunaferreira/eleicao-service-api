package dataa.eleger.modelos.permissoes;

import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.uteis.PermissoesEnum;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissaoDtoRequest {

    private PermissoesEnum permissoesEnum;

    public PermissoesEntidade novaPermissao() {
        return new PermissoesEntidade(permissoesEnum);
    }
}
