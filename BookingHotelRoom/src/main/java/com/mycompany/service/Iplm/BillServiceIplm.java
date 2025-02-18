/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.model.Bill;
import com.mycompany.service.IBillService;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    @Override
    public List<Bill> getAllBills() {
        try {
            TypedQuery<Bill> query = entityManager.createQuery(" FROM Bill", Bill.class);
            List<Bill> bills = query.getResultList();
            return bills;
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }

}
