package dataa.eleger.controladores;

import dataa.eleger.modelos.itensDeVoto.ItensDeVotoDtoRequisicao;
import dataa.eleger.modelos.itensDeVoto.ItensDeVotoDtoResposta;
import dataa.eleger.modelos.voto.VotoDtoRequisicao;
import dataa.eleger.modelos.voto.VotoDtoResposta;
import dataa.eleger.service.VotoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eleitor")
@PreAuthorize("hasRole('ELEITOR')")
public class VotoControle {

    private final VotoService votoService;

    public VotoControle(VotoService votoService) {
        this.votoService = votoService;
    }


    @ApiOperation(value = "Incluir novo voto.", notes = "inclui um novo voto para eleição.")
    @PostMapping("/")
    public ResponseEntity<VotoDtoResposta> novoVoto(@RequestBody VotoDtoRequisicao votoDtoRequisicao) {
        VotoDtoResposta resultado = votoService.novoVoto(votoDtoRequisicao);
        return ResponseEntity.ok().body(resultado);
    }

    @PostMapping("/voto/{voto}/candidato/{candidato}")
    public ResponseEntity<ItensDeVotoDtoResposta> salvaCandidatoVotado(
            @RequestBody ItensDeVotoDtoRequisicao itensDeVotoDtoRequisicao) {
        ItensDeVotoDtoResposta resultado = votoService.salvaCandidatoVotado(itensDeVotoDtoRequisicao);
        return ResponseEntity.ok().body(resultado);
    }


    @GetMapping("/")
    public ResponseEntity<List<VotoDtoResposta>> listarTodosOsVotos() {
        List<VotoDtoResposta> resultado = votoService.ListarTodosOsVotos();
        return ResponseEntity.ok().body(resultado);
    }

}
