package com.dailycodework.lackside.services.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.lackside.models.Room;
import com.dailycodework.lackside.repositories.RoomRepository;
import com.dailycodework.lackside.services.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SerialException, SQLException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        
        if (!photo.isEmpty()) {
            byte[] photoBytes = photo.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }

        Room roomSaved = this.roomRepository.save(room);

        return roomSaved;
    }

}
