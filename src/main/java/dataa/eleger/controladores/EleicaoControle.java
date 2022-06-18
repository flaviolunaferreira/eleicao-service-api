package dataa.eleger.controladores;

import dataa.eleger.Exceptions.IntegratyViolation;
import dataa.eleger.Exceptions.NotFound;
import dataa.eleger.dto.eleicao.FichaCompletaEleicaoDtoResposta;
import dataa.eleger.dto.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.dto.eleicao.EleicaoDtoResposta;
import dataa.eleger.entidades.EleicaoEntidade;
import dataa.eleger.service.EleicaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    /************************************************************************************
     * Salva um nova eleição no cadastro usando um objeto de transferencia de dados
     * @param {Dto} eleicaoDtoRequisicao
     * @return JSon com o cadastro realizado ou uma mensagem de erro!
     ***********************************************************************************/
    @ApiOperation(value = "Cadastra uma nova eleição", notes = "inclui uma nova eleição no banco de dados")
    @PostMapping("/")
    public ResponseEntity<EleicaoEntidade> novaEleicao(@Validated @RequestBody EleicaoDtoRequisicao eleicaoDtoRequisicao) {
        EleicaoEntidade result = eleicaoService.novaEleicao(eleicaoDtoRequisicao);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    /************************************************************************************
     * Lista um resumo de todas as eleições cadastradas por página
     * Obs... é necessário informar a quantidade de itens por página
     * @param pagina
     * @param itens
     * @return retorna as eleições gravadas por página
     ***********************************************************************************/
    @ApiOperation(value = "Listar todas", notes = "Lista todas as eleições cadastradas ordenada por id." +
            "Lembrando que a primeira página é a 0.")
    @GetMapping("/pagina/{pagina}/itens/{itens}")
    public ResponseEntity<List<EleicaoDtoResposta>> listarTodasEleicoes(@PathVariable int pagina,
                                                                        @PathVariable int itens) {
        List<EleicaoDtoResposta> result = eleicaoService.listarTodasEleicoes(pagina, itens);
        return ResponseEntity.ok().body(result);
    }


    /************************************************************************************
     * Procura a eleição pelo id
     * @param id
     * @return retorna a ficha completa do cadastro de eleicao
     * @throws NotFound
     ***********************************************************************************/
    @ApiOperation(value = "Lista eleição por Id", notes = "Ficha completa da eleição filtrada pelo id")
    @GetMapping("/{id}")
    public ResponseEntity<FichaCompletaEleicaoDtoResposta> procurarEleicaoPorId(@PathVariable Long id) {
        FichaCompletaEleicaoDtoResposta result = eleicaoService.procurarEleicaoPorId(id);
        return ResponseEntity.ok().body(result);
    }


    /************************************************************************************
     * Lista todas as eleições que contém o nome ou parte dele
     * @param nome
     * @return {dto} eleicaoDtoResponse
     ***********************************************************************************/
    @ApiOperation(value = "Lista todas as eleições por nome",
            notes = "Lista todas a eleições que contem um nome o parte dele")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<EleicaoDtoResposta>> listaEleicaoPorNome(@PathVariable String nome) {
        List<EleicaoDtoResposta> result = eleicaoService.listaEleicaoPorNome(nome);
        return ResponseEntity.ok().body(result);
    }


    /**********************************************************************************
     * Altera um registro eleição já gravado.
     * @param eleicaoDtoRequisicao
     * @param id
     * @return ficha completa da eleição alterada
     *********************************************************************************/
    @ApiOperation(value = "atualiza cadastro de eleiçoes.", notes = "Atualiza o cadastro da eleiçao.")
    @PutMapping("/{id}")
    public ResponseEntity<FichaCompletaEleicaoDtoResposta> atualizarEleicao(
            @Validated @RequestBody EleicaoDtoRequisicao eleicaoDtoRequisicao, @PathVariable Long id) {
        FichaCompletaEleicaoDtoResposta result = eleicaoService.atualizarEleicao(eleicaoDtoRequisicao, id);
        return ResponseEntity.ok().body(result);
    }

    /**********************************************************************************
     * Apaga um registro definitivamento do banco de dados
     * @param id
     * @return NotContent
     * @throws IntegratyViolation
     *********************************************************************************/
    public ResponseEntity<?> apagaEleicao(@PathVariable Long id) throws IntegratyViolation {

        // atrribuindo responsabilidade para outra classe
        eleicaoService.apagaEleicao(id);

        // retornando estatus que o registro foi apagado
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
