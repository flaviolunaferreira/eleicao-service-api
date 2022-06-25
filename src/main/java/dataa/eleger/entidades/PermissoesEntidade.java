package dataa.eleger.entidades;

import dataa.eleger.uteis.PermissoesEnum;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class PermissoesEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissao;

    private PermissoesEnum permissoesEnum;

    public PermissoesEntidade(PermissoesEnum permissoesEnum) {
        this.permissoesEnum = permissoesEnum;
    }
}
