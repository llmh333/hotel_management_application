/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.model.Booking;
import com.mycompany.service.IBookingSerive;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author lminh
 */
public class BookingServiceIplm implements IBookingSerive{
    
    private EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public String createBooking(Booking booking) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(booking);
            entityManager.getTransaction().commit();
            return booking.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(booking);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Booking> getAllBookings() {
        try {
            TypedQuery<Booking> query = entityManager.createQuery("FROM Booking", Booking.class);
            List<Booking> bookings = query.getResultList();
            return bookings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
