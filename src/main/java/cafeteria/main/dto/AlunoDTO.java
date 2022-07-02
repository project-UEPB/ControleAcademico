package cafeteria.main.dto;

import cafeteria.main.domain.Projeto;
import cafeteria.main.domain.Turma;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoDTO {
    private String name;
    private String matricula;
    private String email;
    private Turma turma;
    // private Projeto projeto;

    private double CRA;
}