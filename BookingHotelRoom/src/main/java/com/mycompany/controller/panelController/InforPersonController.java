/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller.panelController;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.common.Validator;
import com.mycompany.controller.ChangeCustomerController;
import com.mycompany.controller.ChangeUserPasswordController;
import com.mycompany.model.User;
import com.mycompany.service.IUserService;
import com.mycompany.service.Iplm.UserServiceIplm;
import com.mycompany.view.ChangeCustomerView;
import com.mycompany.view.ChangeUserPasswordView;
import com.mycompany.view.panel.InforPersonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 *
 * @author lminh
 */
public class InforPersonController {

    private InforPersonPanel inforPersonPanel;
    private IUserService userService = new UserServiceIplm();
    private User user;

    public InforPersonController(InforPersonPanel inforPersonPanel, User user) {
        this.inforPersonPanel = inforPersonPanel;
        this.user = user;
        initInforPersonPanel();
    }

    public void initInforPersonPanel() {
        setInformationPanel();
        this.inforPersonPanel.setBtnChangeInforPerson(new ChangeInforPerson());
        this.inforPersonPanel.setBtnChangePassword(new ChangePassword());
    }

    public void setInformationPanel() {
        this.inforPersonPanel.setName(user.getName());
        this.inforPersonPanel.setEmail(user.getEmail());
        this.inforPersonPanel.setPhoneNumber(user.getPhoneNumber());
        this.inforPersonPanel.setAddress(user.getAddress());
        this.inforPersonPanel.setUsername(user.getUsername());
        this.inforPersonPanel.setBirthday(user.getBirthday());
        this.inforPersonPanel.setRole(user.getRole());
        this.inforPersonPanel.setBoxSex(user.getSex());
    }

    private class ChangePassword implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ChangeUserPasswordController changeUserPasswordController = new ChangeUserPasswordController(new ChangeUserPasswordView(), InforPersonController.this.user);
            changeUserPasswordController.show_changePasswordView();
        }
    }

    private class ChangeInforPerson implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int ans = JOptionPane.showConfirmDialog(inforPersonPanel,"Bạn chắc chắn muốn thay đổi thông tin", "Thay đổi thông tin cá nhân", JOptionPane.OK_CANCEL_OPTION);
            if (ans == JOptionPane.OK_OPTION) {
                String name = inforPersonPanel.getName();
                String email = inforPersonPanel.getEmail();
                String phoneNumber = inforPersonPanel.getPhoneNumber();
                String address = inforPersonPanel.getAddress();
                String role = inforPersonPanel.getRole();
                String sex = inforPersonPanel.getSex();
                LocalDate birthday = inforPersonPanel.getBirthday();

                if (name.isBlank() || email.isBlank() || phoneNumber.isBlank() || address.isBlank() || role.isBlank() || sex.isBlank() || birthday == null) {
                    JOptionPane.showMessageDialog(inforPersonPanel, "Vui lòng điền đầy đủ thông tin");
                } else if (!Validator.isValidEmail(inforPersonPanel.getEmail())) {
                    JOptionPane.showMessageDialog(inforPersonPanel, "Email không hợp lệ");
                } else if (!Validator.isValidPhoneNumber(inforPersonPanel.getPhoneNumber())) {
                    JOptionPane.showMessageDialog(inforPersonPanel, "Số điện thoại không hợp lệ");
                } else {
                    User oldUser = userService.findUserByID(user.getId());
                    oldUser.setAddress(address);
                    oldUser.setEmail(email);
                    oldUser.setPhoneNumber(phoneNumber);
                    oldUser.setBirthday(birthday);
                    oldUser.setRole(role);
                    oldUser.setName(name);
                    oldUser.setSex(sex);

                    int checkChangeInfor = userService.changeInforUser(oldUser);
                    if (checkChangeInfor == ExitCodeConfig.EXIT_CODE_OK) {
                        JOptionPane.showMessageDialog(inforPersonPanel, "Thay đổi thông tin thành công");
                        InforPersonController.this.setInformationPanel();

                    } else if (checkChangeInfor == ExitCodeConfig.EXIT_CODE_ERROR) {
                        JOptionPane.showMessageDialog(inforPersonPanel, "Có lỗi xảy ra, vui lòng thử lại");
                    }
                }

            }

        }
    }
}
