package com.kirito666.JavaWebExample.servlet;

import com.kirito666.JavaWebExample.bean.Notifaction;
import com.kirito666.JavaWebExample.service.NotifactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Finger
 */
@WebServlet(name = "NotifactionServlet", urlPatterns = "/notifaction/all")
public class NotifactionServlet extends HttpServlet {
    private final NotifactionService notifactionService = new NotifactionService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        List<Notifaction> notifactions;
        int count;
        notifactions = notifactionService.queryAll();

        count = notifactionService.count();
        req.getSession().setAttribute("notifactions", notifactions);
        resp.getWriter().print(count);
    }
}
