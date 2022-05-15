package cafeteria.main.repository;

import java.util.Optional;

import cafeteria.main.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Optional -> serve para previnir erros
    Optional<Professor> findByName(String name);
}