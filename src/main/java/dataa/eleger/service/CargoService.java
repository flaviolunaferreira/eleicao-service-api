package dataa.eleger.service;

import dataa.eleger.Exceptions.IntegratyViolation;
import dataa.eleger.dto.cargo.CargoDtoRequisicao;
import dataa.eleger.dto.cargo.CargoDtoResposta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CargoService {
    CargoDtoResposta novoCargo(CargoDtoRequisicao cargoDtoRequisicao);

    List<CargoDtoResposta> listarTodoosCargos(int pagina, int itens);

    List<CargoDtoResposta> pesquisarCargoPorNome(String nome);

    CargoDtoResposta pesquisarCargoPorId(Long id);

    CargoDtoResposta atualizarCargo(CargoDtoRequisicao cargoDtoRequisicao, Long id);

    void apagarCargo(Long id) throws IntegratyViolation;
}
