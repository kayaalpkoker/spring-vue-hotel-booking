package edu.sabanciuniv.hotelbookingapp.service.impl;

import edu.sabanciuniv.hotelbookingapp.model.Hotel;
import edu.sabanciuniv.hotelbookingapp.model.Room;
import edu.sabanciuniv.hotelbookingapp.model.dto.RoomDTO;
import edu.sabanciuniv.hotelbookingapp.repository.RoomRepository;
import edu.sabanciuniv.hotelbookingapp.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room saveRoom(RoomDTO roomDTO, Hotel hotel) {
        log.info("Attempting to save a new room: {}", roomDTO);
        Room room = mapRoomDtoToRoom(roomDTO, hotel);
        room = roomRepository.save(room);
        log.info("Successfully saved room with ID: {}", room.getId());
        return room;
    }

    @Override
    public List<Room> saveRooms(List<RoomDTO> roomDTOs, Hotel hotel) {
        log.info("Attempting to save rooms: {}", roomDTOs);
        List<Room> rooms = roomDTOs.stream()
                .map(roomDTO -> saveRoom(roomDTO, hotel)) // save each room
                .collect(Collectors.toList());
        log.info("Successfully saved rooms: {}", rooms);
        return rooms;
    }

    @Override
    public Room findRoomById(Long id) {
        return null;
    }

    @Override
    public List<Room> findRoomsByHotelId(Long hotelId) {
        return null;
    }

    @Override
    public Room updateRoom(RoomDTO roomDTO) {
        return null;
    }

    @Override
    public void deleteRoom(Long id) {

    }

    @Override
    public Room mapRoomDtoToRoom(RoomDTO roomDTO, Hotel hotel) {
        log.debug("Mapping RoomDTO to Room: {}", roomDTO);
        Room room = Room.builder()
                .hotel(hotel)
                .roomType(roomDTO.getRoomType())
                .roomCount(roomDTO.getRoomCount())
                .pricePerNight(roomDTO.getPricePerNight())
                .build();
        log.debug("Mapped Room: {}", room);
        return room;

    }

    @Override
    public RoomDTO mapRoomToRoomDto(Room room) {
        return RoomDTO.builder()
                .id(room.getId())
                .hotelId(room.getHotel().getId())
                .roomType(room.getRoomType())
                .roomCount(room.getRoomCount())
                .pricePerNight(room.getPricePerNight())
                .build();
    }
}
