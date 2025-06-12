package com.dailycodework.lackside.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.lackside.dtos.BaseResponse;
import com.dailycodework.lackside.dtos.RequestCreateRoomDto;
import com.dailycodework.lackside.dtos.RoomResponse;
import com.dailycodework.lackside.models.Room;
import com.dailycodework.lackside.services.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<RoomResponse>> createRoom(@ModelAttribute RequestCreateRoomDto dto) {
        try {
            Room saveRoom = this.roomService.createRoom(dto.getPhoto(), dto.getRoomType(), dto.getRoomPrice());
            RoomResponse roomResponse = new RoomResponse(saveRoom.getId(), saveRoom.getRoomType(), saveRoom.getRoomPrice());

            return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(true, "Successful", roomResponse));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(false, ex.getMessage(), null));
        }
    }
}
