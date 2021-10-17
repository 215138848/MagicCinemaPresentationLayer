package za.ac.cput.view.admin.customer.customerAction;

import za.ac.cput.entity.Client;
import za.ac.cput.entity.Employee;
import za.ac.cput.factory.ClientFactory;
import za.ac.cput.factory.EmployeeFactory;
import za.ac.cput.service.CustomerFunctionality;
import za.ac.cput.service.EmployeeFunctionality;
import za.ac.cput.view.admin.customer.UICustomer;
import za.ac.cput.view.admin.equipment.UIEmployee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UICustomerAdd extends JFrame implements ActionListener {
    private JLabel lblTitle;
    private JTextField txtFName, txtLName,txtContactNumber;

    public UICustomerAdd(int x, int y) {
        super("❣ Customer Add Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(x, y);

        //Title
        //North Panel
        JPanel panelMain = new JPanel(new GridLayout(1, 0));
        panelMain.setSize(new Dimension(500, 60));
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.setBackground(Color.WHITE);

        lblTitle = new JLabel("ADD NEW CUSTOMER", SwingConstants.LEFT);
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Courier", Font.BOLD,16));
        lblTitle.setPreferredSize(new Dimension(500, 50));
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMain.add(lblTitle);

        //Center Panel
        JPanel panelCenter = new JPanel(null);
        panelCenter.setPreferredSize(new Dimension(540, 120));
        panelCenter.setBackground(Color.WHITE);


        JLabel fName = new JLabel("First Name:");
        fName.setBounds(10, 10, 80, 25);
        panelCenter.add(fName);
        txtFName = new JTextField();
        txtFName.setBounds(100, 10, 160, 25);
        panelCenter.add(txtFName);

        JLabel lName = new JLabel("Surname:");
        lName.setBounds(280, 10, 80, 25);
        panelCenter.add(lName);
        txtLName = new JTextField();
        txtLName.setBounds(370, 10, 160, 25);
        panelCenter.add(txtLName);

        JLabel email = new JLabel("Number:");
        email.setBounds(10, 40, 80, 25);
        panelCenter.add(email);
        txtContactNumber = new JTextField();
        txtContactNumber.setBounds(100, 40, 430, 25);
        panelCenter.add(txtContactNumber);


        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(this);
        btnSave.setBackground(Color.GREEN);
        btnSave.setBounds(200, 80, 80, 25);
        panelCenter.add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(this);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setBounds(290, 80, 80, 25);
        panelCenter.add(btnCancel);

        getContentPane().add(panelMain, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);

        //Display the window.
        setUndecorated(true);
        setSize(500, 700);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE")) {
            if(txtFName.getText().equals("") || txtLName.getText().equals("") || txtContactNumber.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all required information.");

            }
            else {
                System.out.println("Adding information");
                String fName = txtFName.getText();
                String lName = txtLName.getText();
                String contactNumber = txtContactNumber.getText();

                Client client = ClientFactory.createClient(fName, lName, contactNumber);

                System.out.println(client);

                CustomerFunctionality.saveCustomer(client.getName(), client.getSurname(), client.getContactNumber());
                UICustomer.refresh();
                dispose();
            }


        }
        else if (e.getActionCommand().equals("CANCEL")) {
            dispose();
        }

    }
}
