package groenendaal.tijs.iprwc_api.customer;

import groenendaal.tijs.iprwc_api.customer.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipalService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserPrincipalService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService.findOneByUsername(username);
        String[] authorities = { user.getRole().toString() };
        if (user.getId() != null) {
            authorities = new String[]{user.getRole().toString(), user.getId().toString()};
        }
        return createUserDetails(user.getUsername(), user.getPassword(), authorities);
    }

    public UserDetails createUserDetails(String username, String password, String[] authorities) {
        return User.builder()
                .username(username)
                .password(password)
                .authorities(authorities)
                .build();
    }
}
