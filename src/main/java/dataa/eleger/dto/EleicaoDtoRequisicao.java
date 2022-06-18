package dataa.eleger.dto;


import dataa.eleger.entidades.EleicaoEntidade;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EleicaoDtoRequisicao {

    private String nome;
    private LocalDate inicio;
    private LocalDate fim;

    public EleicaoEntidade novaEleicao() {
        return new EleicaoEntidade(nome, inicio, fim);
    }
}
