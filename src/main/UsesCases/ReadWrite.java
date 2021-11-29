package main.UsesCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.Entity.Employee;
import main.Entity.Userable;
import main.Entity.*;
import main.UsesCases.*;
public interface ReadWrite<T> {

    public abstract void writeDataToFile(T OutputData) throws IOException;
    public abstract T readDataFromFileTo(T InputData) throws IOException, ClassNotFoundException;


}