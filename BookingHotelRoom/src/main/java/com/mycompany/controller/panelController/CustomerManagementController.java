/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller.panelController;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.common.Validator;
import com.mycompany.controller.ChangeCustomerController;
import com.mycompany.model.Customer;
import com.mycompany.service.ICustomerService;
import com.mycompany.service.Iplm.CustomerServiceIplm;
import com.mycompany.view.ChangeCustomerView;
import com.mycompany.view.panel.CustomerManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author lminh
 */
public class CustomerManagementController {
    private CustomerManagement customerManagement;
    private ICustomerService customerService = new CustomerServiceIplm();
    
    public CustomerManagementController(CustomerManagement customerManagement) {
        this.customerManagement = customerManagement;
        initCustomerManagement();
    }

    public void updateCustomerTable() {
        customerService = new CustomerServiceIplm();
        List<Customer> customers = customerService.getAllCustomers();
        customerManagement.setTableCustomer(customers);
    }

    public void initCustomerManagement() {
        List<Customer> customers = customerService.getAllCustomers();
        this.customerManagement.setBtnAddCustomer(new AddCustomerController());
        this.customerManagement.setBtnChangeCustomer(new EditCustomerController());
        this.customerManagement.setBtnDeleteCustomer(new DeleteCustomerController());
        this.customerManagement.setTableCustomer(customers);
    }

    private class AddCustomerController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(customerManagement, "Xác nhận thêm khách hàng mới", "Thêm khách hàng", JOptionPane.OK_CANCEL_OPTION);
            if (ans == JOptionPane.OK_OPTION) {
                String name = customerManagement.getCustomerName();
                String email = customerManagement.getEmail();
                String phoneNumber = customerManagement.getPhoneNumber();
                LocalDate birthday = customerManagement.getBirthday();
                String sex = customerManagement.getSex();
                if (name.isBlank() || email.isBlank() || phoneNumber.isBlank() || sex.isBlank() || birthday == null) {
                    JOptionPane.showMessageDialog(customerManagement, "Vui lòng điền đầy đủ thông tin");
                } else if (Validator.isValidEmail(email)) {
                    JOptionPane.showMessageDialog(customerManagement, "Email không hợp lệ");
                } else if (Validator.isValidPhoneNumber(phoneNumber)) {
                    JOptionPane.showMessageDialog(customerManagement, "Số điện thoại không hợp lệ");
                } else {
                    Customer customer = Customer.builder()
                            .name(name)
                            .birthday(birthday)
                            .sex(sex)
                            .email(email)
                            .phoneNumber(phoneNumber)
                            .build();
                    int checkAdd = customerService.addCustomer(customer);
                    if (checkAdd == ExitCodeConfig.EXIT_CODE_OK) {
                        JOptionPane.showMessageDialog(customerManagement, "Thêm khách hàng mới thành công");
                        CustomerManagementController.this.updateCustomerTable();
                    } else if (checkAdd == ExitCodeConfig.EXIT_CODE_ELEMENT_EXISTS) {
                        JOptionPane.showMessageDialog(customerManagement, "Số điện thoại hoặc Email đã tồn tại");
                    } else if (checkAdd == ExitCodeConfig.EXIT_CODE_ERROR) {
                        JOptionPane.showMessageDialog(customerManagement, "Có lỗi xảy ra vui lòng thử lại");
                    }
                }

            }
        }
    }

    private class EditCustomerController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerService.findCustomerByID(customerManagement.getSelectionTable());
            ChangeCustomerController changeCustomerController = new ChangeCustomerController(new ChangeCustomerView(), CustomerManagementController.this, customer);
            changeCustomerController.showChangeCustomerView();
        }
    }

    private class DeleteCustomerController implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(customerManagement, "Bạn chắc chắn muốn xóa khách hàng đã chọn", "Xóa khách hàng", JOptionPane.OK_CANCEL_OPTION);
            if (ans == JOptionPane.OK_OPTION) {
                int checkDelete = customerService.deleteCustomer(customerManagement.getSelectionTable());
                if (checkDelete == ExitCodeConfig.EXIT_CODE_OK) {
                    JOptionPane.showMessageDialog(customerManagement, "Xóa khách hàng thành công");
                    CustomerManagementController.this.updateCustomerTable();
                } else if (checkDelete == ExitCodeConfig.EXIT_CODE_ELEMENT_EXISTS) {
                    JOptionPane.showMessageDialog(customerManagement, "Khách hàng đã chọn không tồn tại");
                } else if (checkDelete == ExitCodeConfig.EXIT_CODE_ERROR) {
                    JOptionPane.showMessageDialog(customerManagement, "Có lỗi xảy ra, vui lòng thử lại");
                } else if (checkDelete == ExitCodeConfig.EXIT_CODE_CAN_NOT_DELETE) {
                    JOptionPane.showMessageDialog(customerManagement, "Không thể xóa khách hàng đang thuê phòng");
                }
            }
        }
    }
}
