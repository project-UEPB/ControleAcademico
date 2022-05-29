package cafeteria.main.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cafeteria.main.dto.AlunoWithPIBICDTO;
import cafeteria.main.domain.Aluno;

public class AlunoWithPIBICMapper {

    public AlunoWithPIBICDTO convertToAlunoWithPIBICDTO(Aluno aluno) {
        AlunoWithPIBICDTO alunoDTO = modelMapper.map(aluno, AlunoWithPIBICDTO.class);

        return alunoDTO;
    }

    public Aluno convertFromAlunoWithPIBICDTO(AlunoWithPIBICDTO alunoDTO) {
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
    
        return aluno;
    }

}