package dataa.eleger.controladores;

import dataa.eleger.dto.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.dto.eleicao.EleicaoDtoResposta;
import dataa.eleger.entidades.EleicaoEntidade;
import dataa.eleger.service.EleicaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/eleicao")
public class EleicaoControle {

    //criando nova instancia do serviço
    private final EleicaoService eleicaoService;

    // injetando dependencias
    @Autowired
    public EleicaoControle(EleicaoService eleicaoService) {
        this.eleicaoService = eleicaoService;
    }


    /**
     * Salva um nova eleição no cadastro usando um objeto de transferencia de dados
     * @param {Dto} eleicaoDtoRequisicao
     * @return JSon com o cadastro realizado ou uma mensagem de erro!
     */
    @ApiOperation(value = "Cadastra uma nova eleição", notes = "inclui uma nova eleição no banco de dados")
    @PostMapping("/")
    public ResponseEntity<EleicaoEntidade> novaEleicao(@Validated @RequestBody EleicaoDtoRequisicao eleicaoDtoRequisicao) {
        EleicaoEntidade result = eleicaoService.novaEleicao(eleicaoDtoRequisicao);
        return ResponseEntity.ok().body(result);
    }


    /**
     * Lista todas a eleições cadastradas por ordem de data
     * @return {Dto} eleicaoDtoReesponse
     */
    @ApiOperation(value = "Listar todas", notes = "Lista todas as eleições cadastradas ordenada por data")
    @GetMapping("/")
    public ResponseEntity<List<EleicaoDtoResposta>> listarTodasEleicoes() {
        List<EleicaoDtoResposta> result = eleicaoService.listarTodasEleicoes();
        return ResponseEntity.ok().body(result);
    }


    public ResponseEntity<Optional<EleicaoDtoResposta>> procurarEleicaoPorId(@PathVariable Integer id) {
        Optional<EleicaoDtoResposta> result = eleicaoService.procurarEleicaoPorId(id);
        return ResponseEntity.ok().body(result);
    }

}
