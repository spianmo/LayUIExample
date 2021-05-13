package com.kirito666.JavaWebExample.dao;

import com.kirito666.JavaWebExample.bean.Notifaction;
import com.kirito666.JavaWebExample.utils.JDBCUtil;
import org.intellij.lang.annotations.Language;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Finger
 */
public class NotifactionDao {
    public List<Notifaction> selectAll() {
        @Language("MySQL") String sql = "select * from announcement order by publish_date desc";

        List<Notifaction> notifactions = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{})) {

            while (rs.next()) {
                Notifaction notifaction = new Notifaction(rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("detail"),
                        rs.getDate("publish_date"));
                notifactions.add(notifaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifactions;
    }

    public int count() {
        @Language("MySQL")String sql = "select count(*) as countNum from announcement";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{})) {

            while (rs.next()) {
                int count = rs.getInt("countNum");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int insertNotifaction(String title, String detail) {
        @Language("MySQL")String sql = "insert into announcement(title, detail,publish_date) values(?,?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        title, detail,
                        new Date(System.currentTimeMillis())
                });
        return result;
    }
}
