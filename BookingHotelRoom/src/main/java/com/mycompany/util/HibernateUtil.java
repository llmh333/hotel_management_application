/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author lminh
 */
public class HibernateUtil {
    private static EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory("application-unit");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityManagerFactory;
    } 
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
    public static void closeEntityManeger() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
