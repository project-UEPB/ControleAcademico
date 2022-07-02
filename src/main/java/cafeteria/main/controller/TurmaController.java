package cafeteria.main.controller;

import java.util.List;
import java.util.Optional;

import cafeteria.main.repository.TurmaRepository;
import cafeteria.main.domain.Aluno;
import cafeteria.main.domain.Professor;
import cafeteria.main.domain.Turma;
import cafeteria.main.repository.AlunoRepository;
import cafeteria.main.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/turmas")
@Api(value = "Turma")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    @ApiOperation(value = "Busca uma lista de todas as turmas")
    public List<Turma> getTurmas() {
        return turmaRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca uma turma pelo seu id")
    public Optional<Turma> getTurmaById(@PathVariable Long id) {
        return turmaRepository.findById(id);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation(value = "Cria uma nova turma")
    public Turma createTurma(@RequestBody Turma turma) {
        return turmaRepository.save(turma);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza uma turma a partir do seu id")
    public Turma updateTurma(@PathVariable("id") Long id, @RequestBody Turma turma) {
        turma.setId(id);
        return turmaRepository.save(turma);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui uma turma a partir do seu id")
    public void deleteTurma(@PathVariable Long id) {
        turmaRepository.delete(turmaRepository.findById(id).get());
    }

    @PatchMapping("/{turmaId}/vincularProfessor/{idProfessor}")
    @ApiOperation(value = "Vincula um professor a uma turma a partir do seu identificador")
    public Turma vincularProfessor(@PathVariable("turmaId") Long turmaId, @PathVariable("idProfessor") Long idProfessor) {
        Professor professor = professorRepository.findById(idProfessor).get();
        Turma turma = turmaRepository.findById(turmaId).get();
        turma.setProfessor(professor);
        return turmaRepository.save(turma);
    }

    @PatchMapping("/{turmaId}/matricularAluno/{alunoId}")
    @ApiOperation(value = "Vincula um aluno a uma turma a partir do seu identificador")
    public Aluno patchAlunoAndProfessor(@PathVariable("turmaId") Long turmaId, @PathVariable("alunoId") Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).get();
        Turma turma = turmaRepository.findById(turmaId).get();
        aluno.setTurma(turma);
        return alunoRepository.save(aluno);
    }
}