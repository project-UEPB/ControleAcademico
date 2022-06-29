package cafeteria.main.repository;

import cafeteria.main.domain.Projeto;
import cafeteria.main.domain.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    Optional<Turma> findByName(String name);
}
