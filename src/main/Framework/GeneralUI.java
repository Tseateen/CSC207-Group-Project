package main.Framework;

public class GeneralUI {

    public static void runGeneral(){
        System.out.println("Welcome to the HR System!");
        boolean loginResult = LoginUI.runLogin();
        while(true)
        if (loginResult){
            QuestionUI.runQuestion();
            break;
        }else{
            System.out.println("Account username doesn't exist or password does not match. Please type again!");
        }
    }
}
