package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.repository.MessageRepository;

import java.util.*;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message save(Message message) {
        message.setCreated(new Date(System.currentTimeMillis()));
        return messageRepository.save(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }

    public Optional<Message> findById(long id) {
        return messageRepository.findById(id);
    }

    public Optional<List<Message>> findAll() {
        return Optional.of((List<Message>) messageRepository.findAll());
    }

    public Optional<List<Message>> findByRoom(long id) {
        Optional<List<Message>> messages = messageRepository.findByRoomId(id);
        messages.ifPresent(
                messageList ->  messageList.sort(Comparator.comparing(Message::getCreated).reversed())
        );
        return messages;
    }
}
