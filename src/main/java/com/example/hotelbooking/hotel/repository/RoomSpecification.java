package com.example.hotelbooking.hotel.repository;

import com.example.hotelbooking.hotel.model.entity.Room;
import com.example.hotelbooking.hotel.model.entity.RoomFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> byRoomIdRoomNameInOutDatesAnRoomPrice(RoomFilter filter) {


        return Specification.where(byRoomId(filter.getId()))
                .and(byRoomName(filter.getRoomName()))
                .and(byRoomGuestMaximumRoomCapacity(filter.getMaximumRoomCapacity()))
                .and(RoomByHotelId(filter.getHotelId()))
                .and(RoomBetweenMinAndMaxPrice(filter.getRoomMinPrice(), filter.getRoomMaxPrice()))
                .and(RoomBetweenDateAvailableWillBeOccupied(filter.getDateWhenRoomWillBeOccupied(),
                        filter.getDateWhenRoomWillBeAvailable()));
    }

    static Specification<Room> byRoomId(Long roomId) {

        return (root, query, cb) -> {

            if (roomId == null) {
                return null;
            }

            return cb.equal(root.get("room").get("id"), roomId);
        };
    }

    static Specification<Room> byRoomName(String roomName) {

        return (root, query, cb) -> {

            if (roomName == null) {
                return null;
            }

            return cb.equal(root.get("room").get("name"), roomName);
        };
    }

    static Specification<Room> byRoomGuestMaximumRoomCapacity(Short roomCapacity) {

        return (root, query, cb) -> {

            if (roomCapacity == null) {
                return null;
            }

            return cb.equal(root.get("room").get("maximumRoomCapacity"), roomCapacity);
        };
    }

    static Specification<Room> RoomByHotelId(Long hotelId) {

        return (root, query, cb) -> {

            if (hotelId == null) {
                return null;
            }

            return cb.equal(root.get("room").get("hotelId"), hotelId);
        };
    }

//    static Specification<Room> RoomByMinPrice(Integer maxPrice) {
//
//        return (root, query, cb) -> {
//
//            if (maxPrice == null) {
//                return null;
//            }
//
//            return cb.equal(root.get("room").get("roomPrice"), maxPrice);
//        };
//    }
//
//    static Specification<Room> RoomByMaxPrice(Integer minPrice) {
//
//        return (root, query, cb) -> {
//
//            if (minPrice == null) {
//                return null;
//            }
//
//            return cb.equal(root.get("room").get("roomPrice"), minPrice);
//        };
//    }
//
//    static Specification<Room> RoomByDateWhenRoomWillBeOccupied(LocalDate whenRoomWillBeOccupied) {
//
//        return (root, query, cb) -> {
//
//            if (whenRoomWillBeOccupied == null) {
//                return null;
//            }
//
//            return cb.equal(root.get("room").get("dateWhenRoomWillBeOccupied"), whenRoomWillBeOccupied);
//        };
//    }
//
//    static Specification<Room> RoomByDateWhenRoomWillBeAvailable(LocalDate WhenRoomWillBeAvailable) {
//
//        return (root, query, cb) -> {
//
//            if (WhenRoomWillBeAvailable == null) {
//                return null;
//            }
//
//            return cb.equal(root.get("room").get("dateWhenRoomWillBeAvailable"), WhenRoomWillBeAvailable);
//        };
//    }

    static Specification<Room> RoomBetweenMinAndMaxPrice(Integer minPrice, Integer maxPrice) {

        return (root, query, cb) -> {

            if (maxPrice == null || maxPrice == null) {
                return null;
            }

            Predicate max = cb.gt(root.get("maxPrice"), maxPrice);
            Predicate min = cb.lt(root.get("maxPrice"), minPrice);

            return cb.and(min, max);
        };
    }

    static Specification<Room> RoomBetweenDateAvailableWillBeOccupied(LocalDate whenRoomWillBeOccupied,
                                                                      LocalDate whenRoomWillBeAvailable) {

        return (root, query, cb) -> {

            if (whenRoomWillBeOccupied == null || whenRoomWillBeAvailable == null) {
                return null;
            }


            return cb.and(cb.equal(root.get("whenRoomWillBeOccupied"), whenRoomWillBeOccupied),
                    cb.equal(root.get("whenRoomWillBeAvailable"), whenRoomWillBeAvailable));
        };
    }
}


// todo:  ID комнаты;
//      ○ заголовок;
//      ○ минимальная и максимальная цена;
//      ○ количество гостей в комнате;
//      ○ дата заезда и дата выезда;
//      ○ ID отеля.
//cb.lessThan(whenRoomWillBeOccupied, root.get("whenRoomWillBeOccupied"), cb.greaterThan(root.get("WhenRoomWillBeAvailable"),WhenRoomWillBeAvailable)