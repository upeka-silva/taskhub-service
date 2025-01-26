package lk.project.taskhub.service.impl;

import lk.project.taskhub.Exceptions.UserAlreadyExistsException;
import lk.project.taskhub.Exceptions.UserNotFoundException;
import lk.project.taskhub.dto.request.RegistrationRequestDataDto;
import lk.project.taskhub.model.User;
import lk.project.taskhub.repository.UserRepository;
import lk.project.taskhub.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()){
            return users;
        }
        return List.of();
    }

    @Override
    public User registerUser(RegistrationRequestDataDto registrationRequestDto) {
        Optional<User> sendUser = userRepository.findByEmail(registrationRequestDto.getEmail());

              if (sendUser.isPresent()){
                  throw new UserAlreadyExistsException("user already exists!");
              }

              User newUser = new User();
              newUser.setFirstName(registrationRequestDto.getFirstName());
              newUser.setLastName(registrationRequestDto.getLastName());
              newUser.setEmail(registrationRequestDto.getEmail());
              newUser.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));
              newUser.setRole(registrationRequestDto.getRole());
              return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> selectedUser = userRepository.findByEmail(email);
        if(selectedUser.isPresent()){
            return  selectedUser;
        }
        throw new UserNotFoundException("User not found!"+email);
    }


}
