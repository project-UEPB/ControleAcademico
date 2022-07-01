package cafeteria.main.repository;

import cafeteria.main.domain.Professor;
import cafeteria.main.domain.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Optional<Projeto> findByName(String name);

    Projeto save(Professor professor);
}
