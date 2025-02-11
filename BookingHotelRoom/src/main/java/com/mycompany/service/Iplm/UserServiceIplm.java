/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.common.PasswordEncryption;
import static com.mycompany.dao.UserDAO.checkPassword;
import com.mycompany.model.User;
import com.mycompany.request.LoginRequest;
import com.mycompany.request.RegisterRequest;
import com.mycompany.respone.UserRespone;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import com.mycompany.common.PasswordEncryption;
import static com.mycompany.common.PasswordEncryption.*;
import com.mycompany.service.IUserService;
import jakarta.persistence.Query;
/**
 *
 * @author lminh
 */
public class UserServiceIplm implements IUserService {
    
    EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public UserRespone login(LoginRequest loginRequest) {
//       List<User> users = new ArrayList<>();
//        System.out.println(password);
        try {
            TypedQuery<User> query = entityManager.createQuery("FROM User WHERE username = :username", User.class);
            query.setParameter("username", loginRequest.getUsername());
            User user = query.getSingleResult();
            if (user != null) {
                if (user.getUsername().equals(loginRequest.getUsername()) && checkPassword(loginRequest.getPassword(), user.getPassword())) {
                    return convertToRespone(user);
                }
            } else return null;
            
            
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserRespone convertToRespone(User user) {
        if (user != null) {
        return new UserRespone().builder()
            .name(user.getUsername())
            .birthDay(user.getBirthday())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .address(user.getAddress())
            .build();
        } else return null;
        
    }
    
    @Override
    public User convertToUser(RegisterRequest registerRequest) {
        return new User().builder()
                .name(registerRequest.getName())
                .birthday(registerRequest.getBirthday())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .address(registerRequest.getAddress())
                .sex(registerRequest.getSex())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .build();
    }
    
    @Override
    public boolean register(RegisterRequest registerRequest) {
        try {
            registerRequest.setPassword(encryptPassword(registerRequest.getPassword()));
            entityManager.getTransaction().begin();
            entityManager.merge(convertToUser(registerRequest));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }

    @Override
    public boolean changePassword(String password, String email) {
        try {
            Query query = entityManager.createQuery("UPDATE User SET password = :newPassword WHERE email = :email");
            query.setParameter("newPassword", password);
            query.setParameter("email", email);
            entityManager.getTransaction().begin();
            query.executeUpdate();                 
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
    
    
}
