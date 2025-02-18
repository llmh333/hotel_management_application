/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.common;

/**
 *
 * @author lminh
 */
public class Validator {
    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isValidPhoneNumber(String phone) {
        return phone.matches("^(\\+84|0)[3-9][0-9]{8}$");
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
    
//    public static boolean isValidDateTime(String firstDateTime, String secondDateTime) {
//        
//    }
}
