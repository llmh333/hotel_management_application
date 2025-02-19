/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller.panelController;

import com.mycompany.model.Bill;
import com.mycompany.service.IBillService;
import com.mycompany.service.Iplm.BillServiceIplm;
import com.mycompany.view.panel.StatisticalPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author lminh
 */
public class StatiscialPanelController {

    private StatisticalPanel statisticalPanel;
    private IBillService billService = new BillServiceIplm();

    public StatiscialPanelController(StatisticalPanel statisticalPanel) {

        this.statisticalPanel = statisticalPanel;
        initStatiscial();
    }
    
    public void reloadService() {
        this.billService = new BillServiceIplm();
 
    }

    public void initStatiscial() {
        this.statisticalPanel.setBtnConfirm(new BtnConfirm());
//        this.statisticalPanel.setBtnDeleteBill(new BtnDelete());
    }
    
//    private class BtnDelete implements  ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int ans = JOptionPane.showConfirmDialog(statisticalPanel, "Bạn chắc chắn muốn xóa Bill đã chọn", "Xác nhận xóa Bill", JOptionPane.OK_CANCEL_OPTION);
//            if (ans == 0) {
//                String idBill = statisticalPanel.getSelectionTable();
//                if (billService.deleteBill(idBill)) {
//                    StatiscialPanelController.this.reloadService();
//                    JOptionPane.showMessageDialog(statisticalPanel, "Xóa Bill thành công");
//                } else {
//                    JOptionPane.showMessageDialog(statisticalPanel, "Có lỗi xảy ra vui lòng thử lại");
//                }
//            }
//        }
//
//    }

    private class BtnConfirm implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            statisticalPanel.setBoxDate();
            TreeMap<LocalDate, List<Bill>> billTable = new TreeMap<>();
            List<Bill> bills = new ArrayList<Bill>();
            bills = billService.getAllBills();

            for (Bill bill : bills) {
                LocalDate localDateTime = bill.getPaymentTime().toLocalDate();
                billTable.computeIfAbsent(localDateTime, k -> new ArrayList<>()).add(bill);
            }
            statisticalPanel.setTableStatiscial(billTable);

        }
    }
    
}
