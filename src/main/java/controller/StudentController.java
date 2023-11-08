package controller;

import model.ClassRoom;
import model.Student;
import service.ClassService;
import service.iClassService;
import service.iStudentService;
import service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    private iStudentService<Student> studentIService = new StudentService();
    private iClassService<ClassRoom> iClassService = new ClassService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "home":
                showHome(request, response);
                break;
            case "create":
                showFormCreate(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            case "edit":
                showFormEdit(request, response);
                break;
        }
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id", id);
        Student student = studentIService.findStudentById(id);
        request.setAttribute("editStudent", student);
        List<ClassRoom> clasezList = iClassService.findAll();
        request.setAttribute("clasezList", clasezList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentIService.delete(id);
        response.sendRedirect("/student?action=home");
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create.jsp");
        List<ClassRoom> clasezList = iClassService.findAll();
        request.setAttribute("clasezList", clasezList);
        dispatcher.forward(request, response);
    }

    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> studentList = null;
        String search = request.getParameter("search");
        if (search != null) {
            studentList = studentIService.findStudentByName(search);
        } else {
            studentList = studentIService.findAll();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home.jsp");
        request.setAttribute("listStudent", studentList);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "create":
                createStudent(request, response);
                break;
            case "edit":
                editStudent(request, response);
                break;
        }
    }

    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classRoomId = Integer.parseInt(request.getParameter("classRoomId"));
        ClassRoom classRoom = new ClassRoom(classRoomId);
        Student student = new Student(name, dateOfBirth,email, address, phoneNumber, classRoom);
        studentIService.edit(student, id);
        response.sendRedirect("/student?action=home");
    }

    private void createStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int classRoomId = Integer.parseInt(request.getParameter("classRoomId"));
        ClassRoom classRoom = new ClassRoom(classRoomId);
        Student student = new Student(name,  dateOfBirth,email, address, phoneNumber, classRoom);
        studentIService.add(student);
        response.sendRedirect("/student?action=home");
    }
}