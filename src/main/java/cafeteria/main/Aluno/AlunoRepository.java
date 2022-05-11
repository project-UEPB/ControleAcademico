package cafeteria.main.Aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AlunoRepository extends JpaRepository<Aluno, Long> {
    // Optional -> serve para previnir erros
    Optional<Aluno> findByName(String name);
}