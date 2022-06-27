package dataa.eleger.configuracoes;

import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.entidades.UsuarioEntidade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PrincipalUsuarioConfig implements UserDetails {

    private String nome;
    private String senha;
    private Collection<? extends GrantedAuthority> autorizacoes = new ArrayList<>();

    public PrincipalUsuarioConfig(UsuarioEntidade usuario) {

        System.out.println(usuario);

        List<SimpleGrantedAuthority> permissoes = usuario.getPermissoesEntidade().stream().map(item -> {
            return new SimpleGrantedAuthority("ROLE_".concat(item.getPermissoesEnum().getDescricao()));
            }).collect(Collectors.toList());


        if (permissoes.isEmpty()) return;

        this.nome = usuario.getNome();
        this.senha = usuario.getSenha();
        this.autorizacoes = permissoes;
    }

    public static PrincipalUsuarioConfig criar(UsuarioEntidade usuarioEntidade) {
        return new PrincipalUsuarioConfig(usuarioEntidade);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autorizacoes;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
