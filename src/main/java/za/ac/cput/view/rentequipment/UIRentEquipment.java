package za.ac.cput.view.rentequipment;

import za.ac.cput.entity.Client;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.service.CustomerFunctionality;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.cart.UICart;
import za.ac.cput.view.dashboard.sales.UIDashboard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class UIRentEquipment extends JFrame implements ActionListener {
    private JMenuBar mainMenu;
    private ImageIcon iconLogo;
    private JLabel  lblHeading;
    private ArrayList<Client> ClientList;

    public UIRentEquipment() {
        super("❣ Ordering Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color checkoutLineSpacing = new Color(34,34,34);



        //North Panel
        JPanel panelNorth = new JPanel(new GridLayout(0, 3));
        panelNorth.setSize(new Dimension(1000, 60));
        panelNorth.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelNorth.setBackground(Color.WHITE);

        lblHeading = new JLabel("Gear Catalog", SwingConstants.LEFT);
        lblHeading.setForeground(Color.DARK_GRAY);
        lblHeading.setFont(new Font("Courier", Font.BOLD,16));
        lblHeading.setPreferredSize(new Dimension(100, 50));
        lblHeading.setHorizontalAlignment(SwingConstants.LEFT);
        lblHeading.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        //panelNorth.add(lblHeading);


        //Panel Center
        JPanel panelCenterHolder = new JPanel(new BorderLayout());
        panelCenterHolder.setBackground(Color.WHITE);
        JLabel filterText = new JLabel("Filter By:");

        ArrayList<Equipment> equipmentArrayList = EquipmentFunctionality.getAllEquipment();
        JPanel panelCenterData = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelCenterData.setPreferredSize(new Dimension(370,  (160 * equipmentArrayList.size())));
        panelCenterData.setBackground(Color.WHITE);

        //Feed Data into array -->
        String[] filterTypeStrings = EquipmentFunctionality.getAllCategories();
        JComboBox filterTypeList = new JComboBox(filterTypeStrings);
        filterTypeList.setBackground(Color.WHITE);

        filterTypeList.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                System.out.println(filterTypeList.getSelectedItem());
                ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
                String index = (String) filterTypeList.getSelectedItem();
                if(filterTypeList.getSelectedIndex() == 0) {
                    index = "clearFilters";
                }
                equipmentArrayList = EquipmentFunctionality.getAllEquipmentByCategory(index);

                panelCenterData.removeAll();

                for (int x =0; x < equipmentArrayList.size(); x++) {
                    JDisplayPanel data = new JDisplayPanel(
                            equipmentArrayList.get(x).getMake() + " " + equipmentArrayList.get(x).getModel(),
                            equipmentArrayList.get(x).getImagePath(),
                            equipmentArrayList.get(x).getRentalPrice(),
                            equipmentArrayList.get(x).getQuantity(),
                            equipmentArrayList.get(x).getEquipmentID());

                    panelCenterData.add(data);
                }

                panelCenterData.setPreferredSize(new Dimension(370,  (160 * equipmentArrayList.size())));
                panelCenterData.revalidate();
                panelCenterData.repaint();
            }
        });

        JPanel filterHolder = new JPanel(new GridLayout(3,3));
        filterHolder.setBackground(Color.WHITE);
        filterHolder.add(lblHeading);
        filterHolder.add(filterText);
        filterHolder.add(filterTypeList);

        panelCenterHolder.add(filterHolder, BorderLayout.NORTH);

        for (int x =0; x < equipmentArrayList.size(); x++) {
            JDisplayPanel data = new JDisplayPanel(
                    equipmentArrayList.get(x).getMake() + " " + equipmentArrayList.get(x).getModel(),
                    equipmentArrayList.get(x).getImagePath(),
                    equipmentArrayList.get(x).getRentalPrice(),
                    equipmentArrayList.get(x).getQuantity(),
                    equipmentArrayList.get(x).getEquipmentID());

            panelCenterData.add(data);
        }
        panelCenterHolder.add(panelCenterData, BorderLayout.CENTER);
        JScrollPane panelCenter = new JScrollPane(panelCenterHolder);
        panelCenter.setBackground(Color.WHITE);
        panelCenter.setBorder(new EmptyBorder(0,10,0,0));
        panelCenter.setPreferredSize(new Dimension(400, 450));

        //Panel East

        JPanel eastHolder = new JPanel();
        eastHolder.setBackground(Color.WHITE);
        eastHolder.setPreferredSize(new Dimension(300, 450));
        eastHolder.setBorder(new EmptyBorder(5, 20, 0, 20));
        BoxLayout boxlayout = new BoxLayout(eastHolder, BoxLayout.Y_AXIS);
        eastHolder.setLayout(boxlayout);
        eastHolder.setBackground(Color.BLACK);


        JLabel lblSummary = new JLabel("SUMMARY");
        lblSummary.setFont(new Font("Ayuthaya", Font.BOLD, 24));
        lblSummary.setForeground(Color.WHITE);
        lblSummary.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, checkoutLineSpacing));

        JButton btnDelete = new JButton("REMOVE ITEM");
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(this);

        //Add calculations to determine to total
        int calculation = 0;
        JLabel lblTotal = new JLabel("TOTAL: R" + calculation);
        lblTotal.setBorder(new EmptyBorder(3, 90, 2, 0));
        lblTotal.setFont(new Font("Ayuthaya", Font.BOLD, 14));
        lblTotal.setForeground(Color.WHITE);

        JButton btnCheckout = new JButton("CHECKOUT");
        btnCheckout.setBackground(Color.BLACK);
        btnCheckout.setForeground(Color.WHITE);
        btnCheckout.setPreferredSize(new Dimension(200, 50));

        JPanel deleteButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButtonPanel.setBackground(Color.BLACK);
        deleteButtonPanel.add(btnDelete);


        //South Panel
        GridLayout statsLayout = new GridLayout(3, 3);
        JPanel panelStats = new JPanel(statsLayout);
        panelStats.setSize(new Dimension(1000, 60));
        panelStats.setBorder(new EmptyBorder(20, 20, 20, 20));
        panelStats.setBackground(Color.WHITE);

        ClientList = CustomerFunctionality.getAllClients();
        String[] ClientNameList = CustomerFunctionality.getClientNames();
        JPanel panelWest = new JPanel(new GridLayout(9, 1));
        panelWest.setBorder( BorderFactory.createEmptyBorder(100,10,0,10) );
        panelWest.setPreferredSize(new Dimension(200, 300));
        JLabel panelWestLabelUser = new JLabel("SELECT CURRENT CUSTOMER:");
        JComboBox comboUsers = new JComboBox(ClientNameList);
        GenericHelper.createUserTracker(ClientList.get(0).getClientID());
        comboUsers.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = 0;
                selectedIndex = comboUsers.getSelectedIndex();
                GenericHelper.createUserTracker(ClientList.get(selectedIndex).getClientID());
            }});
        JButton goToCart = new JButton("Go to cart");
        goToCart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new UICart(ClientList.get(comboUsers.getSelectedIndex()).getClientID());
                dispose();
            }
        });
        JButton backButton = new JButton("Go To Dashboard");
        backButton.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                UIDashboard dashboard = new UIDashboard();
                dispose();
            }});

        panelWest.add(panelWestLabelUser);
        panelWest.add(comboUsers);
        panelWest.add(goToCart);
        panelWest.add(new JLabel());
        panelWest.add(new JLabel());
        panelWest.add(new JLabel());
        panelWest.add(new JLabel());
        panelWest.add(new JLabel());
        panelWest.add(backButton);


        setJMenuBar(mainMenu);
        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(panelStats, BorderLayout.SOUTH);
        getContentPane().add(panelWest, BorderLayout.WEST);


        //Display the window.
        setSize(1200, 1000);
        setPreferredSize(new Dimension(1150, 760));
        pack();
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
