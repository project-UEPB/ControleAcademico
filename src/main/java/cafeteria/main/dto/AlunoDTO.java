package cafeteria.main.dto;

import cafeteria.main.domain.Turma;
import cafeteria.main.enums.AlunoNivel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {
    private String name;
    private String matricula;
    private String email;
    private Turma turma;
    private AlunoNivel alunoNivel;
    private double CRA;
}