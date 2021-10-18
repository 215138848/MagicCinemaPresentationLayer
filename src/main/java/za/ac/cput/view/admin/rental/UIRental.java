package za.ac.cput.view.admin.rental;

import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.entity.rent.RentItems;
import za.ac.cput.factory.RentItemsFactory;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.service.RentalFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.admin.rental.equipmentActions.UIRentalReturn;
import za.ac.cput.view.general.AdminSideNavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UIRental extends JFrame {

    private JMenuBar mainMenu, buttonMenu, buttonMenu1;
    private ImageIcon iconLogo;
    private JLabel lblTitle, lblCurrentUser, lblHeadingStorage, lblHeadingEquipment;
    private static JTable table,equipmentPanelTable;
    private static DefaultTableModel model;
    private JScrollPane pane,pane0;
    private int indexSelected;

    public UIRental() {
        super("❣ Admin Rental Management");
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

        JButton btnUpdate1 = new JButton("Return Rental");
        btnUpdate1.setBackground(navColor);
        btnUpdate1.setForeground(textColor);
        btnUpdate1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                indexSelected = equipmentPanelTable.getSelectedRow();
                if(indexSelected != -1) {
                    int col = 0;
                    String id = String.valueOf(equipmentPanelTable.getValueAt( indexSelected, col ));
                    System.out.println(id);

                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                    String endDate = formatter.format(date);

                    RentItems returnItem = RentalFunctionality.getRentalItemById(id);
                    RentItems updateRental = RentItemsFactory.createRentItems(returnItem.getClient(), returnItem.getEquipment(), returnItem.getCost(), returnItem.getEstimateEndDate(),
                            endDate, 0, returnItem.getQuantity(),returnItem.getFinalCost());

                    RentItems output = new RentItems.Builder().copy(updateRental).setId(returnItem.getId()).build();
                    RentalFunctionality.updateRentalInformation(output);
                    double totalCost = output.getFinalCost();
                    JOptionPane.showMessageDialog(null, "Total cost:  R" + totalCost );

                    refresh();


                }
                else {
                    JOptionPane.showMessageDialog(null, "Select a row to update!.");
                }

            }
        });

        JButton btnReload1 = new JButton("Reload");
        btnReload1.setBackground(navColor);
        btnReload1.setForeground(textColor);

        buttonMenu1.add(btnUpdate1);
        buttonMenu1.add(btnReload1);
        panelMainStorage.add(buttonMenu);

        lblHeadingEquipment = new JLabel("Admin Rental Management", SwingConstants.LEFT);
        lblHeadingEquipment.setForeground(navColor);
        lblHeadingEquipment.setFont(new Font("Courier", Font.BOLD,16));
        lblHeadingEquipment.setPreferredSize(new Dimension(100, 50));
        lblHeadingEquipment.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeadingEquipment.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMainEquipment.add(lblHeadingEquipment);
        panelMainEquipment.add(buttonMenu1);

        JPanel nav = new AdminSideNavigationBar(userInfo, "Administrator", "images/employee-icons/person.jpg", "Rental Management", this);
        nav.setBackground(navColor);


        //Storage Center Panel
        JPanel equipmentPanel = new JPanel(new BorderLayout());
        equipmentPanelTable = new JTable();
        Object[] columnNames = { "ID", "Client", "Cost", "Days Overdue", "End Date", "Equipment", "EED", "Final Cost", "Quantity", "Start Date" };
        model = new DefaultTableModel(new Object[0][0], columnNames);
        ArrayList<RentItems> rentItemsArrayList = RentalFunctionality.getAllRentals();
        for(RentItems rentItem : rentItemsArrayList) {
            Object[] o = new Object[10];
            o[0] = rentItem.getId();
            o[1] = rentItem.getClient();
            o[2] = rentItem.getCost();
            o[3] = rentItem.getDaysOverdue();
            o[4] = rentItem.getEndDate();
            o[5] = rentItem.getEquipment();
            o[6] = rentItem.getEstimateEndDate();
            o[7] = rentItem.getFinalCost();
            o[8] = rentItem.getQuantity();
            o[9] = rentItem.getStartDate();
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
        ArrayList<RentItems> rentItemsArrayList = RentalFunctionality.getAllRentals();
        for(RentItems rentItem : rentItemsArrayList) {
            Object[] o = new Object[10];
            o[0] = rentItem.getId();
            o[1] = rentItem.getClient();
            o[2] = rentItem.getCost();
            o[3] = rentItem.getDaysOverdue();
            o[4] = rentItem.getEndDate();
            o[5] = rentItem.getEquipment();
            o[6] = rentItem.getEstimateEndDate();
            o[7] = rentItem.getFinalCost();
            o[8] = rentItem.getQuantity();
            o[9] = rentItem.getStartDate();
            model.addRow(o);
        }
    }
}//end

