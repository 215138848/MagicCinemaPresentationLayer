package za.ac.cput.view.cart;

import za.ac.cput.view.rentequipment.UIRentEquipment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICart extends JFrame implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth;
    private JLabel lblHeading;
    private JButton btnBack, btnClearCart, btnCheckout;
    public UICart(String customerId) {
        super("Cart Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println(customerId);
        panelNorth = new JPanel();
        panelNorth.setBorder(new EmptyBorder(60,20,10,20));
        lblHeading = new JLabel("Cart Details");
        lblHeading.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
        panelNorth.add(lblHeading);

        panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        String[][] data = {
                { "1", "Kundan Kumar Jha", "4031", "4" , "111" },
                { "1", "Anand Jha", "6014", "321", "2" , "1235" }
        };
        String[] columnNames = { " ", "Name", "Price", "Quantity", "Subtotal" };
        JTable j = new JTable(data, columnNames);
        j.setBounds(30, 40, 600, 300);
        JScrollPane sp = new JScrollPane(j);
        panelCenter.add(sp);

        panelSouth = new JPanel();
        panelSouth.setBackground(Color.WHITE);
        btnBack = new JButton("Back");
        btnBack.setBackground(Color.BLUE);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new UIRentEquipment();
                dispose();
            }});
        btnClearCart = new JButton("Clear Cart");
        btnClearCart.setBackground(Color.RED);
        btnClearCart.setForeground(Color.WHITE);
        btnCheckout = new JButton("Checkout");
        btnCheckout.setBackground(Color.GREEN);
        btnCheckout.setForeground(Color.WHITE);

        panelSouth.add(btnBack);
        panelSouth.add(btnClearCart);
        panelSouth.add(btnCheckout);

        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);
        getContentPane().add(panelSouth, BorderLayout.SOUTH);


        //Display the window.
        setSize(1200, 1000);
        setPreferredSize(new Dimension(950, 620));
        pack();
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
