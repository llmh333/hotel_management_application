/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Room;
import com.mycompany.model.Customer;
import com.mycompany.service.ICustomer;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.view.InformationRoomView;
import com.sun.mail.auth.OAuth2SaslClientFactory;

/**
 *
 * @author lminh
 */
public class InformationRoomController {
    
    private ICustomer customerService = new CustomerServiceIplm();
    private InformationRoomView informationRoom;
    private Room room;
    
    public InformationRoomController(InformationRoomView informationRoom, Room room) {
        this.informationRoom = informationRoom;
        this.room = room;
        initInformationRoom();
    }
    
    public void initInformationRoom() {
        this.informationRoom.setVisible(true);
        if (room != null) {
            this.informationRoom.setRoomNumber(room.getRoomNumber());
            this.informationRoom.setRoomType(room.getRoomType());
            this.informationRoom.setRoomQuantity(room.getQuantity());
            this.informationRoom.setRoomFeature(room.getRoomFeature());
            this.informationRoom.setRoomStatus(room.getStatus());   
                      
            if (room.getCustomer_id()!= null) {
                Customer customer = customerService.findCustomerByID(room.getCustomer_id());
                if (customer != null) {
                    this.informationRoom.setCustomerName(customer.getName());
                    String expectedTime =  room.getCheckInTime().toString() +" -> "+ room.getCheckOutTime().toString();
                    this.informationRoom.setExpectTime(expectedTime);  
                }
                
            } else {
                this.informationRoom.setCustomerName("Chưa có");
                this.informationRoom.setExpectTime("Chưa thuê");
            }
            

            
        }
        

    }
    
}
