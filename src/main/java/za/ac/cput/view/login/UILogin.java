package za.ac.cput.view.login;
import za.ac.cput.entity.Employee;
import za.ac.cput.service.EmployeeFunctionality;
import za.ac.cput.util.GenericHelper;
import za.ac.cput.view.dashboard.admin.UIAdminDashboard;
import za.ac.cput.view.dashboard.sales.UIDashboard;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UILogin extends JFrame implements ActionListener {

    private Container cMainContainer;
    private JPanel panelKeypad;
    private JLabel lblTitle, lblAccessCode;
    private JPasswordField passAccessCode;
    private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnStar, btnX, btnLogin;
    private ImageIcon iconLogo;
    private Color main_color, sub_color, text_color;

    public UILogin() {
        super("CINEMA MAGIC");

        // Color

        main_color = new Color(0,0,0);
        sub_color = new Color(255, 255, 255);
        text_color = new Color(169,169,169);

        cMainContainer = getContentPane();
        cMainContainer.setLayout(null);

        // Image
        iconLogo = new ImageIcon("images/logo.png");
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setIcon(iconLogo);
        lblTitle.setSize(300, 60);
        lblTitle.setLocation(25, 30);
        cMainContainer.add(lblTitle);

        //Button Panel
        panelKeypad = new JPanel(new GridLayout(4,3));
        panelKeypad.setBackground(sub_color);

        //Buttons
        btn1 = new JButton("1");
        btn1.setSize(50,50);
        btn1.setBackground(main_color);
        btn1.setForeground(sub_color);
        btn1.addActionListener(this);
        btn2 = new JButton("2");
        btn2.setSize(50,50);
        btn2.setBackground(main_color);
        btn2.setForeground(sub_color);
        btn2.addActionListener(this);
        btn3 = new JButton("3");
        btn3.setSize(50,50);
        btn3.setBackground(main_color);
        btn3.setForeground(sub_color);
        btn3.addActionListener(this);
        btn4 = new JButton("4");
        btn4.setSize(50,50);
        btn4.setBackground(main_color);
        btn4.setForeground(sub_color);
        btn4.addActionListener(this);
        btn5 = new JButton("5");
        btn5.setSize(50,50);
        btn5.setBackground(main_color);
        btn5.setForeground(sub_color);
        btn5.addActionListener(this);
        btn6 = new JButton("6");
        btn6.setSize(50,50);
        btn6.setBackground(main_color);
        btn6.setForeground(sub_color);
        btn6.addActionListener(this);
        btn7 = new JButton("7");
        btn7.setSize(50,50);
        btn7.setBackground(main_color);
        btn7.setForeground(sub_color);
        btn7.addActionListener(this);
        btn8 = new JButton("8");
        btn8.setSize(50,50);
        btn8.setBackground(main_color);
        btn8.setForeground(sub_color);
        btn8.addActionListener(this);
        btn9 = new JButton("9");
        btn9.setSize(50,50);
        btn9.setBackground(main_color);
        btn9.setForeground(sub_color);
        btn9.addActionListener(this);
        btnStar = new JButton("*");
        btnStar.setSize(50,50);
        btnStar.setBackground(main_color);
        btnStar.setForeground(sub_color);
        btn0 = new JButton("0");
        btn0.setSize(50,50);
        btn0.setBackground(main_color);
        btn0.setForeground(sub_color);
        btn0.addActionListener(this);
        btnX = new JButton("X");
        btnX.setSize(50,50);
        btnX.setBackground(main_color);
        btnX.setForeground(sub_color);
        btnX.addActionListener(this);

        //Label Access Code
        lblAccessCode = new JLabel("Access Code:");
        lblAccessCode.setSize(350, 100);
        lblAccessCode.setLocation(60, 90);
        cMainContainer.add(lblAccessCode);

        //Add Buttons To Panel
        panelKeypad.add(btn1);
        panelKeypad.add(btn2);
        panelKeypad.add(btn3);
        panelKeypad.add(btn4);
        panelKeypad.add(btn5);
        panelKeypad.add(btn6);
        panelKeypad.add(btn7);
        panelKeypad.add(btn8);
        panelKeypad.add(btn9);
        panelKeypad.add(btnStar);
        panelKeypad.add(btn0);
        panelKeypad.add(btnX);

        panelKeypad.setSize(210, 280);
        panelKeypad.setLocation(60, 160);
        cMainContainer.add(panelKeypad);

        //Access Code Text
        passAccessCode = new JPasswordField();
        passAccessCode.setEditable(false);
        passAccessCode.setEchoChar('*');
        //passAccessCode.setEchoChar((char)0);
        passAccessCode.setBackground(sub_color);
        passAccessCode.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        passAccessCode.setSize(210, 50);
        passAccessCode.setLocation(60, 440);
        cMainContainer.add(passAccessCode);

        //Submit Button
        btnLogin = new JButton("SIGN IN");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 15));
        btnLogin.setBorder(new LineBorder(main_color, 1));
        btnLogin.setBackground(main_color);
        btnLogin.setForeground(sub_color);
        btnLogin.setSize(210, 30);
        btnLogin.setLocation(60, 510);
        btnLogin.addActionListener(this);
        cMainContainer.add(btnLogin);

        setBounds(300, 90, 350, 600);
        setTitle("CINEMA MAGIC: LOGIN ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cMainContainer.setBackground(sub_color);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("SIGN IN")) {
            if(passAccessCode.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this,"Please enter your access pin.","Alert",JOptionPane.WARNING_MESSAGE);
            }
            else {
                String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
                int accessCode = Integer.parseInt(getTextFromAccess);
                System.out.println(accessCode);
                Employee employee = EmployeeFunctionality.getEmployeeByPassword(Integer.parseInt(getTextFromAccess));

                if (employee != null) {
                    System.out.println(employee.getRole());
                    if (employee.getRole().equals("2")) {
                        UIAdminDashboard adminUI = new UIAdminDashboard();
                    } else {
                        UIDashboard homeGUI = new UIDashboard();
                    }
                    GenericHelper.createEmployeeTracker(employee.getEmployeeNumber());
                    this.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(this, "User password is not valid");
                }
            }
        }
        else if(e.getActionCommand().equals("1")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "1");
        }
        else if(e.getActionCommand().equals("2")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "2");
        }
        else if(e.getActionCommand().equals("3")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "3");
        }
        else if(e.getActionCommand().equals("4")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "4");
        }
        else if(e.getActionCommand().equals("5")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "5");
        }
        else if(e.getActionCommand().equals("6")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "6");
        }
        else if(e.getActionCommand().equals("7")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "7");
        }
        else if(e.getActionCommand().equals("8")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "8");
        }
        else if(e.getActionCommand().equals("9")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "9");
        }
        else if(e.getActionCommand().equals("0")) {
            String getTextFromAccess = String.valueOf(passAccessCode.getPassword());
            passAccessCode.setText(getTextFromAccess + "0");
        }
        else if(e.getActionCommand().equals("X")) {
            passAccessCode.setText("");
        }

    }
}