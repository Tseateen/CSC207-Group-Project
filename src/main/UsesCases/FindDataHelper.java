package main.UsesCases;

import main.Entity.Employee;
import main.Entity.PartTimeEmployee;
import main.Entity.User;
import main.Entity.Userable;
import main.UsesCases.*;
public interface FindDataHelper {
    public abstract Userable findUserHelper();
    public abstract Employee findEmployeeHelper();
    public abstract String findEmployeeTypeHelper(String userID);
}
