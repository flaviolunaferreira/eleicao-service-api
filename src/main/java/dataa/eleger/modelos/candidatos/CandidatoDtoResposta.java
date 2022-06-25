package dataa.eleger.modelos.candidatos;

import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.CargoEntidade;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoDtoResposta {

    private Long idCandidato;
    private String nomeCandidato;
    private CargoEntidade cargoEntidade;
    private String foto;

    private String criadoPor;
    private LocalDateTime criadoData;
    private String modificadoPor;
    private LocalDateTime modificadoData;


    public CandidatoDtoResposta(CandidatoEntidade candidatoEntidade) {
        this.setIdCandidato(candidatoEntidade.getIdCandidato());
        this.setNomeCandidato(candidatoEntidade.getNomeCandidato());
        this.setCargoEntidade(candidatoEntidade.getCargoEntidade());
        this.setFoto(candidatoEntidade.getFoto());
        this.setCriadoPor(candidatoEntidade.getCriadoPor());
        this.setCriadoData(candidatoEntidade.getCriadoData());
        this.setModificadoPor(candidatoEntidade.getModificadoPor());
        this.setModificadoData(candidatoEntidade.getModificadoData());
    }
}
