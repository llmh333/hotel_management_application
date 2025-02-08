/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bookinghotelroom;

import com.mycompany.controller.AuthController;
import com.mycompany.model.User;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.SigninView;
import com.mycompany.view.SignupView;
import jakarta.persistence.EntityManager;
import javax.swing.UIManager;

/**
 *
 * @author lminh
 */
public class BookingHotelRoom {
//    static EntityManager entityManager = HibernateUtil.getEntityManager();
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            SigninView signinView = new SigninView();
            SignupView signupView = new SignupView();
            new AuthController(signinView, signupView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
