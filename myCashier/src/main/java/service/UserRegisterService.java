package service;

import dao.UserRegister;

public class UserRegisterService {
    public int regiter(String username,String password){
        UserRegister user = new UserRegister();
        int ret = user.regiter(username,password);
        return ret;
    }
}
