package dataa.eleger.repositorios;

import dataa.eleger.entidades.EleicaoEntidade;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EleicaoRepositorio extends PagingAndSortingRepository<EleicaoEntidade, Long> {
    List<EleicaoEntidade> findByNomeContainingIgnoreCase(String nome);
}
