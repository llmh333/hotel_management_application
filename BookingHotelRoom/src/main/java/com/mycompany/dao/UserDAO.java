/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.model.User;
import com.mycompany.util.HibernateUtil;
import com.mysql.cj.Session;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author lminh
 */
public class UserDAO {
    static EntityManager entityManager;
    
    public UserDAO() {
        entityManager =  HibernateUtil.getEntityManager();
    }
    
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
    public boolean login(String username, String password) {
        List<User> users = new ArrayList<>();
        System.out.println(password);
        try {
            TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
            users = query.getResultList();
            for(int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                if ((user.getUsername().equals(username)) && (checkPassword(password, user.getPassword())) ) {
                    System.out.println("thanh cong");
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean register(User user) {
        try {
            user.setPassword(hashPassword(user.getPassword()));
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }
    
}
