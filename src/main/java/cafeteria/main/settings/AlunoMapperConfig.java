package cafeteria.main.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cafeteria.main.mapper.AlunoMapper;

import org.modelmapper.ModelMapper;

@Configuration
public class AlunoMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AlunoMapper alunoMapper() {
        return new AlunoMapper();
    }


}