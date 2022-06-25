package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.modelos.permissoes.PermissaoDtoRequest;
import dataa.eleger.modelos.permissoes.PermissaoDtoResposta;
import dataa.eleger.repositorios.PermissaoRepositorio;
import dataa.eleger.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    private final PermissaoRepositorio permissaoRepositorio;

    @Autowired
    public PermissaoServiceImpl(PermissaoRepositorio permissaoRepositorio) {
        this.permissaoRepositorio = permissaoRepositorio;
    }

    @Override
    public PermissaoDtoResposta salvarNovaPermissao(PermissaoDtoRequest permissaoDtoRequest) throws ValorDuplicado {
        PermissoesEntidade procurarPorNome = permissaoRepositorio
                .findByPermissoesEnum(permissaoDtoRequest.getPermissoesEnum());
        if (procurarPorNome == null) return new PermissaoDtoResposta(permissaoRepositorio.save(permissaoDtoRequest.novaPermissao()));
        throw new ValorDuplicado("Sinto muito... já tenho uma permissão com esse nome.");
    }

    @Override
    public PermissoesEntidade buscarPorId(Long permissao) throws NaoEncontrado {
         return permissaoRepositorio.findById(permissao)
            .orElseThrow(() ->  new NaoEncontrado("Desculpe, mas não encontrei a permissão com o id: " + permissao));
    }
}
