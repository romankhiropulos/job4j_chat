package ru.job4j.chat.mapper;

import org.mapstruct.Mapper;
import ru.job4j.chat.dto.RoomDTO;
import ru.job4j.chat.model.Room;

@Mapper
public interface RoomDTOMapper {

    Room sourceToDestination(RoomDTO source);

    RoomDTO destinationToSource(Room destination);

}
