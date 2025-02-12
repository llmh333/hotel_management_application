/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Room;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.RoomManagePanel;
import jakarta.persistence.EntityManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author lminh
 */
public class RoomMapManageController {
    private RoomManagePanel roomManagePanel;
    public IRoomService roomService = new RoomServiceIplm();
    public EntityManager entityManager = HibernateUtil.getEntityManager();
    
    public RoomMapManageController(RoomManagePanel roomManagePanel) {
        this.roomManagePanel = roomManagePanel;
        initRoomManage();
    }
    
    public RoomManagePanel getRoomManagePanel() {
        return this.roomManagePanel;
    }
    public void initRoomManage() {
        this.roomManagePanel.setBtnAddRoom(new BtnAddRoom());
//        this.roomManagePanel.setBtnChangeInforRoom(listener);
//        this.roomManagePanel.setBtnDeleteRoom(listener);
    }
    
    private class BtnAddRoom implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Room room = Room.builder()
                    .roomNumber(roomManagePanel.getRoomNumber())
                    .roomType(roomManagePanel.getRoomType())
                    .roomFeature(roomManagePanel.getRoomFeature())
                    .quantity(roomManagePanel.getRoomQuantity())
                    .price(roomManagePanel.getRoomPrice())
                    .status(roomManagePanel.getRoomStatus())
                    .build();
            if (roomService.addRoom(room)) {
                JOptionPane.showMessageDialog(roomManagePanel, "Thêm phòng thành công");
            }
            else {
                JOptionPane.showMessageDialog(roomManagePanel, "Số phòng đã tồn tại");
            }
        }
        
    }
}


