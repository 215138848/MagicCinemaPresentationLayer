package za.ac.cput.view.rentequipment;

import okhttp3.MediaType;
import za.ac.cput.entity.rentalcart.Cart;
import za.ac.cput.service.CartFunctionality;
import za.ac.cput.util.GenericHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDisplayPanel extends JPanel implements ActionListener {


    private JLabel itemName, itemPrice;
    private String itemPath;
    private JButton addToCart;
    private ImageIcon image;
    private JComboBox quantityComboBox;
    private double itemCost;
    protected final int quantity;
    private String customerId, equipmentId;

    public JDisplayPanel(String itemName, String itemPath, double itemPrice, int quantity, String equipmentId) {
        this.itemCost = itemPrice;
        this.quantity = quantity;
        this.equipmentId = equipmentId;

        this.itemName = new JLabel(itemName);
        this.itemPrice = new JLabel(String.valueOf(itemPrice), SwingConstants.CENTER);
        this.itemPath = itemPath;

        this.image = new ImageIcon(this.itemPath);
        this.itemName.setIcon(this.image);
        this.itemName.setVerticalTextPosition(JButton.TOP);
        this.itemName.setHorizontalTextPosition(JButton.CENTER);
        this.itemName.setFont(new Font("Courier", Font.BOLD,15));


        this.setBackground(Color.WHITE);

        this.addToCart = new JButton("Add To Cart");
        this.addToCart.addActionListener(this);
        this.addToCart.setBackground(Color.BLACK);
        this.addToCart.setForeground(Color.WHITE);
        this.addToCart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.addToCart.setAlignmentX(Component.RIGHT_ALIGNMENT);
        this.addToCart.setPreferredSize(new Dimension(170, 30));

        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(Color.WHITE);
        panelSouth.add(this.addToCart);

        JPanel panelNorth = new JPanel();
        panelNorth.setBackground(Color.WHITE);
        panelNorth.add(this.itemName);


        JPanel panelCenter = new JPanel(new GridLayout(1,2));
        panelCenter.setBackground(Color.WHITE);
        panelCenter.add(this.itemPrice);

        quantityComboBox = new JComboBox();
        if(quantity > 0) {
            for (int x = 0; x <= quantity; x++) {
                quantityComboBox.addItem(x);
            }
        }
        int selectedIndex = 0;
        quantityComboBox.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {

            }});
        panelCenter.add(quantityComboBox);

        JPanel boxContainerOuter = new JPanel();
        boxContainerOuter.setLayout(new BorderLayout());

        boxContainerOuter.add(panelNorth, BorderLayout.NORTH);
        boxContainerOuter.add(panelCenter, BorderLayout.CENTER);
        boxContainerOuter.add(panelSouth, BorderLayout.SOUTH);

        JScrollPane mainScrollPane = new JScrollPane(boxContainerOuter);

        this.add(mainScrollPane);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Add To Cart")) {
            Integer quantityIndex = (Integer) quantityComboBox.getSelectedItem();
            if(quantityIndex != 0) {
                CartFunctionality.saveCart(equipmentId, itemCost, quantityIndex);
            }
            else {
                JOptionPane.showMessageDialog(null, "Value 0 cannot be added to a cart.");
            }

        }

    }
}
