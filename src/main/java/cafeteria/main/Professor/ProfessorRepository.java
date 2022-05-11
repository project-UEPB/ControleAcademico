package cafeteria.main.Professor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Optional -> serve para previnir erros
    Optional<Professor> findByName(String name);
}