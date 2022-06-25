package dataa.eleger.service;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.modelos.permissoes.PermissaoDtoRequest;
import dataa.eleger.modelos.permissoes.PermissaoDtoResposta;
import org.springframework.stereotype.Service;

@Service
public interface PermissaoService {
    PermissaoDtoResposta salvarNovaPermissao(PermissaoDtoRequest permissaoDtoRequest);
    PermissoesEntidade buscarPorId(Long permissao) throws NaoEncontrado;
}
