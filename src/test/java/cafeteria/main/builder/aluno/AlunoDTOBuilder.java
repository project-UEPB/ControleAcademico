package cafeteria.main.builder.aluno;

import cafeteria.main.domain.Turma;
import cafeteria.main.dto.AlunoDTO;

import lombok.Builder;

@Builder
public class AlunoDTOBuilder {   
    
    @Builder.Default
    private String name = "John";

    @Builder.Default
    private String matricula = "100100";

    @Builder.Default
    private String email = "john@mail.com";

    @Builder.Default
    private Turma turma = new Turma();

    @Builder.Default
    private Double CRA = 10.0;
    
//    public AlunoDTO toAlunoDTO() {
//        return new AlunoDTO(name, matricula, email, turma, CRA);
//    }
}