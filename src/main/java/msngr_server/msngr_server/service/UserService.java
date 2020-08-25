package msngr_server.msngr_server.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import msngr_server.msngr_server.domain.User;
import msngr_server.msngr_server.repository.UserDetailsRepository;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public User getUser(String name) {
        return userDetailsRepository.findByName(name);
    }

    public List<User> getUsers() {
        return userDetailsRepository.findAll();
    }

    public ResponseEntity<User> verifyPassword(String name, String password) {
        User userFromDb = getUser(name);
        return userFromDb.getPassword().equals(password)
                ? new ResponseEntity<User>(userFromDb, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.OK);
    }
/*
    public ResponseEntity<User> registration(String name, String password) {
        User user = getUser(name);
        if (user != null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else {
            user = new User();
            user.set
            return new ResponseEntity<User>( HttpStatus.OK);
        }
    }*/

    public void generateUsers() {
        if (getUsers().isEmpty()) {
            User user = new User();
            user.setName("Jane");
            user.setPassword("Jane");
            user.setGender("female");
            user.setEmail("jane@mail.ru");
            user.setLastVisit(LocalDateTime.now());
            userDetailsRepository.save(user);
            user = new User();
            user.setName("Kate");
            user.setPassword("Kate");
            user.setGender("female");
            user.setEmail("Kate@mail.ru");
            user.setLastVisit(LocalDateTime.now());
            userDetailsRepository.save(user);
            user = new User();
            user.setName("Ann");
            user.setPassword("Ann");
            user.setGender("female");
            user.setEmail("ann@mail.ru");
            user.setLastVisit(LocalDateTime.now());
            userDetailsRepository.save(user);
        }
    }
}