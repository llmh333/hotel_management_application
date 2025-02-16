/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Room;
import java.util.List;

/**
 *
 * @author lminh
 */
public interface IRoomService {
    
    public boolean addRoom(Room room);
    
    public boolean changeInfoRoom(Room room);
    
    public String deleteRoom(String roomNumber);
    
    public List<Room> getAllRoom();
    
    public Room findRoomByRoomNumber(String roomNumber);
    
}
