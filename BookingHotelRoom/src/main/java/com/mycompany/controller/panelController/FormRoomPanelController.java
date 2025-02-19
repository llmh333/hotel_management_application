/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller.panelController;

import com.mycompany.controller.BookingRoomController;
import com.mycompany.controller.InformationRoomController;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.BookingRoomView;
import com.mycompany.view.DashboardView;
import com.mycompany.view.panel.FormRoomPanel;
import com.mycompany.view.InformationRoomView;
import com.mycompany.view.panel.RoomMapPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lminh
 */
public class FormRoomPanelController {
    private FormRoomPanel formRoomPanel;
    private IRoomService roomService = new RoomServiceIplm();
    private String roomNumber;
    private User user;
    private RoomMapController roomMapController;
    
    public FormRoomPanelController(FormRoomPanel formRoomPanel , String roomNumber, User user) {
        this.formRoomPanel = formRoomPanel;
        this.roomNumber = roomNumber;
        this.user = user;
        initFormRoomPanel();
    }
    
    public void initFormRoomPanel() {
        this.formRoomPanel.setMenuItemBookRoom(new BookRoom());
        this.formRoomPanel.setMenuItemInforRoom(new InforRoom());
    }
    
    private class BookRoom implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BookingRoomView bookingRoom = new BookingRoomView();
            bookingRoom.setVisible(true);
            Room room = roomService.findRoomByRoomNumber(roomNumber);
            BookingRoomController bookingRoomController = new BookingRoomController(bookingRoom, room , user);
        }
    }
    
    private class InforRoom implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InformationRoomView informationRoom = new InformationRoomView();
            informationRoom.setVisible(true);
            Room room = roomService.findRoomByRoomNumber(roomNumber);
            InformationRoomController informationRoomController = new InformationRoomController(informationRoom, room);
        }
        
    }
}
