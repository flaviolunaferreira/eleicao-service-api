package dataa.eleger.jwt.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.jwt.services.DetalheUsuarioData;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAutenticarFiltro extends UsernamePasswordAuthenticationFilter {

    // essa senhha não deveria estar aqui.
    public static final String TOKEN_SENHA = "efcf6478-6f47-4b14-a592-c1214792fcd2";

    private final AuthenticationManager authenticationManager;

    public JWTAutenticarFiltro(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioEntidade usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioEntidade.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getNome(),
                    usuario.getSenha(),
                    new ArrayList<>()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar o usuário", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        DetalheUsuarioData usuarioData = (DetalheUsuarioData) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject(usuarioData.getUsername())
                .withExpiresAt((new Date(System.currentTimeMillis() + 60000)))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));
    }
}
