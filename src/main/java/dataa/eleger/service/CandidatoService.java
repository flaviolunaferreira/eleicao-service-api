package dataa.eleger.service;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.dto.candidatos.CandidatoDtoRequest;
import dataa.eleger.dto.candidatos.CandidatoDtoResposta;
import dataa.eleger.entidades.CandidatoEntidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidatoService {

    CandidatoDtoResposta salvarNovoCandidato(CandidatoDtoRequest candidatoDtoRequest);

    List<CandidatoDtoResposta> listarCandidatos(int pagina, int itens);

    List<CandidatoDtoResposta> procurarCandidatoPorNome(String nome);

    CandidatoDtoResposta procurarCandidatoPorId(Long id);

    CandidatoDtoResposta atualizaCandidato(CandidatoDtoRequest candidatoDtoRequest, Long id);

    void apagarCandidato(Long id);

    CandidatoEntidade buscarPorId(Long idCandidato) throws NaoEncontrado;

}
