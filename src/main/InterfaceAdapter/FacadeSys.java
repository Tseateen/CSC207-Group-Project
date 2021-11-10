package main.InterfaceAdapter;

import main.Entity.Employee;
import main.Entity.Group;
import main.Entity.Userable;
import main.Entity.Work;
import main.UsesCases.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;





public class FacadeSys {

    // === Instance Variables ===

    // === DataFile ===
    private final DataGateway fileGateway;
    private final LoginList loginList;
    private final EmployeeList employeeList;
    private final Verifier verifier;
    private final WorkList workList;
    private final GroupList groupList;
    private final JournalList journalList;
    private final String username;
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
    public FacadeSys(String username) {
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
        this.username = username;

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
    public void personalInfo(){
        if (this.employeeType.equals("PartTimeEmployee")){
            ArrayList<String> info = accountFacade.partTimeEmployeeInfo();
            String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                    + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                    + "\n Department: " + info.get(6);
            System.out.println(presentInfo);
        }else{
            ArrayList<String> info = accountFacade.FullTimeEmployeeInfo();
            int[] infoInt = accountFacade.getFullTimeEmployeeInfoInt();
            String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
                    + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
                    + "\n Department: " + info.get(6) + "\n Position: " + info.get(7) + "\n State: " +info.get(8)
                    +"\n Total Vacation with Salary: " + infoInt[0] + "\n  Vacation Used: " + infoInt[1];
            System.out.println(presentInfo);
        }
    }

//    public void partTimePersonalInfo(){
//        ArrayList<String> info = accountFacade.partTimeEmployeeInfo();
//        String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
//                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
//                + "\n Department: " + info.get(6);
//        System.out.println(presentInfo);
//    }

//    public void fullTimePersonalInfo(){
//        ArrayList<String> info = accountFacade.FullTimeEmployeeInfo();
//        String presentInfo = "Name: " + info.get(0) + "\n ID: " + info.get(1) + "\n Username: " + info.get(2)
//                + "\n Password: " + info.get(3) + "\n Phone Number: " + info.get(4) + "\n Address: " +info.get(5)
//                + "\n Department: " + info.get(6) + "\n Position: " + info.get(7) + "\n State: " +info.get(8);
//        System.out.println(presentInfo);
//    }

    public void checkSalary(){
        if (this.employeeType.equals("PartTimeEmployee")){
            String presentWage = String.valueOf(accountFacade.partTimeSalary());
            System.out.println(presentWage);
        }else{
            String presentWage = String.valueOf(accountFacade.fullTimeSalary());
            System.out.println(presentWage);
        }
    }
//    public void checkPartTimeSalary() {
//        String presentWage = String.valueOf(accountFacade.partTimeSalary());
//        System.out.println(presentWage);
//    }

//    public void checkFullTimeSalary(){
//        String presentWage = String.valueOf(accountFacade.fullTimeSalary());
//        System.out.println(presentWage);
//    }

    public void setPersonalInfo(String option, String response){
        if (this.employeeType.equals("PartTimeEmployee")){
            accountFacade.setPartTimeBasicInfo(option, response);
        }else{
            accountFacade.setFullTimeBasicInfo(option, response);
        }
    }

//    public void setPartTimePersonalInfo() {
//        Scanner keyIn = new Scanner(System.in);
//        System.out.println("i) Change your name, please type 1; " + "\n" +
//                "ii) Change your password, please type 2; " + "\n" +
//                "iii) Change your phone number, please type 3; " + "\n" +
//                "iv) Change your address, please type 4" + "\n" +
//                "v) Change you attendence, please type 5");
//        String option = keyIn.nextLine();
//        System.out.println("Please type the value you want to change:");
//        String response = keyIn.nextLine();
//        accountFacade.setPartTimeBasicInfo(option, response);
//        System.out.println("Set personal info success!");
//    }

//    public void setFullTimePersonalInfo(){
//        Scanner keyIn = new Scanner(System.in);
//        System.out.println("i) Change your name, please type 1; " + "\n" +
//                "ii) Change your password, please type 2; " + "\n" +
//                "iii) Change your phone number, please type 3; " + "\n" +
//                "iv) Change your address, please type 4" + "\n" +
//                "v) Change you attendence, please type 5");
//        String option = keyIn.nextLine();
//        System.out.println("Please type the value you want to change:");
//        String response = keyIn.nextLine();
//        accountFacade.setFullTimeBasicInfo(option, response);
//        System.out.println("Set personal info success!");
//    }

    public void checkVacation() {
        int[] infoInt = accountFacade.getFullTimeEmployeeInfoInt();
        String vacationInfo = "Total Vacation with Salary: " + infoInt[0] + "\n  Vacation Used: " + infoInt[1];
        System.out.println(vacationInfo);
    }



    // ==================== Work UI Method ==============

    public void checkWorkInfo() {
        for (Userable u: this.loginList){
            if (u.getUsername().equals(this.username)){
                System.out.println(workFacade.SelfWork(u));
                break;
            }
        }
        System.out.println("You only can check your work here.\n" +
                "If you want check your work detail, please type its id\n ");
        VisitStep("check");
        /** new
         * Todo: If this user is any work's leader, show it out differently.
         */
    }


    public void createWork() {
        System.out.println("Please entry work's level which you want to create.");
        VisitStep("create");
    }


    private void Creator(String level) {
        if (!levelVerifier(level)) {
            System.out.println("Work level verifier fail, please try again.");
            return;
        }
        Scanner keyIn = new Scanner(System.in);
        System.out.println();// Todo: 写一句话说输入文件所需的格式 ("name id department level")，用空格隔开。

        String action = keyIn.nextLine();
        System.out.println(workFacade.workCreate(action));
    }


    private boolean levelVerifier(String level) {
        try {
            if (level.length() != 1) {
                return false;
            }
            int a = Integer.parseInt(level);
            return a > accountFacade.user_Level();
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String findWork() {
        Userable self = null;
        for (Userable u: this.loginList){
            if (u.getUsername().equals(this.username)){
                self = u;
                break;
            }
        }
        Employee employee = null;
        ArrayList<Work> ListOfWork = new ArrayList<>();
        for (Employee e : this.employeeList) {
            if (self.getID().equals(e.getID())) ;
            employee = e;
            break;
        }

        for (Work work : this.workList) {
            boolean leader = false;

            if (work.getSign().equals("0") && employee.getLevel() < work.getLevel() &&
                    employee.getDepartment().equals(work.getDepartment())) {
                ListOfWork.add(work);
            }
        }
        System.out.println("Following are the work that you can do:");
        for (Work w : ListOfWork) {
            return workFacade.WorkDetail(w.getID());
        }
        return null;
    }


    public ArrayList<String> findAllWorkers(){
        Userable self = null;
        for (Userable u: this.loginList){
            if (u.getUsername().equals(this.username)){
                System.out.println(workFacade.SelfWork(u));
                break;
            }
        }
        return workFacade.AllWorkers(self);
    }


    public void createLeader(String workid, String leaderid) {
        /**
         * Todo: Write Exception.
         * 1. Show all non-leader works which is in same department and lower level
         * 2. Let them choose the work based on work id
         * 3. Ask him did he need to know about employees information who can be involved be him
         *   This part may be implemented by calling AllWorker below.
         * 4. Let them choose group leader
         */

        Userable self = null;
        for (Userable u: this.loginList){
            if (u.getUsername().equals(this.username)){
                self = u;
                break;
            }
        }

        if (!((findAllWorkers().contains(leaderid)) || leaderid.equals(self.getID()))) {
            System.out.println("You can only choose between yourself and one of the employees shown above");
            return;
        }
            for (Userable u : this.loginList) {
                if (u.getID().equals(leaderid)) {
                    this.groupList.addGroup(u, workid);
                    break;
                }
            }
        }

     public String findLeadWork() {
         List<String> groupids = new ArrayList<String>();

         Userable self = null;
         for (Userable u: this.loginList){
             if (u.getUsername().equals(this.username)){
                 self = u;
                 break;
             }
         }

         for (Group group: this.groupList){
             if (group.getLeader().equals(self)){
                 groupids.add(group.getWorkid());
             }
         }

         List<String> workid = new ArrayList<String>();

         for (String gid: groupids){
             for (Work w: this.workList){
                 if (w.getID().equals(gid)){
                     workid.add(w.getID());
                     break;
                 }
             }
         }
         System.out.println("Following are the work IDs of the work which are lead by you: choose the work ID where you " +
                 "want to choose members");
         for (String w : workid) {
             return w;
         }
         return null;
     }


    public void distributeWork(String employeeid, String wid) {
        /** Todo: Write Exception.
         * 1. Show all works that this user lead
         * 2. Let them choose the work based on work id (they only can choose work that they lead)
         * 3. Ask him did he need to know about employees information who can be invoked be him
         *    This part may be implemented by calling AllWorker below.
         * 4. Let them choose group members
         */

        List<Userable> members = new ArrayList<>();
        String[] parts;
        parts = employeeid.split(" ");
        for (String eid: parts) {
            for (Userable u: this.loginList){
                if (!((findAllWorkers().contains(u.getID())))) {
                    System.out.println("You can only choose the employees shown above");
                    return;
                }
                if (u.getID().equals(eid)){
                    members.add(u);
                    break;
                }
            }
        }
        for (Group g: this.groupList){
            if (g.getWorkid().equals(wid)){
                this.groupManager.addMembers(members, g);
                break;
            }
        }
    }


    public Group findWorkKpi(String woid) {
        /**
         * * Todo: Write Exception.
         * (Update: Group leader to assign KPI)
         */

        Group group = null;
        Work work = null;
        for (Work w: this.workList){
            if (w.getID().equals(woid)){
                work = w;
                break;
            }
        }
        for (Group g: this.groupList){
            if (g.getWorkid().equals(woid)){
                group = g;
                break;
            }
        }

        Userable self = null;
        for (Userable u: this.loginList){
            if (u.getUsername().equals(this.username)){
                self = u;
                break;
            }
        }

        if (!(group.getLeader().equals(self))){
            System.out.println("You are not the leader of this work");
            return; //write exception
        }

        System.out.println("You can now begin assign KPI to each member");
        return group;

    }

    public void giveKpi(String woid, String eid, String kpi){
        Work work = null;
        for (Work w: this.workList){
            if (w.getID().equals(woid)){
                work = w;
                break;
            }
        }
        for (Employee e: this.employeeList){
            if (e.getID().equals(eid)){
                e.setKpi(work, Integer.valueOf(kpi));
                break;
                }
            }
        }

    public void WorkUpdate() {
        /** Todo: This one is designed for employee to report their work progress
         *  we need to record their message to Journal. And if the work finished,
         *  only leader!!! can to change work's statues to 'Finished'. There may be some more steps needed.
         *  also, if the user is this work's leader, they can choose to extend due date
         */
    }


    private void VisitStep(String method) {
        Scanner keyIn = new Scanner(System.in);
        System.out.println("Type E to exit.");
        boolean noExit = true;
        while (noExit) {
            String action = keyIn.nextLine();
            if ("E".equals(action)) {
                noExit = false;
            } else {
                if (method.equals("check")) {System.out.println(workFacade.WorkDetail(action));}
                if (method.equals("create")) {Creator(action);}
            }
        }
    }

    // Here are some method used to show other user information, may used in hr workers or work distribute


    public void UserCreator(String username, String password, String name, String phone, String address,
                            String department, int wage, String position, int userlevel, String status) {t
        // This part may use Creator

        Userable self = null;
        for (Userable u: this.loginList){
            if (u.getUsername().equals(this.username)){
                self = u;
                break;
            }
        }

        accountFacade.CreateNewAccount(username, password, name, phone, address, department, wage, position,
                userlevel, status);
        if (!(levelVerifier(Integer.toString(userlevel)))){// Need to be in UI?
            System.out.println("You cannot create a user that has a higher level than you.");
            return;
        }
        System.out.println("The user is successfully created");
        System.out.println("The user info is shown below");
        System.out.println("username: "+ username +" password: "+ password +" name: "+ name +" phone: "+ phone +" address: "
                + address +" department: " + department +" wage: "+ wage +" position: "+ position +" level: "+ userlevel +" status: "
                + status);
    }


    public void UserDelete(String uid) {
        /**
         * We may need to use memento for delete User
         */

        Employee employee = null;
        for (Employee e: this.employeeList){
            if (e.getID().equals(uid)){
                employee = e;
                break;
            }
            if (!(levelVerifier(Integer.toString(e.getLevel())))){// Need to be in UI?
                System.out.println("You cannot delete this employee, please try again.");
                return;
            }
            employeeList.deleteEmployee(uid);
            loginList.deleteUser(uid);
            System.out.println("The user has successfully deleted.");
        }
    }


    public void UserWorkInfoChange(String userid, String option, String info) {
        //Todo: write Exception

        Employee employee = null;
        Userable user = null;

        for (Employee e : this.employeeList) {
            if (e.getID().equals(userid)) {
                employee = e;
                break;
            }

            for (Userable u : this.loginList) {
                if (u.getID().equals(userid)) {
                    user = u;
                    break;
                }

                if (!(levelVerifier(Integer.toString(e.getLevel())))) {// Need to be in UI?
                    System.out.println("You cannot change info to this employee, please try again.");
                    return;
                }

                if (Objects.equals(accountFacade.employeeTypeByID(user), "FullTimeEmployee")) {
                    accountFacade.setFullTimeAdvancedInfo(option, info);
                    System.out.println("The information is successfully updated");
                }

                if (Objects.equals(accountFacade.employeeTypeByID(user), "PartTimeEmployee")) {
                    if ((!Objects.equals(option, "1")) && (!Objects.equals(option, "2")) && (!Objects.equals(option, "3"))) {
                        System.out.println("Part Time Employee does not have these infos");
                    }
                    return;
                }
                accountFacade.setPartTimeAdvancedInfo(option, info);
                System.out.println("The information is successfully updated");
            }
        }
    }

    public void SingleSalaryCheck() {
        /** Todo: Modify this method.
         *
         * shows salary, work days, vacation used, reward, and kpi for given user. Hr worker need to comfirm all those info
         */

        if(Objects.equals(accountFacade.employeeTypeByID(user), "FullTimeEmployee")){
            System.out.println("Please choose the information you want to check about this employee: (Type the corresponding number)");
            System.out.println("1: Wage, 10: Total Vacation with Salary, 11: Vacation Used");
            Scanner keyIn = new Scanner(System.in);
            String option = keyIn.nextLine();
            if(Objects.equals(option, "1")){
                System.out.println("The wage of this employee is "+ accountFacade.fullTimeSalary());
            }else{
                if(Objects.equals(option, "10")) {
                    System.out.println("The total vacation with salary of this employee is "+ accountFacade.getFullTimeEmployeeInfoInt(option));
                }else if(Objects.equals(option, "11")){
                    System.out.println("The vacation used by this employee is "+ accountFacade.getFullTimeEmployeeInfoInt(option));
                }else {
                    System.out.println("No such option, please choose again");
                }
            }
        }if(Objects.equals(accountFacade.employeeTypeByID(accountFacade.findUserHelper()), "PartTimeEmployee")) {
            System.out.println("Please choose the information you want to check about this employee: (Type the corresponding number)");
            System.out.println("1: Wage, 9: Total Vacation with Salary, 10: Vacation Used");
            Scanner keyIn = new Scanner(System.in);
            String option = keyIn.nextLine();
            if (Objects.equals(option, "1")) {
                System.out.println("The wage of this employee is " + accountFacade.partTimeSalary());
            } else {
                if (Objects.equals(option, "9")) {
                    System.out.println("The total vacation with salary of this employee is " + accountFacade.getPartTimeEmployeeInfoInt(option));
                } else if (Objects.equals(option, "10")) {
                    System.out.println("The vacation used by this employee is " + accountFacade.getPartTimeEmployeeInfoInt(option));
                } else {
                    System.out.println("No such option, please choose again");
                }
            }
        }
    }


    public void SalaryCheck(String level) {
        // Todo: reward and kpi is not implemented yet
        /**
         * Todo: Show all(maybe some) other department same level workers' and lower level hr workers'
         * salary, work days, vacation used, reward, and kpi. Hr worker need to comfirm all those info
         */
    }
}
