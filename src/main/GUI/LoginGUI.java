package main.GUI;

import main.InterfaceAdapter.FacadeSys;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {

    private JPanel loginPanel;
    private JLabel usernameLabel;
    private JTextField usernameInput;
    private JLabel passwordLabel;
    private JTextField passwordInput;
    private JButton loginButton;
    private final FacadeSys facadeSys;
    private final int FRAME_HEIGHT = 500;
    private final int FRAME_WIDTH = 500;

    public  LoginGUI(FacadeSys facadeSys){
        this.facadeSys = facadeSys;
        //=== Initial Login Panel ===
        this.loginPanel = new JPanel();
        //===========================

        //=== Draw Username Label & Input ===
        this.usernameLabel = new JLabel("Username: ");
        this.loginPanel.add(this.usernameLabel);
        this.usernameInput = new JTextField(20);
        this.loginPanel.add(this.usernameInput);
        //===================================

        //=== Draw Password Label & Input ===
        this.passwordLabel = new JLabel("Password: ");
        this.loginPanel.add(this.passwordLabel);
        this.passwordInput = new JTextField(20);
        this.loginPanel.add(this.passwordInput);
        //===================================

        // === Draw Button ===
        this.loginButton = new JButton(" Login ");
        this.loginPanel.add(this.loginButton);
        //===================================

        this.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameInput.getText();
                String password = passwordInput.getText();
                boolean result = facadeSys.systemStart(username, password);
                if (!result){
                    System.out.println("here");
                    JOptionPane.showMessageDialog(null, "Invalid Username and Password !");
                }else{
                    LoginGUI.this.setVisible(false);
                    System.out.println("Successfully Login ! ");
                }
            }
        });

        this.setLayout(null);
        this.loginPanel.setSize(250, 250);
        this.loginPanel.setLocation((FRAME_WIDTH - 250) / 2, (FRAME_HEIGHT - 250) / 2);
        this.add(this.loginPanel);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("Welcome To HR System");
    }
}
