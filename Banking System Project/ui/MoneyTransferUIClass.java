// package ui;

// import javax.swing.*;

// import src.CreateMessagePage;
// import src.DashBoard;
// import src.RegistrationPage;

// import java.awt.event.ActionListener;
// import java.awt.event.ActionEvent;
// import java.awt.Color;
// import java.awt.Font;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.Map;

// public class MoneyTransferUIClass extends JFrame implements ActionListener {
// private static final long serialVersionUID = 1L;
// private String bankName, self_AC, benif_AC, paymentAmount, benifName;
// private JLabel welcomeL, DateL, messageL, beneficiaryNameL,
// beneficiaryAccountNumberL, AmountL, SelfAccountNumberL,
// BankNameL;
// private JButton proceedB, cancelB;
// private Font f2;
// private JTextField beneficiaryNameTf, beneficiaryAccountNumberTf, AmountTf,
// SelfAccountNumberTf;
// private JComboBox<String> bankNames;

// private String unm;
// private Boolean flag = true;
// private Date date;
// private CreateMessagePage newPage;
// public static Map<String, String> newInfo = new HashMap<String, String>();

// public MoneyTransferUIClass(CreateMessagePage newPage) {
// this.newPage = newPage;
// }

// public MoneyTransferUIClass() {

// }

// public void MoneyTransferUI(String unm) {
// super.setResizable(false);
// super.setTitle("DashBoard/Money Transfer Page");
// super.setBounds(500, 150, 500, 500);
// super.getContentPane().setBackground(Color.BLACK);
// super.getRootPane().setBorder(BorderFactory.createLineBorder(Color.yellow,
// 3));
// this.unm = unm;
// f2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);

// date = new Date();
// DateL = new JLabel(RegistrationPage.htmlFont(date.toString()));
// DateL.setForeground(new Color(220, 204, 255));
// DateL.setBounds(20, 0, 300, 30);
// super.add(DateL);

// welcomeL = new JLabel(RegistrationPage.htmlFont("Transfer money on ONE
// Click!!"));
// welcomeL.setForeground(Color.decode("#00e600"));
// welcomeL.setBounds(130, 20, 260, 40);
// super.add(welcomeL);

// BankNameL = new JLabel(RegistrationPage.htmlFont("Choose Bank Name:"));
// BankNameL.setBounds(50, 100, 200, 20);
// BankNameL.setForeground(Color.WHITE);
// super.add(BankNameL);

// String ac[] = { "SBI", "BOI", "Canara Bank", "ICICI Bank ", "HDFC Bank",
// "Axis Bank", "Kotak Mahindra Bank",
// "Bank of Baroda" };
// bankNames = new JComboBox<String>(ac);
// bankNames.setBounds(270, 100, 150, 20);
// bankNames.setBackground(Color.BLACK);
// bankNames.setForeground(Color.WHITE);
// bankNames.setEditable(true);
// super.add(bankNames);

// messageL = new JLabel(RegistrationPage.htmlFont(""));
// messageL.setForeground(Color.CYAN);
// messageL.setBounds(90, 330, 300, 20);
// super.add(messageL);

// beneficiaryNameL = new JLabel(RegistrationPage.htmlFont("Beneficiary
// Name:"));
// beneficiaryNameTf = new JTextField();
// beneficiaryNameTf.setBorder(BorderFactory.createLineBorder(Color.decode("#66c2ff"),
// 3));
// beneficiaryNameTf.setFont(f2);
// beneficiaryNameL.setBounds(50, 150, 200, 20);
// beneficiaryNameL.setForeground(Color.WHITE);
// beneficiaryNameTf.setBounds(270, 150, 150, 20);
// super.add(beneficiaryNameTf);
// super.add(beneficiaryNameL);

// beneficiaryAccountNumberL = new JLabel(RegistrationPage.htmlFont("Pay To
// (beneficiary a/c no):"));
// beneficiaryAccountNumberTf = new JTextField();
// beneficiaryAccountNumberTf.setBorder(BorderFactory.createLineBorder(Color.decode("#66c2ff"),
// 3));
// beneficiaryAccountNumberTf.setFont(f2);
// beneficiaryAccountNumberL.setBounds(50, 200, 210, 20);
// beneficiaryAccountNumberL.setForeground(Color.WHITE);
// beneficiaryAccountNumberTf.setBounds(270, 200, 150, 20);
// super.add(beneficiaryAccountNumberTf);
// super.add(beneficiaryAccountNumberL);

// SelfAccountNumberL = new JLabel(RegistrationPage.htmlFont("My Account
// Number:"));
// SelfAccountNumberTf = new JTextField(DashBoard.info.get("accountNumber"));
// SelfAccountNumberTf.setBorder(BorderFactory.createLineBorder(Color.decode("#66c2ff"),
// 3));
// SelfAccountNumberTf.setFont(f2);
// SelfAccountNumberTf.setEditable(false);
// SelfAccountNumberL.setBounds(50, 250, 230, 20);
// SelfAccountNumberL.setForeground(Color.WHITE);
// SelfAccountNumberTf.setBounds(270, 250, 150, 20);
// super.add(SelfAccountNumberTf);
// super.add(SelfAccountNumberL);

// AmountL = new JLabel(RegistrationPage.htmlFont("Payment Amount (INR):"));
// AmountTf = new JTextField();
// AmountTf.setBorder(BorderFactory.createLineBorder(Color.decode("#66c2ff"),
// 3));
// AmountTf.setFont(f2);
// AmountL.setBounds(50, 300, 200, 20);
// AmountL.setForeground(Color.WHITE);
// AmountTf.setBounds(270, 300, 150, 20);
// super.add(AmountTf);
// super.add(AmountL);

// proceedB = new JButton(RegistrationPage.htmlFont("Proceed"));
// proceedB.setBounds(250, 375, 100, 40);//
// proceedB.setForeground(Color.WHITE);
// proceedB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
// proceedB.setBackground(new Color(0, 153, 51));
// super.add(proceedB);
// proceedB.addActionListener(this);

// cancelB = new JButton(RegistrationPage.htmlFont("Cancel"));
// cancelB.setBounds(125, 375, 100, 40);
// cancelB.setForeground(Color.WHITE);
// cancelB.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
// cancelB.setBackground(new Color(255, 77, 77));
// super.add(cancelB);
// cancelB.addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e) {
// setVisible(false);
// new DashBoard().DashBoardUI(unm);
// }
// });

// super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
// super.setLayout(null);
// super.setVisible(true);
// }

// @Override
// public void actionPerformed(ActionEvent e) {
// bankName = bankNames.getSelectedItem().toString();
// benifName = beneficiaryNameTf.getText();
// self_AC = SelfAccountNumberTf.getText();
// benif_AC = beneficiaryAccountNumberTf.getText();
// paymentAmount = AmountTf.getText();

// if (e.getSource() == proceedB) {

// if (benif_AC.isBlank()) {
// messageL.setText("Enter Beneficiary A/c number");
// flag = false;
// } else if (benifName.isBlank()) {
// messageL.setText("Enter Beneficiary name");
// flag = false;
// } else if (!benif_AC.equals(newInfo.get("accountNumber"))) {

// } else if (Integer.parseInt(DashBoard.info.get("balance")) <
// Integer.parseInt(paymentAmount)) {
// messageL.setText("Can't pay amount balance '<' 1000");
// flag = false;
// }

// else

// {
// flag = true;
// if (flag == true) {
// this.setVisible(false);
// // Creating the new page
// newPage = new CreateMessagePage("DashBoard/Money Transfer Page/Transfer
// Summary", 500, 150, 500,
// 500, Color.decode("#ffa64d"));

// // adding text to newly created page
// newPage.MyNavbar((RegistrationPage.htmlFont("Summary of Transaction")), 145,
// 30,
// Color.decode("#4d0028"));
// newPage.MyMessage("Beneficary Name: ", benifName, 110, 130, Color.BLACK);
// newPage.MyMessage("Beneficary A/c No.: ", benif_AC, 110, 160, Color.BLACK);
// newPage.MyMessage("Beneficary Bank Name: ", bankName, 110, 190, Color.BLACK);
// newPage.MyMessage("Payment Amount: ", "Rs. " + paymentAmount, 110, 220,
// Color.BLACK);
// newPage.MyMessage("My Account Number: ", self_AC, 110, 250, Color.BLACK);

// // anonymus class usage to implement buttons of created page using anonymous
// // action listenser
// newPage.myButton("Pay", 250, 375, 100, 40, Color.decode("#cc5200"),
// Color.WHITE)
// .addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e2) {

// DashBoard.modifyAndWriteAmount(unm,
// (Integer.parseInt(DashBoard.info.get("balance"))
// - Integer.parseInt(paymentAmount)),
// DashBoard.info);
// DashBoard.LogFileWriter(date.toString(), DashBoard.info.get("username"),
// "MoneyTransfer", "-" + paymentAmount, "to " + benif_AC, bankName,
// "Successful");

// DashBoard.modifyAndWriteAmount(newInfo.get("username"),
// (Integer.parseInt(newInfo.get("balance"))
// + Integer.parseInt(paymentAmount)),
// newInfo);
// DashBoard.LogFileWriter(date.toString(), newInfo.get("username"),
// "MoneyTransfer",
// "+" + paymentAmount, "from " + DashBoard.info.get("accountNumber"),
// "Omkar's bank", "Successful");

// newPage.setVisible(false);
// DashBoard n = new DashBoard();
// n.DashBoardUI(unm);
// DashBoard.indicatorL.setText("Last Activity: Money Transfer!");
// }
// });
// newPage.myButton("Edit", 125, 375, 100, 40, Color.decode("#803300"),
// Color.WHITE)
// .addActionListener(new ActionListener() {
// public void actionPerformed(ActionEvent e3) {
// newPage.setVisible(false);
// new MoneyTransferUIClass().MoneyTransferUI(unm);
// }
// });
// }
// }
// }
// System.gc();
// }
// }
