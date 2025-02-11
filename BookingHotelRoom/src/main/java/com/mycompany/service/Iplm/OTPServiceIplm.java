/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.respone.OTPCodeRespone;
import com.mycompany.service.IOTPService;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lminh
 */
@Getter
@Setter

public class OTPServiceIplm implements IOTPService{
    private static String otp;
    private static LocalDateTime expiryOTP;
    private static final SecureRandom random = new SecureRandom();
    
    public OTPServiceIplm() {
        
    }
    
    public String getOTP() {
        return this.otp;
    }
    
    public LocalDateTime getExpiryOTP() {
        return this.expiryOTP;
    }
    
    public OTPCodeRespone genarateOTP() {
        OTPServiceIplm.otp = String.format("%06d", random.nextInt(100000));
        OTPServiceIplm.expiryOTP = LocalDateTime.now().plusMinutes(1);
        return new OTPCodeRespone().builder().otpCode(otp).expiryOTPCode(expiryOTP).build();
    }
    
    public boolean checkOTP(OTPCodeRespone otpcr, String otp) {
        if (otpcr.getOtpCode().equals(otp)) {
            return !LocalDateTime.now().isAfter(otpcr.getExpiryOTPCode());
        } else return false;
    }
    
}
