package lk.project.taskhub.controller;
import lk.project.taskhub.model.User;
import lk.project.taskhub.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>>getAllUsers(){
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/email")
    public ResponseEntity<User>getUserByEmail(@RequestParam("email") String email){
        Optional<User> user = userService.findByEmail(email);
        return ResponseEntity.ok(user.get());
    }

}
