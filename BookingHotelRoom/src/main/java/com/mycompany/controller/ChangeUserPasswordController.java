/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.common.PasswordEncryption;
import com.mycompany.common.Validator;
import com.mycompany.model.User;
import com.mycompany.service.IUserService;
import com.mycompany.service.Iplm.UserServiceIplm;
import com.mycompany.view.ChangeCustomerView;
import com.mycompany.view.ChangeUserPasswordView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author lminh
 */
public class ChangeUserPasswordController {
    
    private ChangeUserPasswordView changeUserPasswordView;
    private User user;
    private IUserService userService = new UserServiceIplm();

    public ChangeUserPasswordController(ChangeUserPasswordView changeUserPasswordView, User user) {
        this.changeUserPasswordView = changeUserPasswordView;
        this.user = user;
        initChangePassword();
    }

    public void show_changePasswordView() {
        if (changeUserPasswordView != null) {
            changeUserPasswordView.setVisible(true);
        }
    }

    public void initChangePassword() {
        this.changeUserPasswordView.setBtnConfirm(new Confirm());
    }

    private class Confirm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String oldPassword = changeUserPasswordView.getOldPassword();
            String newPassword = changeUserPasswordView.getNewPassword();
            String repeatPassword = changeUserPasswordView.getRepeatPassword();
            User newUser = userService.findUserByID(user.getId());
            if (Validator.isValidPassword(newPassword) && Validator.isValidPassword(oldPassword)) {
                if (PasswordEncryption.checkPassword(oldPassword,newUser.getPassword()) && newPassword.equals(repeatPassword)) {
                    newUser.setPassword(PasswordEncryption.encryptPassword(newPassword));
                    int checkChangePassword = userService.changeUserPassword(newUser);
                    if (checkChangePassword == ExitCodeConfig.EXIT_CODE_OK) {
                        JOptionPane.showMessageDialog(changeUserPasswordView, "Đổi mật khẩu thành công");
                        if (changeUserPasswordView != null) {
                            changeUserPasswordView.dispose();
                        }
                    } else if (checkChangePassword == ExitCodeConfig.EXIT_CODE_ERROR) {
                        JOptionPane.showMessageDialog(changeUserPasswordView, "Có lỗi xảy ra vui lòng thử lại");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(changeUserPasswordView, "Mật khẩu phải từ 6 kí tự trở lên");
            }

        }
    }
}
