package com.example.hotelbooking.user.model.entity;

//import com.example.hotelbooking.user.enums.RoleType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Data
//@Table(name = "authorities")
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Enumerated(EnumType.STRING)
//    private RoleType authority;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "user_id")
//    @ToString.Exclude
//    private User user;
//
////    public GrantedAuthority toAuthority() {
////
////        return new SimpleGrantedAuthority(authority.name());
////    }
////
//    public static Role from(RoleType type) {
//
//        var role = new Role();
//        role.setAuthority(type);
//
//        return role;
//    }
//}
