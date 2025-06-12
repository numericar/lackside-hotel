package com.dailycodework.lackside.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.lackside.models.Room;

public interface RoomService {
    Room createRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SerialException, SQLException;
}
