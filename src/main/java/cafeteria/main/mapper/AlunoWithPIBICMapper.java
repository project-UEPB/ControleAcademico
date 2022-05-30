package cafeteria.main.mapper;

import org.modelmapper.ModelMapper;

import cafeteria.main.domain.Aluno;
import cafeteria.main.dto.AlunoWithPIBICDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class AlunoWithPIBICMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AlunoWithPIBICDTO convertToAlunoWithPIBICDTO(Aluno aluno) {
        AlunoWithPIBICDTO alunoDTO = modelMapper.map(aluno, AlunoWithPIBICDTO.class);

        return alunoDTO;
    }

    public Aluno convertFromAlunoWithPIBICDTO(AlunoWithPIBICDTO alunoDTO) {
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
    
        return aluno;
    }

}