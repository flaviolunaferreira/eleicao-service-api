package dataa.eleger.modelos.candidatos;

import dataa.eleger.entidades.CandidatoEntidade;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoDtoResposta {

    private Long idCandidato;
    private String candidato;
    private String cargo;
    private String foto;


    public CandidatoDtoResposta(CandidatoEntidade candidatoEntidade) {


        this.setIdCandidato(candidatoEntidade.getIdCandidato());
        this.setCandidato(candidatoEntidade.getNomeCandidato());
        this.setCargo(candidatoEntidade.getCargoEntidade().getNomeCargo());
        this.setFoto(candidatoEntidade.getFoto());

    }
}
