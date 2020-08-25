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
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("{name}")
    public User getUser(@PathVariable(name = "name") String name) {
        return service.getUser(name);
    }

    @GetMapping("{name}/{password}")
    public ResponseEntity<User> verifyPassword(@PathVariable(name = "name") String name, @PathVariable(name = "password") String password) {
        return service.verifyPassword(name, password);
    }
/*
    @PostMapping("{name}/{password}")
    public ResponseEntity<User> registration(   @PathVariable(name = "name") String name,
                                                @PathVariable(name = "password") String password) {
        return service.registration(name, password);
    }*/
}