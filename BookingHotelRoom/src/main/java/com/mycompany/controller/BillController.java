/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.InfoRoom;
import com.mycompany.controller.panelController.PaymentPanelController;
import com.mycompany.model.Bill;
import com.mycompany.model.Booking;
import com.mycompany.model.Customer;
import com.mycompany.model.Room;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.view.BillView;
import com.mycompany.service.IBillService;
import com.mycompany.service.IBookingSerive;
import com.mycompany.service.ICustomerService;
import com.mycompany.service.Iplm.BillServiceIplm;
import com.mycompany.service.Iplm.BookingServiceIplm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.common.InfoRoom.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author lminh
 */
public class BillController {
    
    private BillView billView;
    private PaymentPanelController paymentPanelController;
    private IBillService billService = new BillServiceIplm();
    private IRoomService roomService = new RoomServiceIplm();
    private Bill bill;
    private Booking booking;
    
    public BillController(BillView billView, Bill bill, Booking booking, PaymentPanelController paymentPanelController) {
        this.billView = billView;
        this.bill = bill;
        this.booking = booking;
        this.paymentPanelController = paymentPanelController;
        initBillView();
    }
    
    public void showBillView() {
        if (billView != null) {
            billView.setVisible(true);
        }
    }
    
    
    public void initBillView() {
        
        LocalDateTime localDateTime =  LocalDateTime.now();
        String createTime;
        String today;
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        createTime = localDateTime.format(formatterTime);
        today = localDateTime.format(formatterDay);
        String checkoutTime = localDateTime.format(formatter);


        this.billView.setBillID(bill.getId());  
        this.billView.setUserName(booking.getUser().getName());
        this.billView.setTotalPrice(bill.getTotalPrice());
        this.billView.setCreateTime(createTime);
        this.billView.setToday(today);
        this.billView.setTableBill(booking, bill, checkoutTime);
        this.billView.setBtnPaymentAct(new BtnPayment());
    }
    
    private class BtnPayment implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(billView, "Xác nhận thanh toán", "Thanh toán hóa đơn", JOptionPane.OK_CANCEL_OPTION);
            if (ans == 0) {
                Room room = booking.getRoom();
                room.setCheckInTime(null);
                room.setCheckOutTime(null);
                room.setCustomer_id(null);
                room.setStatus(InfoRoom.STATUS_AVAILABEL);
                roomService.changeInfoRoom(room);
                IBookingSerive bookingSerive = new BookingServiceIplm();
                if (bookingSerive.deleteBookingByID(booking.getId())) {
                    bill.setPaymentTime(LocalDateTime.now());
                    bill.setBooking(null);
                    bill.setStatus("Đã thanh toán");
                    IBillService billService = new BillServiceIplm();
                    billService.addBill(bill);
                    JOptionPane.showMessageDialog(billView, "Thanh toán thành công");
                    paymentPanelController.reloadService();
                } else {
                    JOptionPane.showMessageDialog(billView, "Có lỗi xảy ra, vui lòng thử lại");
                }
            }
            
        }    
    }
    
}
