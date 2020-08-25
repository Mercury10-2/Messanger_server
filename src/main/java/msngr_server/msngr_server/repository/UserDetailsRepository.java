package msngr_server.msngr_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import msngr_server.msngr_server.domain.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
    User findByName(String name);
}