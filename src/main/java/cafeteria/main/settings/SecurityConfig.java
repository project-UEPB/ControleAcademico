package cafeteria.main.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import cafeteria.main.components.authentication.CustomAuthenticationProvider;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // var userDetailsService = new InMemoryUserDetailsManager();

        // var user = User.withUsername("joaozinho")
        // .password(bCryptPasswordEncoder.encode("0000"))
        // .authorities("user")
        // .build();

        // userDetailsService.createUser(user);

        // auth.userDetailsService(userDetailsService);

        auth.authenticationProvider(authenticationProvider);
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
