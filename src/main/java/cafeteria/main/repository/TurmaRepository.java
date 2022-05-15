package cafeteria.main.repository;

import java.util.Optional;

import cafeteria.main.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Optional -> serve para previnir erros
    Optional<Turma> findByName(String name);
}