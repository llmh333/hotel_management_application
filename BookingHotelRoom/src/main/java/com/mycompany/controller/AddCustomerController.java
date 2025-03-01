/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.ExitCodeConfig;
import static com.mycompany.common.ExitCodeConfig.*;
import com.mycompany.common.Validator;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.view.AddCustomerView;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.BookingRoomView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import com.mycompany.service.ICustomerService;

/**
 *
 * @author lminh
 */
public class AddCustomerController {
    
    private ICustomerService customerService = new CustomerServiceIplm();
    private AddCustomerView addCustomerView;
    private Room room;
    private User user;
    private BookingRoomController bookingRoomController;
    
    
    public AddCustomerController(AddCustomerView addCustomerView, Room room, User user, BookingRoomController bookingRoomController) {
        this.addCustomerView = addCustomerView;
        this.bookingRoomController = bookingRoomController;
        this.room = room;
        this.user = user;
        initAddCustomerController();
    }
    
    public void initAddCustomerController() {
        this.addCustomerView.setVisible(true);
        this.addCustomerView.setBtnConfirmAct(new AddCustomer());
    }
    
    
    private class AddCustomer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            String name = addCustomerView.getName();
            String email = addCustomerView.getEmail();
            LocalDate birthday = addCustomerView.getBirthday();
            String phoneNumber = addCustomerView.getPhoneNumnber();
            String sex = addCustomerView.getSex();
            
            if (name.isBlank() || email.isBlank() || birthday == null || phoneNumber.isBlank() || sex.isBlank()) {
                JOptionPane.showMessageDialog(addCustomerView, "Vui lòng điền đầy đủ thông tin");
            } else if (!(Validator.isValidPhoneNumber(phoneNumber))){
                JOptionPane.showMessageDialog(addCustomerView, "Số điện thoại không hợp lệ");
            } else if (!Validator.isValidEmail(email)) {
                JOptionPane.showMessageDialog(addCustomerView, "Email không hợp lệ");
            } else {
                Customer customer = Customer.builder()
                        .name(name)
                        .birthday(birthday)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .sex(sex)
                        .build();
                
                int check_add_customer = customerService.addCustomer(customer);
                if (check_add_customer == EXIT_CODE_OK) {
                    JOptionPane.showMessageDialog(addCustomerView, "Thêm khách hàng mới thành công");
                    bookingRoomController.reloadCustomers();
                    addCustomerView.dispose();
                } else if (check_add_customer == EXIT_CODE_ELEMENT_EXISTS) {
                    JOptionPane.showMessageDialog(addCustomerView, "Khách hàng này đã có trong hệ thống");
                }
            }
            
        }
        
    }
}
