package dataa.eleger.repositorios;

import dataa.eleger.entidades.PermissoesEntidade;
import dataa.eleger.uteis.PermissoesEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissaoRepositorio extends JpaRepository<PermissoesEntidade, Long> {
    PermissoesEntidade findByPermissoesEnum(PermissoesEnum permissoesEnum);

    List<PermissoesEntidade> findByUsuarioEntidade(Long usuario);
}
