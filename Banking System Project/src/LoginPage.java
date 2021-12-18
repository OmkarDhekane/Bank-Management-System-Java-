package src;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    public JButton loginB, newUserB;
    public JTextField tf1;
    public JLabel userL, passwordL, imgL, messageL;// protected
    private ImageIcon im;
    public JPasswordField pf1;

    public LoginPage() {
        JFrame loginPage = new JFrame("User Login");
        loginPage.setResizable(false);
        loginPage.setBounds(500, 150, 700, 600);
        // image
        im = new ImageIcon("./img/loginIcon.png");
        imgL = new JLabel();
        imgL.setBounds(220, 0, 350, 350);
        imgL.setIcon(im);

        // username label
        userL = new JLabel(RegistrationPage.htmlFont("Username"));
        userL.setBounds(200, 340, 100, 30);

        // password label
        passwordL = new JLabel(RegistrationPage.htmlFont("Password"));
        passwordL.setBounds(200, 380, 150, 30);

        messageL = new JLabel(RegistrationPage.htmlFont(""));
        messageL.setBounds(290, 290, 155, 50);
        messageL.setForeground(Color.RED);

        // username textbox
        tf1 = new JTextField();
        tf1.setBounds(300, 350, 180, 20);
        tf1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // password textbox
        pf1 = new JPasswordField();
        pf1.setBounds(300, 385, 180, 20);
        pf1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Login button
        loginB = new JButton("LOGIN");
        loginB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        loginB.setForeground(Color.WHITE);
        loginB.setBackground(Color.decode("#0998e6"));
        loginB.setBounds(200, 430, 280, 40);
        loginB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String username = tf1.getText();
                        String password = new String(pf1.getPassword());

                        if (username.isEmpty() || password.isEmpty()) {
                            messageL.setText(RegistrationPage.htmlFont("Invalid credentials"));
                        } else {
                            int cus_id = 0;
                            try {
                                Statement st = MAIN.connection.createStatement();
                                String q = "SELECT EXISTS(SELECT 1 FROM customer WHERE username = '" + username
                                        + "' and password='" + password + "') as isPresent";
                                ResultSet rs, rs2;
                                rs = st.executeQuery(q);
                                while (rs.next()) {
                                    if (rs.getBoolean("isPresent")) {
                                        rs2 = st.executeQuery(
                                                "select customer_id from customer where username='" + username + "'");
                                        while (rs2.next()) {
                                            cus_id = rs2.getInt("customer_id");
                                        }
                                        System.out.println("Current user id: " + cus_id);
                                        loginPage.setVisible(false);
                                        new DashBoard().DashBoardUI(cus_id, username);
                                        break;
                                    } else {
                                        messageL.setText(RegistrationPage.htmlFont("User not present"));
                                    }
                                }
                                st.close();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                                try {
                                    MAIN.connection.close();
                                } catch (SQLException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }

                    }
                });

        // new user button
        newUserB = new JButton("CREATE ACCOUNT");
        newUserB.setBackground(Color.decode("#ff6347"));
        newUserB.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        newUserB.setForeground(Color.WHITE);
        newUserB.setBounds(200, 480, 280, 40);
        newUserB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginPage.setVisible(false);
                        new RegistrationPage().RegistrationPageUI();
                    }
                });

        loginPage.add(imgL);
        loginPage.add(messageL);
        loginPage.add(userL);
        loginPage.add(passwordL);
        loginPage.add(tf1);
        loginPage.add(pf1);
        loginPage.add(loginB);
        loginPage.add(newUserB);

        loginPage.getRootPane().setDefaultButton(loginB);
        loginPage.setLayout(null);
        loginPage.setVisible(true);
        loginPage.setLocationRelativeTo(null);
        loginPage.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }
}
