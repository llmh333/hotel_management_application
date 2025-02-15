/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.view.BookingRoom;
import com.mycompany.view.DashboardView;
import com.mycompany.view.FormRoomPanel;
import com.mycompany.view.RoomMapPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lminh
 */
public class FormRoomPanelController {
    private FormRoomPanel formRoomPanel;
    private IRoomService roomService;
    private String roomNumber;
    private User user;
    private DashboardView dashboardView;
    
    public FormRoomPanelController(FormRoomPanel formRoomPanel, IRoomService roomService, String roomNumber, User user) {
        this.formRoomPanel = formRoomPanel;
        this.roomService = roomService;
        this.roomNumber = roomNumber;
        this.user = user;
        initFormRoomPanel();
    }
    
    public void initFormRoomPanel() {
        this.formRoomPanel.setMenuItemBookRoom(new bookRoom());
    }
    
    private class bookRoom implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BookingRoom bookingRoom = new BookingRoom();
            bookingRoom.setVisible(true);
            Room room = roomService.findRoomByRoomNumber(roomNumber);
            BookingRoomController bookingRoomController = new BookingRoomController(bookingRoom, roomService, room , user);
        }
    }
}
