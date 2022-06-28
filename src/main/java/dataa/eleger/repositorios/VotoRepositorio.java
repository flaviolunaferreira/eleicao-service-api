package dataa.eleger.repositorios;

import dataa.eleger.entidades.VotoEntidade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepositorio extends PagingAndSortingRepository<VotoEntidade, Long> {

    @Query(value = "SELECT * FROM voto_entidade v where v.id_eleicao = :eleicao and v.id_usuario = :usuario", nativeQuery = true)
    List<VotoEntidade> procurarPorEleicaoAndUsuario(Long eleicao, Long usuario);
}
