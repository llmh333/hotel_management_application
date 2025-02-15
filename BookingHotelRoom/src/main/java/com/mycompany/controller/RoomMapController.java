/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.InfoRoom;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.DashboardView;
import com.mycompany.view.FormRoomPanel;
import com.mycompany.view.RoomMapPanel;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author lminh
 */
public class RoomMapController {
    private RoomMapPanel roomMapPanel;
    private IRoomService roomService = new RoomServiceIplm();
    private User user;
    private DashboardView dashboardView;
    
    public RoomMapController(RoomMapPanel roomMapPanel, User user) {
        this.roomMapPanel = roomMapPanel;
        this.user = user;
        initRoomMapPanel();
    }
    
    public RoomMapPanel getRoomMapPanel() {
        return this.roomMapPanel;
    }
    
    public void showListRoom() {
        List<Room> rooms = roomService.getAllRoom();
        int roomAvail = 0, roomNotAvail = 0, roomBusy = 0;
        if (!rooms.isEmpty()) {
            roomMapPanel.deleteFormRoomPanel();
            for (Room room : rooms) {
                FormRoomPanel formRoomPanel = new FormRoomPanel();
                if (room.getStatus().equals(InfoRoom.STATUS_NOT_AVAILABEL)) {
                    formRoomPanel.setBgrPanelStatusRoom(InfoRoom.redStatus);
                    formRoomPanel.setPopupMenuItem("Đặt phòng", false);
                    roomNotAvail++;
                }
                if (room.getStatus().equals(InfoRoom.STATUS_AVAILABEL)) {
                    formRoomPanel.setBgrPanelStatusRoom(InfoRoom.greenStatus);
                    formRoomPanel.setPopupMenuItem("Thanh toán", false);
                    roomAvail++;
                }
                if (room.getStatus().equals(InfoRoom.STATUS_BUSY)) {
                    formRoomPanel.setBgrPanelStatusRoom(InfoRoom.navyStatus);
                    formRoomPanel.setPopupMenuItem("Đặt phòng", false);
                    formRoomPanel.setPopupMenuItem("Thanh toán", false);
                    roomBusy++;
                }
                formRoomPanel.setLabelRoomType(room.getRoomType());
                formRoomPanel.setLabelRoomNumber(room.getRoomNumber());
                formRoomPanel.setLabelRoomQuantity(room.getQuantity());
                formRoomPanel.setLabelRoomStatus(room.getStatus());
                roomMapPanel.setLabelRoomAvail(String.valueOf(roomAvail));
                roomMapPanel.setLabelRoomNotAvail(String.valueOf(roomNotAvail));
                roomMapPanel.setLabelRoomBusy(String.valueOf(roomBusy));
                new FormRoomPanelController(formRoomPanel, roomService, room.getRoomNumber(), user);
                roomMapPanel.addFormRoomPanel(formRoomPanel);    
            }
        } else {
            System.out.println("hello");
            roomMapPanel.setLabelRoomAvail(String.valueOf(roomAvail));
            roomMapPanel.setLabelRoomNotAvail(String.valueOf(roomNotAvail));
            roomMapPanel.setLabelRoomBusy(String.valueOf(roomBusy));
        }
    }
    public void initRoomMapPanel() {
        showListRoom();
        
    }
    public void showRoomMap() {
        roomMapPanel.setVisible(true);
    }
    
}
