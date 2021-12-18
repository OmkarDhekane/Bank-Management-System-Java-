package src;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ui.DepositPage;
import ui.WithdrawalPage;

public class DashBoard extends JFrame {

    public String username;
    CreateMessagePage cb;

    public DashBoard(String username) {
        this.username = username;
    }

    private String account;
    private int balance;
    private long AN;
    private static final long serialVersionUID = 1L;
    public JLabel welcomeL, checkBalanceL, transactionDetailsL, depositeL, withdrawL, bTobL;
    public JButton welcomeB, checkBalanceB, transactionDetailsB, depositeB, withdrawB, bTobB, loginOutB;
    public static JLabel indicatorL;
    private JFrame dashboard;

    public static String htmlFont(String s) {
        return ("<html><span style='font-size:15px'>" + s + "</span></html>");
    }

    public DashBoard() {
        dashboard = new JFrame(username + "'s dashboard");
        dashboard.setVisible(true);
        dashboard.setResizable(true);
        dashboard.setBounds(300, 200, 1000, 400);
        dashboard.getContentPane().setBackground(Color.decode("#DADADA"));
        dashboard.getRootPane().setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));

        dashboard.setLayout(null);
        dashboard.setLocationRelativeTo(null);
        dashboard.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void DashBoardUI(int cus_id, String username) {
        welcomeL = new JLabel(htmlFont("Welcome " + username + " to your Dashboard!"));
        welcomeL.setForeground(Color.BLACK);
        welcomeL.setBounds(20, 20, 360, 40);

        indicatorL = new JLabel(RegistrationPage.htmlFont(""));
        indicatorL.setForeground(Color.RED);
        indicatorL.setBounds(20, 55, 390, 40);

        transactionDetailsB = new JButton(htmlFont("Transaction History"));
        transactionDetailsB.setBounds(20, 90, 460, 100);
        transactionDetailsB.setForeground(Color.WHITE);
        transactionDetailsB.setBorder(BorderFactory.createLineBorder(Color.WHITE,
                2));
        transactionDetailsB.setBackground(new Color(113, 132, 245));
        transactionDetailsB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PrintWriter writer;
                Statement st;
                try {

                    st = MAIN.connection.createStatement();
                    String q = "select * from transactions where customer_id = " + cus_id;
                    System.out.println(q);
                    ResultSet rs = st.executeQuery(q);
                    writer = new PrintWriter(new OutputStreamWriter(
                            new BufferedOutputStream(new FileOutputStream("transactionLog.txt")), "UTF-8"));
                    writer.append("TranactionID\tAccountID\tType\tAmount\tPeriod\tStatus\n\n");
                    while (rs.next()) {
                        writer.append(Integer.toString(rs.getInt("transaction_id"))).append("\t")
                                .append(Integer.toString(rs.getInt("account_id"))).append("\t")
                                .append(rs.getString("transaction_type")).append("\t")
                                .append(Integer.toString(rs.getInt("amount_of_transaction"))).append("\t")
                                .append(rs.getDate("timestamp").toString()).append("\t")
                                .append(rs.getString("status")).println();
                    }
                    writer.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                JFrame window = new JFrame("Transaction File");
                String all = "";
                try {
                    all = readFile("transactionLog.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JTextArea textArea = new JTextArea(10, 20);
                JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                textArea.setText(all);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                window.add(scroll);
                window.setSize(490, 500);
                window.setVisible(true);
                window.setLocationRelativeTo(null);
                window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            }
        });

        depositeB = new JButton(htmlFont("Deposit"));
        depositeB.setBounds(500, 90, 460, 100);
        depositeB.setForeground(Color.WHITE);
        depositeB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        depositeB.setBackground(new Color(113, 132, 245));
        depositeB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dashboard.setVisible(false);
                new DepositPage().DepositeUI(cus_id, username);
            }
        });

        withdrawB = new JButton(htmlFont("Withdrawal"));
        withdrawB.setBounds(20, 210, 460, 100);
        withdrawB.setForeground(Color.WHITE);
        withdrawB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        withdrawB.setBackground(new Color(113, 132, 245));
        withdrawB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cb != null) {
                    cb.setVisible(false);
                }
                dashboard.setVisible(false);
                new WithdrawalPage().WithdrawalUI(cus_id, username);
            }
        });

        checkBalanceB = new JButton(htmlFont("Check Balance"));
        checkBalanceB.setBounds(500, 210, 460, 100);
        checkBalanceB.setForeground(Color.WHITE);
        checkBalanceB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        checkBalanceB.setBackground(new Color(113, 132, 245));
        checkBalanceB.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Statement st;
                        try {
                            st = MAIN.connection.createStatement();
                            String q = "select actype,balance,ac_no from account where customer_id='" + cus_id + "'";
                            ResultSet rs = st.executeQuery(q);
                            while (rs.next()) {
                                AN = rs.getLong("ac_no");
                                account = rs.getString("actype");
                                balance = rs.getInt("balance");
                                break;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        cb = new CreateMessagePage("DashBoard/Balance Information", 1000, 150, 340, 450,
                                new Color(0, 85, 255));
                        cb.MyMessage("Account number: ", String.valueOf(AN), 35, 35, Color.WHITE);
                        cb.MyMessage("Account Type: ", account, 35, 70, Color.WHITE);
                        cb.MyMessage("Account Balance: ", String.valueOf(balance), 35, 105, Color.WHITE);
                    }
                });

        loginOutB = new JButton(htmlFont("Logout"));
        loginOutB.setBounds(840, 20, 120, 40);
        loginOutB.setForeground(Color.WHITE);
        loginOutB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginOutB.setBackground(new Color(255, 51, 51));
        loginOutB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MAIN.connection.close();
                    System.out.println("User: " + username + " logged out!");
                    System.exit(1);
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        });

        dashboard.add(indicatorL);
        dashboard.add(transactionDetailsB);
        dashboard.add(depositeB);
        dashboard.add(withdrawB);
        dashboard.add(checkBalanceB);
        dashboard.add(loginOutB);
        System.gc();
    }

    String readFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();
            }
            return sb.toString();
        } finally {
            br.close();
        }
    }
}