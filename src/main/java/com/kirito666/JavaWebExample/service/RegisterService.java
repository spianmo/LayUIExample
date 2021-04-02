package com.kirito666.JavaWebExample.service;

import com.kirito666.JavaWebExample.bean.User;
import com.kirito666.JavaWebExample.dao.LoginDao;
import com.kirito666.JavaWebExample.dao.RegisterDao;

import java.sql.SQLException;

public class RegisterService {
    private RegisterDao registerDao=new RegisterDao();
    LoginDao loginDao = new LoginDao();

    public String  register(User register) throws SQLException {
        String result=String.valueOf(registerDao.register(register));

        if (result.equals("0")){
            return "注册失败";
        }else {
            return "注册成功";
        }
    }

}
