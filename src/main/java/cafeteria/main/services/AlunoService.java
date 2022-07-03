package cafeteria.main.services;

import java.util.List;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cafeteria.main.exceptions.AlunoIsAlreadyExistsException;
import cafeteria.main.exceptions.AlunoNotFoundException;
import cafeteria.main.exceptions.ExistingAlunoSameMatriculaException;
import cafeteria.main.domain.Aluno;
import cafeteria.main.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno updateBonusAluno(Aluno aluno, double bonus) throws NotFoundException {
        if (!alunoRepository.findByName(aluno.getMatricula()).isPresent())
            throw new NotFoundException("Não existe um Aluno com essa Matricula!");

        Aluno alunoEntity = alunoRepository.findByName(aluno.getMatricula()).get();

        double discountFinal = alunoEntity.getCRA() + bonus;
        alunoEntity.setCRA(alunoEntity.getCRA() - discountFinal);

        return alunoRepository.save(alunoEntity);
    }

    public Aluno createAluno(Aluno aluno) throws AlunoIsAlreadyExistsException {
        if (alunoRepository.findByName(aluno.getName()).isPresent())
            throw new AlunoIsAlreadyExistsException("Já existe um aluno igual!");
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno aluno) {
        alunoRepository.findById(aluno.getId()).orElseThrow(() -> new AlunoNotFoundException("Não existe um aluno com esse identificador!"));
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) throws AlunoNotFoundException {
        return alunoRepository.findById(id).orElseThrow(() -> new AlunoNotFoundException("Não existe um aluno com esse identificador!"));
    }

    public void deleteAluno(Long id) {
        Aluno alunoToDelete = alunoRepository.findById(id).get();
        alunoRepository.delete(alunoToDelete);
    }
}
