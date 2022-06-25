package dataa.eleger.configuracoes;

import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetalhesDoServicoUsuario implements UserDetailsService {

    private final UsuarioRepositorio usuarioRepositorio;

    @Autowired
    public DetalhesDoServicoUsuario(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String nomeDoUsuario) throws UsernameNotFoundException {
        UsuarioEntidade seExiste = usuarioRepositorio.findByNome(nomeDoUsuario)
                .orElseThrow(() -> new NaoEncontrado("Usuário não encontrado!"));
        return PrincipalUsuarioConfig.criar(seExiste);
    }
}
