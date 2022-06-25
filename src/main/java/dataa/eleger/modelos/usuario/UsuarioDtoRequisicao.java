package dataa.eleger.modelos.usuario;

import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.uteis.Cpf;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDtoRequisicao {

    @NonNull
    @NotBlank
    private String nome;

    @Email
    private String email;

    @NonNull
    @NotBlank
    private String senha;

    @CPF
    private String cpf;

    public UsuarioEntidade novoUsuario() {
        String CPF = new Cpf().formataCpf(cpf);
        return new UsuarioEntidade(nome, email, senha, CPF);
    }
}
