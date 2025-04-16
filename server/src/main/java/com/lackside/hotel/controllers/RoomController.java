package com.lackside.hotel.controllers;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lackside.hotel.dtos.requests.CreateRoomRequestDto;
import com.lackside.hotel.dtos.responses.RoomResponseDto;
import com.lackside.hotel.dtos.responses.RoomResponseDto.RoomResponseDtoBuilder;
import com.lackside.hotel.models.Room;
import com.lackside.hotel.services.IRoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final IRoomService roomService;

    public RoomController(IRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RoomResponseDto> createRoom(@ModelAttribute CreateRoomRequestDto createRoomDto) {
        try {
            Room savedRoom = this.roomService.create(createRoomDto.getRoomType(), createRoomDto.getRoomPrice(),
                    createRoomDto.getPhoto());

            RoomResponseDto roomResponseDto = RoomResponseDto
                    .builder()
                    .id(savedRoom.getId())
                    .roomType(savedRoom.getRoomType())
                    .roomPrice(savedRoom.getRoomPrice())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(roomResponseDto);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<RoomResponseDto>> getRooms() {
        try {
            List<Room> rooms = this.roomService.getRooms();

            List<RoomResponseDto> responseDtos = new ArrayList<>();
            for (Room room : rooms) {
                RoomResponseDtoBuilder roomResponseDtoBuilder = RoomResponseDto.builder();
                roomResponseDtoBuilder
                        .id(room.getId())
                        .roomType(room.getRoomType())
                        .roomPrice(room.getRoomPrice())
                        .isBooked(room.isBooked())
                        .bookings(null);

                byte[] photoBytes = room.getPhoto().getBytes(1, (int) room.getPhoto().length());

                if (photoBytes != null && photoBytes.length > 0) {
                    String photoBase64 = Base64.getEncoder().encodeToString(photoBytes);
                    roomResponseDtoBuilder.photo(photoBase64);
                }

                responseDtos.add(roomResponseDtoBuilder.build());
            }

            return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
