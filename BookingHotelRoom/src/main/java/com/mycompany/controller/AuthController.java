/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.common.Validator;
import com.mycompany.model.User;
import com.mycompany.request.LoginRequest;
import com.mycompany.request.RegisterRequest;
import com.mycompany.respone.UserRespone;
import com.mycompany.service.Iplm.UserServiceIplm;
import com.mycompany.view.ForgotPasswordView;
import com.mycompany.view.SigninView;
import com.mycompany.view.SignupView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.mycompany.service.IUserService;
import com.mycompany.view.DashboardView;

/**
 *
 * @author lminh
 */
public class AuthController {
    private SigninView signinView;
    private SignupView signupView;
    private IUserService userService = new UserServiceIplm();
    
    public AuthController(SigninView signinView) {
        this.signinView = signinView;
        initSigninView();
    }
    
    public AuthController(SignupView signupView) {
        this.signupView = signupView;
        initSignupView();
    }
    
    public void showSigninView() {
        this.signinView.setVisible(true);
    }
    
    public void showSignupView() {
        this.signupView.setVisible(true);
    }
    
    public void initSignupView() {
        this.signupView.setBtnBackAct(new setBtnBack());
        this.signupView.setBtnRegisterAct(new setBtnRegister());
    }
    
    private void initSigninView() {
        this.signinView.setBtnCloseAct(new CloseApp());
        this.signinView.setBtnLogin(new loginListener());
        this.signinView.setBtnForgotPassword(new setForgotPasswordView());
        this.signinView.setBtnRegister(new setSignUpViewListener());
        
    }
    
    private class setForgotPasswordView implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ForgotPasswordView forgotPasswordView = new ForgotPasswordView();
            forgotPasswordView.setVisible(true);
            new ForgotPasswordController(forgotPasswordView);
        }
        
    }
     
    private class loginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoginRequest loginRequest = signinView.getLoginRequest();
            User user = userService.login(loginRequest);
            if (user != null) {
                if (user.getStatus().equals("online")) {
                    JOptionPane.showMessageDialog(signinView, "Tài khoản đang đăng nhập ở nơi khác");
                    userService = new UserServiceIplm();
                }
                else {
                    JOptionPane.showMessageDialog(signinView, "Đăng nhập thành công");
                    User u = userService.findUserByID(user.getId());
                    userService.statusUser(user.getId(), "online");
                    DashboardView dashboardView = new DashboardView();
                    DashboardController dashboardController = new DashboardController(dashboardView, user);
                    if (signinView != null) {
                        signinView.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(signinView, "Tài khoản hoặc mật khẩu không chính xác");
            }
        }
    }
    
    private class setSignUpViewListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            SignupView signupView = new SignupView();
            AuthController authController = new AuthController(signupView);
            authController.showSignupView();         
        }
        
    }
    
    private class CloseApp implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
        
    }
    
    private class setBtnBack implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            signupView.dispose();
            if (signinView != null) {
                signinView.setVisible(true);
            }
        }
        
    }
    
    private class setBtnRegister implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            RegisterRequest registerRequest = signupView.getRegisterRequest();
            System.out.println(registerRequest.toString());
            if (registerRequest.getEmail().isBlank()
                    || registerRequest.getPassword().isBlank()
                    || registerRequest.getUsername().isBlank()
                    || registerRequest.getName().isBlank()
                    || registerRequest.getSex() == null
                    || registerRequest.getPhoneNumber().isBlank()
                    || registerRequest.getAddress().isBlank()
                    || registerRequest.getBirthday() == null) {
                JOptionPane.showMessageDialog(signupView, "Vui lòng điền đầy đủ thông tin");
            }
            else {
                if (Validator.isValidEmail(registerRequest.getEmail()) == false) {
                    JOptionPane.showMessageDialog(signupView, "Email không hợp lệ");
                } else if (Validator.isValidPhoneNumber(registerRequest.getPhoneNumber()) == false) {
                    JOptionPane.showMessageDialog(signupView, "Số điện thoại không hợp lệ");
                } else if (Validator.isValidPassword(registerRequest.getPassword()) == false) {
                    JOptionPane.showMessageDialog(signupView, "Mật khẩu phải nhiều hơn 6 kí tự");
                } else {
                    int checkUser = userService.register(registerRequest);
                    if (checkUser == ExitCodeConfig.EXIT_CODE_OK) {
                        JOptionPane.showMessageDialog(signupView, "Đăng ký tài khoản thành công");
                        if (signupView != null) {
                            signupView.dispose();
                        }
                    }
                    else if (checkUser == ExitCodeConfig.EXIT_CODE_EMAIL_EXISTS) {
                        System.out.println(registerRequest.toString());
                        JOptionPane.showMessageDialog(signupView, "Email đã tồn tại");
                    }
                    else if (checkUser == ExitCodeConfig.EXIT_CODE_USERNAME_EXISTS) {
                        JOptionPane.showMessageDialog(signupView, "Tên đăng nhập đã tồn tại");
                    }
                    else if (checkUser == ExitCodeConfig.EXIT_CODE_PHONENUMBER_EXISTS) {
                        JOptionPane.showMessageDialog(signupView, "Số điện thoại đã tồn tại");
                    }
                    else if (checkUser == ExitCodeConfig.EXIT_CODE_EMAIL_INVALID) {
                        JOptionPane.showMessageDialog(signupView, "Email không hợp lệ");
                    }
                }
                
            }

        }
        
    }
}
