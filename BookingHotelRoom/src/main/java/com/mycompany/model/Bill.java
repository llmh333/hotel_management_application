/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

/**
 *
 * @author lminh
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @OneToOne
    @JoinColumn(name = "booking_id", nullable = true)
    private Booking booking;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    @Column(nullable = false)
    private double totalPrice;
    
    @Column(nullable = false)
    private double totalHours;
    
    @Column(nullable = false)
    private String status;

}
