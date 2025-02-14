/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bookinghotelroom;

import com.formdev.flatlaf.FlatLightLaf;
import com.mycompany.controller.AuthController;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.SigninView;
import jakarta.persistence.EntityManager;

import javax.swing.UIManager;
/**
 *
 * @author lminh
 */
public class BookingHotelRoom {
    static EntityManager entityManager = HibernateUtil.getEntityManager();
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            SigninView signinView = new SigninView();
            new AuthController(signinView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
