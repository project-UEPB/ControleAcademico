package cafeteria.main.components.authentication;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentucatuonInfo();
}
