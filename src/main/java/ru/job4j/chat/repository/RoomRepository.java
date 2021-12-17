package ru.job4j.chat.repository;


import org.springframework.data.repository.CrudRepository;
import ru.job4j.chat.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<List<Room>> findByPersonId(long id);
}
