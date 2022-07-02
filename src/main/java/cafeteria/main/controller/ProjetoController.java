package cafeteria.main.controller;

import cafeteria.main.domain.Aluno;
import cafeteria.main.domain.Professor;
import cafeteria.main.domain.Projeto;
import cafeteria.main.repository.AlunoRepository;
import cafeteria.main.repository.ProfessorRepository;
import cafeteria.main.repository.ProjetoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projetos")
@Api(value = "Projeto")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    @ApiOperation(value = "Busca uma lista de todos os projetos")
    public List<Projeto> getProjetos() {
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um projeto pelo seu id")
    public Optional<Projeto> getProjetoById(@PathVariable Long id) {
        return projetoRepository.findById(id);
    }

    @PostMapping(consumes = {"*/*"})
    @ApiOperation(value = "Cria um novo projeto")
    public Projeto createProjeto(@RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um projeto a partir do seu id")
    public Projeto updateProjeto(@PathVariable("id") Long id, @RequestBody Projeto projeto) {
        projeto.setId(id);
        return projetoRepository.save(projeto);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Exclui um projeto a partir do seu id")
    public void deleteRepository(@PathVariable Long id) {
        projetoRepository.delete(projetoRepository.findById(id).get());
    }

    @PatchMapping("/{projetoId}/vincularProfessor/{professorId")
    @ApiOperation(value = "Vincula um professor a um projeto a partir do seu id")
    public Projeto vincularProjeto(@PathVariable("projetoId") Long projetoId, @PathVariable("professorId") Long professorId){
        Professor professor = professorRepository.findById(professorId).get();
        Projeto projeto = projetoRepository.findById(professorId).get();
        projeto.setProfessor(professor);
        return projetoRepository.save(projeto);
    }

    @PatchMapping("/{projetoId}/matricularAluno/{alunoId}")
    @ApiOperation(value = "Vincula um aluno a um projeto a partir do seu identificador")
    public Aluno patchAlunoAndProfessor(@PathVariable("projetoId") Long projetoId, @PathVariable("alunoId") Long alunoId) {
        Aluno aluno = alunoRepository.findById(alunoId).get();
        Projeto projeto = projetoRepository.findById(projetoId).get();
        aluno.setProjeto(projeto);
        return alunoRepository.save(aluno);
    }

}
