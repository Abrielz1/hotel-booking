package com.example.hotelbooking.hotel.repository;

import com.example.hotelbooking.hotel.model.entity.Room;
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
          WHERE h.id = :hotelId and room_available <= :date
          """, nativeQuery = true)
    List<Room> getListOfRoomAvailableOnCurrentDate(@Param("hotelId") Long hotelId,
                                                   @Param("date") LocalDate date);

    @Query(value = """
          SELECT * FROM ROOMS as r join hotels as h on h.id = r.hotel_id
          WHERE h.id = :hotelId and room_available > :currentDate
          """, nativeQuery = true)
    Room getRoomIsNotAvailableOnCurrentDate(@Param("hotelId") Long hotelId,
                                            @Param("currentDate") LocalDate currentDate);

    @Query(value = """
          SELECT * FROM ROOMS WHERE hotel_id = :hotelId and id = :roomId
          """, nativeQuery = true)
    Optional<Room> getRoom(@Param ("hotelId")Long hotelId,
                           @Param("roomId") Long roomId);
}
