package cafeteria.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cafeteria.main.domain.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Optional -> serve para previnir erros
    Optional<Aluno> findByName(String name);
}