package dataa.eleger.modelos.itensDeVoto;

import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.ItensDoVoto;
import dataa.eleger.entidades.VotoEntidade;
import dataa.eleger.repositorios.CandidatoRepositorio;
import dataa.eleger.repositorios.VotoRepositorio;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItensDeVotoDtoRequisicao {

    private Long candidatoEntidade;

    private Long votoEntidade;

    public ItensDoVoto novoItemDoVoto(CandidatoRepositorio candidatoRepositorio, VotoRepositorio votoRepositorio) {
        CandidatoEntidade candidato = candidatoRepositorio.findById(candidatoEntidade).get();
        VotoEntidade voto = votoRepositorio.findById(votoEntidade).get();
        return new ItensDoVoto(candidato, voto);
    }
}
