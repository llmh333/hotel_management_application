/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Bill;

/**
 *
 * @author lminh
 */
public interface IBill {
    
    public void addBill(Bill bill);
    
    public void changeInfoBill(String idBill);
    
    public void deleteBill(String idBill);
    
}
