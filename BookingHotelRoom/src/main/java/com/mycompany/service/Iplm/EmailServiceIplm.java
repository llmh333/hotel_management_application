/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.common.MailConfig;
import com.mycompany.model.User;
import com.mycompany.respone.OTPCodeRespone;
import com.mycompany.service.IEmail;
import com.mycompany.service.IOTPService;
import com.mycompany.service.Iplm.OTPServiceIplm;
import com.mycompany.util.HibernateUtil;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import org.hibernate.Hibernate;
import org.hibernate.QueryParameterException;

/**
 *
 * @author lminh
 */
public class EmailServiceIplm implements IEmail{
    
    @Override
    public OTPCodeRespone sendMail(String receivedEmail) {
       // Get properties object
        try {
            if (!(checkEmail(receivedEmail))) {
                return null;
            }
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", MailConfig.HOST_NAME);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", MailConfig.TSL_PORT);

            // get Session
            Session session = Session.getDefaultInstance(props, new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
                }
            });
        // compose message
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivedEmail));
            message.setSubject("Mã OTP");
            IOTPService otpService = new OTPServiceIplm();
            OTPCodeRespone otpcr = otpService.genarateOTP();
            message.setText("Mã OTP của bạn là: " + otpcr.getOtpCode());
             
            // send message
            Transport.send(message);
            
            return otpcr;
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public boolean checkEmail(String receivedEmail) {
        EntityManager entityManager = HibernateUtil.getEntityManager();
        try {
            TypedQuery<User> query = entityManager.createQuery("FROM User WHERE email = :email",User.class);
            query.setParameter("email", receivedEmail);
            User user = query.getSingleResult();
            if (user != null) {
                return true;
            } 
        } catch (QueryParameterException e) {
            return false;
        }
        
        return false;
    }
    
}
