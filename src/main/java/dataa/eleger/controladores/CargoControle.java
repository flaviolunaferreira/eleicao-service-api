package dataa.eleger.controladores;

import dataa.eleger.Exceptions.ViolacaoDeIntegridade;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.modelos.cargo.CargoDtoRequisicao;
import dataa.eleger.modelos.cargo.CargoDtoResposta;
import dataa.eleger.service.CargoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cargo")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class CargoControle {

    private final CargoService cargoService;

    public CargoControle(CargoService cargoService) {
        this.cargoService = cargoService;
    }


    @ApiOperation(value = "Salva um novo cargo", notes = "Salva umm novo cargo no banco de dados"  )
    @PostMapping("/")
    public ResponseEntity<CargoDtoResposta> salvarNovoCargo(@RequestBody @Validated CargoDtoRequisicao cargoDtoRequisicao) throws ValorDuplicado {

        //Enviando requisição para outra classe resolver
        return new ResponseEntity<>(cargoService.novoCargo(cargoDtoRequisicao), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Pesquisa por nome", notes = "Pesquisar um cargo que contenha um nome ou parte dele.")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CargoDtoResposta>> pesquisarCargoPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(cargoService.pesquisarCargoPorNome(nome), HttpStatus.OK);
    }

    @ApiOperation(value = "Lista Todos.", notes = "Lista todos os cargos cadastrados." +
            " Lembrando que a contagem da página é iniciada pelo 0")
    @GetMapping("/pagina/{pagina}/itens/{itens}")
    public ResponseEntity<List<CargoDtoResposta>> listarTodosCargos(@PathVariable int pagina, @PathVariable int itens) {
        return new ResponseEntity<>(cargoService.listarTodoosCargos(pagina, itens), HttpStatus.OK);
    }

    @ApiOperation(value = "Pesquisa Cargo por Id", notes = "Pesquisa cargo pelo id")
    @GetMapping("{id}")
    public ResponseEntity<CargoDtoResposta> pesquisarCargoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(cargoService.pesquisarCargoPorId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Alterar o cargo", notes = "Alterar o nome do cargo.")
    @PutMapping("/{id}")
    public ResponseEntity<CargoDtoResposta> atualizarCargo(@RequestBody @Validated CargoDtoRequisicao cargoDtoRequisicao, @PathVariable Long id) {
        return new ResponseEntity<>(cargoService.atualizarCargo(cargoDtoRequisicao, id), HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Apagar um Cargo", notes = "Apaga um Cargo definitivamente do cadastro")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarCargo(@PathVariable Long id) throws ViolacaoDeIntegridade {
        cargoService.apagarCargo(id);
        return ResponseEntity.noContent().build();
    }

}
