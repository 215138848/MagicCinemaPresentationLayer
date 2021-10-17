package za.ac.cput.view.admin.equipment;

import za.ac.cput.entity.Employee;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.service.EmployeeFunctionality;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.admin.employee.employeeActions.UIEmployeeAdd;
import za.ac.cput.view.admin.employee.employeeActions.UIEmployeeDelete;
import za.ac.cput.view.admin.employee.employeeActions.UIEmployeeUpdate;
import za.ac.cput.view.admin.equipment.equipmentActions.UIEquipmentAdd;
import za.ac.cput.view.admin.equipment.equipmentActions.UIEquipmentDelete;
import za.ac.cput.view.admin.equipment.equipmentActions.UIEquipmentUpdate;
import za.ac.cput.view.general.AdminSideNavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UIEmployee extends JFrame {

    private JMenuBar mainMenu, buttonMenu, buttonMenu1;
    private ImageIcon iconLogo;
    private JLabel lblTitle, lblCurrentUser, lblHeadingStorage, lblHeadingEquipment;
    private static JTable table,employeeTable;
    private static DefaultTableModel model;
    private JScrollPane pane,pane0;
    private int indexSelected;

    public UIEmployee() {
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

                indexSelected = employeeTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = (String) employeeTable.getValueAt( indexSelected, col );

                    System.out.println(id);
                    int x = getSize().width / 2;
                    int y = getSize().height / 4;
                    new UIEmployeeUpdate(x, y, id);
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
                new UIEmployeeAdd(x, y);
            }
        });

        JButton btnDelete1 = new JButton("Delete");
        btnDelete1.setBackground(navColor);
        btnDelete1.setForeground(textColor);
        btnDelete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                indexSelected = employeeTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = (String) employeeTable.getValueAt(indexSelected, col);
                    int x = getSize().width / 2;
                    int y = getSize().height / 4;
                    new UIEmployeeDelete(x, y, id);

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


        lblHeadingEquipment = new JLabel("Admin Employee Management", SwingConstants.LEFT);
        lblHeadingEquipment.setForeground(navColor);
        lblHeadingEquipment.setFont(new Font("Courier", Font.BOLD,16));
        lblHeadingEquipment.setPreferredSize(new Dimension(100, 50));
        lblHeadingEquipment.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeadingEquipment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMainEquipment.add(lblHeadingEquipment);
        panelMainEquipment.add(buttonMenu1);

        JPanel nav = new AdminSideNavigationBar(userInfo, "Administrator", "images/employee-icons/person.jpg", "Employee Management", this);
        nav.setBackground(navColor);


        //Storage Center Panel
        JPanel equipmentPanel = new JPanel(new BorderLayout());
        employeeTable = new JTable();
        Object[] columnNames = { "ID", "Username", "Password", "Name", "Surname", "Email", "Role" };
        model = new DefaultTableModel(new Object[0][0], columnNames);
        ArrayList<Employee> employeeArrayList = EmployeeFunctionality.getAllEmployees();
        for(Employee employee : employeeArrayList) {
            Object[] o = new Object[7];
            o[0] = employee.getEmployeeNumber();
            o[1] = employee.getUsername();
            o[2] = employee.getPassword();
            o[3] = employee.getName();
            o[4] = employee.getSurname();
            o[5] = employee.getEmail();
            o[6] = employee.getRole();

            model.addRow(o);
        }
        employeeTable.getTableHeader().setOpaque(false);
        employeeTable.getTableHeader().setBackground(navColor);
        employeeTable.getTableHeader().setForeground(Color.WHITE);
        employeeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        employeeTable.setModel(model);
        pane0 = new JScrollPane(employeeTable);
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
        ArrayList<Employee> employeeArrayList = EmployeeFunctionality.getAllEmployees();
        for(Employee employee : employeeArrayList) {
            Object[] o = new Object[7];
            o[0] = employee.getEmployeeNumber();
            o[1] = employee.getUsername();
            o[2] = employee.getPassword();
            o[3] = employee.getName();
            o[4] = employee.getSurname();
            o[5] = employee.getEmail();
            o[6] = employee.getRole();

            model.addRow(o);
        }
    }
}//end

