/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

/**
 *
 * @author lminh
 */
public class OTPService {
    private static String otp;
    private static LocalDateTime expiryOTP;
    private static final SecureRandom random = new SecureRandom();
    
    public static String genarateOTP() {
        OTPService.otp = String.format("%06d", random.nextInt(100000));
        OTPService.expiryOTP = LocalDateTime.now().plusMinutes(1);
        return otp;
    }
    
    public static int checkOTP(String otp) {
        if (otp.equals(OTPService.otp)) {
            if (LocalDateTime.now().isAfter(OTPService.expiryOTP)) {
                resetOTP();
                return 1;
            }
            else {
                resetOTP();
                return 0;
            }
        } else return 2;
    }
    
    public static void resetOTP() {
        otp = null;
        expiryOTP = null;
    }
}
