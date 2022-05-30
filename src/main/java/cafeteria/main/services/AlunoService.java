package cafeteria.main.services;

import java.util.List;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Aluno createAluno(Aluno aluno) throws ExistingAlunoSameMatriculaException {
        if (alunoRepository.findByName(aluno.getMatricula()).isPresent())
            throw new ExistingAlunoSameMatriculaException("Já existe um aluno com essa matricula!");
        return alunoRepository.save(aluno);
    }

    public Aluno updateAluno(Long id, Aluno aluno) {
        aluno.setId(id);
        return alunoRepository.save(aluno);
    }

    public List<Aluno> listAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno findById(Long id) throws NotFoundException {
        return alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Não existe um café com esse identificador!"));
    }

    public void deleteAluno(Long id) {
        Aluno alunoToDelete = alunoRepository.findById(id).get();
        alunoRepository.delete(alunoToDelete);
    }
}
