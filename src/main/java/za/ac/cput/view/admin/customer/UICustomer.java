package za.ac.cput.view.admin.customer;

import za.ac.cput.entity.Client;
import za.ac.cput.service.CustomerFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.admin.customer.customerAction.UICustomerAdd;
import za.ac.cput.view.admin.customer.customerAction.UICustomerDelete;
import za.ac.cput.view.admin.customer.customerAction.UICustomerUpdate;
import za.ac.cput.view.admin.employee.employeeActions.UIEmployeeDelete;
import za.ac.cput.view.admin.employee.employeeActions.UIEmployeeUpdate;
import za.ac.cput.view.general.AdminSideNavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UICustomer extends JFrame {

    private JMenuBar mainMenu, buttonMenu, buttonMenu1;
    private ImageIcon iconLogo;
    private JLabel lblTitle, lblCurrentUser, lblHeadingStorage, lblHeadingEquipment;
    private static JTable table,customerTable;
    private static DefaultTableModel model;
    private JScrollPane pane,pane0;
    private int indexSelected;

    public UICustomer() {
        super("❣ Admin Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color navColor = new Color(186,39,94);
        Color headingText = new Color(230, 196, 96);
        Color textColor = Color.WHITE;

        String userInfo = GenericHelper.getUserName();

        JPanel panelMainStorage = new JPanel(new GridLayout(2, 0));
        panelMainStorage.setSize(new Dimension(1000, 60));
        panelMainStorage.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMainStorage.setBackground(Color.WHITE);

        buttonMenu = new JMenuBar();
        buttonMenu.setOpaque(true);
        buttonMenu.setBackground(Color.WHITE);
        buttonMenu.add(Box.createHorizontalGlue());


        JPanel panelMainEquipment = new JPanel(new GridLayout(2, 0));
        panelMainEquipment.setSize(new Dimension(1000, 60));
        panelMainEquipment.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMainEquipment.setBackground(Color.WHITE);

        buttonMenu1 = new JMenuBar();
        buttonMenu1.setOpaque(true);
        buttonMenu1.setBackground(Color.WHITE);
        buttonMenu1.add(Box.createHorizontalGlue());

        JButton btnUpdate1 = new JButton("Update");
        btnUpdate1.setBackground(navColor);
        btnUpdate1.setForeground(textColor);
        btnUpdate1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                indexSelected = customerTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = (String) customerTable.getValueAt( indexSelected, col );

                    System.out.println(id);
                    int x = getSize().width / 2;
                    int y = getSize().height / 4;
                    new UICustomerUpdate(x, y, id);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Select a row to update!.");
                }

            }
        });

        JButton btnAdd1 = new JButton("Add");
        btnAdd1.setBackground(navColor);
        btnAdd1.setForeground(textColor);
        btnAdd1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int x = getSize().width / 2;
                int y = getSize().height / 4;
                new UICustomerAdd(x, y);
            }
        });

        JButton btnDelete1 = new JButton("Delete");
        btnDelete1.setBackground(navColor);
        btnDelete1.setForeground(textColor);
        btnDelete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                indexSelected = customerTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = (String) customerTable.getValueAt(indexSelected, col);
                    int x = getSize().width / 2;
                    int y = getSize().height / 4;
                    new UICustomerDelete(x, y, id);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Select a row to delete!.");
                }
            }
        });


        JButton btnReload1 = new JButton("Reload");
        btnReload1.setBackground(navColor);
        btnReload1.setForeground(textColor);

        buttonMenu1.add(btnUpdate1);
        buttonMenu1.add(btnAdd1);
        buttonMenu1.add(btnDelete1);
        buttonMenu1.add(btnReload1);


        lblHeadingEquipment = new JLabel("Admin Customer Management", SwingConstants.LEFT);
        lblHeadingEquipment.setForeground(navColor);
        lblHeadingEquipment.setFont(new Font("Courier", Font.BOLD,16));
        lblHeadingEquipment.setPreferredSize(new Dimension(100, 50));
        lblHeadingEquipment.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeadingEquipment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMainEquipment.add(lblHeadingEquipment);
        panelMainEquipment.add(buttonMenu1);

        JPanel nav = new AdminSideNavigationBar(userInfo, "Administrator", "images/employee-icons/person.jpg", "Customer Management", this);
        nav.setBackground(navColor);


        //Storage Center Panel
        JPanel equipmentPanel = new JPanel(new BorderLayout());
        customerTable = new JTable();
        Object[] columnNames = { "ID", "Name", "Surname", "Contact Number"};
        model = new DefaultTableModel(new Object[0][0], columnNames);
        ArrayList<Client> customerArrayList = CustomerFunctionality.getAllClients();
        for(Client customer : customerArrayList) {
            Object[] o = new Object[4];
            o[0] = customer.getClientID();
            o[1] = customer.getName();
            o[2] = customer.getSurname();
            o[3] = customer.getContactNumber();

            model.addRow(o);
        }
        customerTable.getTableHeader().setOpaque(false);
        customerTable.getTableHeader().setBackground(navColor);
        customerTable.getTableHeader().setForeground(Color.WHITE);
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.setModel(model);
        pane0 = new JScrollPane(customerTable);
        pane0.setPreferredSize(new Dimension(1000, 450));
        equipmentPanel.add(panelMainEquipment, BorderLayout.NORTH);
        equipmentPanel.add(pane0,BorderLayout.CENTER);


        JPanel panelCenter = new JPanel(new GridLayout(1,1));
        panelCenter.add(equipmentPanel);
        JScrollPane scroll = new JScrollPane(panelCenter);
        scroll.setPreferredSize(new Dimension(1000, 450));
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(nav, BorderLayout.WEST);


        //Display the window.
        setSize(1000, 750);
        pack();
        setVisible(true);
        //setResizable(false);
    }

    public static void refresh() {
        model.setRowCount(0);
        ArrayList<Client> customerArrayList = CustomerFunctionality.getAllClients();
        for(Client customer : customerArrayList) {
            Object[] o = new Object[4];
            o[0] = customer.getClientID();
            o[1] = customer.getName();
            o[2] = customer.getSurname();
            o[3] = customer.getContactNumber();

            model.addRow(o);
        }
    }
}//end

