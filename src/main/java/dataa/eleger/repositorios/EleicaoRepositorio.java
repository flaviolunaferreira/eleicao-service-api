package dataa.eleger.repositorios;

import dataa.eleger.entidades.EleicaoEntidade;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EleicaoRepositorio extends PagingAndSortingRepository<EleicaoEntidade, Long> {
}
