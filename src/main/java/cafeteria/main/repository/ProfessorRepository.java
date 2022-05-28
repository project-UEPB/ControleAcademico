package cafeteria.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cafeteria.main.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    // Optional -> serve para previnir erros
    Optional<Professor> findByName(String name);
}