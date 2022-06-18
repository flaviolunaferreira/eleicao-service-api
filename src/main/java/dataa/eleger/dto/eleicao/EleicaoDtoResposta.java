package dataa.eleger.dto.eleicao;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EleicaoDtoResposta {

    private String nome;
    private LocalDate inicio;
    private LocalDate fim;

}
