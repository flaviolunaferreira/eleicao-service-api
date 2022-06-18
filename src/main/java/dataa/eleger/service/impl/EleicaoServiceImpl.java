package dataa.eleger.service.impl;

import dataa.eleger.dto.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.entidades.EleicaoEntidade;
import dataa.eleger.service.EleicaoService;
import org.springframework.stereotype.Service;

@Service
public class EleicaoServiceImpl implements EleicaoService {

    @Override
    public EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao) {
        EleicaoEntidade result = eleicaoDtoRequisicao.novaEleicao();
        return result;
    }
}
