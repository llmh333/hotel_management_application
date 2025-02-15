/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author lminh
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String roomNumber;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String status;
    
    @Column(columnDefinition = "varchar(10)", nullable = false)
    private String roomType;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String roomFeature;
    
    @Column(columnDefinition = "varchar(10)", nullable = false)
    private String quantity;

    @Column(nullable = false)
    private double price;
    
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;

    private LocalDateTime checkInTime;
    
    private LocalDateTime checkoutTime;
    
}
