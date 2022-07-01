package cafeteria.main.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import cafeteria.main.components.authentication.CustomAuthenticationProvider;
import cafeteria.main.filters.AuthenticationFilter;
import cafeteria.main.filters.AuthorizationFilter;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private CustomAuthenticationProvider authenticationProvider;

    private static final String[] AUTH_WHITELIST = {
        "/v2/api-docs",
        "/signup",
        "/actuator/health",
        "/login/**",
        "/h2-console/**",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html/**",
        "/webjars/**"
    };
    
    private static final String[] AUTH_ADMIN = {
            "/turmas/**",
            "/professores/**",
            "/alunos/**",
            "/projeto/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.cors().and().csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/alunos/**").hasAnyAuthority("user", "admin")
            .antMatchers(AUTH_ADMIN).hasAuthority("admin")
            .antMatchers(AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated()
            .and().addFilter(new AuthenticationFilter(authenticationManager()))
            .addFilter(new AuthorizationFilter(authenticationManager()))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
