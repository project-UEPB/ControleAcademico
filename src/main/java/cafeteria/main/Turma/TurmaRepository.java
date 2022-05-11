package cafeteria.main.Turma;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Optional -> serve para previnir erros
    Optional<Turma> findByName(String name);
}