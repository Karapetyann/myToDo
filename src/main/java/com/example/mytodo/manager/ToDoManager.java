package com.example.mytodo.manager;

import com.example.mytodo.db.DBConnectionProvider;
import com.example.mytodo.model.Status;
import com.example.mytodo.model.ToDo;
import com.example.mytodo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoManager {
    Connection connection = DBConnectionProvider.getInstance().getConnection();
    UserManager userManager = new UserManager();

    public void changeStatusDone(int id) {
        String sql = "UPDATE to_do SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "DONE");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void add(ToDo toDo) {
        String sql = "INSERT INTO to_do(title,created_date,finish_date,user_id,status)VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, toDo.getTitle());
            ps.setDate(2, new Date(toDo.getCreatedDate().getTime()));
            ps.setDate(3,  new Date(toDo.getFinisheDate().getTime()));
            ps.setInt(4, toDo.getUser().getId());
            ps.setString(5, String.valueOf(toDo.getStatus()));
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int anInt = generatedKeys.getInt(1);
                toDo.setId(anInt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int id) {
        String sql = "DELETE FROM to_do WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ToDo getById(int id) {
        String sql = "SELECT * FROM to_do WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return ToDo.builder()
                        .id(id)
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finisheDate(resultSet.getDate("finish_date"))
                        .user(userManager.getById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<ToDo> getByUser(int id){
        String sql = "SELECT * FROM to_do WHERE user_id =" + id;
        List<ToDo> toDoList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                toDoList.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finisheDate(resultSet.getDate("finish_date"))
                        .user(userManager.getById(id))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return toDoList;
    }

    public void updateToDo(ToDo toDo) {
        String sql = "UPDATE to_do SET title = ?,created_date = ?,finish_date = ?,user_id = ?,status = ? WHERE id ="+ toDo.getId();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, toDo.getTitle());
            ps.setDate(2, new Date(toDo.getCreatedDate().getTime()));
            ps.setDate(3,  new Date(toDo.getFinisheDate().getTime()));
            ps.setInt(4, toDo.getUser().getId());
            ps.setString(5, String.valueOf(toDo.getStatus()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeStatusNew(int id) {
        String sql = "UPDATE to_do SET status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "NEW");
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
