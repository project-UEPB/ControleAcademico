package cafeteria.main.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cafeteria.main.builder.aluno.AlunoBuilder;
import cafeteria.main.domain.Aluno;
import cafeteria.main.exceptions.AlunoIsAlreadyExistsException;
import cafeteria.main.exceptions.AlunoNotFoundException;
import cafeteria.main.exceptions.ExistingAlunoSameMatriculaException;
import cafeteria.main.repository.AlunoRepository;
import cafeteria.main.services.AlunoService;
import javassist.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

@SpringBootTest
@ActiveProfiles("test")
class AlunoServiceTests {

   private static final long VALID_ALUNO_ID = 1;
   private static final long INVALID_ALUNO_ID = 10;

	@Mock
   private AlunoRepository alunoRepository;

   @InjectMocks
   private AlunoService alunoService;

   @BeforeEach
   public void initMocks() {
       MockitoAnnotations.initMocks(this);
   }

   @AfterEach
   void tearDown() {
   }

   @Nested
   class CreateTests {
       @Test
       void whenNewValidAlunoInformedThenItShouldBeCreated() throws ExistingAlunoSameMatriculaException {
           // given
           Aluno expectedSavedAluno = AlunoBuilder.builder().build().toAluno();

           // when
           when(alunoRepository.save(expectedSavedAluno))
               .thenReturn(expectedSavedAluno);

           //then
           Aluno createdAluno = alunoService.createAluno(expectedSavedAluno);

           assertThat(createdAluno.getId(), is(equalTo(expectedSavedAluno.getId())));
           assertThat(createdAluno.getName(), is(equalTo(expectedSavedAluno.getName())));
       }

       @Test
       void whenNotRegisteredAlunoIsGivenThenThrowAnException() {
           // given
           Aluno expectedFoundAluno = AlunoBuilder.builder().build().toAluno();

           // when
           when(alunoRepository.findById(expectedFoundAluno.getId()))
                   .thenReturn(Optional.empty());

           // then
           assertThrows(AlunoNotFoundException.class,
                   () -> alunoService.findById(expectedFoundAluno.getId()));
       }
   }

   @Nested
   class ReadTests {
       @Test
       void whenValidAlunoRegistrationIsGivenThenReturnAAluno() throws AlunoNotFoundException, NotFoundException {
           // given
           Aluno expectedFoundAluno = AlunoBuilder.builder().build().toAluno();

           // when
           when(alunoRepository.findById(expectedFoundAluno.getId()))
                   .thenReturn(Optional.of(expectedFoundAluno));

           // then
           Aluno foundAluno = alunoService.findById(expectedFoundAluno.getId());

           assertThat(foundAluno, is(equalTo(expectedFoundAluno)));
       }

       @Test
       void whenNotRegisteredAlunoIsGivenThenThrowAnException() {
           // given
           Aluno expectedFoundAluno = AlunoBuilder.builder().build().toAluno();

           // when
           when(alunoRepository.findByName(expectedFoundAluno.getName()))
                   .thenReturn(Optional.empty());

           // then
           assertThrows(AlunoNotFoundException.class,
                   () -> alunoService.findById(expectedFoundAluno.getId()));
       }
   }

   @Nested
   class UpdateTests {
       @Test
       void whenValidAlunoIsGivenThenReturnAnUpdatedAluno() throws AlunoNotFoundException {
           // given
           Aluno expectedFoundAluno = AlunoBuilder.builder().build().toAluno();
           Aluno expectedUpdatedAluno = AlunoBuilder.builder().name("Robert").build().toAluno();

           // when
           when(alunoRepository.findById(VALID_ALUNO_ID)).thenReturn(Optional.of(expectedFoundAluno));
           when(alunoRepository.save(expectedUpdatedAluno)).thenReturn(expectedUpdatedAluno);

           // then
           Aluno updatedAluno = alunoService.updateAluno(VALID_ALUNO_ID, expectedUpdatedAluno);

           assertThat(updatedAluno, is(equalTo(expectedUpdatedAluno)));
       }

       @Test
       void whenInvalidAlunoIsGivenThenThrowAnException() {
           // given
           Aluno expectedFoundAluno = AlunoBuilder.builder().build().toAluno();

           // when
           when(alunoRepository.findById(INVALID_ALUNO_ID)).thenReturn(Optional.empty());

           // then
           assertThrows(AlunoNotFoundException.class, () -> alunoService.updateAluno(INVALID_ALUNO_ID, expectedFoundAluno));
       }
   }

   @Nested
   class DeleteTests {
       @Test
       void whenExclusionIsCalledWithValidIdThenAAlunoShouldBeDeleted() throws AlunoNotFoundException {
           // given
           Aluno expectedDeletedAluno = AlunoBuilder.builder().build().toAluno();

           // when
           when(alunoRepository.findById(expectedDeletedAluno.getId()))
                   .thenReturn(Optional.of(expectedDeletedAluno));
           doNothing().when(alunoRepository).deleteById(expectedDeletedAluno.getId());

           // then
           alunoService.deleteAluno(expectedDeletedAluno.getId());

           verify(alunoRepository, times(1)).findById(expectedDeletedAluno.getId());
           verify(alunoRepository, times(1)).delete(expectedDeletedAluno);
       }
   }

}
