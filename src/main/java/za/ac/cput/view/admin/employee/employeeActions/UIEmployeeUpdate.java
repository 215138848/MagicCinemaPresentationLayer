package za.ac.cput.view.admin.employee.employeeActions;

//import za.ac.cput.repository.employee.IEmployeeRepository;
import za.ac.cput.entity.Employee;
import za.ac.cput.entity.catelag.Equipment;
import za.ac.cput.factory.EmployeeFactory;
import za.ac.cput.service.EmployeeFunctionality;
import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.view.admin.equipment.UIEmployee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIEmployeeUpdate extends JFrame implements ActionListener {
    private JLabel lblTitle;
    private JTextField txtFName, txtLName, txtEmail, txtPassword, txtUsername;
    private JComboBox cbAccountType;
    private Employee employee;
    private int index;

    public UIEmployeeUpdate(int x, int y, String id) {
        super("❣ Employee Update Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(x, y);
        employee = EmployeeFunctionality.getEmployeeById(id);


        //Title
        //North Panel
        JPanel panelMain = new JPanel(new GridLayout(1, 0));
        panelMain.setSize(new Dimension(500, 60));
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.setBackground(Color.WHITE);

        lblTitle = new JLabel("UPDATE EMPLOYEES", SwingConstants.LEFT);
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Courier", Font.BOLD, 16));
        lblTitle.setPreferredSize(new Dimension(500, 50));
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMain.add(lblTitle);

        //Center Panel
        JPanel panelCenter = new JPanel(null);
        panelCenter.setPreferredSize(new Dimension(540, 200));
        panelCenter.setBackground(Color.WHITE);


        JLabel username = new JLabel("Username:");
        username.setBounds(10, 10, 80, 25);
        panelCenter.add(username);
        txtUsername = new JTextField();
        txtUsername.setText(employee.getUsername());
        txtUsername.setBounds(100, 10, 430, 25);
        panelCenter.add(txtUsername);

        JLabel fName = new JLabel("First Name:");
        fName.setBounds(10, 40, 80, 25);
        panelCenter.add(fName);
        txtFName = new JTextField();
        txtFName.setText(employee.getName());
        txtFName.setBounds(100, 40, 160, 25);
        panelCenter.add(txtFName);

        JLabel lName = new JLabel("Surname:");
        lName.setBounds(280, 40, 80, 25);
        panelCenter.add(lName);
        txtLName = new JTextField();
        txtLName.setText(employee.getSurname());
        txtLName.setBounds(370, 40, 160, 25);
        panelCenter.add(txtLName);

        JLabel email = new JLabel("Email:");
        email.setBounds(10, 80, 80, 25);
        panelCenter.add(email);
        txtEmail = new JTextField();
        txtEmail.setText(employee.getEmail());
        txtEmail.setBounds(100, 80, 430, 25);
        panelCenter.add(txtEmail);


        JLabel password = new JLabel("Password:");
        password.setBounds(10, 120, 80, 25);
        panelCenter.add(password);
        txtPassword = new JTextField();
        txtPassword.setText(String.valueOf(employee.getPassword()));
        txtPassword.setBounds(100, 120, 160, 25);
        panelCenter.add(txtPassword);


        JLabel accountType = new JLabel("Account Type:");
        accountType.setBounds(280, 120, 80, 25);
        panelCenter.add(accountType);

        String[] accountTypes = {"","Sales Person", "Administrator"};
        cbAccountType = new JComboBox(accountTypes);
        cbAccountType.setSelectedIndex(Integer.parseInt(employee.getRole()));
        cbAccountType.setBounds(370, 120, 160, 25);
        panelCenter.add(cbAccountType);

        JButton btnSave = new JButton("UPDATE");
        btnSave.addActionListener(this);
        btnSave.setBackground(Color.GREEN);
        btnSave.setBounds(200, 160, 80, 25);
        panelCenter.add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(this);
        btnCancel.setBackground(Color.BLACK);
        btnCancel.setBounds(290, 160, 80, 25);
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
        if (e.getActionCommand().equals("UPDATE")) {
            if(txtUsername.getText().equals("") || txtFName.getText().equals("") || txtLName.getText().equals("") ||
                    txtEmail.getText().equals("") || txtPassword.getText().equals("") || cbAccountType.getSelectedIndex() ==0) {
                JOptionPane.showMessageDialog(null, "Please fill in all required information.");

            }
            else {
                System.out.println("Updating information");
                String id = employee.getEmployeeNumber();
                String username = txtUsername.getText();
                String fName = txtFName.getText();
                String lName = txtLName.getText();
                String email = txtEmail.getText();
                int password = Integer.parseInt(txtPassword.getText());
                String accountType = String.valueOf(cbAccountType.getSelectedIndex());

                Employee updateEmployee = new Employee.Builder().copy(employee)
                        .setUsername(username)
                        .setName(fName)
                        .setSurname(lName)
                        .setEmail(email)
                        .setPassword(password)
                        .setRole(accountType)
                        .build();

                EmployeeFunctionality.updateEmployee(updateEmployee);
                UIEmployee.refresh();
                dispose();
            }

        } else if (e.getActionCommand().equals("CANCEL")) {
            dispose();
        }
    }
}
