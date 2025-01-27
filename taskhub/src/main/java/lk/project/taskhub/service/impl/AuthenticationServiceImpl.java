package lk.project.taskhub.service.impl;
import jakarta.mail.MessagingException;
import lk.project.taskhub.Exceptions.UserAlreadyExistsException;
import lk.project.taskhub.Exceptions.UserNotFoundException;
import lk.project.taskhub.Exceptions.UserNotVerifiedException;
import lk.project.taskhub.dto.request.LoginRequestDataDto;
import lk.project.taskhub.dto.request.RegistrationRequestDataDto;
import lk.project.taskhub.dto.response.LoginResponseDto;
import lk.project.taskhub.dto.response.VerifyUserDto;
import lk.project.taskhub.model.User;
import lk.project.taskhub.repository.UserRepository;
import lk.project.taskhub.secuirty.jwt.JwtUtills;
import lk.project.taskhub.service.AuthenticationService;
import lk.project.taskhub.service.EmailService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final JwtUtills jwtUtills;


    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager, EmailService emailService, JwtUtills jwtUtills) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
        this.jwtUtills = jwtUtills;
    }

    public User singUp(RegistrationRequestDataDto registrationRequestDataDto){
        User user = new User(
                registrationRequestDataDto.getFirstName(),
                registrationRequestDataDto.getLastName(),
                registrationRequestDataDto.getEmail(),
                passwordEncoder.encode(registrationRequestDataDto.getPassword()),
                registrationRequestDataDto.getRole(),
                generateVerificationCode(),
                LocalDateTime.now().plusMinutes(15),false

        );
        sendVerificationEmail(user);
        return userRepository.save(user);

    }

    public LoginResponseDto authenticate(LoginRequestDataDto loginDataDto){
        User user = userRepository.findByEmail(loginDataDto.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException("User not found!"));

        if(!user.isEnable()){
            throw new UserNotVerifiedException("This user not verified!"+loginDataDto.getEmail());
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDataDto.getEmail(), loginDataDto.getPassword())
        );
                     UserDetailsImpl userDetails = UserDetailsImpl.build(user);
                     String token = jwtUtills.generateToken(userDetails);
        return new LoginResponseDto(token,LocalDateTime.now().plus(Duration.ofMillis(jwtUtills.getExpirationTime())));

    }

    public void verifyUser(VerifyUserDto verifyUserDto){
        Optional<User> selectedUser = userRepository.findByEmail(verifyUserDto.getEmail());
        if(selectedUser.isPresent()){
            if(selectedUser.get().getVerificationCodeExpireAt().isBefore(LocalDateTime.now())){
                throw new RuntimeException("Verification code has expired!!");
            }
            if(selectedUser.get().getVerificationCode().equals(verifyUserDto.getVerificationCode())){
                selectedUser.get().setEnable(true);
                selectedUser.get().setVerificationCode(null);
                selectedUser.get().setVerificationCodeExpireAt(null);
                userRepository.save(selectedUser.get());
            }else{
                throw new RuntimeException("Invalid verification code");
            }
        }else{
            throw new UserNotFoundException("user not found!"+verifyUserDto.getEmail());
        }
    }

public void reSendVerificationCode(String email){
    Optional<User> selectedUser = userRepository.findByEmail(email);
    if(selectedUser.isPresent()){
        User user = selectedUser.get();
        if (user.isEnable()) {
            throw new UserAlreadyExistsException("Account already Exists!");
        }
        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpireAt(LocalDateTime.now().plusHours(1));
        sendVerificationEmail(user);
    }
}

    public void sendVerificationEmail(User user){
        String subject = "Account verification";
        String verificationCode = user.getVerificationCode();
        String message = String.format(
                "<html>" +
                        "<body>" +
                        "<h2>Dear %s,</h2>" +
                        "<p>Thank you for signing up with us! Please use the verification code below to verify your account:</p>" +
                        "<h3 style='color:blue;'>Verification Code: %s</h3>" +
                        "<p>If you did not request this, please ignore this email.</p>" +
                        "<br>" +
                        "<p>Best regards,</p>" +
                        "<p><strong>Task-hub Team</strong></p>" +
                        "<p><strong>S.Pasindu Upeka Silva</strong></p>"+
                        "</body>" +
                        "</html>",
                user.getFirstName(),
                verificationCode
        );
        try{
            emailService.settVerificationEmail(user.getEmail(),subject,message);
        }catch (MessagingException e){
            e.printStackTrace();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public String generateVerificationCode(){
        Random random = new Random();
        int code = random.nextInt(900000)+100000;
        return String.valueOf(code);


    }










}
