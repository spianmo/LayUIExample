package com.kirito666.JavaWebExample.dao;

import com.kirito666.JavaWebExample.bean.Message;
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
public class MessageDao {
    public List<Message> selectByKeyword(int pageNum, int pageSize,String keyword) {
        @Language("MySQL") String sql = "select * from message where detail = ? limit ?,?";

        List<Message> messages = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{(pageNum - 1) * pageSize,
                                     pageSize, keyword})) {

            while (rs.next()) {
                Message message = new Message(rs.getInt("id"),
                        rs.getInt("card_id"),
                        rs.getString("detail"),
                        rs.getDate("public_date"));
                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public List<Message> selectAll(int pageNum, int pageSize) {
        @Language("MySQL") String sql = "select * from message limit ?,?";

        List<Message> messages = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                Message message = new Message(rs.getInt("id"),
                        rs.getInt("card_id"),
                        rs.getString("detail"),
                        rs.getDate("public_date"));
                messages.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public int count() {
        @Language("MySQL")String sql = "select count(*) as countNum from message";
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

    public int insertMessage(String cardId, String detail) {
        @Language("MySQL")String sql = "insert into message(card_id, detail,public_date) values(?,?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        cardId, detail,
                        new Date(System.currentTimeMillis())
                });
        return result;
    }
}
