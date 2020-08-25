package msngr_server.msngr_server.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;

import msngr_server.msngr_server.domain.Message;
import msngr_server.msngr_server.domain.Views;
import msngr_server.msngr_server.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("messages")
@CrossOrigin(origins = { "http://localhost:8081" })
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> getMessages() {
        return service.getMessages();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Long id) {
        return service.getMessage(id);
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        return service.create(message);
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Long id, @RequestBody Message message) {
        return service.update(id, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}