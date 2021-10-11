package Uses_Cases;

import Entity.*;

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
            return AccountList.get(account).getPassword().equals(password);
        }
        return false;
    }

    /**
     * 这一小段是用来验证账号的存在的，如果感觉不需要就删掉就好
     * @param account
     * @return
     */
    public boolean verifyExist(String account) {
        return AccountList.containsKey(account);
    }

    /**
     * 这里是不是只用账号去判定会好一些，在登陆之后用账号判定
     * @param account
     * @param password
     * @return
     */
    public boolean verifyAuthority(String account, String password) {
        if (verifyAccountExist(account,password)) {
            return AM.getEmployeeList().get(AccountList.get(account)).getLevel() < 1;
        }
        return false;
    }

}
