package cafeteria.main.components.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import cafeteria.main.domain.User;
import cafeteria.main.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) 
            throw new UsernameNotFoundException(username + "not found in database!");

        return User.builder().username(username).password(user.getPassword()).authority(user.getAuthority()).build();
    }
}
