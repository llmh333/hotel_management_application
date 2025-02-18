/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.model.Bill;
import com.mycompany.service.IBillService;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author lminh
 */
public class BillServiceIplm implements IBillService{

    EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public boolean addBill(Bill bill) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bill);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBill(String idBill) {
        try {
            Query query = entityManager.createQuery("DELETE FROM Bill WHERE id = :id", Bill.class);
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
