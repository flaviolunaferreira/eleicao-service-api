package dataa.eleger.repositorios;

import dataa.eleger.entidades.ItensDoVoto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensDoVotoRepositorio extends PagingAndSortingRepository<ItensDoVoto, Long> {
}
