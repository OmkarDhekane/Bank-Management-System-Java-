package ui;

import javax.swing.*;
import src.DashBoard;
import src.MAIN;
import src.RegistrationPage;

import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WithdrawalPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private String Holdername;
    private Long AccountNum;
    private int AmountGiven;
    protected JLabel welcomeL, AccountNumberL, HolderNameL, DateL, WithdrawL, messageL;
    protected JButton DepositeB, continueB, cancelB;
    protected JTextField AccountNumberTf, HolderNameTf, WithdrawAmountTf;
    public static JLabel clockL;

    private static void clock() {

        Thread clockT = new Thread() {
            public void run() {
                try {
                    for (;;) {
                        Calendar cal = new GregorianCalendar();
                        int day = cal.get(Calendar.DAY_OF_MONTH);
                        int mouth = cal.get(Calendar.MONTH);
                        int year = cal.get(Calendar.YEAR);

                        int second = cal.get(Calendar.SECOND);
                        int minute = cal.get(Calendar.MINUTE);
                        int hour = cal.get(Calendar.HOUR);
                        clockL.setText("Time: " + hour + ":" + minute + ":" + second + "  Date: " + day + "/"
                                + (mouth + 1) + "/" + year);
                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        clockT.start();
    }

    public void WithdrawalUI(int cus_id, String unm) {

        super.setResizable(false);
        super.setTitle("DashBoard/Withdrawal Page");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(Color.BLACK);
        super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow, 3));

        clockL = new JLabel(RegistrationPage.htmlFont(""));
        clockL.setForeground(new Color(220, 204, 255));
        clockL.setBounds(30, 60, 300, 30);
        super.add(clockL);
        clock();

        welcomeL = new JLabel(RegistrationPage.htmlFont("Withdrawal your money with ONE click !!"));
        welcomeL.setForeground(Color.ORANGE);
        welcomeL.setBounds(30, 20, 390, 40);
        super.add(welcomeL);

        messageL = new JLabel(RegistrationPage.htmlFont(""));
        messageL.setForeground(Color.CYAN);
        messageL.setBounds(110, 310, 200, 20);
        super.add(messageL);

        HolderNameL = new JLabel(RegistrationPage.htmlFont("A/c holders username:"));
        HolderNameTf = new JTextField(unm);
        HolderNameL.setBounds(50, 150, 180, 20);
        HolderNameTf.setBounds(270, 150, 150, 20);
        HolderNameL.setForeground(Color.WHITE);
        super.add(HolderNameTf);
        super.add(HolderNameL);

        AccountNumberL = new JLabel(RegistrationPage.htmlFont("Account Number: "));
        AccountNumberTf = new JTextField();
        AccountNumberL.setBounds(50, 200, 200, 35);
        AccountNumberTf.setBounds(270, 200, 150, 20);
        AccountNumberL.setForeground(Color.WHITE);
        super.add(AccountNumberTf);
        super.add(AccountNumberL);

        WithdrawL = new JLabel(RegistrationPage.htmlFont("Amount to be Withdrawn(Rs.):"));
        WithdrawAmountTf = new JTextField();
        WithdrawL.setBounds(50, 250, 250, 20);
        WithdrawL.setForeground(Color.WHITE);
        WithdrawAmountTf.setBounds(270, 250, 150, 20);
        super.add(WithdrawAmountTf);
        super.add(WithdrawL);

        continueB = new JButton("Withdraw");
        continueB.setBounds(340, 400, 90, 30);
        continueB.setForeground(Color.WHITE);
        continueB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        continueB.setBackground(new Color(0, 153, 51));
        super.add(continueB);
        continueB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Holdername = HolderNameTf.getText();
                AccountNum = Long.parseLong(AccountNumberTf.getText());
                AmountGiven = Integer.parseInt(WithdrawAmountTf.getText());

                if (Holdername.isBlank()) {
                    messageL.setText("Holder's name can't be blank");
                } else if (WithdrawAmountTf.getText() == null) {
                    messageL.setText("Enter account number!");
                } else {
                    String q1, q2, q3;
                    try {
                        Statement st = MAIN.connection.createStatement();
                        q1 = "select account_id from account natural join customer where customer.username='"
                                + Holdername + "' and account.ac_no=" + AccountNum;

                        int acc_id = -1;
                        ResultSet rs1 = st.executeQuery(q1);
                        while (rs1.next()) {
                            acc_id = rs1.getInt("account_id");
                            break;
                        }
                        System.out.println("Acc id " + acc_id);
                        System.out.println("Amount given: " + AmountGiven);

                        PreparedStatement pst;
                        q3 = "insert into transactions(customer_id,account_id,transaction_type,amount_of_transaction,timestamp,status) values(?,?,?,?,?,?)";
                        pst = MAIN.connection.prepareStatement(q3);
                        pst.setInt(1, cus_id);
                        pst.setInt(2, acc_id);
                        pst.setString(3, "withdrawal");
                        pst.setInt(4, AmountGiven);
                        pst.setDate(5, new Date(System.currentTimeMillis()));

                        q2 = "update account set balance=balance-" + AmountGiven + " where account_id=" + acc_id;
                        if (st.executeUpdate(q2) == 1) {
                            pst.setString(6, "success");
                        } else {
                            pst.setString(6, "failed");
                        }
                        pst.execute(); // execute transaction
                        st.close();
                        System.out.println("User updated successfully!");
                        messageL.setText("Database updated!");

                    } catch (SQLException e3) {
                        e3.printStackTrace();
                    }
                }
                setVisible(false);
                new DashBoard().DashBoardUI(cus_id, unm);

            }
        });

        cancelB = new JButton("Cancel");
        cancelB.setBounds(220, 400, 90, 30);
        cancelB.setForeground(Color.WHITE);
        cancelB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cancelB.setBackground(new Color(255, 77, 77));
        super.add(cancelB);
        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DashBoard().DashBoardUI(cus_id, unm);
            }
        });

        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        super.setLayout(null);
        super.setVisible(true);
    }
}
