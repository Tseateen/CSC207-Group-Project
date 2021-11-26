package main.UsesCases;

import java.util.Objects;

public interface IVerifier {
    public boolean userExists(String userID);
    public boolean ValidToCreateThisLevel(String level);
    public boolean validToDeleteThisUser(String userID);
    public abstract  boolean verifyForLogin(String account, String password);
}
