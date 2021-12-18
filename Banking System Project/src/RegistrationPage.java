package src;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class RegistrationPage extends JFrame {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String gender;
    private String username;
    private String newpassword;
    private String confirmpassword;
    private Integer age;
    private Long contact;
    private String account;
    private int initalAmount;
    private long AN;

    private JButton b1, cancelB;
    private JLabel welcomeL, nameL, surnameL, ageL, genderL, contactL, usernameL, newPasswordL,
            acL, confirmPasswordL, message, initalAmountL;
    private JTextField nameTf, surnameTf, contactTf, usernameTf, newPasswordTf, initalAmountTf;
    private JPasswordField confirmPasswordTf;
    private JRadioButton m, f, oth;
    private JSpinner agesp;
    private SpinnerModel val;
    private JComboBox<String> typeOfAccount;

    public static String htmlFont(String s) {
        return ("<html><span style='font-size:12px'>" + s + "</span></html>");
    }

    public void RegistrationPageUI() {
        super.setResizable(false);
        super.setTitle("Registration");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(new Color(0, 51, 102));
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.white, 3));

        welcomeL = new JLabel(htmlFont("Welcome to the Registration Form"));
        welcomeL.setForeground(new Color(220, 204, 255));
        welcomeL.setBounds(100, 10, 300, 30);
        super.add(welcomeL);

        nameL = new JLabel(htmlFont("Name: "));
        nameTf = new JTextField();
        nameL.setBounds(50, 80, 120, 20);
        nameL.setForeground(Color.WHITE);
        nameTf.setBounds(160, 80, 120, 20);
        super.add(nameTf);

        surnameL = new JLabel(htmlFont("Surname: "));
        surnameTf = new JTextField();
        surnameL.setBounds(50, 110, 120, 20);
        surnameL.setForeground(Color.WHITE);
        surnameTf.setBounds(160, 110, 120, 20);
        super.add(surnameTf);

        ageL = new JLabel(htmlFont("Age: "));
        ageL.setBounds(320, 110, 120, 20);
        ageL.setForeground(Color.WHITE);
        val = new SpinnerNumberModel(18, 10, 80, 1);
        agesp = new JSpinner(val);
        agesp.setBounds(355, 110, 60, 25);
        super.add(agesp);
        super.add(ageL);

        genderL = new JLabel(htmlFont("Gender: "));
        genderL.setBounds(50, 150, 120, 30);
        genderL.setForeground(Color.WHITE);
        m = new JRadioButton(htmlFont("Male"));
        f = new JRadioButton(htmlFont("Female"));
        oth = new JRadioButton(htmlFont("Not to say"));
        ButtonGroup bg = new ButtonGroup();

        m.setBounds(160, 150, 70, 30);
        m.setBackground(new Color(0, 51, 102));
        m.setForeground(Color.WHITE);
        f.setBounds(230, 150, 80, 30);
        f.setBackground(new Color(0, 51, 102));
        f.setForeground(Color.WHITE);
        oth.setBounds(310, 150, 120, 30);
        oth.setBackground(new Color(0, 51, 102));
        oth.setForeground(Color.WHITE);
        bg.add(m);
        bg.add(f);
        bg.add(oth);
        super.add(m);
        super.add(f);
        super.add(oth);
        super.add(genderL);
        contactL = new JLabel(htmlFont("Contact: "));
        contactTf = new JTextField();
        contactL.setBounds(50, 200, 120, 20);
        contactL.setForeground(Color.WHITE);
        contactTf.setBounds(160, 200, 120, 20);
        super.add(contactTf);

        usernameL = new JLabel(htmlFont("Username: "));
        usernameTf = new JTextField();
        usernameL.setBounds(50, 230, 120, 20);
        usernameL.setForeground(Color.WHITE);
        usernameTf.setBounds(160, 230, 120, 20);
        super.add(usernameTf);

        newPasswordL = new JLabel(htmlFont("New Password: "));
        newPasswordTf = new JTextField();
        newPasswordL.setBounds(50, 270, 120, 35);
        newPasswordL.setForeground(Color.WHITE);
        newPasswordTf.setBounds(160, 282, 120, 20);
        super.add(newPasswordTf);

        confirmPasswordL = new JLabel(htmlFont("Confirm Password: "));
        confirmPasswordTf = new JPasswordField();
        confirmPasswordL.setBounds(50, 320, 120, 35);
        confirmPasswordL.setForeground(Color.WHITE);
        confirmPasswordTf.setBounds(160, 332, 120, 20);
        super.add(confirmPasswordTf);

        initalAmountL = new JLabel(htmlFont("Initial Amount: "));
        initalAmountTf = new JTextField();
        initalAmountL.setBounds(320, 290, 120, 35);
        initalAmountL.setForeground(Color.WHITE);
        initalAmountTf.setBounds(320, 332, 120, 20);
        super.add(initalAmountTf);

        acL = new JLabel(htmlFont("Account Type:"));
        acL.setBounds(320, 195, 120, 20);
        acL.setForeground(Color.WHITE);
        super.add(acL);

        String ac[] = { "Current Account", "Salary Account", "Savings Account", "Fix deposit", "Other" };
        typeOfAccount = new JComboBox<String>(ac);
        typeOfAccount.setBounds(320, 235, 120, 20);
        super.add(typeOfAccount);

        b1 = new JButton("Continue");
        b1.setBounds(340, 400, 90, 30);
        b1.setForeground(Color.WHITE);
        b1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        b1.setBackground(new Color(0, 153, 51));
        super.add(b1);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cus_id = 0;
                name = nameTf.getText();
                surname = surnameTf.getText();
                username = usernameTf.getText();
                newpassword = newPasswordTf.getText();
                confirmpassword = new String(confirmPasswordTf.getPassword());
                System.out.println(confirmpassword);
                contact = Long.valueOf(contactTf.getText());
                age = Integer.valueOf(agesp.getValue().toString());

                if (m.isSelected()) {
                    gender = "Male";
                } else if (f.isSelected()) {
                    gender = "Female";
                } else if (oth.isSelected()) {
                    gender = "Prefer not";
                } else {
                    gender = "Prefer not";
                }
                account = typeOfAccount.getSelectedItem().toString();
                initalAmount = Integer.valueOf(initalAmountTf.getText());

                if (newpassword.isBlank()) {
                    message.setText("Password can't be blank");

                } else if (newpassword.length() < 6) {
                    message.setText("Password is too Weak!");

                } else if ((initalAmount) < 1000) {
                    message.setText("Initial amount must be >= 1000");
                } else if (newpassword.contains("#") || newpassword.contains("%") || newpassword.contains("@")
                        || newpassword.contains("&") || newpassword.contains("$") || newpassword.contains("^")
                        || newpassword.contains("!") || newpassword.contains("?")) {
                    message.setText("@#!$%^&?_ not Allowed!");

                } else if (!newpassword.equals(confirmpassword)) {
                    message.setText("Password not matching!");
                } else {
                    message.setText("Password matched!");
                    try {
                        String q = "insert into customer(fname,lname,age,gender,contact,username,password)"
                                + " values(?,?,?,?,?,?,?);";
                        String q2 = "insert into account(customer_id,actype,balance,ac_no)"
                                + " values(?,?,?,?);";
                        PreparedStatement pst = MAIN.connection.prepareStatement(q);
                        PreparedStatement pst2 = MAIN.connection.prepareStatement(q2);
                        pst.setString(1, name);
                        pst.setString(2, surname);
                        pst.setInt(3, age);
                        pst.setString(4, gender);
                        pst.setLong(5, contact);
                        pst.setString(6, username);
                        pst.setString(7, newpassword);
                        pst.execute();

                        Random random = new Random();
                        AN = 10000000000L + (long) (random.nextDouble() * 9999999999L); // assigning account number

                        ResultSet rs = MAIN.connection.createStatement()
                                .executeQuery("select customer_id from customer where username='" + username + "'");

                        while (rs.next()) {
                            cus_id = rs.getInt("customer_id");
                        }
                        System.out.println(cus_id);

                        pst2.setInt(1, cus_id);
                        pst2.setString(2, account);
                        pst2.setInt(3, initalAmount);
                        pst2.setLong(4, AN);
                        pst2.execute();

                        System.out.println("New user added!");
                    } catch (SQLException sqle) {
                        sqle.printStackTrace();
                        try {
                            MAIN.connection.close();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                String q1, q3;
                try {
                    Statement st = MAIN.connection.createStatement();
                    q1 = "select customer_id,account_id from account where ac_no = " + AN;
                    int acc_id = -1;
                    cus_id = 0;
                    ResultSet rs1 = st.executeQuery(q1);
                    while (rs1.next()) {
                        cus_id = rs1.getInt("customer_id");
                        acc_id = rs1.getInt("account_id");
                        break;
                    }
                    PreparedStatement pst;
                    q3 = "insert into transactions(customer_id,account_id,transaction_type,amount_of_transaction,timestamp,status) values(?,?,?,?,?,?)";
                    pst = MAIN.connection.prepareStatement(q3);
                    pst.setInt(1, cus_id);
                    pst.setInt(2, acc_id);
                    pst.setString(3, "deposit");
                    pst.setInt(4, initalAmount);
                    pst.setDate(5, new Date(System.currentTimeMillis()));
                    pst.setString(6, "success");
                    pst.execute(); // execute transaction
                } catch (Exception e5) {
                    e5.printStackTrace();

                }
                setVisible(false);
                new LoginPage();
            }
        });

        cancelB = new JButton("Cancel");
        cancelB.setBounds(220, 400, 90, 30);
        cancelB.setForeground(Color.WHITE);
        cancelB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cancelB.setBackground(new Color(255, 77, 77));
        super.add(cancelB);
        // cancelB.addActionListener(ob);
        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LoginPage();
            }
        });

        message = new JLabel(htmlFont(""));
        message.setBounds(35, 400, 180, 35);
        message.setForeground(Color.CYAN);
        super.add(message);

        super.add(nameL);
        super.add(surnameL);
        super.add(contactL);
        super.add(usernameL);
        super.add(newPasswordL);
        super.add(confirmPasswordL);
        super.add(initalAmountL);

        super.setLayout(null);
        super.setVisible(true);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
