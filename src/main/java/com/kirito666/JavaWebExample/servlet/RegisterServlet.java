package com.kirito666.JavaWebExample.servlet;

import com.kirito666.JavaWebExample.bean.User;
import com.kirito666.JavaWebExample.dao.LoginDao;
import com.kirito666.JavaWebExample.service.RegisterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");


        User register = null;
        RegisterService registerService = new RegisterService();
        String result = "注册失败";
        String userName, password, reader;
        userName = req.getParameter("username");
        password = req.getParameter("password");
        reader = req.getParameter("reader");
        register = new User(userName, password, reader);

        LoginDao loginDao = new LoginDao();
        User byName = loginDao.selectOne(register.getUsername());
        if (byName != null) {
            result = "用户已经存在";
        } else {
            try {
                result = registerService.register(register);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //注册成功：——>跳转至登录页面进行登录
        //注册失败：——>注册页面提示：注册失败
        if (result.equals("注册成功")) {
            // 注册
            resp.sendRedirect("/index.jsp?message=" + result);
        } else {

            req.getRequestDispatcher("/register.jsp?message=" + result).forward(req, resp);

        }
    }
}
