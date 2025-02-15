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
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GeneratedColumn;

/**
 *
 * @author lminh
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String name;
    
    @Column(columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String email;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String birthday;
    
    @Column(columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String phoneNumber;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String address;
    
    @Column(columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String username;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String password;
    
    @Column(columnDefinition = "varchar(100)", nullable = false)
    private String sex;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Booking> bookings;
}
