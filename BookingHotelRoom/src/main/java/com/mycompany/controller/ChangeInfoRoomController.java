/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Room;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.ChangeInfoRoom;
import com.mycompany.view.RoomManagePanel;
import com.mycompany.view.RoomMapPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Action;
import javax.swing.JOptionPane;

/**
 *
 * @author lminh
 */
public class ChangeInfoRoomController {
    private ChangeInfoRoom changeInfoRoom;
    private IRoomService roomService;
    private Room roomChangeInfo;
    private RoomManagePanel roomManagePanel;
    public ChangeInfoRoomController(ChangeInfoRoom changeInfoRoom, IRoomService roomService, RoomManagePanel roomManagePanel, Room roomChangeInfo) {
        this.roomService = roomService;
        this.roomChangeInfo = roomChangeInfo;
        this.changeInfoRoom = changeInfoRoom;
        this.roomManagePanel = roomManagePanel;
        initChangeInfoRoom();
    }
    
    public void initChangeInfoRoom() {
        this.changeInfoRoom.setVisible(true);
        this.changeInfoRoom.setRoomNumber(roomChangeInfo.getRoomNumber());
        this.changeInfoRoom.setRoomType(roomChangeInfo.getRoomType());
        this.changeInfoRoom.setRoomQuantity(roomChangeInfo.getQuantity());
        this.changeInfoRoom.setRoomFeature(roomChangeInfo.getRoomFeature());
        this.changeInfoRoom.setRoomPrice(String.valueOf(roomChangeInfo.getPrice()));
        this.changeInfoRoom.setBtnConFirmAct(new BtnConfirm());
    } 
    
    
    private class BtnConfirm implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            roomChangeInfo.setRoomType(changeInfoRoom.getRoomType());
            roomChangeInfo.setRoomFeature(changeInfoRoom.getRoomFeature());
            roomChangeInfo.setQuantity(changeInfoRoom.getRoomQuantity());
            roomChangeInfo.setPrice(changeInfoRoom.getRoomPrice());
            
            if (roomService.changeInfoRoom(roomChangeInfo)) {
                JOptionPane.showMessageDialog(changeInfoRoom, "Thay đổi thông tin phòng thành công");
                List<Room> rooms = roomService.getAllRoom();
                System.out.println(rooms);
                roomManagePanel.setDataTableListRoom(rooms);
                changeInfoRoom.dispose();

            }
            else {
                JOptionPane.showMessageDialog(changeInfoRoom, "Có lỗi xảy ra vui lòng thử lại");
            }
        }
        
    }
}
