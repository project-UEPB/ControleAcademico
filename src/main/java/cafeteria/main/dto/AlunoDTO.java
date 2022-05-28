package cafeteria.main.dto;

import cafeteria.main.entity.Turma;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlunoDTO {
    private String name;
    private String matricula;
    private String email;
    private Turma turma;

    private double nota;
}