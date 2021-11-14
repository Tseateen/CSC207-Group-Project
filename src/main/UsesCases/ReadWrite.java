package main.UsesCases;

import java.io.IOException;

public interface ReadWrite {

    public abstract void writeUserToFile(LoginList userList) throws IOException;
    public abstract void writeUserEmployeeToFile(EmployeeList employeeList) throws IOException;
    public abstract void writeWorkToFile(WorkList workList) throws IOException;
    public abstract void writeGroupToFile(GroupList groupList) throws IOException;
    public abstract LoginList readUserFromFileTo(LoginList loginList) throws IOException, ClassNotFoundException;
    public abstract EmployeeList readEmployeeFromFileTo(EmployeeList employeeList) throws IOException, ClassNotFoundException;
    public abstract WorkList readWorkFromFileTo(WorkList workList) throws IOException, ClassNotFoundException;
    public abstract GroupList readGroupFromFileTo(GroupList groupList) throws IOException, ClassNotFoundException;

}