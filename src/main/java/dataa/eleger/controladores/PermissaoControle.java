package dataa.eleger.controladores;

import dataa.eleger.modelos.permissoes.PermissaoDtoRequest;
import dataa.eleger.modelos.permissoes.PermissaoDtoResposta;
import dataa.eleger.service.PermissaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/permissao")
//@PreAuthorize("hasRole('ADMINISTRADOR')")
public class PermissaoControle {

    private final PermissaoService permissaoService;

    @Autowired
    public PermissaoControle(PermissaoService permissaoService) {
        this.permissaoService = permissaoService;
    }


    @ApiOperation(value = "Cadastra uma Nova Permissão", notes = "Cadastra uma nova Permissão de acesso." +
            "As Opções autorizadas são: ELEITOR e ADMINISTRADOR")
    @PostMapping("/")
    public ResponseEntity<PermissaoDtoResposta> salvarNovaPermissao(@RequestBody
                                                                    @Validated
                                                                    PermissaoDtoRequest permissaoDtoRequest) {
        PermissaoDtoResposta resultado = permissaoService.salvarNovaPermissao(permissaoDtoRequest);
        return ResponseEntity.ok().body(resultado);
    }

    @GetMapping("/")
    public ResponseEntity<List<PermissaoDtoResposta>> listarTodasPermissoes() {
        List<PermissaoDtoResposta> resultado = permissaoService.listarTodasPermissoes();
        return ResponseEntity.ok().body(resultado);
    }

}
