/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDAO;
import com.mycompany.model.User;
import com.mycompany.service.EmailService;
import com.mycompany.service.OTPService;
import com.mycompany.util.HibernateUtil;
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
    private String otp;
    public ForgotPasswordController(ForgotPasswordView forgotPasswordView) {
        this.forgotPasswordView = forgotPasswordView;
        this.forgotPasswordView.setbtnGetCodeAction(e -> sendOTP());
        this.forgotPasswordView.setbtnConfirmAction(e -> confirmOTP());
        this.forgotPasswordView.setbtnBack(e -> backForgotPassword());
    }
    
    private void sendOTP() {
        String receivedEmail = forgotPasswordView.txtReceivedEmail.getText();
        try {
            EntityManager entityManager = HibernateUtil.getEntityManager();
            TypedQuery<User> query = entityManager.createQuery("FROM User WHERE email = :email",User.class);
            query.setParameter("email", receivedEmail);
            User u = query.getSingleResult();
            System.out.println(u.toString());

            if (receivedEmail.isBlank()) {
                JOptionPane.showMessageDialog(forgotPasswordView, "Vui lòng điền email");
            } else {
                otp = OTPService.genarateOTP();
                Timer timer = new Timer(1000, new ActionListener() {
                int countdown = 60;

                @Override
                public void actionPerformed(ActionEvent e) {
                    forgotPasswordView.btnGetCode.setEnabled(false);
                    forgotPasswordView.btnGetCode.setText(countdown + " giây");
                    countdown--;
                    if (countdown < 0) {
                        ((Timer) e.getSource()).stop(); // Dừng Timer khi countdown = 0
                        forgotPasswordView.btnGetCode.setText("Lấy mã");
                        forgotPasswordView.btnGetCode.setEnabled(true);
                    }
                }
            });
            timer.start();
                EmailService.sendEmail(otp, receivedEmail);
                forgotPasswordView.labelNotifi.setText("Mã xác thực đã được gửi đến email của bạn !");
            }
        } catch (NoResultException e) {
            JOptionPane.showMessageDialog(forgotPasswordView, "Không tồn tại email này trên hệ thống");
        }
        
    }
    
    private void confirmOTP() {
        int check;
        String currentOTP = forgotPasswordView.txtOTPCode.getText();
        String newPassword = forgotPasswordView.txtNewPassword.getText();
        String email = forgotPasswordView.txtReceivedEmail.getText();
        if (currentOTP.isBlank() || newPassword.isBlank() || email.isBlank()) {
            JOptionPane.showMessageDialog(forgotPasswordView, "Vui lòng điền đầy đủ thông tin !!!");
        } else {
            check = OTPService.checkOTP(currentOTP);
             if (check == 0) {
                String encrypPassword = UserDAO.hashPassword(forgotPasswordView.txtNewPassword.getText());
                EntityManager entityManager = HibernateUtil.getEntityManager();
                Query query = entityManager.createQuery("UPDATE User SET password = :newPassword WHERE email = :email");
                query.setParameter("newPassword", encrypPassword);
                query.setParameter("email", email);
                entityManager.getTransaction().begin();
                query.executeUpdate();                 
                entityManager.getTransaction().commit();
                forgotPasswordView.txtNewPassword.setText("");
                forgotPasswordView.txtOTPCode.setText("");
                forgotPasswordView.txtReceivedEmail.setText("");
                JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP đúng, đổi mật khẩu thành công");
                

             } 
             if (check == 1) {
                JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP đã hết hạn");
             }
             if (check == 2) {
                JOptionPane.showMessageDialog(forgotPasswordView, "Mã OTP không tồn tại");
             }
        }
    }
    private void backForgotPassword() {
        forgotPasswordView.dispose();
        new SigninView().setVisible(true);
    }
}

