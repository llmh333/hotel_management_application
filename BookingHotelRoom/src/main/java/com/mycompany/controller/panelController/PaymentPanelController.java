/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller.panelController;

import com.mycompany.controller.BillController;
import com.mycompany.model.Bill;
import com.mycompany.model.Booking;
import com.mycompany.service.IBillService;
import com.mycompany.service.IBookingSerive;
import com.mycompany.service.Iplm.BillServiceIplm;
import com.mycompany.service.Iplm.BookingServiceIplm;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.view.panel.PaymentPanel;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JOptionPane;
import com.mycompany.service.ICustomerService;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.BillView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lminh
 */
public class PaymentPanelController {
    private PaymentPanel paymentPanel;
    private ICustomerService customerService = new CustomerServiceIplm();
    private IBookingSerive bookingService = new BookingServiceIplm();
    private IRoomService roomService = new RoomServiceIplm();
    private String customerPhoneNumber;
    
    public List<Customer> customers = customerService.getAllCustomers();

    public PaymentPanelController(PaymentPanel paymentPanel) {
        this.paymentPanel = paymentPanel;
        this.paymentPanel.setSelectionListCustomer(new SelectCustomer());
//        this.paymentPanel.set
        initPaymentPanel();
    }
    
    public PaymentPanel getPaymentPanel() {
        if (paymentPanel != null) {
            return paymentPanel;
        } else return null;
    }

    public void reloadService() {
        bookingService = new BookingServiceIplm();
        customerService = new CustomerServiceIplm();
        roomService = new RoomServiceIplm();
    }

    public void initPaymentPanel() {
        if (customers != null) {
            this.paymentPanel.setListCustomer(customers);
            this.paymentPanel.setSelectionListCustomer(new SelectCustomer());
            this.paymentPanel.setBtnPayment(new BtnPayment());
        }
    }
    
    
    private class SelectCustomer extends MouseAdapter {        
        public void mouseClicked(MouseEvent event) {
            if (event.getClickCount() == 2) {
                customerPhoneNumber = paymentPanel.getCustomerPhoneNumber();
                Customer customer = customerService.findCustomerByPhoneNumber(customerPhoneNumber);
                paymentPanel.setName(customer.getName());
                paymentPanel.setEmail(customer.getEmail());
                paymentPanel.setPhoneNumbe(customer.getPhoneNumber());
                paymentPanel.setSex(customer.getSex());
                paymentPanel.setBirthday(customer.getBirthday());
                List<Booking> bookings = bookingService.findBookingByCustomer(customer);
                paymentPanel.setTableBooking(bookings);
            }
        }     
    }
    
    private class BtnPayment implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Room room = roomService.findRoomByRoomNumber(paymentPanel.getSelectionValueBooking());
            Booking bookingSelected = bookingService.findBookingByRoom(room);
            
            LocalDateTime localDateTime =  LocalDateTime.now();
            double totalMinutes = Duration.between(bookingSelected.getCheckInTime(), localDateTime).toMinutes();

            double totalHours = totalMinutes/60;
            BigDecimal bigDecimal = new BigDecimal(totalHours);
            bigDecimal.setScale(2, RoundingMode.HALF_UP);
            totalHours = bigDecimal.doubleValue();
            double totalPrice = totalHours*room.getPrice();
            
            System.out.println(totalHours);

            Bill bill = Bill.builder()
                    .status("Chưa thanh toán")
                    .booking(bookingSelected)
                    .totalPrice(totalPrice)
                    .totalHours(totalHours)
                    .build();
            BillController billController = new BillController(new BillView(), bill, bookingSelected, PaymentPanelController.this);
            billController.showBillView();
        }
        
    }
    
    
}
