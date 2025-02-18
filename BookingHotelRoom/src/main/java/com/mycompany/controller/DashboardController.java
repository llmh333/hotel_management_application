/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.controller.panelController.RoomMapManageController;
import com.mycompany.controller.panelController.PaymentPanelController;
import com.mycompany.controller.panelController.RoomMapController;
import com.mycompany.common.InfoRoom;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.DashboardView;
import com.mycompany.view.panel.FormRoomPanel;
import com.mycompany.view.panel.InforPersonPanel;
import com.mycompany.view.panel.PaymentPanel;
import com.mycompany.view.panel.RoomManagePanel;
import com.mycompany.view.panel.RoomMapPanel;
import com.mycompany.view.panel.StatisticalPanel;
import jakarta.persistence.EntityManager;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author lminh
 */
public class DashboardController {
    private final Color redColor = new Color(175,17,23);
    private final Color darkRedColor = new Color(140,17,23);
    private DashboardView dashboardView;
    private User user;
    public DashboardController(DashboardView dashboardView, User user) {
        this.dashboardView = dashboardView;
        this.user = user;
        initDashboardView();
    }
           
    public void initDashboardView() {
        RoomMapController roomMapController = new RoomMapController(new RoomMapPanel(),user);
        RoomMapPanel roomMapPanel = roomMapController.getRoomMapPanel();
        this.dashboardView.addPanelToPanelScreen(roomMapPanel, "RoomMap");
        this.dashboardView.addPanelToPanelScreen(new RoomManagePanel(), "RoomManagePanel");
        this.dashboardView.addPanelToPanelScreen(new PaymentPanel(), "PaymentPanel");
        this.dashboardView.addPanelToPanelScreen(new StatisticalPanel(), "StatisticalPanel");
        this.dashboardView.addPanelToPanelScreen(new InforPersonPanel(), "InforPersonPanel");

        this.dashboardView.setBtnCloseAct(new closeApp());
        this.dashboardView.setBtnRoomMapAct(new swapScreen());
        this.dashboardView.setBtnRoomManage(new swapScreen());
        this.dashboardView.setBtnPayment(new swapScreen());
        this.dashboardView.setBtnStatistical(new swapScreen());
        this.dashboardView.setBtnPersonInfor(new swapScreen());
    }
    public void showPanel(String namePanel) {
            CardLayout cardLayout = (CardLayout) dashboardView.getPanelScreen().getLayout();
            cardLayout.show(dashboardView.getPanelScreen(), namePanel);
        }
    
    private class swapScreen implements ActionListener {
         
        @Override
        public void actionPerformed(ActionEvent e) {
            String act = e.getActionCommand();
            if (act.equals("Sơ đồ phòng")) {
                screenRoomMap();
                RoomMapController roomMapController = new RoomMapController(new RoomMapPanel(), user);
                RoomMapPanel roomMapPanel = roomMapController.getRoomMapPanel();
                dashboardView.addPanelToPanelScreen(roomMapPanel, "RoomMapPanel");
                showPanel("RoomMapPanel");
            }
            if (act.equals("Quản lý phòng")) {
                screenRoomManage();
                RoomMapManageController roomMapManageController = new RoomMapManageController(new RoomManagePanel(),user);
                RoomManagePanel roomManagePanel = roomMapManageController.getRoomManagePanel();
                dashboardView.addPanelToPanelScreen(roomManagePanel, "RoomManagePanel");
                showPanel("RoomManagePanel");
            }
            if (act.equals("Thanh toán")) {
                screenPayment();
                PaymentPanelController paymentPanelController = new PaymentPanelController(new PaymentPanel());
                PaymentPanel paymentPanel = paymentPanelController.getPaymentPanel();
                dashboardView.addPanelToPanelScreen(paymentPanel, "PaymentPanel");
                showPanel("PaymentPanel");
            }
            if (act.equals("Thống kê")) {
                screenStatistical();
                showPanel("StatisticalPanel");
            }
            if (act.equals("Thông tin cá nhân")) {
                screenPersonInfor();
                showPanel("InforPersonPanel");
            }
        }
        
        
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
    }
    
    private class closeApp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    
}
