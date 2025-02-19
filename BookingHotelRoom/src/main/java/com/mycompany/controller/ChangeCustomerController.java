/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.controller.panelController.CustomerManagementController;
import com.mycompany.model.Customer;
import com.mycompany.service.ICustomerService;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.view.ChangeCustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lminh
 */
public class ChangeCustomerController {
    private ChangeCustomerView changeCustomerView;
    private ICustomerService customerService = new CustomerServiceIplm();
    private CustomerManagementController customerManagementController;
    private Customer customer;

    public ChangeCustomerController(ChangeCustomerView changeCustomerView, CustomerManagementController customerManagementController, Customer customer) {
        this.changeCustomerView = changeCustomerView;
        this.customerManagementController = customerManagementController;
        this.customer = customer;
        initChangeCustomer();
    }

    public void initChangeCustomer() {
        setInfor();
        this.changeCustomerView.setBtnConfirm(new Confirm());
    }

    public void showChangeCustomerView() {
        if (changeCustomerView != null) {
            changeCustomerView.setVisible(true);
        }
    }

    public void setInfor() {
        changeCustomerView.setName(customer.getName());
        changeCustomerView.setEmail(customer.getEmail());
        changeCustomerView.setPhoneNumber(customer.getPhoneNumber());
        changeCustomerView.setBoxSex(customer.getSex());
        changeCustomerView.setBirthday(customer.getBirthday());
    }

    private class Confirm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            customer.setName(changeCustomerView.getName());
            customer.setEmail(changeCustomerView.getEmail());
            customer.setPhoneNumber(changeCustomerView.getPhoneNumber());
            customer.setBirthday(changeCustomerView.getBirthday());
            customer.setSex(changeCustomerView.getSex());

            int checkChange = customerService.changeInfoCustomer(customer);
            if (checkChange == ExitCodeConfig.EXIT_CODE_OK) {
                JOptionPane.showMessageDialog(changeCustomerView, "Thay đổi thông tin khách hàng thành công");
                customerManagementController.updateCustomerTable();
                changeCustomerView.dispose();
            }
            else if (checkChange == ExitCodeConfig.EXIT_CODE_ERROR) {
                JOptionPane.showMessageDialog(changeCustomerView, "Có lỗi xảy ra, vui lòng thử lại");
            }
            else if (checkChange == ExitCodeConfig.EXIT_CODE_NO_RESULT) {
                JOptionPane.showMessageDialog(changeCustomerView, "Không tồn tại khách hàng này");
            }

        }
    }


}
