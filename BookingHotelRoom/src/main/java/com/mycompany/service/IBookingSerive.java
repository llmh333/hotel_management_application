/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Booking;
import java.util.List;

/**
 *
 * @author lminh
 */
public interface IBookingSerive {
    
    public String createBooking(Booking booking);
    
    public boolean deleteBooking(Booking booking);
    
    public List<Booking> getAllBookings();
    
}
