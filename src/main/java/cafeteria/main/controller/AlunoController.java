package cafeteria.main.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cafeteria.main.domain.Aluno;
import cafeteria.main.dto.AlunoDTO;
import cafeteria.main.dto.AlunoWithPIBICDTO;
import cafeteria.main.dto.AlunoWithProjetoDTO;
import cafeteria.main.dto.GenericResponseErrorDTO;
import cafeteria.main.exceptions.ExistingAlunoSameMatriculaException;
import cafeteria.main.mapper.AlunoMapper;


import cafeteria.main.services.AlunoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/alunos")
@Api(value = "Aluno")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AlunoController {

    private AlunoService alunoService;
    private AlunoMapper alunoMapper;

    @GetMapping
    @ApiOperation(value = "Busca uma lista de alunos")
    public List<AlunoDTO> getAlunos() {
        List<Aluno> alunos = alunoService.listAllAlunos();
        return alunos.stream()
                .map(alunoMapper::convertToAlunoDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um aluno pelo seu id")
    public ResponseEntity<?> getAlunoById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(alunoMapper.convertToAlunoDTO(alunoService.findById(id)), HttpStatus.OK);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PostMapping
    @ApiOperation(value = "Cria um novo aluno")
    public ResponseEntity<?> createAluno(@RequestBody AlunoDTO alunoDTO) {
        try {
            Aluno aluno = alunoMapper.convertFromAlunoDTO(alunoDTO);
            return new ResponseEntity<>(alunoService.createAluno(aluno), HttpStatus.CREATED);
        } catch (ExistingAlunoSameMatriculaException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

    @PatchMapping
    @ApiOperation(value = "Atualiza o CRA de um aluno concedendo um bônus")
    public ResponseEntity<?> updatePIBICAluno(@RequestBody AlunoWithPIBICDTO alunoDTO) {
        try {
            Aluno aluno = alunoMapper.convertFromAlunoWithPIBICDTO(alunoDTO);
            Aluno alunoUpdated = alunoService.updateBonusAluno(aluno, alunoDTO.getCRA());
            return new ResponseEntity<>(alunoMapper.convertToAlunoDTO(alunoUpdated), HttpStatus.NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
        }
    }

//    @PatchMapping("{alunoId}/updateLevel/{projetoId}")
//    @ApiOperation(value = "Atribui um nivel no projeto ao aluno")
//    public ResponseEntity<?> updateLevelAluno(@RequestBody AlunoWithProjetoDTO alunoDTO) {
//        try {
//            Aluno aluno = alunoMapper.convertFromAlunoWithProjetoDTO(alunoDTO);
//            Aluno alunoUpdated = alunoService.assignStudentLevel(aluno, alunoDTO.getAlunoNivel());
//            return new ResponseEntity<>(alunoMapper.convertToAlunoDTO(alunoUpdated), HttpStatus.NO_CONTENT);
//        } catch (NotFoundException e) {
//            return ResponseEntity.badRequest().body(new GenericResponseErrorDTO(e.getMessage()));
//        }
//    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um aluno a partir do seu id")
    public Aluno updateAluno(@PathVariable("id") Long id, @RequestBody Aluno aluno) {
        aluno.setId(id);
        return alunoService.updateAluno(id, aluno);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um aluno a partir do seu id")
    public void deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
    }
}