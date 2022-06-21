package dataa.eleger.repositorios;

import dataa.eleger.entidades.CandidatoEntidade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatoRepositorio extends PagingAndSortingRepository<CandidatoEntidade, Long> {
    List<CandidatoEntidade> findByNomeCandidatoContainingIgnoreCase(String nome);
}
