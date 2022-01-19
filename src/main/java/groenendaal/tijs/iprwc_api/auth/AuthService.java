package groenendaal.tijs.iprwc_api.auth;

import groenendaal.tijs.iprwc_api.auth.model.UserLogin;
import groenendaal.tijs.iprwc_api.auth.model.UserLoginResult;
import groenendaal.tijs.iprwc_api.exception.WrongCredentialsException;
import groenendaal.tijs.iprwc_api.helper.JwtHelper;
import groenendaal.tijs.iprwc_api.user.UserPrincipalService;
import groenendaal.tijs.iprwc_api.user.UserService;
import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import groenendaal.tijs.iprwc_api.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Value("${jwt.cookie-name}")
    private String cookieName;

    @Value("${jwt.cookie-secure}")
    private Boolean secureCookie;

    @Value("${jwt.cookie.restrict-site}")
    private Boolean sameSiteStrict;

    private final UserPrincipalService userPrincipalService;
    private final Argon2PasswordEncoder argon2PasswordEncoder;
    private final JwtHelper jwtHelper;
    private final UserService userService;

    @Autowired
    public AuthService(
            UserPrincipalService userPrincipalService,
            Argon2PasswordEncoder argon2PasswordEncoder,
            JwtHelper jwtHelper,
            UserService userService
    ) {
        this.userPrincipalService = userPrincipalService;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
        this.jwtHelper = jwtHelper;
        this.userService = userService;
    }

    public UserLoginResult loginResult(UserLogin userLogin) {
        UserDetails userDetails;

        try {
            userDetails = userPrincipalService.loadUserByUsername(userLogin.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new WrongCredentialsException();
        }

        if(!argon2PasswordEncoder.matches(userLogin.getPassword(), userDetails.getPassword())) {
            throw new WrongCredentialsException();
        }

        return createLoginResult(userDetails);
    }

    public UserLoginResult createLoginResult(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        claims.put(HttpSecurityConfig.AUTHORITIES_CLAIM_NAME, authorities);

        String jwt = jwtHelper.createJwtForClaims(userDetails.getUsername(), claims);

        UserEntity userEntity = this.userService.findOneByUsername(userDetails.getUsername());

        return new UserLoginResult(jwt, new UserResponse(userEntity));
    }

    public HttpCookie createCookie(String value, long maxAge) {
        return ResponseCookie.from(cookieName, value)
                .path("/")
                .httpOnly(true)
                .maxAge(maxAge)
                .secure(secureCookie)
                .sameSite(sameSiteStrict ? "Strict" : "Lax")
                .build();
    }

    public UserLoginResult signIn(UserEntity newUser) {
        newUser.setPassword(argon2PasswordEncoder.encode(newUser.getPassword()));
        userService.createUser(newUser);

        UserDetails userDetails = userPrincipalService.createUserDetails(
                newUser.getUsername(),
                newUser.getPassword(),
                new String[]{ newUser.getRole().toString(), newUser.getId().toString() });

        return createLoginResult(userDetails);
    }

    public UserResponse getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = this.userService.findOneByUsername(username);
        return new UserResponse(user);
    }

}
