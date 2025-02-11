/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

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

/**
 *
 * @author lminh
 */
public final class AuthController {
//    private final UserDAO userDAO;
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
            UserRespone userRespone = userService.login(loginRequest);
            if (userRespone != null) {
                JOptionPane.showMessageDialog(signinView, "Đăng nhập thành công");
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
            if (signinView != null) {
                signupView.setVisible(false);
            }
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
            if (userService.register(registerRequest)) {
                JOptionPane.showMessageDialog(signupView, "Đăng ký tài khoản thành công");
            }
            else {
                System.out.println(registerRequest.toString());
                JOptionPane.showMessageDialog(signupView, "Có lỗi xảy ra, vui lòng thử lại");
            }
        }
        
    }
}
