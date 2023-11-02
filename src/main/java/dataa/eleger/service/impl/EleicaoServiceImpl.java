package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.ViolacaoDeIntegridade;
import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.Exceptions.ViolacaoDeRegra;
import dataa.eleger.modelos.eleicao.EleicaoDtoRequisicao;
import dataa.eleger.modelos.eleicao.EleicaoDtoResposta;
import dataa.eleger.modelos.eleicao.FichaCompletaEleicaoDtoResposta;
import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.entidades.EleicaoEntidade;
import dataa.eleger.repositorios.EleicaoRepositorio;
import dataa.eleger.service.CandidatoService;
import dataa.eleger.service.EleicaoService;
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
    private final CandidatoService candidatoService;

    // injetando dependencia
    public EleicaoServiceImpl(EleicaoRepositorio eleicaoRepositorio, CandidatoService candidatoService) {
        this.eleicaoRepositorio = eleicaoRepositorio;
        this.candidatoService = candidatoService;
    }


    /**************************************************************************
     * Metodo para salvar um novo cadástro de eleição
     * @param eleicaoDtoRequisicao
     * @return entidade salva.
     *************************************************************************/
    @Override
    public EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao) throws ViolacaoDeRegra {

        // validando que a data de inicio e antes da data final
        if ( eleicaoDtoRequisicao.getInicio().isBefore(eleicaoDtoRequisicao.getFim())) {

            // usando a classe Dto para retornar as informações que preciso para o cadástro
            EleicaoEntidade result = eleicaoDtoRequisicao.novaEleicao();

            //salvando e retornando valor salvo
            return eleicaoRepositorio.save(result);

        }
        throw( new ViolacaoDeRegra("Nao consigo salvar esta eleiçao... por favor verifique a data Digitada!"));
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
        // evitando um volume alto de delay de tráfigo.
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
     * @throws NaoEncontrado
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
       // atribuindo novos valores -> as chaves são desnecessária mas fica mais visível.
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
     * Apaga um registro definitivamente do banco de dados, se nao tiver relacionamentos pendentes.
     * @param id
     * @throws ViolacaoDeIntegridade
     *************************************************************************/
    @Override
    public void apagaEleicao(Long id) throws ViolacaoDeIntegridade {

        // buscando registro
        buscaPorId(id);

        // se der tudo certo ele apaga
        try {
            eleicaoRepositorio.deleteById(id);
        // caso tenha algum registro filho ele lança uma excceção
        } catch (ViolacaoDeIntegridade e) {
            throw new ViolacaoDeIntegridade("Erro de integridade relacional -> ", e);
        }

    }

    /**
     * Inclui um nova candidato no cadastro de eleiçao
     * @param eleicao 
     * @param candidato
     * @return fichha completa da eleiçao {FichaCompletaEleicaoDtoResposta}
     */
    @Override
    public FichaCompletaEleicaoDtoResposta cadastraCandidato(Long eleicao, Long candidato) throws ValorDuplicado {

        EleicaoEntidade eleicaoEntidade = buscaPorId(eleicao);
        CandidatoEntidade candidatoEntidade = candidatoService.buscarPorId(candidato);
        List<CandidatoEntidade> candidatosCadastrados = eleicaoEntidade.getCandidato();

        List<CandidatoEntidade> seExisteCandidato = candidatosCadastrados
        .stream().filter(item -> item.getNomeCandidato()
                            .contentEquals(candidatoEntidade.getNomeCandidato()))
                    .collect(Collectors.toList());

            if (seExisteCandidato.isEmpty()) {
                candidatosCadastrados.add(candidatoEntidade);
                eleicaoEntidade.setCandidato(candidatosCadastrados);
                return new FichaCompletaEleicaoDtoResposta(eleicaoRepositorio.save(eleicaoEntidade));
            }

            throw new ValorDuplicado("Sinto Muito... Já tenho o nome deste Candidato cadastrado.");
    }


    /**************************************************************************
     * criando método buscar por id para usar em outros serviços
     *************************************************************************/
    public EleicaoEntidade buscaPorId(Long id) throws NaoEncontrado {

        // mandando uma resposta mais amigável para o front
        return eleicaoRepositorio.findById(id).orElseThrow(
                () -> new NaoEncontrado("Desculpe, mas não conseguir encontrar a eleição com o id: " + id)
        );
    }

}
