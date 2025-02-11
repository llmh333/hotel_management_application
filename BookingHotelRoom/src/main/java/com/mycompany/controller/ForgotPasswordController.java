/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;
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
import com.mycompany.view.SigninView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.Timer;

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
//    private void sendOTP() {
//        String receivedEmail = forgotPasswordView.txtReceivedEmail.getText();
//        try {
//            EntityManager entityManager = HibernateUtil.getEntityManager();
//            TypedQuery<User> query = entityManager.createQuery("FROM User WHERE email = :email",User.class);
//            query.setParameter("email", receivedEmail);
//            User u = query.getSingleResult();
//            System.out.println(u.toString());
//
//            if (receivedEmail.isBlank()) {
//                JOptionPane.showMessageDialog(forgotPasswordView, "Vui lòng điền email");
//            } else {
//                otp = OTPServiceIplm.genarateOTP();
//                Timer timer = new Timer(1000, new ActionListener() {
//                int countdown = 60;
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    forgotPasswordView.btnGetCode.setEnabled(false);
//                    forgotPasswordView.btnGetCode.setText(countdown + " giây");
//                    countdown--;
//                    if (countdown < 0) {
//                        ((Timer) e.getSource()).stop(); // Dừng Timer khi countdown = 0
//                        forgotPasswordView.btnGetCode.setText("Lấy mã");
//                        forgotPasswordView.btnGetCode.setEnabled(true);
//                    }
//                }
//            });
//            timer.start();
//                EmailServiceIplm.sendEmail(otp, receivedEmail);
//                forgotPasswordView.labelNotifi.setText("Mã xác thực đã được gửi đến email của bạn !");
//            }
//        } catch (NoResultException e) {
//            JOptionPane.showMessageDialog(forgotPasswordView, "Không tồn tại email này trên hệ thống");
//        }
//        
//    }
    
//    private void confirmOTP() {
//        int check;
//        String currentOTP = forgotPasswordView.txtOTPCode.getText();
//        String newPassword = forgotPasswordView.txtNewPassword.getText();
//        String email = forgotPasswordView.txtReceivedEmail.getText();
//        if (currentOTP.isBlank() || newPassword.isBlank() || email.isBlank()) {
//            JOptionPane.showMessageDialog(forgotPasswordView, "Vui lòng điền đầy đủ thông tin !!!");
//        } else {
//            check = OTPServiceIplm.checkOTP(currentOTP);
//             if (check == 0) {
//                String encrypPassword = UserDAO.hashPassword(forgotPasswordView.txtNewPassword.getText());
//                EntityManager entityManager = HibernateUtil.getEntityManager();
//                Query query = entityManager.createQuery("UPDATE User SET password = :newPassword WHERE email = :email");
//                query.setParameter("newPassword", encrypPassword);
//                query.setParameter("email", email);
//                entityManager.getTransaction().begin();
//                query.executeUpdate();                 
//                entityManager.getTransaction().commit();
//                forgotPasswordView.txtNewPassword.setText("");
//                forgotPasswordView.txtOTPCode.setText("");
//                forgotPasswordView.txtReceivedEmail.setText("");
//                JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP đúng, đổi mật khẩu thành công");
//                
//
//             } 
//             if (check == 1) {
//                JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP đã hết hạn");
//             }
//             if (check == 2) {
//                JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP không tồn tại");
//             }
//        }
//    }
//    private void backForgotPassword() {
//        forgotPasswordView.dispose();
//    }
}

