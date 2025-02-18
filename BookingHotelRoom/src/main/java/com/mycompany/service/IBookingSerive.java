/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Booking;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;

import java.util.List;

/**
 *
 * @author lminh
 */
public interface IBookingSerive {
    
    public String createBooking(Booking booking);
    
    public boolean deleteBookingByID(String idBooking);
    
    public List<Booking> findBookingByCustomer(Customer customer);
    
    public List<Booking> getAllBookings();
    
    public Booking findBookingByRoom(Room room);
    
}
