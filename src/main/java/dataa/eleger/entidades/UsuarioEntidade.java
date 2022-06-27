package dataa.eleger.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String cpf;

    @OneToMany(mappedBy = "usuarioEntidade", fetch = FetchType.EAGER)
    private List<PermissoesEntidade> permissoesEntidade = new ArrayList<>();

    private String criadoPor = System.getProperty("user.name");
    private LocalDateTime criadoData = LocalDateTime.now();
    private String modificadoPor = System.getProperty("user.name");
    private LocalDateTime modificadoData = LocalDateTime.now();

    public UsuarioEntidade(String nome, String email, String senha, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }
}
