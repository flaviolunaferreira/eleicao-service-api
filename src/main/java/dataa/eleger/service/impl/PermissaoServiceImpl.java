package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.modelos.permissoes.PermissaoDtoRequest;
import dataa.eleger.modelos.permissoes.PermissaoDtoResposta;
import dataa.eleger.repositorios.PermissaoRepositorio;
import dataa.eleger.repositorios.UsuarioRepositorio;
import dataa.eleger.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    private final PermissaoRepositorio permissaoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public PermissaoServiceImpl(PermissaoRepositorio permissaoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.permissaoRepositorio = permissaoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public PermissaoDtoResposta salvarNovaPermissao(PermissaoDtoRequest permissaoDtoRequest) throws ValorDuplicado {

        Optional<UsuarioEntidade> usuarioEntidade = usuarioRepositorio.findById(permissaoDtoRequest.getUsuario());

        List<PermissoesEntidade> seExiste = usuarioEntidade.get().getPermissoesEntidade()
                .stream().filter(item -> item.getPermissoesEnum().getDescricao()
                .contentEquals(permissaoDtoRequest.getPermissao().getDescricao()))
                .collect(Collectors.toList());

        if (seExiste.isEmpty())
            return new PermissaoDtoResposta(
                    permissaoRepositorio.save(permissaoDtoRequest.novaPermissao(usuarioRepositorio))
            );
        throw new ValorDuplicado("Sinto muito... já tenho uma permissão com esse nome para esse Usuário.");
    }

    @Override
    public PermissoesEntidade buscarPorId(Long permissao) throws NaoEncontrado {
         return permissaoRepositorio.findById(permissao)
            .orElseThrow(() ->  new NaoEncontrado("Desculpe, mas não encontrei a permissão com o id: " + permissao));
    }

    @Override
    public List<PermissaoDtoResposta> listarTodasPermissoes() {
        List<PermissoesEntidade> list = permissaoRepositorio.findAll();
        return list.stream().map(PermissaoDtoResposta::new).collect(Collectors.toList());
    }
}
