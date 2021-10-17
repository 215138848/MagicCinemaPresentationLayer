package za.ac.cput.view.customer;

import za.ac.cput.entity.rent.EquipmentRental;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.factory.EquipmentRentalFactory;
import za.ac.cput.service.CartFunctionality;
import za.ac.cput.service.CustomerFunctionality;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.service.RentalFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.dashboard.sales.UIDashboard;
import za.ac.cput.view.rentequipment.UIRentEquipment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIAddCustomer extends JFrame {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblHeading;
    private JButton btnSave, btnBack, btnClear;

    public UIAddCustomer() {
        super("Add New Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelNorth = new JPanel();
        panelNorth.setBorder(new EmptyBorder(60, 20, 10, 20));
        lblHeading = new JLabel("Add New Customer");
        lblHeading.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
        panelNorth.add(lblHeading);

        panelCenter = new JPanel(new GridLayout(7, 2));
        panelCenter.setBackground(Color.WHITE);
        JLabel lblName = new JLabel("CUSTOMER NAME: ");
        JTextField txtName = new JTextField();
        JLabel lblSurname = new JLabel("CUSTOMER SURNAME: ");
        JTextField txtSurname = new JTextField();
        JLabel lblNumber = new JLabel("CUSTOMER NUMBER: ");
        JTextField txtNumber = new JTextField();
        panelCenter.add(lblName);
        panelCenter.add(txtName);
        panelCenter.add(lblSurname);
        panelCenter.add(txtSurname);
        panelCenter.add(lblNumber);
        panelCenter.add(txtNumber);

        panelSouth = new JPanel();
        panelSouth.setBackground(Color.WHITE);
        btnSave = new JButton("Save");
        btnSave.setBackground(Color.GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!txtName.getText().equals("") &&
                !txtSurname.getText().equals("") &&
                !txtNumber.getText().equals("")) {

                    String customerName = txtName.getText();
                    String customerSurname = txtSurname.getText();
                    String customerNumber = txtNumber.getText();

                    CustomerFunctionality.saveCustomer(customerName, customerSurname, customerNumber);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Please fill in all the information requested");
                }
            }
        });
        btnClear = new JButton("Clear");
        btnClear.setBackground(Color.PINK);
        btnClear.setForeground(Color.WHITE);
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtSurname.setText("");
                txtNumber.setText("");
            }
        });
        btnBack = new JButton("Back");
        btnBack.setBackground(Color.BLUE);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new UIDashboard();
                dispose();
            }
        });

        panelSouth.add(btnSave);
        panelSouth.add(btnClear);
        panelSouth.add(btnBack);

        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);


        //Display the window.
        setSize(1200, 1000);
        setPreferredSize(new Dimension(950, 620));
        pack();
        setVisible(true);
    }
}
