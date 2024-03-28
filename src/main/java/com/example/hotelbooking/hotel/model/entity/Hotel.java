package com.example.hotelbooking.hotel.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "table")
@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String hotelName;

    @Column(name = "display_name", columnDefinition = "VARCHAR(255)", nullable = false)
    private String displayName;

    @Column(name = "city", columnDefinition = "VARCHAR(255)", nullable = false)
    private String city;

    @Column(name = "hotel_address", columnDefinition = "VARCHAR(255)", nullable = false)
    private String hotelAddress;

    @Column(name = "distance-from_center", nullable = false)
    private Long distanceFromCenter;

    @Column(name = "private ", nullable = true)
    private Integer hotelValuation;

    @Column(name = "hotel_rating", nullable = true)
    private Integer hotelRating;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Room> listOfAvailableRoomsToBook = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Hotel hotel = (Hotel) o;
        return getId() != null && Objects.equals(getId(), hotel.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
