/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bookinghotelroom;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.common.InfoRoom;
import com.mycompany.controller.AuthController;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.SigninView;
import jakarta.persistence.EntityManager;

import javax.swing.UIManager;
/**
 *
 * @author lminh
 */
public class BookingHotelRoom {
    static EntityManager entityManager = HibernateUtil.getEntityManager();
    public static void main(String[] args) {
        try {
//            User newUser = User.builder()
//                    .name("Nguyen Van B")
//                    .email("nguyenvana@gmail.com")
//                    .birthday("2000-01-01")
//                    .phoneNumber("0123456789")
//                    .address("Hanoi, Vietnam")
//                    .username("nguyenvanb")
//                    .password("123457") // Nên mã hóa password
//                    .sex("Male")
//                    .build();
//            Room room1 = Room.builder()
//                    .roomNumber("101")
//                    .roomType(InfoRoom.VIP)
//                    .quantity(InfoRoom.PHONG_BA)
//                    .status(InfoRoom.STATUS_AVAILABEL)
//                    .roomFeature("Full noi that")
//                    .price(500000)
//                    .build();
//            Room room2 = Room.builder()
//                    .roomNumber("102")
//                    .roomType(InfoRoom.VIP)
//                    .quantity(InfoRoom.PHONG_BA)
//                    .status(InfoRoom.STATUS_AVAILABEL)
//                    .roomFeature("Full noi that")
//                    .price(500000)
//                    .build();
//            Room room3 = Room.builder()
//                    .roomNumber("103")
//                    .roomType(InfoRoom.VIP)
//                    .quantity(InfoRoom.PHONG_BA)
//                    .status(InfoRoom.STATUS_AVAILABEL)
//                    .roomFeature("Full noi that")
//                    .price(500000)
//                    .build();
//            Room room4 = Room.builder()
//                    .roomNumber("104")
//                    .roomType(InfoRoom.VIP)
//                    .quantity(InfoRoom.PHONG_BA)
//                    .status(InfoRoom.STATUS_AVAILABEL)
//                    .roomFeature("Full noi that")
//                    .price(500000)
//                    .build();
//            Room room5 = Room.builder()
//                    .roomNumber("105")
//                    .roomType(InfoRoom.VIP)
//                    .quantity(InfoRoom.PHONG_BA)
//                    .status(InfoRoom.STATUS_AVAILABEL)
//                    .roomFeature("Full noi that")
//                    .price(500000)
//                    .build();
//            Customer newCustomer = Customer.builder()
//                    .name("Nguyen Van B")
//                    .birthday("1995-06-15")
//                    .email("nguyenvanb@gmail.com")
//                    .phoneNumber("0987654321")
//                    .status("Đang thuê phòng")
//                    .roomNumber(101)
//                    .build();


//            entityManager.getTransaction().commit();
            UIManager.setLookAndFeel(new FlatLightLaf());
            SigninView signinView = new SigninView();
            new AuthController(signinView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
