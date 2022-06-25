package dataa.eleger.jwt.services.lmpl;

import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.jwt.services.DetalheUsuarioData;
import dataa.eleger.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public DetalheUsuarioServiceImpl(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }


    @Override
    public UserDetails loadUserByUsername(String nomeDoUsuario) throws UsernameNotFoundException {
        Optional<UsuarioEntidade> usuario = usuarioRepositorio.findByNome(nomeDoUsuario);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário [ " + nomeDoUsuario + " ] não encontrado!");
        }
        return new DetalheUsuarioData(usuario);
    }
}
