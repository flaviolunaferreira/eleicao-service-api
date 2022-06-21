package dataa.eleger.controladores;

import dataa.eleger.Exceptions.IntegratyViolation;
import dataa.eleger.dto.candidatos.CandidatoDtoRequest;
import dataa.eleger.dto.candidatos.CandidatoDtoResposta;
import dataa.eleger.service.CandidatoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vi/candidato")
public class CandidatoControle {

    private final CandidatoService candidatoService;

    @Autowired
    public CandidatoControle(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    /**************************************************************************
     * Cadastra um novo candidato na tabela
     * @param candidatoDtoRequest
     * @return Ficha do candidato salvo.
     *************************************************************************/
    @ApiOperation(value = "Salva um nome candidato", notes = "Salva um  nome candidato no bando de dados.")
    @PostMapping("/")
    public ResponseEntity<CandidatoDtoResposta> salvarNovoCandidato(
            @RequestBody CandidatoDtoRequest candidatoDtoRequest) {
        return new ResponseEntity<>(candidatoService.salvarNovoCandidato(candidatoDtoRequest), HttpStatus.CREATED);
    }

    /**************************************************************************
     * Lista todos os candidatos de forma paginada
     * @param pagina
     * @param itens
     * @return lista de candidatos separados por página.
     *************************************************************************/
    @ApiOperation(value = "Lista todos os candidatos", notes = "Lista todos os candidatos de forma paginada.")
    @GetMapping("/pagina/{pagina}/itens/{itens}")
    public ResponseEntity<List<CandidatoDtoResposta>> listarCandidatos(@PathVariable int pagina, @PathVariable int itens) {
        return new ResponseEntity<>(candidatoService.listarCandidatos(pagina, itens), HttpStatus.OK);
    }

    /**************************************************************************
     * Lista de candidatos que contenha a variável fornecida no path.
     * @param nome
     * @return lista completa dos candidatos {CandidatosDtoResposta}
     *************************************************************************/
    @ApiOperation(value = "Lista candidatos por nome",
            notes = "lista todos os candidatos que o nome contenha a variável fornecida.")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<CandidatoDtoResposta>> procurarCandidatoPorNome(@PathVariable String nome) {
        return new ResponseEntity<>(candidatoService.procurarCandidatoPorNome(nome), HttpStatus.OK);
    }

    /**************************************************************************
     * Procura candidato por id.
     * @param {Long} id
     * @return ficha do candidato {CandidatoDtoResposta}
     *************************************************************************/
    @ApiOperation(value = "Procura candidato por Id", notes = "Procura candidato por id.")
    @GetMapping("/{id}")
    public ResponseEntity<CandidatoDtoResposta> procurarCandidatoPorId(@PathVariable Long id) {
        return new ResponseEntity<>(candidatoService.procurarCandidatoPorId(id), HttpStatus.OK);
    }

    /**************************************************************************
     * Atualiza o cadastro do candidato
     * @param candidatoDtoRequest
     * @param id
     * @return ficha modificada do candidato {CandidatoDtoResposta}
     *************************************************************************/
    @ApiOperation(value = "Atualiza o candidato.", notes = "Atualiza o cadástro do candidato.")
    @PutMapping("/{id}")
    public ResponseEntity<CandidatoDtoResposta> atualizaCandidato(
            @RequestBody @Valid CandidatoDtoRequest candidatoDtoRequest,
            @PathVariable Long id) {
        return new ResponseEntity<>(candidatoService.atualizaCandidato(candidatoDtoRequest, id), HttpStatus.ACCEPTED);
    }

    /**************************************************************************
     * Apaga um candidato definitivamente do bando de dados
     * @param id
     * @return Not_Content
     * @throws IntegratyViolation
     *************************************************************************/
    @ApiOperation(value = "Apaga cadastro do candidato.",
            notes = "Apaga o cadastro de um candidado que não tenha tabelas filhas relacionadas.")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> apagarCandidato(@PathVariable Long id) throws IntegratyViolation {
        return ResponseEntity.noContent().build();
    }


}
