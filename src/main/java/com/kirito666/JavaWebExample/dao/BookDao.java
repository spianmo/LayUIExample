package com.kirito666.JavaWebExample.dao;

import com.kirito666.JavaWebExample.bean.Book;
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
public class BookDao {
    public List<Book> selectAll(int pageNum, int pageSize) {
        @Language("MySQL") String sql = "select books.*, book_sort.name as sort " +
                "from books, book_sort where " +
                "books.sort_id=book_sort.id limit ?,?";

        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id") + "",
                        rs.getString(
                                "name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("description"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public int count() {
        String sql = "select count(*) as countNum from books";
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

    public boolean isExist(String username, String bookId) {
        @Language("MySQL") String sql1 = "select EXISTS( SELECT 1 from borrow_books " +
                "where book_id=? and card_id=?) as store";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql1,
                             new Object[]{
                                     bookId, username
                             });) {

            while (rs.next()) {
                return rs.getBoolean("store");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public int insertBook(String username, String bookId) {
        String sql = "insert into borrow_books(book_id, card_id, " +
                "borrow_date) values(?,?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        bookId, username,
                        new Date(System.currentTimeMillis())
                });
        return result;
    }

    public int borrowBook(int uid, String bookId){
        String sql = "insert into borrow_books(book_id, card_id, " +
                "borrow_date) values(?,?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        bookId, uid,
                        new Date(System.currentTimeMillis())
                });
        return result;
    }
}
