package msngr_server.msngr_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import msngr_server.msngr_server.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}