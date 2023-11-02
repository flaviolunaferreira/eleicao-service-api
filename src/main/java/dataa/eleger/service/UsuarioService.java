package dataa.eleger.service;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.modelos.usuario.UsuarioDtoRequisicao;
import dataa.eleger.modelos.usuario.UsuarioDtoResposta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {
    List<UsuarioDtoResposta> listarTodosUsuarios(int pagina, int itens);

    UsuarioDtoResposta salvarNovoUsuario(UsuarioDtoRequisicao usuarioDtoRequisicao);

    Boolean validarUsuario(String usuario, String senha);

//    UsuarioDtoResposta IncluiPermissao(Long usuario, Long permissao) throws ValorDuplicado;

    UsuarioEntidade buscarUsuarioPorId(Long usuario) throws NaoEncontrado;
}
