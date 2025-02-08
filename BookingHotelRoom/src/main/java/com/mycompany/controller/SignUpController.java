/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.dao.UserDAO;
import com.mycompany.view.SignupView;

/**
 *
 * @author lminh
 */
public class SignUpController {
    private UserDAO userDAO;
    private SignupView signupView;
    
    public SignUpController(SignupView signupView) {
        this.userDAO = new UserDAO();
        this.signupView = signupView;
        
    }
    
}
