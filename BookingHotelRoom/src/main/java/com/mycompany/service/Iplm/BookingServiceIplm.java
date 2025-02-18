/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.model.Booking;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import com.mycompany.service.IBookingSerive;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
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
    public boolean deleteBookingByID(String idBooking) {
        try {
            Query query = entityManager.createQuery("delete from Booking where id = :idBooking");
            query.setParameter("idBooking", idBooking);
            entityManager.getTransaction().begin();
            query.executeUpdate();
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

    @Override
    public List<Booking> findBookingByCustomer(Customer customer) {
        try {
            TypedQuery<Booking> query = entityManager.createQuery("FROM Booking WHERE customer = :customer", Booking.class);
            query.setParameter("customer", customer);
            List<Booking> bookings = query.getResultList();
            return bookings;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Booking findBookingByRoom(Room room) {
        try {
            TypedQuery<Booking> query = entityManager.createQuery("FROM Booking WHERE room = :room ", Booking.class);
            query.setParameter("room", room);
            Booking bookingRespone = query.getSingleResult();
            return bookingRespone;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
