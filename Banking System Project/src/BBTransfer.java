package src;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// import src.CreateMessagePage;
// import src.DashBoard;
// import src.RegistrationPage;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

class JTextFieldLimit extends PlainDocument {
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    JTextFieldLimit(int limit, boolean upper) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;
        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}

public class BBTransfer extends JFrame {

    private static final long serialVersionUID = 1L;
    // private String bankName, self_AC, benif_AC, paymentAmount, benifName,
    // username;
    private JLabel welcomeL, DateL, messageL, beneficiaryNameL,
            beneficiaryAccountNumberL, AmountL, SelfAccountNumberL,
            BankNameL;
    private JButton proceedB, cancelB;
    private Font f2;
    private JTextField beneficiaryNameTf, beneficiaryAccountNumberTf, AmountTf,
            SelfAccountNumberTf;
    private JComboBox<String> bankNames;

    // private Boolean flag = true;
    private Date date;
    // private CreateMessagePage newPage;

    public BBTransfer() {
    }

    void BBTransferUI(int cus_id, String username) {
        super.setResizable(false);
        super.setTitle("DashBoard/Money Transfer Page");
        super.setBounds(500, 150, 500, 500);
        super.getContentPane().setBackground(Color.decode("#f1f1f1"));
        // super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow,
        // 3));
        f2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);

        date = new Date();
        DateL = new JLabel(RegistrationPage.htmlFont(date.toString()));
        DateL.setForeground(new Color(0, 0, 0));
        DateL.setBounds(20, 0, 300, 30);
        DateL.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 5));
        super.add(DateL);

        welcomeL = new JLabel(RegistrationPage.htmlFont("Transfer money on ONE Click!!"));
        welcomeL.setForeground(Color.decode("#1C4E80"));
        welcomeL.setBounds(130, 35, 260, 40);
        super.add(welcomeL);

        BankNameL = new JLabel(RegistrationPage.htmlFont("Choose Bank Name:"));
        BankNameL.setBounds(50, 100, 200, 20);
        BankNameL.setForeground(Color.BLACK);
        super.add(BankNameL);

        String ac[] = { "SBI", "BOI", "Canara Bank", "ICICI Bank ", "HDFC Bank",
                "Axis Bank", "Kotak Mahindra Bank",
                "Bank of Baroda" };
        bankNames = new JComboBox<String>(ac);
        bankNames.setBounds(270, 100, 150, 20);
        bankNames.setBackground(Color.BLACK);
        bankNames.setForeground(Color.BLACK);
        bankNames.setBackground(Color.WHITE);
        bankNames.setEditable(true);
        super.add(bankNames);

        messageL = new JLabel(RegistrationPage.htmlFont(""));
        messageL.setForeground(Color.CYAN);
        messageL.setBounds(90, 330, 300, 20);
        super.add(messageL);

        beneficiaryNameL = new JLabel(RegistrationPage.htmlFont("Beneficiary Name:"));
        beneficiaryNameTf = new JTextField();
        beneficiaryNameTf.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                1));
        beneficiaryNameTf.setFont(f2);
        beneficiaryNameL.setBounds(50, 150, 200, 20);
        beneficiaryNameL.setForeground(Color.BLACK);
        beneficiaryNameTf.setBounds(270, 150, 150, 20);
        super.add(beneficiaryNameTf);
        super.add(beneficiaryNameL);

        beneficiaryAccountNumberL = new JLabel(RegistrationPage.htmlFont("Pay to (beneficiary a/c no):"));
        beneficiaryAccountNumberTf = new JTextField(12);
        beneficiaryAccountNumberTf.setDocument(new JTextFieldLimit(11));
        beneficiaryAccountNumberTf.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                1));
        beneficiaryAccountNumberTf.setFont(f2);
        beneficiaryAccountNumberL.setBounds(50, 200, 210, 20);
        beneficiaryAccountNumberL.setForeground(Color.BLACK);
        beneficiaryAccountNumberTf.setBounds(270, 200, 150, 20);
        super.add(beneficiaryAccountNumberTf);
        super.add(beneficiaryAccountNumberL);

        SelfAccountNumberL = new JLabel(RegistrationPage.htmlFont("My Account Number:"));
        // SelfAccountNumberTf = new JTextField(DashBoard.info.get("accountNumber"));
        SelfAccountNumberTf = new JTextField("19330739376");
        SelfAccountNumberTf.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                1));
        SelfAccountNumberTf.setFont(f2);
        SelfAccountNumberTf.setEditable(false);
        SelfAccountNumberL.setBounds(50, 250, 230, 20);
        SelfAccountNumberL.setForeground(Color.BLACK);
        SelfAccountNumberTf.setBounds(270, 250, 150, 20);
        super.add(SelfAccountNumberTf);
        super.add(SelfAccountNumberL);

        AmountL = new JLabel(RegistrationPage.htmlFont("Payment Amount (INR):"));
        AmountTf = new JTextField(7);
        AmountTf.setDocument(new JTextFieldLimit(6));
        AmountTf.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                1));
        AmountTf.setFont(f2);
        AmountL.setBounds(50, 300, 200, 20);
        AmountL.setForeground(Color.BLACK);
        AmountTf.setBounds(270, 300, 150, 20);
        super.add(AmountTf);
        super.add(AmountL);

        JLabel ifscL = new JLabel(RegistrationPage.htmlFont("IFSC Code:"));
        JTextField ifscTf = new JTextField(12);
        ifscTf.setDocument(new JTextFieldLimit(11));
        ifscTf.setBorder(BorderFactory.createLineBorder(Color.BLACK,
                1));
        ifscTf.setFont(f2);
        ifscL.setBounds(50, 350, 200, 20);
        ifscL.setForeground(Color.BLACK);
        ifscTf.setBounds(270, 350, 150, 20);
        super.add(ifscTf);
        super.add(ifscL);

        proceedB = new JButton(RegistrationPage.htmlFont("Proceed"));
        proceedB.setBounds(270, 400, 150, 40);//
        proceedB.setForeground(Color.WHITE);
        proceedB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        proceedB.setBackground(new Color(0, 153, 51));
        super.add(proceedB);
        // proceedB.addActionListener(this);

        cancelB = new JButton(RegistrationPage.htmlFont("Cancel"));
        cancelB.setBounds(50, 400, 150, 40);
        cancelB.setForeground(Color.WHITE);
        cancelB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        cancelB.setBackground(new Color(255, 77, 77));
        super.add(cancelB);
        cancelB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DashBoard().DashBoardUI(cus_id, username);
            }
        });

        super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        super.setLayout(null);
        super.setVisible(true);
    }
}
