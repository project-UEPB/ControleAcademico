package cafeteria.main.controller;

import java.util.List;
import java.util.Optional;

import cafeteria.main.entity.Aluno;
import cafeteria.main.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/alunos")
@Api(value = "Aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    @ApiOperation(value = "Busca uma lista de alunos")
    public List<Aluno> getAlunos() {
        return alunoRepository.findAll();
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