package pl.brzezinski.redditclone.service;

import lombok.AllArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.brzezinski.redditclone.dto.RegisterRequest;
import pl.brzezinski.redditclone.model.NotificationEmail;
import pl.brzezinski.redditclone.model.User;
import pl.brzezinski.redditclone.model.VerificationToken;
import pl.brzezinski.redditclone.repository.UserRepository;
import pl.brzezinski.redditclone.repository.VerificationTokenRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signUp(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendEmail(new NotificationEmail("Please activate your Account.",
                user.getEmail(),
                "Thank you for signing up to Sptring Reddit, " +
                        "please click on the below url to activate your account: " +
                        "http://localhost:8080/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
}