package com.example.tradingCards.view;

import com.example.tradingCards.controller.Controller;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LoginMenu extends JPanel {

    private  JFrame loginFrame;
    private JButton loginButton;
    private JTextField userName;
    private JPasswordField passwordText;
    private JLabel userNameLabel;
    private JLabel passwordLabel;

    private Controller controller;
    public LoginMenu(Controller controller) {


        this.controller = controller;

        //construct components
        loginButton = new JButton ("Login");
        userName = new JTextField (5);
        passwordText = new JPasswordField (5);
        userNameLabel = new JLabel ("User Name:");
        passwordLabel = new JLabel ("  Password:");

        //adjust size and set layout
        setPreferredSize (new Dimension (752, 430));
        setLayout (null);

        //add components
        add (loginButton);
        add (userName);
        add (passwordText);
        add (userNameLabel);
        add (passwordLabel);

        //set component bounds (only needed by Absolute Positioning)
        loginButton.setBounds (285, 205, 105, 30);
        userName.setBounds (250, 95, 180, 25);
        passwordText.setBounds (250, 145, 180, 25);
        userNameLabel.setBounds (180, 95, 70, 25);
        passwordLabel.setBounds (180, 145, 70, 25);
    }

    public JFrame getLoginFrame(){
        return loginFrame;
    }

}
