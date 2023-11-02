package dataa.eleger.modelos.eleicao;

import dataa.eleger.entidades.EleicaoEntidade;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EleicaoDtoResposta {

    private String nome;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public EleicaoDtoResposta(EleicaoEntidade eleicaoEntidade) {
        this.setNome(eleicaoEntidade.getNome());
        this.setInicio(eleicaoEntidade.getInicio());
        this.setFim(eleicaoEntidade.getFim());
    }
}
