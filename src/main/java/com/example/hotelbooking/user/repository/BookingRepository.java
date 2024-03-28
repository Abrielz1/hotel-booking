package com.example.hotelbooking.user.repository;

import com.example.hotelbooking.user.model.entity.Booking;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query(value = """
                   SELECT * FROM bookings as b join rooms as r on r.id = b.room_id
                   join hotels as h on h.id = r.hotel_id
                   WHERE h.id = :hotelId AND r.id = :roomId
                   AND room_available BETWEEN :start AND :end
                   """, nativeQuery = true)
    List<Booking> getAllRoomsInHotelWhichAreInUse(@Param("hotelId") Long hotelId,
                                                            @Param("roomId") Long roomId,
                                                            @Param("start") LocalDate start,
                                                            @Param("end") LocalDate end,
                                                            PageRequest page);
}