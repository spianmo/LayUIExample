package com.kirito666.JavaWebExample.servlet;


import com.kirito666.JavaWebExample.bean.User;
import com.kirito666.JavaWebExample.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

/**
 * @author Finger
 */
@WebServlet(name = "PersonalInfoServlet", urlPatterns = "/personal" +
        "/upload")
@MultipartConfig()
public class PersonalInfoServlet extends HttpServlet {

    private UserService userService = new UserService();
    private final static String HEADER_FILE_DIR = "D:\\JetBrainProjects\\JavaWebExample\\header\\";

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(
                req.getParameter("username"),
                req.getParameter("nickname"),
                req.getParameter("sex"),
                req.getParameter("cellphone"),
                req.getParameter("email"),
                req.getParameter("remarks"));

        Collection<Part> parts = req.getParts();
        try {
            if (parts.size() > 1) {
                Part part = req.getPart("avatar");
                String fileName = part.getSubmittedFileName();
                String[] fileNames = fileName.split("\\.");
                String uuid = UUID.randomUUID().toString();
                String file = uuid + "." + fileNames[fileNames.length - 1];
                part.write(HEADER_FILE_DIR + file);
                user.setHeader("/header/" + file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String message = userService.uploadUserInfo(user,
                req.getSession());

        req.getRequestDispatcher("/personalInfo.jsp?message=" + message).forward(req, resp);

    }
}
