package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.modelos.usuario.UsuarioDtoRequisicao;
import dataa.eleger.modelos.usuario.UsuarioDtoResposta;
import dataa.eleger.repositorios.UsuarioRepositorio;
import dataa.eleger.service.UsuarioService;
import dataa.eleger.uteis.Cpf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder encoder;
    private final Cpf cpf;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepositorio usuarioRepositorio, PasswordEncoder encoder, Cpf cpf) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.encoder = encoder;
        this.cpf = cpf;
    }


    /**
     * @param usuarioDtoRequisicao
     * @return
     */
    @Override
    public UsuarioDtoResposta salvarNovoUsuario(UsuarioDtoRequisicao usuarioDtoRequisicao) throws ValorDuplicado {
        // encriptando a senha do usuário.
        usuarioDtoRequisicao.setSenha(encoder.encode(usuarioDtoRequisicao.getSenha()));

        UsuarioEntidade procurarPorCpf = usuarioRepositorio.findUsuarioByCpf(
                cpf.formataCpf(usuarioDtoRequisicao.getCpf()));
        if (procurarPorCpf == null)
            return new UsuarioDtoResposta(usuarioRepositorio.save(usuarioDtoRequisicao.novoUsuario()));
        throw new ValorDuplicado("Sinto Muito... Cpf Já cadastrado!");
    }

    /**
     * @param usuario 
     * @param senha
     * @return
     */
    @Override
    public Boolean validarUsuario(String usuario, String senha) {
        // procurando o usuario pelo nome
        Optional<UsuarioEntidade> opUsuario = usuarioRepositorio.findByNome(usuario);
        // se não existir já retorna falso
        if (opUsuario.isEmpty()) return false;

        // se o usuario existir vou pegar o cadastro dele
        UsuarioEntidade usuarioEntidade = opUsuario.get();

        // agora validando a senha
        boolean valid = encoder.matches(senha, usuarioEntidade.getSenha());

        //retornando o resultado
        return valid ? true : false;
    }


    /**
     * @return
     */
    @Override
    public List<UsuarioDtoResposta> listarTodosUsuarios(int pagina, int itens) {

        if(itens > 50) itens = 50;

        PageRequest page = PageRequest.of(pagina, itens);

        Page<UsuarioEntidade> lista = usuarioRepositorio.findAll(page);

        return lista.stream().map(UsuarioDtoResposta::new).collect(Collectors.toList());

    }

}