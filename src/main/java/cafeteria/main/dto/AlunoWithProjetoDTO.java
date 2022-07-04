package cafeteria.main.dto;

import cafeteria.main.enums.AlunoNivel;
import lombok.Data;

@Data
public class AlunoWithProjetoDTO {
    private String matricula;
    private AlunoNivel alunoNivel;
}
