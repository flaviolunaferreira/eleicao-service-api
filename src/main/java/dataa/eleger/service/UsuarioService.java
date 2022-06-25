package dataa.eleger.service;

import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.modelos.usuario.UsuarioDtoRequisicao;
import dataa.eleger.modelos.usuario.UsuarioDtoResposta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {
    List<UsuarioDtoResposta> listarTodosUsuarios(int pagina, int itens);

    UsuarioDtoResposta salvarNovoUsuario(UsuarioDtoRequisicao usuarioDtoRequisicao);

    Boolean validarUsuario(String usuario, String senha);
}
