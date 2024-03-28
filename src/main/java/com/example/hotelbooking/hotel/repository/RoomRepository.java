package com.example.hotelbooking.hotel.repository;

import com.example.hotelbooking.hotel.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
