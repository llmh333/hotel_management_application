/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.ChangeInfoRoom;
import com.mycompany.view.RoomManagePanel;
import jakarta.persistence.EntityManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author lminh
 */
public class RoomMapManageController {
    private RoomManagePanel roomManagePanel;
    public IRoomService roomService = new RoomServiceIplm();
    public EntityManager entityManager = HibernateUtil.getEntityManager();
    private User user;
    
    public RoomMapManageController(RoomManagePanel roomManagePanel, User user) {
        this.roomManagePanel = roomManagePanel;
        initRoomManage();
    }
    
    public RoomManagePanel getRoomManagePanel() {
        return this.roomManagePanel;
    }
    
    public void showListRoom() {
        List<Room> rooms = roomService.getAllRoom();
        System.out.println(rooms);
        roomManagePanel.setDataTableListRoom(rooms);
        
    }
    public void initRoomManage() {
        showListRoom();
        this.roomManagePanel.setBtnAddRoom(new BtnAddRoom());
        this.roomManagePanel.setBtnChangeInforRoom(new BtnChangeInfoRoom());
        this.roomManagePanel.setBtnDeleteRoom(new BtnDeleteRoom());
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
                showListRoom();
            }
            else {
                JOptionPane.showMessageDialog(roomManagePanel, "Số phòng đã tồn tại");
            }
        }
    }
    
    private class BtnChangeInfoRoom implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Room room = roomService.findRoomByRoomNumber(roomManagePanel.getRoomSelect());
            ChangeInfoRoomController changeInfoRoomController = new ChangeInfoRoomController(new ChangeInfoRoom(), roomService, roomManagePanel,room);
            showListRoom();
        }
        
    }
    
    private class BtnDeleteRoom implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ansConfirm = JOptionPane.showConfirmDialog(roomManagePanel, "Xác nhận xóa phòng đã chọn", "Xóa phòng", JOptionPane.OK_CANCEL_OPTION);
            if (ansConfirm == 0) {
                String roomNumber = roomManagePanel.getRoomSelect();
                System.out.println(roomNumber);
                if (roomService.deleteRoom(roomNumber)) {
                    JOptionPane.showMessageDialog(roomManagePanel, "Xóa phòng thành công");
                    showListRoom();
                } else {
                    JOptionPane.showMessageDialog(roomManagePanel, "Có lỗi xảy ra vui lòng thử lại");
                }
            }
           
        }   
    }
    
}


