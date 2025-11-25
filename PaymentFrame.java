package com.hostel.ui;

import com.hostel.db.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PaymentFrame extends JFrame implements ActionListener {
    JTextField tfAmount, tfMethod;
    JButton bPay;
    int userId;

    public PaymentFrame(int userId) {
        this.userId = userId;
        setTitle("Payment");
        setLayout(new GridLayout(3,1));
        JPanel p;

        p = new JPanel(); p.add(new JLabel("Amount:")); tfAmount = new JTextField(10); p.add(tfAmount); add(p);
        p = new JPanel(); p.add(new JLabel("Method:")); tfMethod = new JTextField(15); p.add(tfMethod); add(p);
        p = new JPanel(); bPay = new JButton("Pay"); bPay.addActionListener(this); p.add(bPay); add(p);

        setSize(400,200); setVisible(true); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement()) {
            double amt = Double.parseDouble(tfAmount.getText());
            String method = tfMethod.getText();
            String sql = "INSERT INTO payments(user_id,amount,method) VALUES("
                    + userId + "," + amt + ",'" + method + "')";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(this,"Payment recorded");
        } catch(Exception e){
            JOptionPane.showMessageDialog(this,"Payment error: " + e.getMessage());
        }
    }
}


//  cd "C:\Users\Yaten Arora\Documents\JAVA Project"; java -cp ".;lib\mysql-connector-j-8.0.31.jar" com.hostel.Main