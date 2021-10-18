package za.ac.cput.view.cart;

import com.toedter.calendar.JDateChooser;
import lombok.SneakyThrows;
import za.ac.cput.entity.rent.EquipmentRental;
import za.ac.cput.entity.rent.RentItems;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.factory.EquipmentRentalFactory;
import za.ac.cput.factory.RentItemsFactory;
import za.ac.cput.service.CartFunctionality;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.service.RentalFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.rentequipment.UIRentEquipment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

public class UICart extends JFrame implements ActionListener {
    private JPanel panelNorth, panelCenter, panelSouth, panelEast;
    private JLabel lblHeading;
    private JButton btnBack, btnClearCart, btnCheckout;
    private Set<Cart> currentCustomerCart;
    private JDateChooser jDateChooser1;

    public UICart(String customerId) {
        super("Cart Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color navColor = new Color(186,39,94);


        System.out.println(customerId);
        currentCustomerCart = CartFunctionality.getCustomerCartInformation(customerId);
        panelNorth = new JPanel();
        panelNorth.setBackground(navColor);
        panelNorth.setBorder(new EmptyBorder(60,20,10,20));
        lblHeading = new JLabel("Cart Details");
        lblHeading.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));
        panelNorth.add(lblHeading);

        panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        Object[] columnNames = { " ", "Name", "Price", "Quantity", "Subtotal" };
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        for(Cart cart : currentCustomerCart) {
            String name = EquipmentFunctionality.readNameById(cart.getEquipmentId());

            Object[] o = new Object[5];
            o[0] = cart.getId();
            o[1] = name;
            o[2] = "R" + cart.getPrice();
            o[3] = cart.getQuantity();
            o[4] = "R" + cart.getSubtotal();
            model.addRow(o);
        }
        panelCenter.setBackground(Color.WHITE);
        JTable j = new JTable();
        j.setModel(model);

        j.getColumnModel().getColumn(0).setPreferredWidth(18);
        j.getColumnModel().getColumn(1).setPreferredWidth(180);
        j.getColumnModel().getColumn(3).setPreferredWidth(30);
        j.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

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
        btnClearCart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to clear your cart?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    CartFunctionality.clearCart(customerId);
                    new UIRentEquipment();
                    dispose();
                } else {
                    // no option
                }

            }});
        btnClearCart.setForeground(Color.WHITE);
        btnCheckout = new JButton("Checkout");

        Set<Cart> CustomerCart = CartFunctionality.getCustomerCartInformation(customerId);
        btnCheckout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                java.util.Date jud =  jDateChooser1.getDate();
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                String estimateDate = formatter.format(jud);

                if (JOptionPane.showConfirmDialog(null, "Are you sure you want to rent your cart?", "WARNING",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    for(Cart cart: CustomerCart) {
                        RentItems rentItem;
                        rentItem = RentItemsFactory.createRentItems(customerId, cart.getEquipmentId(), cart.getSubtotal(), estimateDate, "", 0, cart.getQuantity(), 0);
                        RentalFunctionality.saveRental(rentItem);

                    }
                    new UIRentEquipment();
                    dispose();
                } else {
                    // no option
                }

            }});
        btnCheckout.setBackground(Color.GREEN);
        btnCheckout.setForeground(Color.WHITE);

        panelSouth.add(btnBack);
        panelSouth.add(btnClearCart);
        panelSouth.add(btnCheckout);


        panelEast = new JPanel();
        panelEast.setBackground(Color.WHITE);
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        panelEast.add(new JLabel("Select the estimate return day:"));
        panelEast.add(jDateChooser1);

        getContentPane().add(panelNorth, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.EAST);
        getContentPane().add(panelEast, BorderLayout.CENTER);
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
