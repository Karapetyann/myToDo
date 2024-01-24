package com.example.mytodo.servlet;

import com.example.mytodo.manager.ToDoManager;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/toDo")
public class ToDoServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<ToDo> toDoList = toDoManager.getByUser(user.getId());
        req.getSession().setAttribute("toDoList", toDoList);
        req.getRequestDispatcher("/WEB-INF/myToDo.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
       List<ToDo> toDoList = toDoManager.getByUser(user.getId());
       req.getSession().setAttribute("toDoList", toDoList);
       req.getRequestDispatcher("/WEB-INF/myToDo.jsp").forward(req,resp);
    }
}
