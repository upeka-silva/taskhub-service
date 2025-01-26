package lk.project.taskhub.service;


import lk.project.taskhub.dto.request.LoginRequestDataDto;
import lk.project.taskhub.dto.request.RegistrationRequestDataDto;
import lk.project.taskhub.dto.response.LoginResponseDto;
import lk.project.taskhub.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    User singUp(RegistrationRequestDataDto registrationRequestDataDto);

    LoginResponseDto authenticate(LoginRequestDataDto loginDataDto);






}
