package com.dailycodework.lackside.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodework.lackside.models.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
