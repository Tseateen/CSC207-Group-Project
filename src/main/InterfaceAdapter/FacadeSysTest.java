package main.InterfaceAdapter;

import main.Entity.Employee;
import main.Entity.Group;
import main.Entity.Userable;
import main.Entity.Work;
import main.UsesCases.*;

import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;





public class FacadeSysTest {

    // === Instance Variables ===

    // === DataFile ===
    private final DataGateway fileGateway;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final Verifier verifier;
    private final WorkList workList;
    private final GroupList groupList;
    private final JournalList journalList;
    // === AccountFacade ===
    private final AccountFacade accountFacade;
    // === WorkFacade ===
    private final WorkFacade workFacade;
    private GroupManager groupManager;
    private final String employeeType;
    private WorkManager workManager;

    /**
     * Construct the admin system. This system can let admin manager employee by Uses Cases.
     */
    public FacadeSysTest(String username) {
        this.loginList = new LoginList();
        this.employeeList = new EmployeeList();
        this.verifier = new Verifier(this.loginList);
        this.fileGateway = new DataGateway(this.loginList, this.employeeList);
        this.accountFacade = new AccountFacade(this.loginList, this.employeeList,username);
        this.workList = new WorkList();
        this.groupManager = new GroupManager();
        this.groupList = new GroupList();
        this.journalList = new JournalList();
        this.workManager = new WorkManager();

        this.workFacade = new WorkFacade(this.workList, this.employeeList, this.groupList, this.workManager, this.groupManager);
        this.employeeType = this.accountFacade.employeeType();
    }

    // === System methods ===

    public boolean systemStart(String username, String password) {
        this.fileGateway.ReadInputFileToLoginList();
        this.fileGateway.ReadInputFileToEmployeeList();
        return this.verifier.verifyForLogin(username, password);
    }

    public void systemEnd() {
        this.fileGateway.WriteOutputFile();
    }

    // === Personal UI Method ===

    public boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {
                return false;
            }
            int a = Integer.parseInt(level);
            return a > accountFacade.user_Level(this.employeeType);
        } catch (NumberFormatException e) {
            return false;
        }
    }


    // Here are some method used to show other user information, may used in hr workers or work distribute


    public boolean UserCreator(String info) {
        // Todo: this part is similar with workCreator, so new user's level shouldn't higher than creator
        // Todo: or we may need to default new user's level as 9.
        // Todo: to verify the type of user input and make sure the type match the parameters of CreateNewAccount
        // This part may use Creator
        /**
         *  This part we assume we already checked level in UI
         */
        String[] user_info = info.split("_");
        return this.accountFacade.CreateNewAccount(user_info);
    }


    public boolean UserDelete(String id) {
        /**
         * We may need to use memento for delete User
         *  This part we assume we already checked level in UI
         * Todo: Implement userExist in AccountFacade which used to check user exist or not by their id
         * Todo: Implement getLevel in AccountFacade which used to get user level by their id
         */
        if (this.accountFacade.userExist(id) && levelVerifier(String.valueOf(this.accountFacade.getLevel(id)))) {
            employeeList.deleteEmployee(id);
            loginList.deleteUser(id);
            return true;
        }
        return false;
    }


    public boolean UserWorkInfoChange(String id, String info) {
        /** Todo: Used to change a worker's level, department, position, salary, vacation; can't ...
         *  Here we assume we already find the user by SingleUserFinder and checked level in UI
         *  Let they use '_' to split info, index 0 is which part they want, index 1 is info need to change to.
         */
        String[] Info = info.split("_");
        String[] suit = new String[]{"level", "department", "position", "salary", "vacation"};
        for (String i : suit) {
            if (i.equals(Infop[0])) {return accountFacade.AccountManage(id, Info[0], Info[1]);}
        }
        return false;

    }


    public String[] SingleUserInfoCheck(String id) {
        /**
         *
         * shows all info of a user except password.
         */
        if (!accountFacade.userExist(id)) {return null;}
        if (accountFacade.getDepartment(id).equals("HR") && !levelVerifier(String.valueOf(accountFacade.getLevel(id)))) {
            return null;
        }
        if (!accountFacade.getDepartment(id).equals("HR") && !levelVerifier(String.valueOf(accountFacade.getLevel(id) - 1))) {
            return null;
        }
        return accountFacade.getAllInfo(id);
    }


    public void SalaryCheck(String level) {
        // Todo: reward and kpi is not implemented yet
        /**
         * Todo: Show all(maybe some) other department same level workers' and lower level hr workers'
         * salary, work days, vacation used, reward, and kpi. Hr worker need to comfirm all those info
         */
    }
}
