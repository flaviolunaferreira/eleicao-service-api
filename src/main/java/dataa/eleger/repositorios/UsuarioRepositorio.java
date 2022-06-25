package dataa.eleger.repositorios;

import dataa.eleger.entidades.UsuarioEntidade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends PagingAndSortingRepository<UsuarioEntidade, Long> {
    UsuarioEntidade findUsuarioByCpf(String cpf);

    Optional<UsuarioEntidade> findByNome(String usuario);
}
