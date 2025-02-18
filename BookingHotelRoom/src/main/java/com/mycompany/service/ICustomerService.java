/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Customer;
import java.util.List;

/**
 *
 * @author lminh
 */
public interface ICustomerService {
    
    public int addCustomer(Customer customer);
    
    public int changeInfoCustomer(Customer customer);
    
    public int deleteCustomer(String idCustomer);
    
    public Customer findCustomerByID(String id);
    
    public Customer findCustomerByPhoneNumber(String phoneNumber);
    
    public List<Customer> getAllCustomers();
}
