package dataa.eleger.service;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.Exceptions.ViolacaoDeIntegridade;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.Exceptions.ViolacaoDeRegra;
import dataa.eleger.modelos.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.modelos.eleicao.EleicaoDtoResposta;
import dataa.eleger.modelos.eleicao.FichaCompletaEleicaoDtoResposta;
import dataa.eleger.entidades.EleicaoEntidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleicaoService {

    EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao) throws ViolacaoDeRegra;

    List<EleicaoDtoResposta> listarTodasEleicoes(int pagina, int itens);

    FichaCompletaEleicaoDtoResposta procurarEleicaoPorId(Long id);

    List<EleicaoDtoResposta> listaEleicaoPorNome(String nome);

    FichaCompletaEleicaoDtoResposta atualizarEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao, Long id);

    void apagaEleicao(Long id) throws ViolacaoDeIntegridade;

    FichaCompletaEleicaoDtoResposta cadastraCandidato(Long eleicao, Long candidato) throws ValorDuplicado;

    EleicaoEntidade buscaPorId(Long id) throws NaoEncontrado;
}