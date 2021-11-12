package main.Framework;

import main.InterfaceAdapter.FacadeSys;

import java.util.List;
import java.util.Scanner;

public class CheckSalaryUI {
    private final FacadeSys facadeSys;

    public CheckSalaryUI(FacadeSys facadeSys) {
        this.facadeSys = facadeSys;
    }

    public void run() throws Exception {
        Scanner keyIn = new Scanner(System.in);
        boolean noExist = true;
        System.out.println("You can check the salary-related information about all " +
                "the employees whose level is lower than you");
        System.out.println("Please enter your ID");
        String id = keyIn.nextLine();
        while (noExist) {
            System.out.println("i) Check lower level employees' salary, please type 1; " + "\n" +
                    "ii) Check lower level employees' attendance, please type 2;" + "\n" +
                    "iii) Check lower level employees' total vacation with salary, please type 3; " + "\n" +
                    "iv) Check lower level employees' vacation used, please type 4; " + "\n" +
                    "v) Otherwise, please type E to exit;");
            String check_option = keyIn.nextLine();
            if (!check_option.equalsIgnoreCase("E")) {
                List<String> result = this.facadeSys.checkLowerEmployeeSalary(id, check_option);
                for (String info : result) {
                    System.out.print(info + " ");
                }
                System.out.println();
                System.out.println("If you want to check other type of the information from the lower level employees," +
                        " please type C. Otherwise, please type E to exist");
                String action = keyIn.nextLine();
                if (action.equalsIgnoreCase("E")) {
                    noExist = false;
                }

            }
        }
    }
}
