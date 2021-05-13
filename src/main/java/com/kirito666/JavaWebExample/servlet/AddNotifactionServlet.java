package com.kirito666.JavaWebExample.servlet;

import com.kirito666.JavaWebExample.service.NotifactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Finger
 */
@WebServlet(name = "AddMessageServlet", urlPatterns = "/notifaction/add")
public class AddNotifactionServlet extends HttpServlet {

    private final NotifactionService notifactionService = new NotifactionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String notifation = req.getParameter("notifation");
        String result = notifactionService.addNotifaction(title, notifation);
        resp.getWriter().print(result);
    }
}
