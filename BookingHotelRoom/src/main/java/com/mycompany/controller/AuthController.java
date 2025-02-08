/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;
import com.mycompany.view.Dashboard;
import com.mycompany.view.ForgotPasswordView;
import com.mycompany.view.SigninView;
import com.mycompany.view.SignupView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author lminh
 */
public class AuthController {
    private final UserDAO userDAO;
    private final SigninView signinView;
    private final SignupView signupView;
    private ForgotPasswordView forgotPasswordView;
    public AuthController(SigninView signinView, SignupView signupView) {
        this.userDAO = new UserDAO();
        this.signinView = signinView;
        this.signupView = signupView;
        this.signinView.setSigninAction(e -> login());
        this.signinView.setFormSignupView(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Người dùng nhấn vào Đăng ký!");
                signinView.dispose();
                signupView.setVisible(true);

            }
        });
        this.signupView.setBtnBackAct((e) -> backSignUp());
        this.signupView.setBtnRegisterAct(e -> register());
    }
    
    private void login() {
        String username = signinView.txtUsername.getText();
        String password = signinView.txtPassword.getText();
        
        if (userDAO.login(username, password)) {
            JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
            signinView.dispose();
            new Dashboard().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Sai tài khoản hoặc mật khẩu!");
        }
    }
    
    private void register() {
        String username = signupView.txtUsername.getText();
        String password = signupView.txtPassword.getText();
        String email = signupView.txtEmail.getText();
        String phoneNumber = signupView.txtPhoneNummber.getText();
        String birthday = signupView.txtBirthday.getText();
        String name = signupView.txtName.getText();
        String address = signupView.txtAddress.getText();
        String sex = null;
        if (signupView.radioBtnMale.isSelected()) {
            sex = "Nam";
        }
        else if (signupView.radioBtnFemale.isSelected()) {
            sex = "Nữ";
        }
        if (username.isBlank() || password.isBlank() || address.isBlank() || email.isBlank() || phoneNumber.isBlank() || birthday.isBlank() || name.isBlank() || sex.isBlank()) {
            JOptionPane.showMessageDialog(signupView, "Vui lòng điền đầy đủ thông tin");
        } else {
            User user = new User().builder()
                    .name(name)
                    .birthday(birthday)
                    .username(username)
                    .password(password)
                    .sex(sex)
                    .email(email)
                    .phoneNumber(phoneNumber)
                    .address(address)
                    .build();
            if (userDAO.register(user)) {
                JOptionPane.showMessageDialog(signupView, "Đăng ký tài khoản thành công, quay lại trang đăng nhập");
            }
            else {
                JOptionPane.showMessageDialog(signupView, "Có lỗi xảy ra, vui lòng thử lại sau một vài phút");
            }
        }
    }
    
    private void backSignUp() {
        signupView.dispose();
        signinView.setVisible(true);
    }
}
