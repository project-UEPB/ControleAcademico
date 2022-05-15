package cafeteria.main.controller;

import java.util.List;
import java.util.Optional;

import cafeteria.main.repository.TurmaRepository;
import cafeteria.main.entity.Turma;
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

    @PostMapping
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
}