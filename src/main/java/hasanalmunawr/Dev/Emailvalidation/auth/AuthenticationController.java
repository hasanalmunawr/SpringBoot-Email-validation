package hasanalmunawr.Dev.Emailvalidation.auth;

import hasanalmunawr.Dev.Emailvalidation.dto.RequestRegis;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/regis")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RequestRegis regis) throws MessagingException {
        authenticationService.register(regis);
        return ResponseEntity.accepted().build();
    }
}
