/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.InfoRoom;
import com.mycompany.model.Booking;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IBookingSerive;
import com.mycompany.service.ICustomer;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.BookingServiceIplm;
import com.mycompany.service.Iplm.CustomerIplm;
import com.mycompany.view.BookingRoom;
import com.mycompany.view.DashboardView;
import com.mycompany.view.RoomMapPanel;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author lminh
 */
public class BookingRoomController {
    private BookingRoom bookingRoom;
    private ICustomer customerService = new CustomerIplm();
    private IRoomService roomService;
    private IBookingSerive bookingSerive = new BookingServiceIplm();
    private Room room;
    private User user;
    private DashboardView dashboardView;
    
    public BookingRoomController(BookingRoom bookingRoom, IRoomService roomService, Room room, User user) {
       this.bookingRoom = bookingRoom;
       this.roomService = roomService;
       this.room = room;
       this.user = user;
       initBookingRoom();
    }
    
    public void initBookingRoom() {
        this.bookingRoom.setBtnConFirm(e -> book());
    }
    
    public void book() {
        Customer customer = Customer.builder()
                .name(bookingRoom.getName())
                .birthday(bookingRoom.getBirthday())
                .phoneNumber(bookingRoom.getPhoneNumnber())
                .email(bookingRoom.getEmail())
                .sex(bookingRoom.getSex())
                .build();
   
        room.setCheckInTime(bookingRoom.getCheckinTime());
        room.setCheckoutTime(bookingRoom.getCheckoutTime());
        room.setStatus(InfoRoom.STATUS_NOT_AVAILABEL);
        
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(room);
        Booking booking = Booking.builder()         
                .checkInTime(bookingRoom.getCheckinTime())
                .checkOutTime(bookingRoom.getCheckoutTime())
                .customer(customer)
                .room(room)
                .customer(customer)
                .room(room)
                .user(user)
                .timeBooking(localDateTime)
                .build();
        try {
            customerService.addCustomer(customer);
            roomService.changeInfoRoom(room);
            bookingSerive.createBooking(booking);
            JOptionPane.showMessageDialog(bookingRoom, "Đặt phòng thành công");
            bookingRoom.dispose();
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(bookingRoom, "Có lỗi xảy ra vui lòng thử lại");
        }

    }
    
    
    
}
