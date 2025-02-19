/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.InfoRoom;
import com.mycompany.controller.panelController.RoomMapController;
import com.mycompany.model.Booking;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IBookingSerive;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.BookingServiceIplm;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.AddCustomerView;
import com.mycompany.view.BookingRoomView;
import com.mycompany.view.DashboardView;
import com.mycompany.view.panel.RoomMapPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import com.mycompany.service.ICustomerService;

/**
 *
 * @author lminh
 */
public class BookingRoomController {
    private BookingRoomView bookingRoom;
    private ICustomerService customerService = new CustomerServiceIplm();
    private IRoomService roomService = new RoomServiceIplm();
    private IBookingSerive bookingSerive = new BookingServiceIplm();
    private Room room;
    private User user;
    public Customer customer;
    private List<Customer> customers;
    
    public BookingRoomController(BookingRoomView bookingRoom , Room room, User user) {
       this.bookingRoom = bookingRoom;
       this.room = room;
       this.user = user;
       this.customers = customerService.getAllCustomers();
       initBookingRoom();
    }
    
    public void initBookingRoom() {
        this.bookingRoom.setBtnConFirm(e -> book());
        this.bookingRoom.setSelectionPhoneNumber(new SelectPhoneNumber());
        this.bookingRoom.setBtnAddCustomer(new AddCustomer());
        this.bookingRoom.reload_customers(customers);
    }

    public void reloadCustomers() {
        this.customerService = new CustomerServiceIplm();
        bookingRoom.reload_customers(customerService.getAllCustomers());
    }

    
    public void book() {
        room.setCheckInTime(bookingRoom.getCheckinTime());
        room.setStatus(InfoRoom.STATUS_NOT_AVAILABEL);
        room.setCustomer_id(customer.getId());
        
        LocalDateTime localDateTime = LocalDateTime.now();
        Booking booking = Booking.builder()         
                .checkInTime(bookingRoom.getCheckinTime())
                .customer(this.customer)
                .room(room)
                .user(user)
                .timeBooking(localDateTime)
                .build();
        try {
            roomService.changeInfoRoom(room);
            bookingSerive.createBooking(booking);
            JOptionPane.showMessageDialog(bookingRoom, "Đặt phòng thành công");
            bookingRoom.dispose();
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(bookingRoom, "Có lỗi xảy ra vui lòng thử lại");
        }

    }
    
    private class SelectPhoneNumber extends MouseAdapter { 
        public Customer customer;
        public void mouseClicked(MouseEvent event) {
            if (event.getClickCount() == 2) {
                this.customer = bookingRoom.getSelectionPhoneNumber();
                BookingRoomController.this.customer = this.customer;
                bookingRoom.setName(customer.getName());
                bookingRoom.setEmail(customer.getEmail());
                bookingRoom.setBirthday(customer.getBirthday());
                bookingRoom.setPhoneNumnber(customer.getPhoneNumber());
                bookingRoom.setSex(customer.getSex());
            }
        }     
    }
    
    private class AddCustomer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new AddCustomerController(new AddCustomerView(), room, user, BookingRoomController.this);
        }
        
    }
    
}
