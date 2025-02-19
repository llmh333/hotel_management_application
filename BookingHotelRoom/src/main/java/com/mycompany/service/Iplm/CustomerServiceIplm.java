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
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import com.mycompany.service.ICustomerService;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author lminh
 */
public class CustomerServiceIplm implements ICustomerService{
    
    public EntityManager entityManager = HibernateUtil.getEntityManager();
    
    @Override
    public int addCustomer(Customer customer) {
        try {
            TypedQuery<Customer> queryPhoneNumber = entityManager.createQuery("FROM Customer WHERE phoneNumber = :phoneNumber", Customer.class);
            queryPhoneNumber.setParameter("phoneNumber", customer.getPhoneNumber());
            Customer customerPhoneNumber = queryPhoneNumber.getSingleResult();
            TypedQuery<Customer> queryEmail = entityManager.createQuery("FROM Customer WHERE phoneNumber = :phoneNumber", Customer.class);
            queryEmail.setParameter("phoneNumber", customer.getPhoneNumber());
            Customer c = queryEmail.getSingleResult();
            return ExitCodeConfig.EXIT_CODE_ELEMENT_EXISTS;
        } catch (NoResultException e) {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
            return ExitCodeConfig.EXIT_CODE_OK;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return ExitCodeConfig.EXIT_CODE_ERROR;
        }
        
    }

    @Override
    public int deleteCustomer(String idCustomer) {
        try {
            Query queryPhoneNumber = entityManager.createQuery("DELETE FROM Customer WHERE id = :id");
            queryPhoneNumber.setParameter("id", idCustomer);
            entityManager.getTransaction().begin();
            queryPhoneNumber.executeUpdate();
            entityManager.getTransaction().commit();
            return ExitCodeConfig.EXIT_CODE_OK;
        } catch (NoResultException e) {
            entityManager.getTransaction().rollback();
            return ExitCodeConfig.EXIT_CODE_NO_RESULT;
        } catch (ConstraintViolationException e) {
            entityManager.getTransaction().rollback();
            return ExitCodeConfig.EXIT_CODE_CAN_NOT_DELETE;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return ExitCodeConfig.EXIT_CODE_ERROR;
        }
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
            TypedQuery<Customer> query = entityManager.createQuery("FROM Customer WHERE id = :id", Customer.class);
            query.setParameter("id", customer.getId());
            Customer c = query.getSingleResult();
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
            return ExitCodeConfig.EXIT_CODE_OK;
        } catch (NoResultException e) {
            entityManager.getTransaction().rollback();
            return ExitCodeConfig.EXIT_CODE_NO_RESULT;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
            return ExitCodeConfig.EXIT_CODE_ERROR;
        }
    }
    
}
