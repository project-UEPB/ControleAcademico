package cafeteria.main.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
    public UserDetailsService userDetailsService() {
		var userDetailsService = new InMemoryUserDetailsManager();

		var user = User.withUsername("joaozinho")
		.password("123456")
		.authorities("user")
		.build();

		userDetailsService.createUser(user);

		return userDetailsService;
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
	 	http.httpBasic();
		// acesso ao Banco de Dados em memória (H2) - origem de endereço diferente (CORS)
        http.cors().and().csrf().disable();
        http.headers().frameOptions().sameOrigin(); 
		
		http.authorizeRequests().anyRequest().authenticated(); // ou .permitAll() para anular a necessidade de autenticação
	}
}
