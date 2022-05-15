package cafeteria.main.repository;

import java.util.Optional;

import cafeteria.main.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Optional -> serve para previnir erros
    Optional<Aluno> findByName(String name);
}