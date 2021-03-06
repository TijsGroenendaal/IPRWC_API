package groenendaal.tijs.iprwc_api.auth;

import groenendaal.tijs.iprwc_api.auth.model.UserLogin;
import groenendaal.tijs.iprwc_api.auth.model.UserLoginResult;
import groenendaal.tijs.iprwc_api.user.model.UserEntity;
import groenendaal.tijs.iprwc_api.user.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Value("${jwt.lifetime}")
    private Long lifetime;

    private static final int millisecondsToSeconds = 1000;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLogin userLogin) {
        UserLoginResult result = authService.loginResult(userLogin);
        HttpCookie cookie = authService.createCookie(result.getToken(), lifetime / millisecondsToSeconds);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(result.getUser());
    }

    @PostMapping("/logout")
    public ResponseEntity<UserLoginResult> logoutUser() {
        HttpCookie cookie = authService.createCookie("", 0);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(null);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponse> signInUser(
            @RequestBody UserEntity userEntity
            ) {
        UserLoginResult result = authService.signIn(userEntity);
        HttpCookie cookie = authService.createCookie(result.getToken(), lifetime / millisecondsToSeconds);
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(result.getUser());
    }

    @GetMapping("/user")
    public UserResponse getUser() {
        return authService.getUser();
    }
}
