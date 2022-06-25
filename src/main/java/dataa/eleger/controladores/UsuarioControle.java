package dataa.eleger.controladores;

import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.entidades.UsuarioEntidade;
import dataa.eleger.modelos.usuario.UsuarioDtoRequisicao;
import dataa.eleger.modelos.usuario.UsuarioDtoResposta;
import dataa.eleger.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioControle {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioControle(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @ApiOperation(value = "Cadastra um novo Usuário", notes = "Cadastra um novo usuário no banco de dados.")
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDtoResposta> salvarNovoUsuario(
            @RequestBody @Validated UsuarioDtoRequisicao usuarioDtoRequisicao) {
        UsuarioDtoResposta resultado = usuarioService.salvarNovoUsuario(usuarioDtoRequisicao);
        return ResponseEntity.ok().body(resultado);
    }

    @ApiOperation(value = "Lista todos os usuário", notes = "Lista todos os usuários cadastrados.")
    @GetMapping("/pagina/{pagina}/itens/{itens}")
    public ResponseEntity<List<UsuarioDtoResposta>> listarTodosUsuarios(@PathVariable int pagina,
                                                                        @PathVariable int itens) {
        List<UsuarioDtoResposta> resultado = usuarioService.listarTodosUsuarios(pagina, itens);
        return ResponseEntity.ok().body(resultado);
    }

    /**
     * Atenção... na vida real as informações do usuário devem vir atravéz do body encriptografado...
     * evitando interceptação de dados.
     * @param usuario
     * @param senha
     * @return boolean
     */
    @ApiOperation(value = "Valida informações de login", notes = "Valida informações de login de um funcionário já cadastrado")
    @GetMapping("/login")
    public ResponseEntity<Boolean> validarUsuario(@RequestParam String usuario,
                                                  @RequestParam String senha) {
        // atribuindo responsabilidade para outra classe
        Boolean resultado = usuarioService.validarUsuario(usuario, senha);

        // montando a responsta
        HttpStatus status = (resultado) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;

        return  ResponseEntity.status(status).body(resultado);

    }


    @ApiOperation(value = "Adiciona permissão ao usuário", notes = "Inclui uma nova permissão ao usuário.")
    @PutMapping("/permissao/usuario/{usuario}/permissao/{permissao}")
    public ResponseEntity<UsuarioDtoResposta> incluirPermmissao(@PathVariable Long usuario,
                                                                @PathVariable Long permissao)
                                                                throws ValorDuplicado {
        UsuarioDtoResposta resultado = usuarioService.IncluiPermissao(usuario, permissao);
        return ResponseEntity.ok().body(resultado);
    }

}
