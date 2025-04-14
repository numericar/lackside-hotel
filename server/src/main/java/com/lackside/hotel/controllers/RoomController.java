package com.lackside.hotel.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lackside.hotel.dtos.requests.CreateRoomRequestDto;
import com.lackside.hotel.dtos.responses.RoomResponseDto;
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

}
