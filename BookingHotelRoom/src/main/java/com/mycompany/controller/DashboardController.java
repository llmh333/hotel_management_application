/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.view.DashboardView;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author lminh
 */
public class DashboardController {
    private final Color yellowcColor = new Color(248,148,7);
    private final Color redColor = new Color(175,17,23);
    private final Color darkRedColor = new Color(140,17,23);
    private DashboardView dashboardView;
    public DashboardController(DashboardView dashboardView) {
        this.dashboardView = dashboardView;
        this.dashboardView.setBtnCloseAct(e -> closeApp());
        this.dashboardView.setBtnRoomMapAct(e -> screenRoomMap());
        this.dashboardView.setBtnRoomManage(e -> screenRoomManage());
        this.dashboardView.setBtnPayment(e -> screenPayment());
        this.dashboardView.setBtnStatistical(e -> screenStatistical());
        this.dashboardView.setBtnPersonInfor(e -> screenPersonInfor());
    }
    
    private void screenRoomMap() {
        dashboardView.btnRoomMap.setBackground(darkRedColor);         
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(true);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);        

        dashboardView.roomMapPanel1.setVisible(true);
        dashboardView.roomManagePanel1.setVisible(false); 
        dashboardView.paymentPanel1.setVisible(false);
        dashboardView.statisticalPanel1.setVisible(false);
        dashboardView.inforPersonPanel1.setVisible(false);
        
    }
    
    private void screenRoomManage() {
        dashboardView.btnRoomMap.setBackground(redColor);         
        dashboardView.btnRoomManage.setBackground(darkRedColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(true);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);

        dashboardView.roomMapPanel1.setVisible(false);
        dashboardView.roomManagePanel1.setVisible(true); 
        dashboardView.paymentPanel1.setVisible(false);
        dashboardView.statisticalPanel1.setVisible(false);
        dashboardView.inforPersonPanel1.setVisible(false);
    }
    
    private void screenPayment() {
        dashboardView.btnRoomMap.setBackground(redColor);         
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(darkRedColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(true);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);

        dashboardView.roomMapPanel1.setVisible(false);
        dashboardView.roomManagePanel1.setVisible(false); 
        dashboardView.paymentPanel1.setVisible(true);
        dashboardView.statisticalPanel1.setVisible(false);
        dashboardView.inforPersonPanel1.setVisible(false);
    }
    
    private void screenStatistical() {
        dashboardView.btnRoomMap.setBackground(redColor);         
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(darkRedColor);
        dashboardView.btnPersonInfor.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(true);
        dashboardView.panelBorderInforPerson.setVisible(false);

        dashboardView.roomMapPanel1.setVisible(false);
        dashboardView.roomManagePanel1.setVisible(false); 
        dashboardView.paymentPanel1.setVisible(false);
        dashboardView.statisticalPanel1.setVisible(true);
        dashboardView.inforPersonPanel1.setVisible(false);
    }
    
    private void screenPersonInfor() {
        dashboardView.btnRoomMap.setBackground(redColor);         
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(darkRedColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(true);

        dashboardView.roomMapPanel1.setVisible(false);
        dashboardView.roomManagePanel1.setVisible(false); 
        dashboardView.paymentPanel1.setVisible(false);
        dashboardView.statisticalPanel1.setVisible(false);
        dashboardView.inforPersonPanel1.setVisible(true);
    }
    
    private void closeApp() {
        System.exit(0);
    }
    
}
