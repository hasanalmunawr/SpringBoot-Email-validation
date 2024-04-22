package hasanalmunawr.Dev.Emailvalidation.auth;

import hasanalmunawr.Dev.Emailvalidation.Entity.Token;
import hasanalmunawr.Dev.Emailvalidation.Entity.UserEntity;
import hasanalmunawr.Dev.Emailvalidation.dto.RequestRegis;
import hasanalmunawr.Dev.Emailvalidation.repostiory.RoleRepo;
import hasanalmunawr.Dev.Emailvalidation.repostiory.TokenRepo;
import hasanalmunawr.Dev.Emailvalidation.repostiory.UserRepo;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepo roleRepo;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final TokenRepo tokenRepo;

    private final EmailService emailService;

    @Value("${security.mailing.frontend.activation-url}")
    private String activationUrl;


    public void register(RequestRegis regis) throws MessagingException {
     var userRole = roleRepo
             .findByName("USER")
             .orElseThrow(() -> new RuntimeException("Role Not Found"));

     var user = UserEntity.builder()
             .email(regis.getEmail())
             .password(passwordEncoder.encode(regis.getPassword()))
             .firstName(regis.getFirstName())
             .lastName(regis.getLastName())
             .accountLocked(false)
             .enabled(false)
             .roles(List.of(userRole))
             .build();

     userRepo.save(user);

     sendValidationEmail(user);
    }

    private void sendValidationEmail(UserEntity user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
         emailService.sendEmail(
                 user.getEmail(),
                 user.getFullName(),
                 EmailTemplatename.ACTIVATE_ACCOUNT,
                 activationUrl,
                 newToken,
                 "Account activation"
         );
    }

    private String generateAndSaveActivationToken(UserEntity user) {
        String generateToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generateToken)
                .createdDate(LocalDateTime.now())
                .expiryDate(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepo.save(token);
        return generateToken;
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomChar = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomChar));
        }
        return codeBuilder.toString();
    }
}
