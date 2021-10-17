package za.ac.cput.view.admin.equipment.equipmentActions;

import za.ac.cput.service.EmployeeFunctionality;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.view.admin.equipment.UIEquipment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIEquipmentDelete extends JFrame implements ActionListener {
    private JLabel lblTitle;
    private JTextField txtFName, txtLName, txtEmail, txtPassword;
    private JComboBox cbAccountType;
    private String id;

    public UIEquipmentDelete(int x, int y, String id) {
        super("❣ Storage Location Delete Menu");
        this.id = id;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(x, y);

        Color navColor = new Color(186, 39, 94);
        Color headingText = new Color(230, 196, 96);
        Color textColor = Color.WHITE;

        //North Panel
        JPanel panelMain = new JPanel(new GridLayout(1, 0));
        panelMain.setSize(new Dimension(300, 60));
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.setBackground(Color.WHITE);

        lblTitle = new JLabel("DELETE THIS EQUIPMENT?", SwingConstants.LEFT);
        lblTitle.setForeground(navColor);
        lblTitle.setFont(new Font("Courier", Font.BOLD, 16));
        lblTitle.setPreferredSize(new Dimension(250, 50));
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMain.add(lblTitle);

        //Center Panel
        JPanel panelCenter = new JPanel(null);
        panelCenter.setPreferredSize(new Dimension(250, 100));
        panelCenter.setBackground(Color.WHITE);


        JLabel fDelete = new JLabel("Are you sure you want to delete?");
        fDelete.setBounds(10, 10, 300, 25);
        panelCenter.add(fDelete);

        JButton btConfirm = new JButton("YES");
        btConfirm.addActionListener(this);
        btConfirm.setBackground(navColor);
        btConfirm.setBounds(20, 35, 80, 25);
        panelCenter.add(btConfirm);

        JButton btnCancel = new JButton("NO");
        btnCancel.addActionListener(this);
        btnCancel.setBackground(navColor);
        btnCancel.setBounds(120, 35, 80, 25);
        panelCenter.add(btnCancel);

        getContentPane().add(panelMain, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);

        //Display the window.
        setUndecorated(true);
        setSize(250, 150);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("YES")) {
            EquipmentFunctionality.deleteEquipment(id);
            UIEquipment.refresh();
            dispose();
        } else if (e.getActionCommand().equals("NO")) {
            dispose();
        }

    }
}