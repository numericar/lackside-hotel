package com.lackside.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lackside.hotel.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    
}
