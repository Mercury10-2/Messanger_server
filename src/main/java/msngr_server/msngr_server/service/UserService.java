package msngr_server.msngr_server.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import msngr_server.msngr_server.domain.User;
import msngr_server.msngr_server.dto.UserDto;
import msngr_server.msngr_server.repository.UserDetailsRepository;

@Service
public class UserService {

    private final UserDetailsRepository userDetailsRepository;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public ResponseEntity<UserDto> getUser(String name) {
        return getResponseEntityDto(getUserFromDb(name));
    }

    public List<UserDto> getUsers() {
        return getUserListFromDb().stream()
                .map(user -> getDto(user))
                .collect(Collectors.toList());
    }

    public ResponseEntity<UserDto> verifyPassword(String name, String password) {
        User user = getUserFromDb(name);
        if (user == null)
            return sendError("name");
        if (!user.getPassword().equals(password))
            return sendError("password");
        return getResponseEntityDto(user);
    }

    public ResponseEntity<UserDto> registration(String name, String password, String gender, String email) {
        User user = getUserFromDb(name);
        if (user != null)
            return sendError("exists");
        else {
            user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setGender(gender);
            user.setEmail(email);
            user.setLastVisit(LocalDateTime.now());
            return getResponseEntityDto(userDetailsRepository.save(user));
        }
    }

    private ResponseEntity<UserDto> getResponseEntityDto(User user) {
        return new ResponseEntity<UserDto>(getDto(user), HttpStatus.OK);
    }

    private UserDto getDto(User user) {
        return new UserDto(user.getName(), user.getEmail(), user.getGender(), LocalDateTime.now());
    }

    private ResponseEntity<UserDto> sendError(String error) {
        return new ResponseEntity<UserDto>(new UserDto(error), HttpStatus.OK);
    }

    private User getUserFromDb(String name) {
        return userDetailsRepository.findByName(name);
    }

    private List<User> getUserListFromDb() {
        return userDetailsRepository.findAll();
    }

    public void generateUsers() {
        if (getUsers().isEmpty()) {
            User user = new User();
            user.setName("Юля");
            user.setPassword("Юля");
            user.setGender("женщина");
            user.setEmail("julia@mail.ru");
            user.setLastVisit(LocalDateTime.now());
            userDetailsRepository.save(user);
            user = new User();
            user.setName("Катя");
            user.setPassword("Катя");
            user.setGender("женщина");
            user.setEmail("Kate@mail.ru");
            user.setLastVisit(LocalDateTime.now());
            userDetailsRepository.save(user);
            user = new User();
            user.setName("Аня");
            user.setPassword("Аня");
            user.setGender("женщина");
            user.setEmail("ann@mail.ru");
            user.setLastVisit(LocalDateTime.now());
            userDetailsRepository.save(user);
        }
    }
}