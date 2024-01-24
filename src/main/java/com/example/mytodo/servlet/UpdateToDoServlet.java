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
import javax.swing.text.html.HTML;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@WebServlet(urlPatterns = "/updateToDo")
public class UpdateToDoServlet extends HttpServlet {
    ToDoManager toDoManager = new ToDoManager();
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo byId = toDoManager.getById(id);
        req.setAttribute("todo", byId);
        req.getRequestDispatcher("/WEB-INF/updateToDo.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("todoId"));
            String title = req.getParameter("title");
            Date createdDate = DateUtil.stringToDate(req.getParameter("createdDate"));
            Date finishDate = DateUtil.stringToDate(req.getParameter("finishDate"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            String status = req.getParameter("status");
            toDoManager.updateToDo(ToDo.builder()
                    .id(id)
                    .title(title)
                    .createdDate(createdDate)
                    .finisheDate(finishDate)
                    .user(userManager.getById(userId))
                    .status(Status.valueOf(status))
                    .build());
            resp.sendRedirect("/toDo");
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
