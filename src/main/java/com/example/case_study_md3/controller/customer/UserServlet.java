package com.example.case_study_md3.controller.customer;
import com.example.case_study_md3.model.ERole;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.UserService;
import com.example.case_study_md3.utils.Config;
import com.example.case_study_md3.utils.DateFormat;
import com.example.case_study_md3.utils.ValidateUtils;

import java.io.*;
import java.sql.SQLException;
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
//            case "view":
//                showDetailsUser(req, resp);
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
            resp.sendRedirect("/user?action=login");
//            req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
        }
    }
    private void showLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
//        req.getRequestDispatcher("/product").forward(req, resp);
        resp.sendRedirect("/product");
    }
    private void showSignup(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
    }
    private void showLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/homepage/signIn.jsp").forward(req, resp);
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
            case "myAccount":
                updateInfo(req, resp);
                break;
            default:
                break;
        }
    }

    private void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = new ArrayList<>();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        validateInputName(req, errors, user);
        validateInputAddress(req, errors, user);
        validateInputPhone(req, errors, user);
        validateInputEmail(req, errors, user);
        validateInputDob(req, errors, user);
        if (errors.isEmpty()) {
            userService.update(user.getId(), user);
            req.setAttribute("message", "Sửa thành công");
        } else {
            req.setAttribute("errors", errors);
            req.setAttribute("user", user);
        }
        req.getRequestDispatcher("/WEB-INF/homepage/user-account.jsp").forward(req, resp);
    }

    private void validateInputDob(HttpServletRequest req, List<String> errors, User user) {
        String dob = req.getParameter("birthday");
        if (!ValidateUtils.isDOBValid(dob)) {
            errors.add("Ngày sinh không hợp lệ");
        } else {
            user.setDob(DateFormat.parse(dob));
        }
    }

    private void validateInputEmail(HttpServletRequest req, List<String> errors, User user) {
        String email = req.getParameter("email");
        if (!ValidateUtils.isEmailValid(email)) {
            errors.add("Email không hợp lệ");
        } else {
            user.setEmail(email);
        }
    }

    private void validateInputPhone(HttpServletRequest req, List<String> errors, User user) {
        String phone = req.getParameter("phone");
        if (!ValidateUtils.isPhoneValid(phone)) {
            errors.add("Phone không hợp lệ");
        } else {
            user.setPhone(phone);
        }
    }

    private void validateInputAddress(HttpServletRequest req, List<String> errors, User user) {
        String address = req.getParameter("address");
        if (!ValidateUtils.isAddressValid(address)) {
            errors.add("Địa chỉ không hợp lệ");
        } else {
            user.setAddress(address);
        }
    }

    private void validateInputName(HttpServletRequest req, List<String> errors, User user) {
        String name = req.getParameter("name");
        if (!ValidateUtils.isNameValid(name)) {
            errors.add("Tên không hợp lệ. Tên phải từ 8-30 ký tự và bđ là chữ cái");
        } else {
            user.setName(name);
        }
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
                resp.sendRedirect("user?action=myAccount");
//                req.getRequestDispatcher("/WEB-INF/admin/user/create-user.jsp").forward(req, resp);
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
//            req.getRequestDispatcher("/product").forward(req, resp);
            resp.sendRedirect("/product");
        }
    }
}