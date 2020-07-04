package service;

import dao.UserLogin;
import entity.Account;

public class UserLoginService {
    public Account login(String username, String password){
        UserLogin login = new UserLogin();
        Account user=login.login(username,password);
        return user;
    }
}
