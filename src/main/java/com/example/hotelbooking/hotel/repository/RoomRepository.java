package com.example.hotelbooking.hotel.repository;

import com.example.hotelbooking.hotel.model.entity.Room;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {

    @Query(value = """
          SELECT * FROM ROOMS as r join hotels as h on h.id = r.hotel_id
          WHERE h.id = :hotelId and available <= :targetDate
          """, nativeQuery = true)
    List<Room> getListOfRoomAvailableOnCurrentDate(@Param("hotelId") Long hotelId,
                                                   @Param("targetDate") LocalDate targetDate,
                                                   PageRequest page);

    @Query(value = """
          SELECT * FROM ROOMS WHERE hotel_id = :hotelId and id = :roomId
          """, nativeQuery = true)
    Optional<Room> getRoom(@Param ("hotelId")Long hotelId,
                           @Param("roomId") Long roomId);

    @Query(value = """
           SELECT r.id, r.available, r.occupied, r.capacity, r.description, r.name, r.price, r.hotel_id  
            FROM ROOMS as r JOIN HOTELS AS h
            on r.hotel_id = h.id
           WHERE h.id = :hotelId
           """, nativeQuery = true)
    List<Room> getAllRoomsInHotel(@Param ("hotelId")Long hotelId, PageRequest page);
}
