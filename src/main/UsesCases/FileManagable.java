package main.UsesCases;

import java.io.IOException;

public interface FileManagable {


    public abstract LoginList readFile(String filepath) throws IOException;
}
