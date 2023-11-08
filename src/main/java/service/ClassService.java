package service;

import model.ClassRoom;
import model.Student;
import model.ClassRoom;
import service.ConnectionToMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassService implements iClassService<ClassRoom> {
    private Connection connection = ConnectionToMySql.getConnection();


    @Override
    public List<ClassRoom> findAll() {
        List<ClassRoom> clasezList = new ArrayList<>();
        String sql = "select *from classRoom;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("classname");
                ClassRoom classRoom = new ClassRoom(id, name);
                clasezList.add(classRoom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clasezList;
    }
}