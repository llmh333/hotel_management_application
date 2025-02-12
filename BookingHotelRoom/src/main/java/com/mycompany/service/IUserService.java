/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.User;
import com.mycompany.request.LoginRequest;
import com.mycompany.request.RegisterRequest;
import com.mycompany.respone.UserRespone;

/**
 *
 * @author lminh
 */
public interface IUserService {
    
    public UserRespone login(LoginRequest loginRequest);
    
    public int register(RegisterRequest registerRequest);
    
    public UserRespone convertToRespone(User user);
    
    public User convertToUser(RegisterRequest registerRequest);
    
    public boolean changePassword(String password, String email);

    public int checkUserExists(RegisterRequest registerRequest);

}
