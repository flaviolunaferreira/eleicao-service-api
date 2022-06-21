package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.IntegratyViolation;
import dataa.eleger.Exceptions.NotFound;
import dataa.eleger.dto.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.dto.eleicao.EleicaoDtoResposta;
import dataa.eleger.dto.eleicao.FichaCompletaEleicaoDtoResposta;
import dataa.eleger.entidades.EleicaoEntidade;
import dataa.eleger.repositorios.EleicaoRepositorio;
import dataa.eleger.service.EleicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EleicaoServiceImpl implements EleicaoService {

    /**************************************************************************
     * Classe destinada as regras de nogócio da aplicação
     * Esse não é o padrao de código mas neste exemplo não estou usando o mapper, tentando diminuir
     * a carga cognitiva... usando o construtor da classe para mapear as informmações.
     *************************************************************************/


    // criando uma nova instancia do repositorio
    private final EleicaoRepositorio eleicaoRepositorio;

    // injetando dependencia
    @Autowired
    public EleicaoServiceImpl(EleicaoRepositorio eleicaoRepositorio) {
        this.eleicaoRepositorio = eleicaoRepositorio;
    }


    /**************************************************************************
     * Metodo para salvar um novo cadástro de eleição
     * @param eleicaoDtoRequisicao
     * @return entidade salva.
     *************************************************************************/
    @Override
    public EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao) {

        // usando a classe Dto para retornar as informações que preciso para o cadástro
        EleicaoEntidade result = eleicaoDtoRequisicao.novaEleicao();

        //salvando e retornando valor salvo
        return eleicaoRepositorio.save(result);
    }


    /**************************************************************************
     * Consulta todas as eleições por página
     * @param pagina -> Número da página da pesquisa
     * @param itens -> quantidade de itens por página
     * @return lista paginada das eleições
     *************************************************************************/
    @Override
    public List<EleicaoDtoResposta> listarTodasEleicoes(int pagina, int itens) {

        // se o front mandar mais de 50 itens por página vou definir o valor máximo para 50
        // evitando um volume alto de deley de tráfigo.
        if (itens > 50) itens = 50;

        // definindo a pagina e quantidade de itens da resposta
        PageRequest page = PageRequest.of(pagina, itens);

        // listando bruta da entidade já paginada
        Page<EleicaoEntidade> result = eleicaoRepositorio.findAll(page);

        // mapeando informações usando o método de classe
        return result.stream().map(EleicaoDtoResposta::new).collect(Collectors.toList());

    }


    /**************************************************************************
     * pesquisando eleição por id
     * @param id
     * @return lista comleta da eleição por id
     * @throws NotFound
     *************************************************************************/
    @Override
    public FichaCompletaEleicaoDtoResposta procurarEleicaoPorId(Long id) {

        // usando metodo da classe dto para mapear a resposta
        return new FichaCompletaEleicaoDtoResposta(buscaPorId(id));
    }


    /**************************************************************************
     * Lista todas as eleições que o nome contenham o parametro fornecido
     * @param nome
     * @return lista de eleições filtradas pelo parametro.
     *************************************************************************/
    @Override
    public List<EleicaoDtoResposta> listaEleicaoPorNome(String nome) {

        // usando metodo do repositorio para perquisar o nome da eleição que contenhha a variável;
        List<EleicaoEntidade> list = eleicaoRepositorio.findByNomeContainingIgnoreCase(nome);

        // usando o método da classe para retornar a resposta mapeada.
        return list.stream().map(EleicaoDtoResposta::new).collect(Collectors.toList());
    }

    /**
     * Altera os dados cadastrados das eleições procurando pelo id
     * @apiNote O método spring update é o mesmo do save.
     * @param eleicaoDtoRequisicao
     * @param id
     * @return fichha completa da eleição cadastrada
     */
    @Override
    public FichaCompletaEleicaoDtoResposta atualizarEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao, Long id) {
        // procurando eleição
       EleicaoEntidade resposta = buscaPorId(id);
       // atribuindo novos valores -> as chaves são desnecessária mas fica mais visíveel.
        {
            resposta.setNome(eleicaoDtoRequisicao.getNome());
            resposta.setInicio(eleicaoDtoRequisicao.getInicio());
            resposta.setFim(eleicaoDtoRequisicao.getFim());
            resposta.setModificadoPor(System.getProperty("user.name"));
            resposta.setModificadoData(LocalDateTime.now());
        }
        EleicaoEntidade result = eleicaoRepositorio.save(resposta);
        return new FichaCompletaEleicaoDtoResposta(result);
    }


    /**************************************************************************
     * Apaga um registro definiticamente do banco de dados, se nao tiver relacionamentos pendentes
     * @param id
     * @throws IntegratyViolation
     *************************************************************************/
    @Override
    public void apagaEleicao(Long id) throws IntegratyViolation {

        // buscando registro
        EleicaoEntidade result = buscaPorId(id);

        // se der tudo certo ele apaga
        try {
            eleicaoRepositorio.deleteById(id);
        // caso tenha algum registro filho ele lança uma excceção
        } catch (IntegratyViolation e) {
            throw new IntegratyViolation("Erro de integridade relacional -> ", e);
        }

    }


    /**************************************************************************
     * criando método buscar por id para usar em outros serviços
     *************************************************************************/
    private EleicaoEntidade buscaPorId(Long id) throws NotFound{

        // mandando uma resposta mais amigável para o front
        return eleicaoRepositorio.findById(id).orElseThrow(
                () -> new NotFound("Desculpe, mas não conseguir encontrar a eleição com o id: " + id)
        );
    }

}
