package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.IntegratyViolation;
import dataa.eleger.Exceptions.NotFound;
import dataa.eleger.dto.candidatos.CandidatoDtoRequest;
import dataa.eleger.dto.candidatos.CandidatoDtoResposta;
import dataa.eleger.entidades.CandidatoEntidade;
import dataa.eleger.repositorios.CandidatoRepositorio;
import dataa.eleger.service.CandidatoService;
import dataa.eleger.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepositorio candidatoRepositorio;
    private final CargoService cargoService;

    @Autowired
    public CandidatoServiceImpl(CandidatoRepositorio candidatoRepositorio, CargoService cargoService) {
        this.candidatoRepositorio = candidatoRepositorio;
        this.cargoService = cargoService;
    }


    /**************************************************************************
     * Salva um novo candidato usando metodo do construtor para mapear a entidade
     * @param candidatoDtoRequest
     * @return ficha completa do candidato com detalhes do cargo almejado.
     *************************************************************************/
    @Override
    public CandidatoDtoResposta salvarNovoCandidato(CandidatoDtoRequest candidatoDtoRequest) {
        CandidatoEntidade resultado = candidatoDtoRequest.newCandidato(candidatoDtoRequest, cargoService);
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
            candidato.setCargoEntidade(cargoService.buscarPorId(candidatoDtoRequest.getIdCargo()));
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
    public void apagarCandidato(Long id) {
        CandidatoEntidade resultado = buscarPorId(id);
        try {
            candidatoRepositorio.delete(resultado);
        } catch(Exception e) {
            throw new IntegratyViolation("Não foi possível apagar o candidato, existem chaves de relacionamento pendentes. " + e);
        }
    }


    /**
     * Busca um cadidato por id e lança uma exceção se não encontrar
     * @param idCandidato
     * @return ficha do candidato {CandidatoEntidade}
     * @throws NotFound
     */
    @Override
    public CandidatoEntidade buscarPorId(Long idCandidato) throws NotFound {
        return candidatoRepositorio.findById(idCandidato).orElseThrow(() ->
            new NotFound("Desculpe mas não encontrei o candidato com o id: " + idCandidato));
    }
}