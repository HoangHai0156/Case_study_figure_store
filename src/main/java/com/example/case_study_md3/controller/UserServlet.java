package com.example.case_study_md3.controller;
import com.example.case_study_md3.model.ERole;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
            case "logout":
                showLogout(req, resp);
                break;
            case "myAccount":
                showMyAccount(req, resp);
            case "delete":
//                showDeleteUser(req, resp);
                break;
            case "view":
                showDetailsUser(req, resp);
            default:
//                showListUser(req,resp);
                break;
        }
    }

    private void showMyAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                checkLogin(req, resp);
                break;
            case "signup":
                signup(req, resp);
                break;
            default:
                break;
        }
    }

    private void showLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        resp.sendRedirect("/product");
    }

    private void signup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String re_pass = req.getParameter("confirm-password");
        if (!password.equals(re_pass)) {
            errors.add("Mật khẩu không trùng khớp. Hãy nhập lại");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
        } else {
            User user = userService.checkAccount(email);
            if (user != null) {
                errors.add("Email đã đăng ký. Vui lòng nhập email khác");
                req.setAttribute("errors", errors);
                req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
            } else {
                User userNew = new User();
                userNew.setCreateAt(new Date());
                userNew.setEmail(email);
                userNew.setPassword(password);
                userNew.seteRole(ERole.USER);
                userService.save(userNew);
                req.setAttribute("message", "Đăng ký thành công. Login để đăng nhập");
                req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
            }
        }
    }

    private void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userService.checkUser(email, password);
        if (user == null) {
            errors.add("Email hoặc password không đúng");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/product");
        }
    }

        private void showDetailsUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);
    }
    private void showSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
    }
    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
    }
}