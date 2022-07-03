package cafeteria.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cafeteria.main.domain.Aluno;
import cafeteria.main.domain.Professor;
import io.jsonwebtoken.io.Encoder;

@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
