package cafeteria.main.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cafeteria.main.domain.Aluno;
import cafeteria.main.dto.AlunoDTO;

public class AlunoMapper {

    @Autowired
    private ModelMapper modelMapper;
    
    public AlunoDTO convertToAlunoDTO(Aluno aluno) {
        AlunoDTO alunoDTO = modelMapper.map(aluno, AlunoDTO.class);

        return alunoDTO;
    }

    public Aluno convertFromAlunoDTO(AlunoDTO alunoDTO) {
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);
    
        return aluno;
    }

}