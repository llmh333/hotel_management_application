/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.respone.OTPCodeRespone;

/**
 *
 * @author lminh
 */
public interface IOTPService {
    
    public OTPCodeRespone genarateOTP();
    
    public boolean checkOTP(OTPCodeRespone otpcr, String otp);
}
