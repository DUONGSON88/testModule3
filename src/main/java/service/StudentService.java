package service;

import model.ClassRoom;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements iStudentService<Student> {
    private final Connection connection=ConnectionToMySql.getConnection();
    @Override
    public boolean add(Student student) {
        return false;
    }

    @Override
    public boolean edit(Student student, int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Student> findAll() {
       List<Student> studentList=new ArrayList<>();
       String sql="select s.*,cR.name from student s join classRoom cR on cR.id = s.idClass;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String date = resultSet.getString("dateOfBirth");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phoneNumber");
                int classId = resultSet.getInt("classRoomId");
                String classname = resultSet.getString("className");
                ClassRoom classRoom = new ClassRoom(classId, classname);
                Student student = new Student(id, name, email, date, address, phone, classRoom);
                studentList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    @Override
    public List<Student> findStudentByName(String nameFind) {
        return null;
    }

    @Override
    public Student findStudentById(int id) {
        return null;
    }
}
