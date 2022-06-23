package dataa.eleger.service;

import dataa.eleger.Exceptions.ViolacaoDeIntegridade;
import dataa.eleger.Exceptions.NaoEncontrado;
import dataa.eleger.Exceptions.ValorDuplicado;
import dataa.eleger.dto.cargo.CargoDtoRequisicao;
import dataa.eleger.dto.cargo.CargoDtoResposta;
import dataa.eleger.entidades.CargoEntidade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CargoService {
    CargoDtoResposta novoCargo(CargoDtoRequisicao cargoDtoRequisicao) throws ValorDuplicado;

    List<CargoDtoResposta> listarTodoosCargos(int pagina, int itens);

    List<CargoDtoResposta> pesquisarCargoPorNome(String nome);

    CargoDtoResposta pesquisarCargoPorId(Long id);

    CargoDtoResposta atualizarCargo(CargoDtoRequisicao cargoDtoRequisicao, Long id);

    void apagarCargo(Long id) throws ViolacaoDeIntegridade;

    CargoEntidade buscarPorId(Long id) throws NaoEncontrado;
}
