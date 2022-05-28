package cafeteria.main.components.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentucatuonInfo() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
}
