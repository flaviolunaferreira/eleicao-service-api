package dataa.eleger.service;

import dataa.eleger.dto.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.dto.eleicao.EleicaoDtoResposta;
import dataa.eleger.entidades.EleicaoEntidade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EleicaoService {
    EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao);

    List<EleicaoDtoResposta> listarTodasEleicoes();

    Optional<EleicaoDtoResposta> procurarEleicaoPorId(Integer id);
}
