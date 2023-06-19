package com.example.case_study_md3.controller.admin;

import com.example.case_study_md3.model.Pageable;
import com.example.case_study_md3.model.User;
import com.example.case_study_md3.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminUserServlet", value = "/admin")
public class AdminUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || !user.geteRole().name().equals("ADMIN")){
            resp.sendRedirect("/user?action=login");
            return;
        }

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            default:
                showUser(req, resp);
        }
    }

    private void showUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.findAll();
        int count = userService.getTotalUser();
        int endPage = count/3;
        if (count % 3 != 0) {
            endPage++;
        }
        req.setAttribute("users", users);
        req.setAttribute("endPage", endPage);
        req.getRequestDispatcher("/WEB-INF/admin/user/list-user.jsp").forward(req, resp);
    }
}
