package com.mycompany.controller;


import com.mycompany.model.Room;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.ChangeInfoRoom;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lminh
 */
public class ChangeInforRoomController {
    private ChangeInfoRoom addRoom;
    
    public ChangeInforRoomController(ChangeInfoRoom addRoom) {
        this.addRoom = addRoom;
        initAddRoom();
    }
    
    public void initAddRoom() {
        this.addRoom.setBtnConFirmAct(new ConfirmAddRoom());
    }
    
    
    private class ConfirmAddRoom implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
//            Room room = Room.builder()
//                    .roomNumber(addRoom.getRoomNumber())
//                    .roomType(addRoom.getRoomType())
//                    .roomFeature(addRoom.getRoomFeature())
//                    .quantity(addRoom.getRoomQuantity())
//                    .price(addRoom.getRoomPrice())
//                    .build();
//            if (roomService.addRoom(room)) {
//                JOptionPane.showMessageDialog(addRoom, "Thêm phòng thành công");
//            }
//            else {
//                JOptionPane.showMessageDialog(addRoom, "Số phòng đã tồn tại");
//            }
        }
        
    }
}
