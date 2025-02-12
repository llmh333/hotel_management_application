/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;


import com.mycompany.request.ForgotPasswordRequest;
import com.mycompany.respone.OTPCodeRespone;
import com.mycompany.service.IEmail;
import com.mycompany.service.IOTPService;
import com.mycompany.service.IUserService;
import com.mycompany.service.Iplm.EmailServiceIplm;
import com.mycompany.service.Iplm.OTPServiceIplm;
import com.mycompany.service.Iplm.UserServiceIplm;
import com.mycompany.common.PasswordEncryption;
import com.mycompany.view.ForgotPasswordView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author lminh
 */
//hitcrazy5@gmail.com
//zfsx nzlf nnxn wgt
public class ForgotPasswordController {
    private ForgotPasswordView forgotPasswordView;
//    private String otp;
    private OTPCodeRespone otpcr;
    public ForgotPasswordController(ForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
        initForgotPassword();
    }
    public void initForgotPassword() {
        this.forgotPasswordView.setbtnGetCodeAction(new sendOTP());
        this.forgotPasswordView.setbtnConfirmAction(new confirmNewPassword());
        this.forgotPasswordView.setbtnBack(new setBtnBack());
    }
    
    public void showForgotPasswordView() {
        if (forgotPasswordView != null) {
            forgotPasswordView.setVisible(true);
        }
    }
    private class sendOTP implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ForgotPasswordRequest forgotPasswordRequest = forgotPasswordView.getForgotPasswordRequest();
            IEmail emailOTP = new EmailServiceIplm();
            System.out.println(forgotPasswordRequest.getEmail());
            otpcr = emailOTP.sendMail(forgotPasswordRequest.getEmail());
            if (otpcr != null) {
                System.out.println("Gui email thanh cong");
            } else {
                JOptionPane.showMessageDialog(forgotPasswordView, "Email không tồn tại trong hệ thống");
            }
        }
        
    }
    private class confirmNewPassword implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ForgotPasswordRequest forgotPasswordRequest = forgotPasswordView.getForgotPasswordRequest();
            if (forgotPasswordRequest.getEmail().isBlank() || forgotPasswordRequest.getNewPassword().isBlank() || forgotPasswordRequest.getOtp().isBlank()) {
                JOptionPane.showMessageDialog(forgotPasswordView, "Vui lòng điền đầy đủ thông tin");
            }
            else {
                if (otpcr != null) {
                    IOTPService otpService = new OTPServiceIplm();
                    if (otpService.checkOTP(otpcr, forgotPasswordRequest.getOtp())) {
                        IUserService userService = new UserServiceIplm();
                        if (userService.changePassword(PasswordEncryption.encryptPassword(forgotPasswordRequest.getNewPassword()), forgotPasswordRequest.getEmail())) {
                            JOptionPane.showMessageDialog(forgotPasswordView, "Đổi mật khẩu thành công");
                        } else {
                            JOptionPane.showMessageDialog(forgotPasswordView, "Có lỗi xảy ra vui lòng thử lại");
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP không tồn tại");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(forgotPasswordView, "Email không tồn tại trong hệ thống");
                }
            }
            
        }
    }
    
    private class setBtnBack implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            forgotPasswordView.dispose();
        }
        
    }
}

