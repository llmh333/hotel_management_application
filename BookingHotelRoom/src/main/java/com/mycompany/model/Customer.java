/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 *
 * @author lminh
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {      
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String name;
    
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String birthday;

    @Column(columnDefinition = "varchar(5)", nullable = false)
    private String sex;
    
    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String email;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    private String phoneNumber;
    
    @Column(columnDefinition = "varchar(100)", nullable = true)
    private int roomNumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
    
}
