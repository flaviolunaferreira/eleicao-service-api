package dataa.eleger.service;

import dataa.eleger.dto.EleicaoDtoRequisicao;
import dataa.eleger.entidades.EleicaoEntidade;
import org.springframework.stereotype.Service;

@Service
public interface EleicaoService {
    EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao);
}
