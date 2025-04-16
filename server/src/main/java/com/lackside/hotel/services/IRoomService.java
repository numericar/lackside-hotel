package com.lackside.hotel.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.lackside.hotel.models.Room;

public interface IRoomService {
    Room create(String roomType, BigDecimal roomPrice, MultipartFile photo) throws IOException, SerialException, SQLException;
    List<Room> getRooms();
}
