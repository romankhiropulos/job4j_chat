package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.RoomService;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room message) {
        return new ResponseEntity<Room>(
                this.roomService.save(message),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room message) {
        this.roomService.save(message);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Room message = new Room();
        message.setId(id);
        this.roomService.delete(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<Room>> findByPerson(@PathVariable long id) {
        var rooms = this.roomService.findByPerson(id);
        return new ResponseEntity<List<Room>>(
                rooms.orElse(List.of(new Room())),
                rooms.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> findAll() {
        var rooms = this.roomService.findAll();
        return new ResponseEntity<List<Room>>(
                rooms.orElse(List.of(new Room())),
                rooms.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable long id) {
        var room = this.roomService.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}
