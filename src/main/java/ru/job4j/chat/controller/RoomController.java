package ru.job4j.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.service.RoomService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        validateRoom(room);
        return new ResponseEntity<Room>(
                this.roomService.save(room),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        validateRoom(room);
        this.roomService.save(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Room room = new Room();
        room.setId(id);
        this.roomService.delete(room);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<List<Room>> findByPerson(@PathVariable long id) {
        var rooms = this.roomService.findByPerson(id);
        if (rooms.isPresent() && rooms.get().size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rooms is not found. Please, check request.");
        }
        return new ResponseEntity<>(rooms.orElse(Collections.emptyList()), HttpStatus.OK);
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
        var room = this.roomService.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Room is not found. Please, check request."
        ));
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    private void validateRoom(Room room) throws NullPointerException {
        String errMsg = "Rooms name and description must not be empty";
        String roomName = room.getName();
        String description = room.getDescription();
        Objects.requireNonNull(roomName, errMsg);
        Objects.requireNonNull(description, errMsg);
        roomName = roomName.strip();
        description = description.strip();
        if (Objects.equals(roomName, "") || Objects.equals(description, "")) {
            throw new NullPointerException(errMsg);
        }
    }
}
