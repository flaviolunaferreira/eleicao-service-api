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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EleicaoServiceImpl implements EleicaoService {

    /**
     * Classe destinada as regras de nogócio da aplicação
     * Esse não é o padrao de código mas neste exemplo não estou usando o mapper, tentando diminuir
     * a carga cognitiva... usando o construtor da classe para mapear as informmações.
     */

    // criando uma nova instancia do repositorio
    private final EleicaoRepositorio eleicaoRepositorio;

    // injetando dependencia
    @Autowired
    public EleicaoServiceImpl(EleicaoRepositorio eleicaoRepositorio) {
        this.eleicaoRepositorio = eleicaoRepositorio;
    }


    /**
     * Metodo para salvar um novo cadástro de eleição
     * @param eleicaoDtoRequisicao
     * @return entidade salva.
     */
    @Override
    public EleicaoEntidade novaEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao) {

        // usando a classe Dto para retornar as informações que preciso para o cadástro
        EleicaoEntidade result = eleicaoDtoRequisicao.novaEleicao();

        //salvando e retornando valor salvo
        return eleicaoRepositorio.save(result);
    }

    /**
     * Consulta todas as eleições por página
     * @param pagina -> Número da página da pesquisa
     * @param itens -> quantidade de itens por página
     * @return lista paginada das eleições
     */
    @Override
    public List<EleicaoDtoResposta> listarTodasEleicoes(int pagina, int itens) {

        // se o front mandar mais de 50 itens vou definir o valor máximo para 50
        if (itens > 50) itens = 50;

        // definindo a pagina e quantidade de itens da resposta
        PageRequest page = PageRequest.of(pagina, itens);

        // listando bruta da entidade já paginada
        Page<EleicaoEntidade> result = eleicaoRepositorio.findAll(page);

        // mapeando informações usando o método de classe
        List<EleicaoDtoResposta> resposta = result.stream().map(EleicaoDtoResposta::new).collect(Collectors.toList());

        return resposta;
    }


    @Override
    public FichaCompletaEleicaoDtoResposta procurarEleicaoPorId(Long id) throws NotFound {
        EleicaoEntidade result = eleicaoRepositorio.findById(id).orElseThrow(
                () -> new NotFound("Desculpe, mas não conseguir encontrar a eleição com o id: " + id)
        );
       return new FichaCompletaEleicaoDtoResposta(result);

    }

    @Override
    public List<EleicaoDtoResposta> listaEleicaoPorNome(String nome) {
        return null;
    }

    @Override
    public FichaCompletaEleicaoDtoResposta atualizarrEleicao(EleicaoDtoRequisicao eleicaoDtoRequisicao, Long id) {
        return null;
    }

    @Override
    public void apagaEleicao(Long id) throws IntegratyViolation {

    }
}
