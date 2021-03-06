package msngr_server.msngr_server.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import msngr_server.msngr_server.domain.User;
import msngr_server.msngr_server.dto.UserDto;
import msngr_server.msngr_server.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = { "http://localhost:8081" })
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
        service.generateUsers();
    }

    @GetMapping
    public List<UserDto> getUsers() {
        return service.getUsers();
    }

    @GetMapping("{name}")
    public ResponseEntity<UserDto> getUser(@PathVariable(name = "name") String name) {
        return service.getUser(name);
    }

    @GetMapping("{name}/{password}")
    public ResponseEntity<UserDto> verifyPassword(  @PathVariable(name = "name") String name,
                                                    @PathVariable(name = "password") String password) {
        return service.verifyPassword(name, password);
    }
    
    @PostMapping("{name}/{password}/{gender}/{email}")
    public ResponseEntity<UserDto> registration(@PathVariable(name = "name") String name,
                                                @PathVariable(name = "password") String password,
                                                @PathVariable(name = "gender") String gender,
                                                @PathVariable(name = "email") String email) {
        return service.registration(name, password, gender, email);
    }
}