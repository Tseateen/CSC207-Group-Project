package Uses_Cases;

import Entity.Employee;
import Entity.Userable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class Verifier {
    /**
     * 用于验证账号和权限
     */
    private Map<String, Userable> AccountList; //key是account number，item是user
    private AccountManager AM;

    /**
     * 从外部输入一个由AccountManager生成的map 从而生成AccountList
     * @param AM
     */
    public Verifier(AccountManager AM){
        this.AM = AM;
        AccountList =new HashMap<String, Userable>();
        for (Userable i : AM.getEmployeeList().keySet()) {
            AccountList.put(i.getAccount(), i);
        }
    }

    public void AddAccount(Userable user) {
        AccountList.put(user.getAccount(), user);
    }
    public void RmAccount(Userable user) {
        AccountList.remove(user.getAccount());
    }
    /**
     * 这里要不要把验证账号存在和验证账号密码的步骤分开？用于后面的创建账号之类的
     * @param account
     * @param password
     * @return
     */
    public boolean verifyAccountExist(String account, String password) {
        if (AccountList.containsKey(account)) {
            if (AccountList.get(account).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean verifyAuthority(String account, String password) {
        if (verifyAccountExist(account,password)) {
            if (AM.getEmployeeList().get(AccountList.get(account)).getLevel() < 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 这个东西我没明白要干啥，感觉是不是没什么存在的必要？
     * @param account
     * @param password
     * @return
     */
    public boolean verifyAdmin(String account, String password){
        return account.equals("1") && password.equals("0");

    }

}
