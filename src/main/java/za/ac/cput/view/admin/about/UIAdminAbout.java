package za.ac.cput.view.admin.about;

import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.general.AdminSideNavigationBar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UIAdminAbout extends JFrame {

    public UIAdminAbout() {
        super("❣ CINEMA MAGIC ABOUT");

        JPanel centerNorthPanel = new JPanel();
        centerNorthPanel.setBackground(Color.WHITE);
        ImageIcon iconHeading = new ImageIcon("images/dashboard/magic-cinema-dashboard.png");
        JLabel imageHeadingLabel = new JLabel();
        imageHeadingLabel.setIcon(iconHeading);

        centerNorthPanel.add(imageHeadingLabel);

        GridLayout layout = new GridLayout(4,1);
        layout.setHgap(5);
        JPanel centerCenterPanel = new JPanel(layout);
        centerCenterPanel.setPreferredSize(new Dimension(1000,300));
        centerCenterPanel.setBorder(new EmptyBorder(0,195,0,195));
        centerCenterPanel.setBackground(Color.WHITE);

        JLabel lblHeading = new JLabel("About Us");
        lblHeading.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 32));

        JTextArea textHolder = new JTextArea("Magic Cinema is a database management system for renting out software and keeping track of when " +
                "the software is to be returned, when it is overdue, Magic Cinema stores data that makes interaction easier between " +
                "employee and customer in their daily life.");
        textHolder.setFont(new Font("Arial", Font.PLAIN, 16));
        textHolder.setLineWrap(true);
        textHolder.setWrapStyleWord(true);


        centerCenterPanel.add(lblHeading);
        centerCenterPanel.add(textHolder);

        JPanel centerSouthPanel = new JPanel();
        centerSouthPanel.setBackground(Color.WHITE);
        ImageIcon iconLogos = new ImageIcon("images/dashboard/logos-dashboard.png");
        JLabel imageHolderLogos = new JLabel();
        imageHolderLogos.setIcon(iconLogos);

        centerSouthPanel.add(imageHolderLogos);

        String userInfo = GenericHelper.getEmployeeId();
        JPanel nav = new AdminSideNavigationBar(userInfo, "Administrator", "images/employee-icons/person.jpg", "About", this);
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.add(centerNorthPanel, BorderLayout.NORTH);
        centerPanel.add(centerCenterPanel, BorderLayout.CENTER);
        centerPanel.add(centerSouthPanel, BorderLayout.SOUTH);

        getContentPane().add(centerPanel, BorderLayout.CENTER);
        getContentPane().add(nav, BorderLayout.WEST);

        setSize(1200, 1000);
        setPreferredSize(new Dimension(1150, 760));
        setResizable(false);
        pack();
        setVisible(true);
    }
}
