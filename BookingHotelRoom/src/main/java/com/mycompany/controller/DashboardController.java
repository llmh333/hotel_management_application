/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.controller.panelController.*;
import com.mycompany.common.InfoRoom;
import com.mycompany.model.Room;
import com.mycompany.model.User;
import com.mycompany.service.IRoomService;
import com.mycompany.service.IUserService;
import com.mycompany.service.Iplm.RoomServiceIplm;
import com.mycompany.service.Iplm.UserServiceIplm;
import com.mycompany.util.HibernateUtil;
import com.mycompany.view.DashboardView;
import com.mycompany.view.panel.*;
import jakarta.persistence.EntityManager;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author lminh
 */
public class DashboardController {
    private final Color redColor = new Color(175,17,23);
    private final Color darkRedColor = new Color(140,17,23);
    private DashboardView dashboardView;
    private IUserService userService = new UserServiceIplm();
    private User user;
    
    public DashboardController(DashboardView dashboardView, User user) {
        this.dashboardView = dashboardView;
        this.user = user;
        initDashboardView();
    }

    private void handleExit() {

    }

    public void initDashboardView() {


        RoomMapController roomMapController = new RoomMapController(new RoomMapPanel(),user);
        RoomMapPanel roomMapPanel = roomMapController.getRoomMapPanel();
        this.dashboardView.addPanelToPanelScreen(roomMapPanel, "RoomMap");

        this.dashboardView.setBtnCloseAct(new closeApp());
        this.dashboardView.setBtnRoomMapAct(new swapScreen());
        this.dashboardView.setBtnRoomManage(new swapScreen());
        this.dashboardView.setBtnPayment(new swapScreen());
        this.dashboardView.setBtnStatistical(new swapScreen());
        this.dashboardView.setBtnCustomerManagement(new swapScreen());
        if (user.getRole().equals("ADMIN")) {
            this.dashboardView.setBtnUserManagement(new swapScreen());
            this.dashboardView.setEnableBtnUserMgt(true);
        } else {
            this.dashboardView.setEnableBtnUserMgt(false);
        }
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
                StatisticalPanel statisticalPanel = new StatisticalPanel();
                StatiscialPanelController statiscialPanelController = new StatiscialPanelController(statisticalPanel);
                dashboardView.addPanelToPanelScreen(statisticalPanel, "StatisticalPanel");
                showPanel("StatisticalPanel");
            }
            if (act.equals("Quản lí khách hàng")) {
                screenCustomerManagement();
                CustomerManagement customerManagement = new CustomerManagement();
                CustomerManagementController customerManagementController = new CustomerManagementController(customerManagement);
                dashboardView.addPanelToPanelScreen(customerManagement, "CustomerManagementPanel");
                showPanel("CustomerManagementPanel");
            }
            if (act.equals("Quản lí nhân viên")  && (user.getRole().equals("ADMIN"))) {
                screenUserManagement();
                UserManagementPanel userManagementPanel = new UserManagementPanel();
                UserManagementController userManagementController = new UserManagementController(userManagementPanel);
                dashboardView.addPanelToPanelScreen(userManagementPanel, "UserManagementPanel");
                showPanel("UserManagementPanel");
            }
            if (act.equals("Thông tin cá nhân")) {
                screenPersonInfor();
                InforPersonPanel inforPersonPanel = new InforPersonPanel();
                InforPersonController inforPersonController = new InforPersonController(inforPersonPanel, user);
                dashboardView.addPanelToPanelScreen(inforPersonPanel, "InforPersonPanel");
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
        dashboardView.btnCustomerManagement.setBackground(redColor);
        dashboardView.btnUserManagement.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(true);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(false);
        dashboardView.panelBorderUserMgt.setVisible(false);

    }

    private void screenRoomManage() {
        dashboardView.btnRoomMap.setBackground(redColor);
        dashboardView.btnRoomManage.setBackground(darkRedColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(redColor);
        dashboardView.btnCustomerManagement.setBackground(redColor);
        dashboardView.btnUserManagement.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(true);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(false);
        dashboardView.panelBorderUserMgt.setVisible(false);

    }

    private void screenPayment() {
        dashboardView.btnRoomMap.setBackground(redColor);
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(darkRedColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(redColor);
        dashboardView.btnCustomerManagement.setBackground(redColor);
        dashboardView.btnUserManagement.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(true);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(false);
        dashboardView.panelBorderUserMgt.setVisible(false);

    }

    private void screenStatistical() {
        dashboardView.btnRoomMap.setBackground(redColor);
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(darkRedColor);
        dashboardView.btnPersonInfor.setBackground(redColor);
        dashboardView.btnCustomerManagement.setBackground(redColor);
        dashboardView.btnUserManagement.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(true);
        dashboardView.panelBorderInforPerson.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(false);
        dashboardView.panelBorderUserMgt.setVisible(false);

    }

    private void screenCustomerManagement() {
        dashboardView.btnRoomMap.setBackground(redColor);
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(redColor);
        dashboardView.btnCustomerManagement.setBackground(darkRedColor);
        dashboardView.btnUserManagement.setBackground(redColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(true);
        dashboardView.panelBorderUserMgt.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(false);
    }

    private void screenUserManagement() {
        dashboardView.btnRoomMap.setBackground(redColor);
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnCustomerManagement.setBackground(redColor);
        dashboardView.btnUserManagement.setBackground(darkRedColor);
        dashboardView.btnPersonInfor.setBackground(redColor);


        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(false);
        dashboardView.panelBorderUserMgt.setVisible(true);
        dashboardView.panelBorderInforPerson.setVisible(false);
    }

    private void screenPersonInfor() {
        dashboardView.btnRoomMap.setBackground(redColor);
        dashboardView.btnRoomManage.setBackground(redColor);
        dashboardView.btnPayment.setBackground(redColor);
        dashboardView.btnStatistical.setBackground(redColor);
        dashboardView.btnUserManagement.setBackground(redColor);
        dashboardView.btnCustomerManagement.setBackground(redColor);
        dashboardView.btnPersonInfor.setBackground(darkRedColor);

        dashboardView.panelBoderRoomMap.setVisible(false);
        dashboardView.panelBoderRoomManage.setVisible(false);
        dashboardView.panelBorderPayment.setVisible(false);
        dashboardView.panelBorderStatis.setVisible(false);
        dashboardView.panelBorderCusMgt.setVisible(false);
        dashboardView.panelBorderUserMgt.setVisible(false);
        dashboardView.panelBorderInforPerson.setVisible(true);
    }
    
    private class closeApp implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(dashboardView, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                userService.statusUser(user.getId(), "offline");
                System.exit(0);
            }
            System.exit(0);
        }
    }
    
}
