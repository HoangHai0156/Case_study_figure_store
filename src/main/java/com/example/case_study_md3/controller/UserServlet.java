package com.example.case_study_md3.controller;

import com.example.case_study_md3.service.UserService;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "UserServlet", value = "/user")
public class UserServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = new UserService();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                showLogin(req, resp);
                break;
            case "signup":
                showSignup(req, resp);
                break;
            case "delete":
//                showDeleteUser(req, resp);
                break;
            case "view":
//                showDetailsUser(req, resp);
            default:
//                showListUser(req,resp);
                break;
        }
    }

    private void showSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/create-user.jsp").forward(req, resp);
    }

    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signIn.jsp").forward(req, resp);
    }


}