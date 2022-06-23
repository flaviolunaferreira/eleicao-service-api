package dataa.eleger.service;

import dataa.eleger.Exceptions.ViolacaoDeIntegridade;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.dto.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.dto.eleicao.EleicaoDtoResposta;
import dataa.eleger.dto.eleicao.FichaCompletaEleicaoDtoResposta;
import dataa.eleger.entidades.EleicaoEntidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EleicaoService {

    EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao);

    List<EleicaoDtoResposta> listarTodasEleicoes(int pagina, int itens);

    FichaCompletaEleicaoDtoResposta procurarEleicaoPorId(Long id);

    List<EleicaoDtoResposta> listaEleicaoPorNome(String nome);

    FichaCompletaEleicaoDtoResposta atualizarEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao, Long id);

    void apagaEleicao(Long id) throws ViolacaoDeIntegridade;

    FichaCompletaEleicaoDtoResposta cadastraCandidato(Long eleicao, Long candidato) throws ValorDuplicado;
}
