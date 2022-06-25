package dataa.eleger.modelos.eleicao;


import dataa.eleger.entidades.EleicaoEntidade;
import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EleicaoDtoRequisicao {


    @NotBlank(message = "Descrição do processo de eleição não pode estar em branco.")
    @NotNull(message = "Descrição do Processo de eleição não pode ser nulo")
    private String nome;

    @NotBlank(message = "Data de Início deve ser preenchida.")
    @NotNull(message = "Data de Início deve ser preenchida.")
    private LocalDate inicio;

    @NotBlank(message = "Data final deve ser preenchida.")
    @NotBlank(message = "Data final deve ser preenchida.")
    private LocalDate fim;

    public EleicaoEntidade novaEleicao() {
        return new EleicaoEntidade(nome, inicio, fim);
    }
}
