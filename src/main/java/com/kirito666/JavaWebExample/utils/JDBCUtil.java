package com.kirito666.JavaWebExample.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对jdbc的完整封装
 */
public class JDBCUtil {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/library?serverTimezone=UTC&characterEncoding=UTF-8&useUnicode=true";
    private static String username = "Kirito66";
    private static String password = "123456";
    private static JDBCUtil instance = null;

    private CallableStatement callableStatement = null;
    private Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    public static JDBCUtil getInstance() {
        if (instance == null) {
            return new JDBCUtil();
        }
        return instance;
    }

    /**
     * 建立数据库连接
     *
     * @return 数据库连接
     */
    public Connection getConnection() {
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                System.out.println("加载驱动错误");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(url, username,
                    password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * insert update delete SQL语句的执行的统一方法
     *
     * @param sql    SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 受影响的行数
     */
    public int executeUpdate(String sql, Object[] params) {
        int affectedLine = 0;

        try {
            conn = this.getConnection();
            pst = conn.prepareStatement(sql);

            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            affectedLine = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }
        return affectedLine;
    }

    /**
     * SQL 查询将查询结果直接放入ResultSet中
     *
     * @param sql    SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    public ResultSet executeQueryRS(String sql, Object[] params) {
        try {
            conn = this.getConnection();
            pst = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            rst = pst.executeQuery();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rst;
    }

    /**
     * SQL 查询将查询结果：一行一列
     *
     * @param sql    SQL语句
     * @param params 参数数组，若没有参数则为null
     * @return 结果集
     */
    public Object executeQuerySingle(String sql, Object[] params) {
        Object object = null;
        try {
            conn = this.getConnection();
            pst = conn.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    pst.setObject(i + 1, params[i]);
                }
            }
            rst = pst.executeQuery();

            if (rst.next()) {
                object = rst.getObject(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 获取结果集，并将结果放在List中
     *
     * @param sql SQL语句
     *            params  参数，没有则为null
     * @return List
     * 结果集
     */
    public List<Object> excuteQuery(String sql, Object[] params) {
        ResultSet rs = executeQueryRS(sql, params);
        ResultSetMetaData rsmd = null;
        int columnCount = 0;
        try {
            rsmd = rs.getMetaData();
            columnCount = rsmd.getColumnCount();
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        }

        List<Object> list = new ArrayList<Object>();

        try {
            while (rs.next()) {
                Map<String, Object> map = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                }
                list.add(map);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return list;
    }

    /**
     * 存储过程带有一个输出参数的方法
     *
     * @param sql         存储过程语句
     * @param params      参数数组
     * @param outParamPos 输出参数位置
     * @param SqlType     输出参数类型
     * @return 输出参数的值
     */
    public Object excuteQuery(String sql, Object[] params, int outParamPos, int SqlType) {
        Object object = null;
        conn = this.getConnection();
        try {
            callableStatement = conn.prepareCall(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    callableStatement.setObject(i + 1, params[i]);
                }
            }

            callableStatement.registerOutParameter(outParamPos, SqlType);
            callableStatement.execute();
            object = callableStatement.getObject(outParamPos);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeAll();
        }

        return object;
    }

    /**
     * 关闭所有资源
     */
    private void closeAll() {
        if (rst != null) {
            try {
                rst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
