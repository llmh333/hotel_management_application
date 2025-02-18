/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service.Iplm;

import com.mycompany.common.ExitCodeConfig;
import com.mycompany.model.Customer;
import com.mycompany.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import com.mycompany.service.ICustomerService;

/**
 *
 * @author lminh
 */
public class CustomerServiceIplm implements ICustomerService{
    
    public EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public int addCustomer(Customer customer) {
        try {
            TypedQuery<Customer> query = entityManager.createQuery("FROM Customer WHERE phoneNumber = :phoneNumber", Customer.class);
            query.setParameter("phoneNumber", customer.getPhoneNumber());
            Customer c = query.getSingleResult();
            return ExitCodeConfig.EXIT_CODE_ELEMENT_EXISTS;
        } catch (NoResultException e) {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return ExitCodeConfig.EXIT_CODE_OK;
        }
        
    }

    @Override
    public int deleteCustomer(String idCustomer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Customer findCustomerByID(String id) {
        try {
            TypedQuery<Customer> query = entityManager.createQuery("FROM Customer WHERE id = :id", Customer.class);
            query.setParameter("id", id);
            Customer customer = query.getSingleResult();
            return customer;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        try {
            TypedQuery<Customer> query = entityManager.createQuery("FROM Customer", Customer.class);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Customer findCustomerByPhoneNumber(String phoneNumber) {
        try {
            TypedQuery<Customer> query = entityManager.createQuery("FROM Customer WHERE phoneNumber = :phoneNumber", Customer.class);
            query.setParameter("phoneNumber", phoneNumber);
            Customer customer = query.getSingleResult();
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int changeInfoCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return ExitCodeConfig.EXIT_CODE_OK;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return ExitCodeConfig.EXIT_CODE_ERROR;
        }
    }
    
}
