package dataa.eleger.repositorios;

import dataa.eleger.entidades.CargoEntidade;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CargoRepositorio extends PagingAndSortingRepository<CargoEntidade, Long> {
    List<CargoEntidade> findByNomeCargoContainingIgnoreCase(String nome);
}
