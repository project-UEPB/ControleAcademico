package cafeteria.main.builder.aluno;

import cafeteria.main.domain.Aluno;
import cafeteria.main.domain.Projeto;
import cafeteria.main.domain.Turma;
import cafeteria.main.enums.AlunoNivel;
import lombok.Builder;

@Builder
public class AlunoBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "John";

    @Builder.Default
    private String matricula = "100100";

    @Builder.Default
    private String email = "john@mail.com";

    @Builder.Default
    private Double CRA = 10.0;

    @Builder.Default
    private AlunoNivel alunoNivel = AlunoNivel.JUNIOR;

    @Builder.Default
    private Turma turma = new Turma();
    
    @Builder.Default
    private Projeto projeto = new Projeto();
    
   public Aluno toAluno() {
       return new Aluno(id, name, matricula, email, CRA, alunoNivel, turma, projeto);
   }
}