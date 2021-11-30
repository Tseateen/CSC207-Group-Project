package main.UsesCases;

import java.io.IOException;
import java.io.Serializable;

public interface IReadWrite extends Serializable {

    void writeDataToFile() throws IOException;
    void readDataFromFile() throws IOException, ClassNotFoundException;


}