package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.repository.MessageRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void add(Message message) {
        message.setCreated(new Date(System.currentTimeMillis()));
        messageRepository.save(message);
    }

    public void delete(Message message) {
        messageRepository.delete(message);
    }

    public Optional<Message> findById(long id) {
        return messageRepository.findById(id);
    }

    public List<Message> findMessagesByRoomId(long postId) {
        return messageRepository.findMessagesByRoomId(postId);
    }
}
