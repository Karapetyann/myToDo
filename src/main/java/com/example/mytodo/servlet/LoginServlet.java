package com.example.mytodo.servlet;

import com.example.mytodo.manager.UserManager;
import com.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    UserManager userManager = new UserManager();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getByEmial(email);
        if (user != null && user.getPassword().equals(password)){
            req.getSession().setAttribute("user", user);
            req.getRequestDispatcher("/toDo").forward(req,resp);
        }else {
            req.getSession().setAttribute("msg","Invalid email or password");
            resp.sendRedirect("/");
        }
    }
}
