package com.lackside.hotel.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lackside.hotel.models.Room;
import com.lackside.hotel.models.Room.RoomBuilder;
import com.lackside.hotel.repositories.RoomRepository;

@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(String roomType, BigDecimal roomPrice, MultipartFile photo) throws IOException, SerialException, SQLException {
        RoomBuilder roomBuilder = Room
            .builder()
            .roomType(roomType)
            .roomPrice(roomPrice);

        if (!photo.isEmpty()) {
            byte[] photoBytes = photo.getBytes();

            Blob blob = new SerialBlob(photoBytes);
            roomBuilder.photo(blob);
        }
        
        Room roomSaved = this.roomRepository.save(roomBuilder.build());

        return roomSaved;
    }

    @Override
    public List<Room> getRooms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRooms'");
    }

}
