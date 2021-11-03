package com.vit.billing.pages;

import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import com.vit.billing.utils.Database;

import net.miginfocom.swing.MigLayout;

public class Login {

  Database database;

  JFrame loginFrame;
  JPanel loginPanel;

  public void createLoginForm(JPanel panel, JFrame frame) {

    JLabel errorLabel = new JLabel("");

    JLabel usernameLabel = new JLabel("Username");
    JLabel passwordLabel = new JLabel("Password");
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);

    panel.add(usernameLabel, "align label");
    panel.add(usernameField, "wrap");
    panel.add(passwordLabel, "align label");
    panel.add(passwordField, "wrap");
    panel.add(errorLabel, "wrap, gapbottom 20");

    JButton okBttn = new JButton("Login");
    okBttn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // if (username.equals("admin") || password.equals("password")) {
        // loginFrame.dispose();
        // new Home();
        // } else {
        // errorLabel.setText("Please enter username and password");
        // }

        if (username.equals("") || password.equals("")) {
          errorLabel.setText("Please enter username and password");
        } else {
          try {
            if (database.authenticate(username, password)) {
              loginFrame.dispose();
              System.out.println("Login Successful");
              new Home();
            } else {
              errorLabel.setText("Invalid username or password");
            }
          } catch (Exception e1) {
            e1.printStackTrace();
          }
        }
      }
    });
    JButton cancelBttn = new JButton("Exit");
    cancelBttn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent e) {
        frame.dispose();
        System.exit(0);
      }
    });
    // tag identifies the type of button
    panel.add(okBttn, "tag ok, span, split 3, sizegroup bttn");
    // sizegroups set all members to the size of the biggest member
    panel.add(cancelBttn, "tag cancel, sizegroup bttn");

  }

  public Login(Database db) {

    database = db;

    loginFrame = new JFrame("Login | VIT Billing System");
    loginFrame.setSize(500, 300);

    loginPanel = new JPanel(new MigLayout());

    JLabel loginTitle = new JLabel("Login | VIT Billing System");
    loginTitle.setFont(new FontUIResource("Noto Sans", FontUIResource.BOLD, 20));
    loginTitle.setBorder(new EmptyBorder(10, 10, 10, 10));

    loginPanel.add(loginTitle, "wrap");
    createLoginForm(loginPanel, loginFrame);

    loginFrame.add(loginPanel);
    loginFrame.pack();
    loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    loginFrame.setLocationRelativeTo(null);
    loginFrame.setVisible(true);
  }
}
