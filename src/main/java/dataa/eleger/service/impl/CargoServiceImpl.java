package dataa.eleger.service.impl;

import dataa.eleger.Exceptions.IntegratyViolation;
import dataa.eleger.Exceptions.NotFound;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.dto.cargo.CargoDtoRequisicao;
import dataa.eleger.dto.cargo.CargoDtoResposta;
import dataa.eleger.entidades.CargoEntidade;
import dataa.eleger.repositorios.CargoRepositorio;
import dataa.eleger.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepositorio cargoRepositorio;

    @Autowired
    public CargoServiceImpl(CargoRepositorio cargoRepositorio) {
        this.cargoRepositorio = cargoRepositorio;
    }


    /**
     * @param cargoDtoRequisicao 
     * @return
     */
    @Override
    public CargoDtoResposta novoCargo(CargoDtoRequisicao cargoDtoRequisicao) throws ValorDuplicado {
        CargoEntidade procurarNome = cargoRepositorio.findByNomeCargo(cargoDtoRequisicao.getNomeCargo());
        if (procurarNome == null) return new CargoDtoResposta(cargoRepositorio.save(cargoDtoRequisicao.novoCargo()));
        throw new ValorDuplicado();
    }

    /**
     * @param pagina 
     * @param itens
     * @return
     */
    @Override
    public List<CargoDtoResposta> listarTodoosCargos(int pagina, int itens) {
        if ( itens > 50) itens = 50;
        PageRequest page = PageRequest.of(pagina, itens);
        Page<CargoEntidade> lista = cargoRepositorio.findAll(page);
        return lista.stream().map(CargoDtoResposta::new).collect(Collectors.toList());
    }

    /**
     * @param nome 
     * @return
     */
    @Override
    public List<CargoDtoResposta> pesquisarCargoPorNome(String nome) {
        List<CargoEntidade> lista = cargoRepositorio.findByNomeCargoContainingIgnoreCase(nome);
        return lista.stream().map(CargoDtoResposta::new).collect(Collectors.toList());
    }

    /**
     * Procurar Cargo por id...
     * @param id 
     * @return CargoDtoResposta
     */
    @Override
    public CargoDtoResposta pesquisarCargoPorId(Long id) {
        return new CargoDtoResposta(buscarPorId(id));
    }

    /**
     * @param cargoDtoRequisicao 
     * @param id
     * @return
     */
    @Override
    public CargoDtoResposta atualizarCargo(CargoDtoRequisicao cargoDtoRequisicao, Long id) {
        CargoEntidade resultado = buscarPorId(id);
        {
            resultado.setNomeCargo(cargoDtoRequisicao.getNomeCargo());
        }
        return new CargoDtoResposta(cargoRepositorio.save(resultado));
    }

    /**
     * @param id 
     * @return
     * @throws IntegratyViolation
     */
    @Override
    public void apagarCargo(Long id) throws IntegratyViolation {
        CargoEntidade resultado = buscarPorId(id);
        try {
            cargoRepositorio.deleteById(resultado.getIdCargo());
        } catch(Exception e) {
            throw new IntegratyViolation("Erro de integridade relacional -> ", e);
        }
    }


    public CargoEntidade buscarPorId(Long id) throws NotFound {
        return cargoRepositorio.findById(id)
                .orElseThrow(() -> new NotFound("Desculpe, mas não encontrei um cargo com id: " + id ));
    }

}
