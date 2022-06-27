package dataa.eleger.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntidade usuarioEntidade;
    private PermissoesEnum permissoesEnum;

    public PermissoesEntidade(UsuarioEntidade usuarioEntidade, PermissoesEnum permissoesEnum)
    {
        this.usuarioEntidade = usuarioEntidade;
        this.permissoesEnum = permissoesEnum;
    }

}
