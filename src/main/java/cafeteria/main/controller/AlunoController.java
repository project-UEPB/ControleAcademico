package cafeteria.main.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cafeteria.main.domain.Aluno;
import cafeteria.main.domain.Turma;
import cafeteria.main.dto.AlunoDTO;
import cafeteria.main.mapper.AlunoMapper;
import cafeteria.main.repository.AlunoRepository;
import cafeteria.main.repository.TurmaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/alunos")
@Api(value = "Aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {

    private AlunoRepository alunoRepository;
    private AlunoMapper alunoMapper;

    @GetMapping
    @ApiOperation(value = "Busca uma lista de alunos")
    public List<AlunoDTO> getAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream()
        .map(alunoMapper::convertToAlunoDTO)
        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um aluno pelo seu id")
    public Optional<Aluno> getAlunoById(@PathVariable Long id) {
        return alunoRepository.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo aluno")
    public Aluno createAluno(@RequestBody Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um aluno a partir do seu id")
    public Aluno updateAluno(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um aluno a partir do seu id")
    public void deleteAluno(@PathVariable Long id) {
        alunoRepository.delete(alunoRepository.findById(id).get());
    }
}