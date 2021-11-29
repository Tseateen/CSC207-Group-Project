package main.UsesCases;

import java.io.IOException;

public interface IReadWrite {

    void writeDataToFile() throws IOException;
    void readDataFromFile() throws IOException, ClassNotFoundException;


}