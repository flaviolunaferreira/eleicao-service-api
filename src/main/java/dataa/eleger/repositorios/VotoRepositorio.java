package dataa.eleger.repositorios;

import dataa.eleger.entidades.VotoEntidade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepositorio extends PagingAndSortingRepository<VotoEntidade, Long> {
}
