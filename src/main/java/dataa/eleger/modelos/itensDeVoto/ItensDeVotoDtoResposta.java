package dataa.eleger.modelos.itensDeVoto;

import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.ItensDoVoto;
import dataa.eleger.entidades.VotoEntidade;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItensDeVotoDtoResposta {

    private Long IdItemDoVoto;
    private CandidatoEntidade candidatoEntidade;
    private VotoEntidade votoEntidade;

    public ItensDeVotoDtoResposta(ItensDoVoto voto) {
        this.setIdItemDoVoto(voto.getIdItemDoVoto());
        this.setCandidatoEntidade(voto.getCandidatoEntidade());
        this.setVotoEntidade(voto.getVotoEntidade());
    }

}
