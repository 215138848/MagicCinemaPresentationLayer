package za.ac.cput.view.dashboard.admin;


import za.ac.cput.view.general.AdminSideNavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class UIAdminDashboard extends JFrame {
    private JLabel lblHeading;

    public UIAdminDashboard() {
        super("❣ CINEMA MAGIC MANAGEMENT SYSTEM");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color navColor = new Color(186,39,94);

        JPanel panelMain = new JPanel();
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.setBackground(Color.WHITE);

        lblHeading = new JLabel("CINEMA MAGIC MANAGEMENT SYSTEM", SwingConstants.LEFT);
        lblHeading.setForeground(navColor);
        lblHeading.setFont(new Font("SansSerif Bold", Font.BOLD, 26));
        lblHeading.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeading.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMain.add(lblHeading);

        JPanel centerData = new JPanel();
        centerData.setBackground(Color.WHITE);

        JPanel panelNotificationHolder = new JPanel(new GridLayout(1, 3));
        panelNotificationHolder.setBackground(Color.WHITE);
        panelNotificationHolder.setBorder(new EmptyBorder(10,10,10,10));
        JPanel notificationOne = new DashboardDataDisplay("Total Number Of Rentals", "10000", "images/admin-dashboard/rentals.png");
        notificationOne.setBackground(Color.WHITE);
        JPanel notificationTwo = new DashboardDataDisplay("Total Number Of Customers", "10000", "images/admin-dashboard/customers.png");
        notificationTwo.setBackground(Color.WHITE);
        JPanel notificationThree = new DashboardDataDisplay("Total Number Of Gear", "10000", "images/admin-dashboard/equipment.png");
        notificationThree.setBackground(Color.WHITE);

        panelNotificationHolder.add(notificationOne);
        panelNotificationHolder.add(notificationTwo);
        panelNotificationHolder.add(notificationThree);

        JPanel nav = new AdminSideNavigationBar("Nina Abrev", "Administrator", "images/employee-icons/person.jpg", "Dashboard");
        nav.setBackground(navColor);

        JPanel panelSouth = new JPanel(new GridLayout(1, 1));
        panelSouth.setBackground(Color.WHITE);
        JLabel heading = new JLabel("Recent Transactions ♡");
        heading.setFont(new Font("SansSerif Bold", Font.BOLD, 20));

        panelSouth.add(heading);
        panelSouth.add(new JLabel());
        panelSouth.add(new JLabel());
        panelSouth.add(new JLabel());

        //Request data from the database ->
        String[] columnName = {"RENTAL ID" , "CUSTOMER NAME", "EQUIPMENT ID", "COST", "DATE RENTED"};
        Object[][] tableData = {
                {"null", "null", "null", "null", "null"},
                {"null", "null", "null", "null", "null"}
        };



        JTable dataTable = new JTable(tableData, columnName);
        JScrollPane dataList = new JScrollPane(dataTable);
        dataList.getViewport().setOpaque(false);
        dataTable.setOpaque(false);
        dataTable.setEnabled(false);

        ((DefaultTableCellRenderer)dataTable.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.LEFT);
        dataTable.getTableHeader().setOpaque(false);
        dataTable.getTableHeader().setBackground(navColor);


        JPanel transactionHolder = new JPanel(new BorderLayout());
        transactionHolder.setBackground(Color.WHITE);
        transactionHolder.add(panelSouth, BorderLayout.NORTH);
        transactionHolder.add(dataList, BorderLayout.CENTER);

        centerData.add(panelNotificationHolder, BorderLayout.NORTH);
        centerData.add(new JLabel());
        centerData.add(transactionHolder, BorderLayout.CENTER);

        JPanel panelCenter = new JPanel(new BorderLayout());
        panelCenter.setBackground(Color.WHITE);
        panelCenter.add(panelMain, BorderLayout.NORTH);
        panelCenter.add(centerData, BorderLayout.CENTER);


        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(nav, BorderLayout.WEST);


        setSize(1200, 1000);
        setPreferredSize(new Dimension(1150, 760));
        pack();
        setVisible(true);
    }
}
