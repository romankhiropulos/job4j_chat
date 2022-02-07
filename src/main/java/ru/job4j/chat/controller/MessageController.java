package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.service.MessageService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        validateMessage(message);
        return new ResponseEntity<Message>(
                this.messageService.save(message),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        validateMessage(message);
        this.messageService.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Message message = new Message();
        message.setId(id);
        this.messageService.delete(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<List<Message>> findByRoom(@PathVariable long id) {
        var messages = this.messageService.findByRoom(id);
        if (messages.isPresent() && messages.get().size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Messages is not found. Please, check request.");
        }
        return new ResponseEntity<>(messages.orElse(Collections.emptyList()), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Message>> findAll() {
        var messages = this.messageService.findAll();
        return new ResponseEntity<List<Message>>(
                messages.orElse(List.of(new Message())),
                messages.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable long id) {
        var message = this.messageService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Message is not found. Please, check request."
        ));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private void validateMessage(Message message) throws NullPointerException {
        String errMsg = "Message must not be empty";
        String text = message.getDescription();
        Objects.requireNonNull(text, errMsg);
        text = text.strip();
        if (Objects.equals(text, "")) {
            throw new NullPointerException(errMsg);
        }
    }
}
