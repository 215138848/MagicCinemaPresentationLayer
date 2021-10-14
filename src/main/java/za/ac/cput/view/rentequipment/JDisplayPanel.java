package za.ac.cput.view.rentequipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDisplayPanel extends JPanel implements ActionListener {

    private JLabel itemName, itemPrice;
    private String itemPath;
    private JButton addToCart;
    private ImageIcon image;

    public JDisplayPanel(String itemName, String itemPath, double itemPrice) {
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


        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(Color.WHITE);
        panelCenter.add(this.itemPrice);

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
            System.out.println("Add " + itemName.getText() + " to cart");
        }

    }
}
