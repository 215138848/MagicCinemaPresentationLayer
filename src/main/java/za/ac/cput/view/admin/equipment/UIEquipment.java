package za.ac.cput.view.admin.equipment;

import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.util.GenericHelper;
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

public class UIEquipment extends JFrame {

    private JMenuBar mainMenu, buttonMenu, buttonMenu1;
    private ImageIcon iconLogo;
    private JLabel lblTitle, lblCurrentUser, lblHeadingStorage, lblHeadingEquipment;
    private static JTable table,equipmentPanelTable;
    private static DefaultTableModel model;
    private JScrollPane pane,pane0;
    private int indexSelected;

    public UIEquipment() {
        super("❣ Admin Storage Management");
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

                indexSelected = equipmentPanelTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = (String) equipmentPanelTable.getValueAt( indexSelected, col );

                    System.out.println(id);
                    int x = getSize().width / 2;
                    int y = getSize().height / 4;
                    new UIEquipmentUpdate(x, y, id);
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
                new UIEquipmentAdd(x, y);
            }
        });

        JButton btnDelete1 = new JButton("Delete");
        btnDelete1.setBackground(navColor);
        btnDelete1.setForeground(textColor);
        btnDelete1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                indexSelected = equipmentPanelTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = (String) equipmentPanelTable.getValueAt(indexSelected, col);
                    int x = getSize().width / 2;
                    int y = getSize().height / 4;
                    new UIEquipmentDelete(x, y, id);

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

        lblHeadingStorage = new JLabel("Admin Equipment Storage", SwingConstants.LEFT);
        lblHeadingStorage.setForeground(navColor);
        lblHeadingStorage.setFont(new Font("Courier", Font.BOLD,16));
        lblHeadingStorage.setPreferredSize(new Dimension(100, 50));
        lblHeadingStorage.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeadingStorage.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMainStorage.add(lblHeadingStorage);
        panelMainStorage.add(buttonMenu);

        lblHeadingEquipment = new JLabel("Admin Equipment Management", SwingConstants.LEFT);
        lblHeadingEquipment.setForeground(navColor);
        lblHeadingEquipment.setFont(new Font("Courier", Font.BOLD,16));
        lblHeadingEquipment.setPreferredSize(new Dimension(100, 50));
        lblHeadingEquipment.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeadingEquipment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMainEquipment.add(lblHeadingEquipment);
        panelMainEquipment.add(buttonMenu1);

        JPanel nav = new AdminSideNavigationBar(userInfo, "Administrator", "images/employee-icons/person.jpg", "Equipment Management", this);
        nav.setBackground(navColor);


        //Storage Center Panel
        JPanel equipmentPanel = new JPanel(new BorderLayout());
        equipmentPanelTable = new JTable();
        Object[] columnNames = { "ID", "Category", "SubId", "Make", "Model", "C.P.U", "Availability", "Quantity" };
        model = new DefaultTableModel(new Object[0][0], columnNames);
        ArrayList<Equipment> equipmentArrayList = EquipmentFunctionality.getAll();
        for(Equipment equipment : equipmentArrayList) {
            Object[] o = new Object[8];
            o[0] = equipment.getEquipmentID();
            o[1] = equipment.getGearCategory();
            o[2] = equipment.getGearSubCategory();
            o[3] = equipment.getMake();
            o[4] = equipment.getModel();
            o[5] = equipment.getRentalPrice();
            o[6] = equipment.isAvailable();
            o[7] = equipment.getQuantity();
            model.addRow(o);
        }
        equipmentPanelTable.getTableHeader().setOpaque(false);
        equipmentPanelTable.getTableHeader().setBackground(navColor);
        equipmentPanelTable.getTableHeader().setForeground(Color.WHITE);
        equipmentPanelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        equipmentPanelTable.setModel(model);
        pane0 = new JScrollPane(equipmentPanelTable);
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
        ArrayList<Equipment> equipmentArrayList = EquipmentFunctionality.getAllEquipment();
        for(Equipment equipment : equipmentArrayList) {
            Object[] o = new Object[8];
            o[0] = equipment.getEquipmentID();
            o[1] = equipment.getGearCategory();
            o[2] = equipment.getGearSubCategory();
            o[3] = equipment.getMake();
            o[4] = equipment.getModel();
            o[5] = equipment.getRentalPrice();
            o[6] = equipment.isAvailable();
            o[7] = equipment.getQuantity();
            model.addRow(o);
        }
    }
}//end

