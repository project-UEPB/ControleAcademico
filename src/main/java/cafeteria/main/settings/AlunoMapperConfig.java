package cafeteria.main.settings;

import cafeteria.main.mapper.AlunoWithPIBICMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cafeteria.main.mapper.AlunoMapper;
import cafeteria.main.mapper.UserMapper;

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

    @Bean
    public AlunoWithPIBICMapper alunoWithPIBICMapper() {
        return new AlunoWithPIBICMapper();
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }


}