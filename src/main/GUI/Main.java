package main.GUI;

import main.Framework.HomePage;
import main.InterfaceAdapter.FacadeSys;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        FacadeSys facadeSys = new FacadeSys();
        LoginGUI LoginFrame = new LoginGUI(facadeSys);
        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginFrame.setVisible(true);
    }
}
