/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.model.Room;
import com.mycompany.service.IRoomService;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author lminh
 */
@Transactional
public class RoomServiceIplm implements IRoomService{

    private EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public boolean addRoom(Room room) {
        try {
            TypedQuery<Room> query = entityManager.createQuery("FROM Room WHERE roomNumber = :roomNumber",Room.class);
            query.setParameter("roomNumber", room.getRoomNumber());
            Room r = query.getSingleResult();
            if (r != null) return false;
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().begin();
            entityManager.persist(room);
            entityManager.getTransaction().commit();
        }
        return true;
    }

    @Override
    public boolean changeInfoRoom(Room room) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(room);
            entityManager.getTransaction().commit();
            
        } catch (Exception e) {
        }
        return true;
    }

    @Override
    public String deleteRoom(String roomNumber) {
        try {
            Query query = entityManager.createQuery("DELETE FROM Room WHERE roomNumber = :roomNumber");
            query.setParameter("roomNumber", roomNumber);
            entityManager.getTransaction().begin();
            int row = query.executeUpdate();
            entityManager.getTransaction().commit();
            return "Thành công";
        } catch (Throwable ConstraintViolationExceptiont) {          
            entityManager.getTransaction().rollback();
            return "Không thể xóa phòng đang có người";

        }        
    }

    @Override
    public List<Room> getAllRoom() {
        try {
            List<Room> rooms = new ArrayList<Room>();
            TypedQuery<Room> query = entityManager.createQuery("FROM Room",Room.class);
            rooms = query.getResultList();
            System.out.println(rooms);
            rooms.sort(Comparator.comparing(Room::getRoomNumber));
            return rooms;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }

    @Override
    public Room findRoomByRoomNumber(String roomNumber) {
        try {
            TypedQuery<Room> query = entityManager.createQuery("FROM Room WHERE roomNumber = :roomNumber",Room.class);
            query.setParameter("roomNumber", roomNumber);
            Room room = query.getSingleResult();
            return room;
        } catch (Exception e) {
        }
        return null;
    }
    
}
