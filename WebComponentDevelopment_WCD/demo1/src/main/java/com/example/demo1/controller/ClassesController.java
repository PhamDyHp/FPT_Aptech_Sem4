package com.example.demo1.controller;

import com.example.demo1.dao.ClassesDAO;
import com.example.demo1.entity.Classes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/classes")
public class ClassesController extends HttpServlet {

    private ClassesDAO classesDAO;

    @Override
    public void init(){
        classesDAO = new ClassesDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<Classes> list = classesDAO.getAll();
        req.setAttribute("classes", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("classes/classes.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");

        Classes classes = new Classes();
        classes.setName(name);
        classesDAO.create(classes);
        res.sendRedirect(req.getContextPath() + "/classes");
    }

}
