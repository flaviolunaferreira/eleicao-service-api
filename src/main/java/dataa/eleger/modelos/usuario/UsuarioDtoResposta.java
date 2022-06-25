package dataa.eleger.modelos.usuario;

import dataa.eleger.entidades.UsuarioEntidade;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoResposta {

    private Long id;
    private String nome;
    private String email;
    private String cpf;

    private String criadoPor;
    private LocalDateTime criadoData;
    private String modificadoPor;
    private LocalDateTime modificadoData;

    public UsuarioDtoResposta(UsuarioEntidade usuarioEntidade) {
        this.setId(usuarioEntidade.getId());
        this.setNome(usuarioEntidade.getNome());
        this.setEmail(usuarioEntidade.getEmail());
        this.setCpf(usuarioEntidade.getCpf());
        this.setCriadoPor(usuarioEntidade.getCriadoPor());
        this.setCriadoData(usuarioEntidade.getCriadoData());
        this.setModificadoPor(usuarioEntidade.getModificadoPor());
        this.setModificadoData(usuarioEntidade.getModificadoData());

    }

}
