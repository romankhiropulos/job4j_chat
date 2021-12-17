package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Message;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.RoomRepository;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room save(Room room) {
        Optional<Room> roomFromDb = roomRepository.findById(room.getId());
        if (roomFromDb.isEmpty()) {
            room.setCreated(new Date(System.currentTimeMillis()));
            return roomRepository.save(room);
        } else {
            Room updatableRoom = roomFromDb.get();
            updatableRoom.setDescription(room.getDescription());
            updatableRoom.setName(room.getName());
            return roomRepository.save(updatableRoom);
        }
    }

    public void deleteById(long id) {
        roomRepository.deleteById(id);
    }

    public Optional<List<Room>> findAll() {
        return Optional.of((List<Room>) roomRepository.findAll());
    }

    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    public Optional<List<Room>> findByPerson(long id) {
        Optional<List<Room>> rooms = roomRepository.findByPersonId(id);
        rooms.ifPresent(
                roomList ->  roomList.sort(Comparator.comparing(Room::getCreated).reversed())
        );
        return rooms;
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
