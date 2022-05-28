package cafeteria.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cafeteria.main.domain.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Optional -> serve para previnir erros
    Optional<Turma> findByName(String name);
}