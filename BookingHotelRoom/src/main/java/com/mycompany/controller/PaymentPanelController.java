/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.service.ICustomer;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.view.panel.PaymentPanel;
import com.mycompany.model.Customer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author lminh
 */
public class PaymentPanelController {
    private PaymentPanel paymentPanel;
    private ICustomer customerService = new CustomerServiceIplm();
    private String customerName;
    
    public List<Customer> customers = customerService.getAllCustomers();

    public PaymentPanelController(PaymentPanel paymentPanel) {
        this.paymentPanel = paymentPanel;
        initPaymentPanel();
    }
    
    public PaymentPanel getPaymentPanel() {
        if (paymentPanel != null) {
            return paymentPanel;
        } else return null;
    }
    
    public void initPaymentPanel() {
        if (customers != null) {
            this.paymentPanel.setListCustomer(customers);
            this.paymentPanel.getSelectionListCustomer(new SelectCustomer());
//            this.paymentPanel.get
        }
    }
    
    
    private class SelectCustomer extends MouseAdapter {        
        public void mouseClicked(MouseEvent event) {
            if (event.getClickCount() == 2) {
                customerName = paymentPanel.getCustomerName();
//                Customer 
//                paymentPanel.setTableBooking(customerName);
            }
        }     
    }
    
    
    
    
}
