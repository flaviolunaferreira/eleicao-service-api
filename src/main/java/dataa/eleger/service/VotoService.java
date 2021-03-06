package dataa.eleger.service;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.entidades.VotoEntidade;
import dataa.eleger.modelos.itensDeVoto.ItensDeVotoDtoRequisicao;
import dataa.eleger.modelos.itensDeVoto.ItensDeVotoDtoResposta;
import dataa.eleger.modelos.voto.VotoDtoRequisicao;
import dataa.eleger.modelos.voto.VotoDtoResposta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VotoService {


    VotoDtoResposta novoVoto(VotoDtoRequisicao votoDtoRequisicao);

    List<VotoDtoResposta> ListarTodosOsVotos();

    ItensDeVotoDtoResposta salvaCandidatoVotado(ItensDeVotoDtoRequisicao itensDeVotoDtoRequisicao);

    VotoEntidade buscarVoto(Long id) throws NaoEncontrado;
}
