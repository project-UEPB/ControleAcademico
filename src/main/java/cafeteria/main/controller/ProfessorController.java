package cafeteria.main.controller;

import java.util.List;
import java.util.Optional;

import cafeteria.main.domain.Professor;
import cafeteria.main.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/professores")
@Api(value = "Professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    @ApiOperation(value = "Busca uma lista de todos os professores")
    public List<Professor> getProfessors() {
        return professorRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um professor pelo seu id")
    public Optional<Professor> getProfessorById(@PathVariable Long id) {
        return professorRepository.findById(id);
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo professor")
    public Professor createProfessor(@RequestBody Professor professor) {
        return professorRepository.save(professor);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um professor a partir do seu id")
    public Professor updateProfessor(@PathVariable("id") Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return professorRepository.save(professor);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um professor a partir do seu id")
    public void deleteProfessor(@PathVariable Long id) {
        professorRepository.delete(professorRepository.findById(id).get());
    }
}