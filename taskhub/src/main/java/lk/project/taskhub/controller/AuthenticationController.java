package lk.project.taskhub.controller;
import lk.project.taskhub.dto.request.LoginRequestDataDto;
import lk.project.taskhub.dto.request.RegistrationRequestDataDto;
import lk.project.taskhub.dto.response.LoginResponseDto;
import lk.project.taskhub.dto.response.VerifyUserDto;
import lk.project.taskhub.model.User;
import lk.project.taskhub.secuirty.jwt.JwtUtills;
import lk.project.taskhub.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final JwtUtills jwtUtills;

    public AuthenticationController(AuthenticationService authenticationService, JwtUtills jwtUtills) {
        this.authenticationService = authenticationService;
        this.jwtUtills = jwtUtills;
    }


    @PostMapping("/signUp")
    public ResponseEntity<User>registerUser(@RequestBody RegistrationRequestDataDto registrationRequestDataDto){
        User registeredUser = authenticationService.singUp(registrationRequestDataDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/logIn")
    public ResponseEntity<LoginResponseDto>logIn(@RequestBody LoginRequestDataDto loginDataDto){
        LoginResponseDto authenticatedUser = authenticationService.authenticate(loginDataDto);
        return ResponseEntity.ok(authenticatedUser);

    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        try {
            authenticationService.verifyUser(verifyUserDto);
            return ResponseEntity.ok("Account verified successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resend")
    public ResponseEntity<?> resendVerificationCode(@RequestParam("email") String email) {
        try {
            authenticationService.reSendVerificationCode(email);
            return ResponseEntity.ok("Verification code sent");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
