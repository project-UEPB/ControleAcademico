package cafeteria.main.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.List;
import java.util.Optional;

import cafeteria.main.builder.aluno.AlunoBuilder;
import cafeteria.main.builder.aluno.AlunoDTOBuilder;
import cafeteria.main.domain.Aluno;
import cafeteria.main.dto.AlunoDTO;
import cafeteria.main.exceptions.AlunoIsAlreadyExistsException;
import cafeteria.main.exceptions.AlunoNotFoundException;
import cafeteria.main.exceptions.ExistingAlunoSameMatriculaException;
import cafeteria.main.mapper.AlunoMapper;
import cafeteria.main.services.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@ActiveProfiles("test")
public class AlunoControllerTest {

   private static final String ALUNO_API_URL_PATH = "/alunos";
   private static final Long VALID_ALUNO_ID = 1L;
   private static final Long INVALID_ALUNO_ID = 10L;

   private MockMvc mockMvc;

   private ObjectMapper objectMapper = new ObjectMapper();

   @Mock
   private AlunoService alunoService;

   @Mock
   private ModelMapper modelMapper;

   @Mock
   private AlunoMapper alunoMapper;

   @InjectMocks
   private AlunoController alunoController;

   @BeforeEach
   void setUp() {
       mockMvc = MockMvcBuilders.standaloneSetup(alunoController).build();
   }

   @Nested
   class GetMethodsTest {

       private AlunoDTO alunoDTO;
       private Aluno aluno;

       @BeforeEach
       void setUp() {
           alunoDTO = AlunoDTOBuilder.builder().build().toAlunoDTO();
           aluno = AlunoBuilder.builder().build().toAluno();
       }

       @Test
       @DisplayName("Call with get All")
       void whenGETIsCalledThenOkStatusIsReturned() throws Exception {
           when(alunoMapper.convertToAlunoDTO(eq(aluno))).thenReturn(alunoDTO);
           when(alunoService.listAllAlunos()).thenReturn(List.of(aluno));

           mockMvc.perform(get(ALUNO_API_URL_PATH)
                   .contentType(MediaType.APPLICATION_JSON)
                   .characterEncoding("UTF-8"))
                   .andDo(print())
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.[0].name", is(aluno.getName())));
       }

        @Test
        @DisplayName("Call with get All")
        void whenGETIsCalledThenStatusIsReturned() throws Exception {
            when(alunoMapper.convertToAlunoDTO(eq(aluno))).thenReturn(alunoDTO);
            when(alunoService.listAllAlunos()).thenReturn(List.of(aluno));
 
            mockMvc.perform(get(ALUNO_API_URL_PATH + "/nothing")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8"))
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }
   }

   @Nested
   class PostMethodsTest {

       private AlunoDTO expectedAlunoDTO;
       private Aluno expectedAluno;

       @BeforeEach
       void setUp() {
           expectedAlunoDTO = AlunoDTOBuilder.builder().build().toAlunoDTO();
           expectedAluno = AlunoBuilder.builder().build().toAluno();
       }

       @Test
       @DisplayName("Call with Valid Fields")
       void whenPOSTIsCalledWithValidFieldsThenShouldObjectCreated() throws Exception {
           String createAlunoRequest = objectMapper.writeValueAsString(
                   objectMapper.readValue(
                           new File("src/test/java/cafeteria/resources/createAlunoValidRequest.json"),
                           AlunoDTO.class));

           when(alunoMapper.convertFromAlunoDTO(eq(expectedAlunoDTO))).thenReturn(expectedAluno);
           when(alunoService.createAluno(eq(expectedAluno))).thenReturn(expectedAluno);
       }

        @Test
        void whenPOSTIsCalledWithInvalidEndpointThenBadRequestStatusIsReturned() throws Exception {
            String createAlunoRequest = objectMapper.writeValueAsString(
                    objectMapper.readValue(
                            new File("src/test/java/cafeteria/resources/createAlunoInvalidRequest.json"),
                            AlunoDTO.class));

            mockMvc.perform(post(ALUNO_API_URL_PATH + "/z")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(String.valueOf(createAlunoRequest)))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
        }
   }

   @Nested
   class PutMethodsTest {
       @Test
       @DisplayName("Call with Valid Fields")
       void whenPUTIsCalledWithValidDataThenOkStatusIsReturned() throws Exception {
            String updateAlunoValidRequest = objectMapper.writeValueAsString(
                    objectMapper.readValue(
                            new File("src/test/java/cafeteria/resources/updateAlunoValidRequest.json"),
                            AlunoDTO.class));
            AlunoDTO alunoDTO = AlunoDTOBuilder.builder().name("John new").build().toAlunoDTO();
            Aluno aluno = AlunoBuilder.builder().name("John new").build().toAluno();

            when(alunoMapper.convertFromAlunoDTO(eq(alunoDTO))).thenReturn(aluno);
            when(alunoService.updateAluno(eq(VALID_ALUNO_ID), eq(aluno))).thenReturn(aluno);

            mockMvc.perform(put(ALUNO_API_URL_PATH + "/" + VALID_ALUNO_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(String.valueOf(updateAlunoValidRequest)))
                .andDo(print())
                .andExpect(status().isOk());
       }
       
       @Test
       @DisplayName("Call with Valid Fields")
       void whenPUTIsCalledWithInvalidEndpointThenOkStatusIsReturned() throws Exception {
           String updateAlunoValidRequest = objectMapper.writeValueAsString(
                   objectMapper.readValue(
                           new File("src/test/java/cafeteria/resources/updateAlunoValidRequest.json"),
                           AlunoDTO.class));
           AlunoDTO alunoDTO = AlunoDTOBuilder.builder().name("John new").build().toAlunoDTO();
           Aluno aluno = AlunoBuilder.builder().name("John new").build().toAluno();

            when(alunoMapper.convertFromAlunoDTO(eq(alunoDTO))).thenReturn(aluno);
            when(alunoService.updateAluno(eq(VALID_ALUNO_ID), eq(aluno))).thenReturn(aluno);

            mockMvc.perform(put(ALUNO_API_URL_PATH + "/z")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(String.valueOf(updateAlunoValidRequest)))
                    .andDo(print())
                    .andExpect(status().is4xxClientError());
       }
   }

   @Nested
   class DeleteMethodsTest {
       @Test
       @DisplayName("Call with valid Id")
       void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
           doNothing().when(alunoService).deleteAluno(VALID_ALUNO_ID);

           mockMvc.perform(delete(ALUNO_API_URL_PATH + "/" + VALID_ALUNO_ID)
                   .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isOk());
       }
       
       @Test
       @DisplayName("Call with valid Id")
       void whenDELETEIsCalledWithInvalidIdThenNoContentStatusIsReturned() throws Exception {
           doNothing().when(alunoService).deleteAluno(INVALID_ALUNO_ID);

           mockMvc.perform(delete(ALUNO_API_URL_PATH + "/z" + INVALID_ALUNO_ID)
                   .contentType(MediaType.APPLICATION_JSON))
                   .andExpect(status().isBadRequest());
       }
   }
}
