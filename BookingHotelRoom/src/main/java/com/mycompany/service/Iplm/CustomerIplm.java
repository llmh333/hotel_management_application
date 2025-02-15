/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.model.Customer;
import com.mycompany.service.ICustomer;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;

/**
 *
 * @author lminh
 */
public class CustomerIplm implements ICustomer{
    
    public EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public boolean addCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
        
    }

    @Override
    public boolean deleteCustomer(String idCustomer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
