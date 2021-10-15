package za.ac.cput.view.dashboard.sales;


import za.ac.cput.view.general.SideNavigationBar;

import javax.swing.*;
import java.awt.*;

public class UIDashboard extends JFrame {

    public UIDashboard() {
        super("❣ CINEMA MAGIC DASHBOARD");

        JPanel centerNorthPanel = new JPanel();
        centerNorthPanel.setBackground(Color.WHITE);
        ImageIcon iconHeading = new ImageIcon("images/dashboard/magic-cinema-dashboard.png");
        JLabel imageHeadingLabel = new JLabel();
        imageHeadingLabel.setIcon(iconHeading);

        centerNorthPanel.add(imageHeadingLabel);

        GridLayout layout = new GridLayout(1,4);
        layout.setHgap(5);
        JPanel centerCenterPanel = new JPanel(layout);
        centerCenterPanel.setBackground(Color.WHITE);

        ImageIcon iconCameras = new ImageIcon("images/dashboard/cameras-dashboard.png");
        JLabel imageHolderCameras = new JLabel();
        imageHolderCameras.setIcon(iconCameras);

        ImageIcon iconLences = new ImageIcon("images/dashboard/lences-dashboard.png");
        JLabel imageHolderLences = new JLabel();
        imageHolderLences.setIcon(iconLences);

        ImageIcon iconLighting = new ImageIcon("images/dashboard/lighting-dashboard.png");
        JLabel imageHolderLighting = new JLabel();
        imageHolderLighting.setIcon(iconLighting);

        ImageIcon iconAccessories = new ImageIcon("images/dashboard/accessories-dashboard.png");
        JLabel imageHolderAccessories = new JLabel();
        imageHolderAccessories.setIcon(iconAccessories);


        centerCenterPanel.add(imageHolderCameras);
        centerCenterPanel.add(imageHolderLences);
        centerCenterPanel.add(imageHolderLighting);
        centerCenterPanel.add(imageHolderAccessories);

        JPanel centerSouthPanel = new JPanel();
        centerSouthPanel.setBackground(Color.WHITE);
        ImageIcon iconLogos = new ImageIcon("images/dashboard/logos-dashboard.png");
        JLabel imageHolderLogos = new JLabel();
        imageHolderLogos.setIcon(iconLogos);

        centerSouthPanel.add(imageHolderLogos);

        JPanel nav = new SideNavigationBar("Nina Abrev", "Administrator", "images/employee-icons/person.jpg", "Dashboard", this);
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
