package cafeteria.main.mapper;

import cafeteria.main.dto.AlunoWithPIBICDTO;
import cafeteria.main.dto.AlunoWithProjetoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import cafeteria.main.domain.Aluno;
import cafeteria.main.dto.AlunoDTO;

public class AlunoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public AlunoDTO convertToAlunoWithProjetoDTO(Aluno aluno) {
        AlunoDTO alunoDTO = modelMapper.map(aluno, AlunoDTO.class);

        return alunoDTO;
    }

    public Aluno convertFromAlunoWithProjetoDTO(AlunoWithProjetoDTO alunoWithProjetoDTO) {
        Aluno aluno = modelMapper.map(alunoWithProjetoDTO, Aluno.class);

        return aluno;
    }

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