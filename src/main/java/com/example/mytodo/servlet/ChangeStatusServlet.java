package com.example.mytodo.servlet;

import com.example.mytodo.manager.ToDoManager;
import com.example.mytodo.manager.UserManager;
import com.example.mytodo.model.Status;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.util.DateUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/changeStatus")
public class ChangeStatusServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo byId = toDoManager.getById(id);
        if (byId.getStatus() == Status.NEW) {
            toDoManager.changeStatusDone(byId.getId());
            byId.setStatus(Status.DONE);
            resp.sendRedirect("/toDo");
        } else {
            toDoManager.changeStatusNew(byId.getId());
            byId.setStatus(Status.NEW);
            resp.sendRedirect("/toDo");
        }
    }

}
