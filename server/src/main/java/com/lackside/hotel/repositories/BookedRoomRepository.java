package com.lackside.hotel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lackside.hotel.models.BookedRoom;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {

}
