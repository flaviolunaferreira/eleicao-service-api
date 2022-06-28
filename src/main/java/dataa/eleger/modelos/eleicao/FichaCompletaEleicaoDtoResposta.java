package dataa.eleger.modelos.eleicao;

import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.EleicaoEntidade;
import dataa.eleger.entidades.VotoEntidade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FichaCompletaEleicaoDtoResposta {

    private Long id;
    private String nome;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    private List<CandidatoEntidade> candidato;
    private String criadoPor;
    private LocalDateTime criadoData;
    private String modificadoPor;
    private LocalDateTime modificadoData;

    public FichaCompletaEleicaoDtoResposta(EleicaoEntidade result) {
        this.setId(result.getId());
        this.setNome(result.getNome());
        this.setInicio(result.getInicio());
        this.setFim(result.getFim());
        this.setCandidato(result.getCandidato());
        this.setCriadoPor(result.getCriadoPor());
        this.setCriadoData(result.getCriadoData());
        this.setModificadoPor(result.getModificadoPor());
        this.setModificadoData(result.getModificadoData());
    }
}

