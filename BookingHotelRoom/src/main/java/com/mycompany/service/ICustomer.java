/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Customer;

/**
 *
 * @author lminh
 */
public interface ICustomer {
    public boolean addCustomer(Customer customer);
    
    public boolean changeInforCustomer(String idCustomer);
    
    public boolean deleteCustomer(String idCustomer);
}
