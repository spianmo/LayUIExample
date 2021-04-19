package com.kirito666.JavaWebExample.dao;

import com.kirito666.JavaWebExample.bean.Admin;
import com.kirito666.JavaWebExample.bean.User;
import com.kirito666.JavaWebExample.utils.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User selectOne(String username) {
        User user = null;
        try (ResultSet resultSet =
                     JDBCUtil.getInstance().executeQueryRS("select " +
                                     "* " +
                                     "from " +
                                     "borrow_card where username=?",
                             new Object[]{username})) {

            while (resultSet.next()) {
                user = new User(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("reader"),
                        resultSet.getString("header"),
                        resultSet.getString("cellphone"),
                        resultSet.getString("email"),
                        resultSet.getString("describe"),
                        resultSet.getBoolean("sex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Admin selectOne(String username, String password) {
        Admin admin = null;
        try (ResultSet resultSet =
                     JDBCUtil.getInstance().executeQueryRS("select " +
                                     "* " +
                                     "from " +
                                     "admin where username=?",
                             new Object[]{username})) {

            while (resultSet.next()) {
                admin = new Admin(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    public int addUser(User register) {
        String sql = "INSERT IGNORE INTO `borrow_card` (username," +
                "`password`, reader) \n" +
                "SELECT ?,?,? WHERE NOT EXISTS (\n" +
                "SELECT 1 FROM borrow_card WHERE `username`=?);";
        return JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{register.getUsername(),
                        register.getPassword(),
                        register.getReader(),
                        register.getUsername()});
    }
}