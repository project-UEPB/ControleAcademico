package cafeteria.main.mapper;

import cafeteria.main.dto.AlunoWithPIBICDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cafeteria.main.domain.Aluno;
import cafeteria.main.dto.AlunoDTO;

public class AlunoMapper {

    @Autowired
    private ModelMapper modelMapper;

    // conversion methods between Coffee and CoffeeDTO ...
    
    public AlunoDTO convertToAlunoWithPIBICDTO(Aluno aluno) {
        AlunoDTO alunoDTO = modelMapper.map(aluno, AlunoDTO.class);

        return alunoDTO;
    }

    public Aluno convertFromAlunoWithPIBICDTO(AlunoWithPIBICDTO alunoWithPIBICDTO) {
        Aluno aluno = modelMapper.map(alunoWithPIBICDTO, Aluno.class);
    
        return aluno;
    }

    public AlunoDTO convertToAlunoDTO(Aluno aluno) {
        AlunoDTO alunoDTO = modelMapper.map(aluno, AlunoDTO.class);

        return alunoDTO;
    }

    public Aluno convertFromAlunoDTO(AlunoDTO alunoDTO) {
        Aluno aluno = modelMapper.map(alunoDTO, Aluno.class);

        return aluno;
    }

}