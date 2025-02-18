/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Bill;

import java.util.List;

/**
 *
 * @author lminh
 */
public interface IBillService {
    
    public boolean addBill(Bill bill);
    
    public boolean deleteBill(String idBill);

    public List<Bill> getAllBills();
    
}
