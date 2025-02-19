/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller.panelController;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.model.User;
import com.mycompany.service.IUserService;
import com.mycompany.service.Iplm.UserServiceIplm;
import com.mycompany.view.panel.UserManagementPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author lminh
 */
public class UserManagementController {
    private UserManagementPanel userManagementPanel;
    private IUserService userService = new UserServiceIplm();

    public UserManagementController(UserManagementPanel userManagementPanel) {
        this.userManagementPanel = userManagementPanel;
        initUserManagement();
    }

    public void initUserManagement() {
        setTableUser();
        this.userManagementPanel.setBtnDeleteUser(new DeleteUser());
        this.userManagementPanel.setBtnChangeRole(new ChangeRoleUser());
    }

    public void setTableUser() {
        List<User> userList = userService.getAllUsers();
        this.userManagementPanel.setTableUser(userList);
    }

    private class DeleteUser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(userManagementPanel, "Bạn chắc chắn muốn xóa nhân viên đã chọn", "Xóa nhân viên", JOptionPane.OK_CANCEL_OPTION);
            if (ans == JOptionPane.OK_OPTION) {
                int checkDelete = userService.deleteUserByID(userManagementPanel.getSelectedUser());
                if (checkDelete == ExitCodeConfig.EXIT_CODE_OK) {
                    JOptionPane.showMessageDialog(userManagementPanel, "Xóa nhân viên thành công");
                    UserManagementController.this.setTableUser();
                } else if (checkDelete == ExitCodeConfig.EXIT_CODE_ERROR) {
                    JOptionPane.showMessageDialog(userManagementPanel, "Có lỗi xảy ra, vui lòng thử lại");
                }
            }
        }
    }

    private class ChangeRoleUser implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User user = userService.findUserByID(userManagementPanel.getSelectedUser());
            if (user.getRole().equals("ADMIN")) {
                int ans = JOptionPane.showConfirmDialog(userManagementPanel, "Bạn chắc chắn muốn sửa vai trò người dùng thành NHÂN VIÊN", "Sửa vai trò ", JOptionPane.OK_CANCEL_OPTION);
                if (ans == JOptionPane.OK_OPTION) {
                    user.setRole("NHÂN VIÊN");
                    int checkChange = userService.changeInforUser(user);
                    if (checkChange == ExitCodeConfig.EXIT_CODE_OK) {
                        JOptionPane.showMessageDialog(userManagementPanel,"Thay đổi vai trò thành công");
                        UserManagementController.this.setTableUser();
                    } else if (checkChange == ExitCodeConfig.EXIT_CODE_ERROR) {
                        JOptionPane.showMessageDialog(userManagementPanel, "Có lỗi xảy ra, vui lòng thử lại");
                    }
                }
            } else if (user.getRole().equals("NHÂN VIÊN")) {
                int ans = JOptionPane.showConfirmDialog(userManagementPanel, "Bạn chắc chắn muốn sửa vai trò người dùng thành ADMIN", "Sửa vai trò ", JOptionPane.OK_CANCEL_OPTION);
                if (ans == JOptionPane.OK_OPTION) {
                    user.setRole("ADMIN");
                    int checkChange = userService.changeInforUser(user);
                    if (checkChange == ExitCodeConfig.EXIT_CODE_OK) {
                        JOptionPane.showMessageDialog(userManagementPanel,"Thay đổi vai trò thành công");
                        UserManagementController.this.setTableUser();
                    } else if (checkChange == ExitCodeConfig.EXIT_CODE_ERROR) {
                        JOptionPane.showMessageDialog(userManagementPanel, "Có lỗi xảy ra, vui lòng thử lại");
                    }
                }
            }
        }
    }
}
