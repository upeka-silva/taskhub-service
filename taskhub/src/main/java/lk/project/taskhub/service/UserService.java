package lk.project.taskhub.service;

import lk.project.taskhub.dto.RegistrationRequestDto;
import lk.project.taskhub.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();
    User registerUser(RegistrationRequestDto registrationRequestDto);
    Optional<User>findByEmail(String email);



}
