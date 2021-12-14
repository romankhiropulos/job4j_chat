package ru.job4j.chat.service;

import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Room;
import ru.job4j.chat.repository.RoomRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void save(Room room) {
        Optional<Room> roomFromDb = roomRepository.findById(room.getId());
        if (roomFromDb.isEmpty()) {
            room.setCreated(new Date(System.currentTimeMillis()));
            roomRepository.save(room);
        } else {
            Room updatableRoom = roomFromDb.get();
            updatableRoom.setDescription(room.getDescription());
            updatableRoom.setName(room.getName());
            roomRepository.save(updatableRoom);
        }
    }

    public void deleteById(long id) {
        roomRepository.deleteById(id);
    }

    public List<Room> getAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public Optional<Room> findById(long id) {
        return roomRepository.findById(id);
    }

    public void delete(Room room) {
        roomRepository.delete(room);
    }
}
