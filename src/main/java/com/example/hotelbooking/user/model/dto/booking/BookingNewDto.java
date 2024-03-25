package com.example.hotelbooking.user.model.dto.booking;

import com.example.hotelbooking.annotation.BeforeReleaseDate;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingNewDto {

    @NotNull
    @BeforeReleaseDate
    private LocalDateTime checkInRoom;

    @Future
    @NotNull
    private LocalDateTime checkOutRoom;
}
