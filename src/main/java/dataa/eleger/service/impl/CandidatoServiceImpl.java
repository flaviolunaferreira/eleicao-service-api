package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.Exceptions.ViolacaoDeIntegridade;
import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.modelos.candidatos.CandidatoDtoRequest;
import dataa.eleger.modelos.candidatos.CandidatoDtoResposta;
import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.repositorios.CandidatoRepositorio;
import dataa.eleger.repositorios.CargoRepositorio;
import dataa.eleger.service.CandidatoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepositorio candidatoRepositorio;
    private final CargoRepositorio cargoRepositorio;

        public CandidatoServiceImpl(CandidatoRepositorio candidatoRepositorio, CargoRepositorio cargoRepositorio, CargoRepositorio cargoRepositorio1) {
        this.candidatoRepositorio = candidatoRepositorio;
        this.cargoRepositorio = cargoRepositorio;
    }


    /**************************************************************************
     * Salva um novo candidato usando metodo do construtor para mapear a entidade
     * @param candidatoDtoRequest
     * @return ficha completa do candidato com detalhes do cargo almejado.
     *************************************************************************/
    @Override
    public CandidatoDtoResposta salvarNovoCandidato(CandidatoDtoRequest candidatoDtoRequest) throws ValorDuplicado {
        List<CandidatoEntidade> candidato = candidatoRepositorio.findByNomeCandidatoContainingIgnoreCase(candidatoDtoRequest.getNomeCandidato());
        CandidatoEntidade resultado = candidatoDtoRequest.newCandidato(cargoRepositorio);
        if (!candidato.isEmpty())
            throw( new ValorDuplicado("Sinto Muito... Já tenho um Candidato com esse nome."));
        return new CandidatoDtoResposta(candidatoRepositorio.save(resultado));
    }

    /**************************************************************************
     *  Lista todos os candidatos por página
     * @param pagina
     * @param itens
     * @return lista completa de todos os candidatos CandidatoDtoResponse
     *************************************************************************/
    @Override
    public List<CandidatoDtoResposta> listarCandidatos(int pagina, int itens) {
        if(itens > 50) itens = 50;
        PageRequest page = PageRequest.of(pagina, itens);
        Page<CandidatoEntidade> lista = candidatoRepositorio.findAll(page);
        return lista.stream().map(CandidatoDtoResposta::new).collect(Collectors.toList());
    }

    /**
     * Procura candidato que contenha o nome o parte dele
     * @param nome
     * @return Ficha completa do candidato.
     */
    @Override
    public List<CandidatoDtoResposta> procurarCandidatoPorNome(String nome) {
        List<CandidatoEntidade> lista = candidatoRepositorio.findByNomeCandidatoContainingIgnoreCase(nome);
        return lista.stream().map(CandidatoDtoResposta::new).collect(Collectors.toList());
    }

    /**
     * Busca candidato pelo número do id
     * @param id
     * @return ficha do candidato {CandidatoDtoResposta}
     */
    @Override
    public CandidatoDtoResposta procurarCandidatoPorId(Long id) {
        return new CandidatoDtoResposta(buscarPorId(id));
    }

    /**
     * Atauliza o cadastro de um cadidato
     * @param candidatoDtoRequest
     * @param id
     * @return ficha campleta do candidato e o cargo almejado.
     */
    @Override
    public CandidatoDtoResposta atualizaCandidato(CandidatoDtoRequest candidatoDtoRequest, Long id) {
        CandidatoEntidade candidato = buscarPorId(id);
        {
            candidato.setNomeCandidato(candidatoDtoRequest.getNomeCandidato());
            candidato.setCargoEntidade(cargoRepositorio.findById(candidatoDtoRequest.getIdCargo()).get());
            candidato.setFoto(candidatoDtoRequest.getFoto());
            candidato.setModificadoPor(System.getProperty("user.name"));
            candidato.setModificadoData(LocalDateTime.now());
        }
        return new CandidatoDtoResposta(candidatoRepositorio.save(candidato));
    }

    /**
     * Apaga definitivamente um candidato do banco de dados
     * @param id
     */
    @Override
    public void apagarCandidato(Long id) throws ViolacaoDeIntegridade{
        try {
            candidatoRepositorio.deleteById(id);
        } catch(Exception e) {
            throw new ViolacaoDeIntegridade("Não foi possível apagar o candidato, existem chaves de relacionamento pendentes. " + e);
        }
    }


    /**
     * Busca um cadidato por id e lança uma exceção se não encontrar
     * @param idCandidato
     * @return ficha do candidato {CandidatoEntidade}
     * @throws NaoEncontrado
     */
    @Override
    public CandidatoEntidade buscarPorId(Long idCandidato) throws NaoEncontrado {
        return candidatoRepositorio.findById(idCandidato).orElseThrow(() ->
            new NaoEncontrado("Desculpe mas não encontrei o candidato com o id: " + idCandidato));
    }
}
