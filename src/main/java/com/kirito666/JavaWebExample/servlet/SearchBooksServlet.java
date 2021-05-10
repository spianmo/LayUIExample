package com.kirito666.JavaWebExample.servlet;

import com.alibaba.fastjson.JSON;
import com.kirito666.JavaWebExample.bean.Book;
import com.kirito666.JavaWebExample.bean.User;
import com.kirito666.JavaWebExample.service.BookService;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "SearchBooksServlet", urlPatterns = "/book/search")
public class SearchBooksServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(
                req.getInputStream(), StandardCharsets.UTF_8);
        HashMap<String, Object> parseObject = JSON.parseObject(paramJson, HashMap.class);
        String param = (String) parseObject.get("search");
        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<Book> books = new ArrayList<>();
        int count = 0;
        if (param == null) {
            books = bookService.searchAllBooks(String.valueOf(((User) req.getSession().getAttribute("user")).getId()), pageNum, pageSize);
        }

        count = bookService.countNum();
        req.getSession().setAttribute("books", books);
        resp.getWriter().print(count);
    }
}