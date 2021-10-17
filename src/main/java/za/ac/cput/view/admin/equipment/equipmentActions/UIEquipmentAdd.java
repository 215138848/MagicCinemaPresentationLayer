package za.ac.cput.view.admin.equipment.equipmentActions;

import za.ac.cput.service.EquipmentFunctionality;
import za.ac.cput.view.admin.equipment.UIEquipment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIEquipmentAdd extends JFrame implements ActionListener {
    private JLabel lblTitle, lblEquipmentId, lblEquipmentCategory, lblSubCategory, lblMake, lblModel, lblCostPerUnit, lblQuantity, lblIsAval, lblImagePath;
    private JTextField txtEquipmentId, txtSubCategory, txtMake, txtModel, txtCostPerUnit, txtQuantity;
    private JComboBox cbEquipmentCategory;
    private JFileChooser jFC;

    public UIEquipmentAdd(int x, int y) {
        super("❣ Equipment Add Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocation(x, y);

        Color navColor = new Color(186,39,94);
        Color headingText = new Color(230, 196, 96);
        Color textColor = Color.WHITE;

        //North Panel
        JPanel panelMain = new JPanel(new GridLayout(1, 0));
        panelMain.setSize(new Dimension(300, 60));
        panelMain.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelMain.setBackground(Color.WHITE);

        lblTitle = new JLabel("ADD NEW EQUIPMENT", SwingConstants.LEFT);
        lblTitle.setForeground(navColor);
        lblTitle.setFont(new Font("Courier", Font.BOLD,16));
        lblTitle.setPreferredSize(new Dimension(300, 50));
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.DARK_GRAY));

        panelMain.add(lblTitle);

        //Center Panel
        JPanel panelCenter = new JPanel(null);
        panelCenter.setPreferredSize(new Dimension(340, 380));
        panelCenter.setBackground(Color.WHITE);

        lblEquipmentId = new JLabel("ID:");
        lblEquipmentId.setBounds(10, 10, 80, 25);
        panelCenter.add(lblEquipmentId);
        txtEquipmentId = new JTextField();
        txtEquipmentId.setBounds(100, 10, 160, 25);
        panelCenter.add(txtEquipmentId);

        lblEquipmentCategory = new JLabel("Category:");
        lblEquipmentCategory.setBounds(10, 50, 80, 25);
        panelCenter.add(lblEquipmentCategory);
        String[] filterTypeStrings = EquipmentFunctionality.getCategoryIds();
        cbEquipmentCategory = new JComboBox(filterTypeStrings);
        cbEquipmentCategory.setBounds(100, 50, 160, 25);
        panelCenter.add(cbEquipmentCategory);

        lblSubCategory = new JLabel("Sub Category:");
        lblSubCategory.setBounds(10, 90, 80, 25);
        panelCenter.add(lblSubCategory);
        txtSubCategory = new JTextField();
        txtSubCategory.setBounds(100, 90, 160, 25);
        panelCenter.add(txtSubCategory);

        lblMake = new JLabel("Make:");
        lblMake.setBounds(10, 130, 80, 25);
        panelCenter.add(lblMake);
        txtMake = new JTextField();
        txtMake.setBounds(100, 130, 160, 25);
        panelCenter.add(txtMake);

        lblModel = new JLabel("Model:");
        lblModel.setBounds(10, 170, 80, 25);
        panelCenter.add(lblModel);
        txtModel = new JTextField();
        txtModel.setBounds(100, 170, 160, 25);
        panelCenter.add(txtModel);

        lblCostPerUnit = new JLabel("Rent Cost:");
        lblCostPerUnit.setBounds(10, 210, 80, 25);
        panelCenter.add(lblCostPerUnit);
        txtCostPerUnit = new JTextField();
        txtCostPerUnit.setBounds(100, 210, 160, 25);
        panelCenter.add(txtCostPerUnit);

        lblQuantity = new JLabel("Quantity:");
        lblQuantity.setBounds(10, 250, 80, 25);
        panelCenter.add(lblQuantity);
        txtQuantity = new JTextField();
        txtQuantity.setBounds(100, 250, 160, 25);
        panelCenter.add(txtQuantity);

        lblImagePath = new JLabel("<html>*Important: Place image in the equipment folder <br/>under same name as ID (Format .jpg)</html>");
        lblImagePath.setForeground(Color.RED);
        lblImagePath.setBounds(10, 290, 300, 30);
        panelCenter.add(lblImagePath);


        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(this);
        btnSave.setBackground(navColor);
        btnSave.setBounds(50, 340, 80, 25);
        panelCenter.add(btnSave);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(this);
        btnCancel.setBackground(navColor);
        btnCancel.setBounds(150, 340, 80, 25);
        panelCenter.add(btnCancel);

        getContentPane().add(panelMain, BorderLayout.NORTH);
        getContentPane().add(panelCenter, BorderLayout.CENTER);

        //Display the window.
        setUndecorated(true);
        setSize(300, 1200);
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("SAVE")) {
            if(txtEquipmentId.getText().equals("") || cbEquipmentCategory.getSelectedIndex() == 0 || txtSubCategory.getText().equals("")
                    || txtModel.getText().equals("") || txtMake.getText().equals("") || txtCostPerUnit.getText().equals("") ||
            txtQuantity.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill in all required information.");
            }
            else {
                String equipmentId = txtEquipmentId.getText();
                String equipmentCategory = (String) cbEquipmentCategory.getSelectedItem();
                String equipmentSubCategory = txtSubCategory.getText();
                String model = txtModel.getText();
                String make = txtMake.getText();
                double costPerUnit = Double.parseDouble(txtCostPerUnit.getText());
                int quantity = Integer.parseInt(txtQuantity.getText());
                EquipmentFunctionality.saveEquipment(equipmentId, equipmentCategory, equipmentSubCategory, make, model, costPerUnit, quantity);
                UIEquipment.refresh();
                dispose();
            }

        }
        else if (e.getActionCommand().equals("CANCEL")) {
            dispose();
        }

    }
}

