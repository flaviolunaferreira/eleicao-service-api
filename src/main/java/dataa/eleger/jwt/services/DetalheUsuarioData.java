package dataa.eleger.jwt.services;

import dataa.eleger.entidades.UsuarioEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {

    private final Optional<UsuarioEntidade> usuario;

    @Autowired
    public DetalheUsuarioData(Optional<UsuarioEntidade> usuario) {
        this.usuario = usuario;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuario.orElse(new UsuarioEntidade()).getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.orElse(new UsuarioEntidade()).getNome();
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
