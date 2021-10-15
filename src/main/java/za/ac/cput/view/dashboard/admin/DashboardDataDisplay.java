package za.ac.cput.view.dashboard.admin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardDataDisplay extends JPanel {
    private JLabel iconHolder, text, count;
    private ImageIcon iconDisplay;

    public DashboardDataDisplay(String title, String countTracker, String displayLocation) {

        Color navColor = new Color(186,39,94);
        Color headingText = new Color(230, 196, 96);
        Color textColor = Color.WHITE;

        text = new JLabel(title);
        text.setForeground(textColor);
        text.setFont(new Font("SansSerif Bold", Font.PLAIN, 16));
        count = new JLabel(countTracker);
        count.setFont(new Font("SansSerif Bold", Font.BOLD, 40));
        count.setForeground(headingText);
        iconDisplay = new ImageIcon(displayLocation);

        iconHolder = new JLabel();
        iconHolder.setIcon(iconDisplay);

        JPanel panelSouth = new JPanel();
        panelSouth.setBackground(navColor);
        panelSouth.add(text);

        JPanel panelNorth = new JPanel(new GridLayout(1,2));
        panelNorth.setBackground(navColor);
        panelNorth.add(iconHolder);


        JPanel panelCenter = new JPanel();
        panelCenter.setBackground(navColor);
        panelCenter.add(count);

        JPanel boxContainerOuter = new JPanel();
        boxContainerOuter.setBackground(navColor);
        boxContainerOuter.setLayout(new BorderLayout());

        boxContainerOuter.add(panelNorth, BorderLayout.NORTH);
        boxContainerOuter.add(panelCenter, BorderLayout.CENTER);
        boxContainerOuter.add(panelSouth, BorderLayout.SOUTH);

        JScrollPane mainScrollPane = new JScrollPane(boxContainerOuter);
        mainScrollPane.setBorder(new EmptyBorder(5,5,10,10));
        mainScrollPane.setBackground(navColor);

        add(mainScrollPane);

    }
}

