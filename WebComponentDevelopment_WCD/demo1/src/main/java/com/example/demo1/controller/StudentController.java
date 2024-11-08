package com.example.demo1.controller;

import com.example.demo1.dao.ClassesDAO;
import com.example.demo1.dao.StudentDAO;
import com.example.demo1.dto.StudentDTO;
import com.example.demo1.entity.Classes;
import com.example.demo1.entity.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/student")
public class StudentController extends HttpServlet {

    private StudentDAO studentDAO;
    private ClassesDAO classesDAO;

    @Override
    public void init(){
        studentDAO = new StudentDAO();
        classesDAO = new ClassesDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // lấy danh sách sinh viên
        List<StudentDTO> list = studentDAO.getAllStudent();
        List<Classes> classesList = classesDAO.getAll();
        // trả về 1 giao diện danh sách sinh viên
        req.setAttribute("students", list);
        req.setAttribute("classes", classesList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("student/list.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if ("delete".equals(action)) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            studentDAO.delete(studentId);
            res.sendRedirect(req.getContextPath() + "/student");
            return;
        }
        if ("edit".equals(action)) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String address = req.getParameter("address");
            String telephone = req.getParameter("telephone");
            Integer class_id = Integer.valueOf(req.getParameter("class_id"));

            Student student = new Student();
            student.setId(studentId);
            student.setName(name);
            student.setEmail(email);
            student.setAddress(address);
            student.setTelephone(telephone);
            student.setClass_id(class_id);

            studentDAO.update(student);
            res.sendRedirect(req.getContextPath() + "/student");
        }

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        Integer class_id = Integer.valueOf(req.getParameter("class_id"));

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setAddress(address);
        student.setTelephone(telephone);
        student.setClass_id(class_id);

        studentDAO.create(student);
        res.sendRedirect(req.getContextPath() + "/student");
    }



}
