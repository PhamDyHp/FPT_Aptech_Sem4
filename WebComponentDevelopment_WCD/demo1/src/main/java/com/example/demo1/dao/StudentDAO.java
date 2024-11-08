package com.example.demo1.dao;

import com.example.demo1.database.Database;
import com.example.demo1.dto.StudentDTO;
import com.example.demo1.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class StudentDAO implements DAOInterface<Student>{

    public List<StudentDTO> getAllStudent() {
        String sql = "SELECT s.id, s.name, s.email, s.address, s.telephone, s.class_id, c.name AS class_name" +
                "  from students s " +
                "  LEFT JOIN classes c ON c.id = s.class_id";
        ArrayList<StudentDTO> list = new ArrayList<>();
        try {
            Database db = Database.createInstance();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new StudentDTO(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("telephone"),
                        rs.getInt("class_id"),
                        rs.getString("class_name")
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Student> getAll() {
        String sql = "select * from students";
        ArrayList<Student> list = new ArrayList<>();
        try {
            Database db = Database.createInstance();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("address"),
                        rs.getString("telephone"),
                        rs.getInt("class_id")
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean create(Student student) {
        String sql = "insert into students(name, email, address, telephone, class_id) values(?,?,?,?,?)";
        try {
            Database db = Database.createInstance();
            PreparedStatement prst = db.getPreparedStatement(sql);
            prst.setString(1, student.getName());
            prst.setString(2, student.getEmail());
            prst.setString(3, student.getAddress());
            prst.setString(4, student.getTelephone());
            prst.setInt(5, student.getClass_id());
            prst.execute();
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET name = ?, email = ?, address = ?, telephone = ?, class_id = ? WHERE id = ?";
        try {
            Database db = Database.createInstance();
            PreparedStatement prst = db.getPreparedStatement(sql);

            prst.setString(1, student.getName());
            prst.setString(2, student.getEmail());
            prst.setString(3, student.getAddress());
            prst.setString(4, student.getTelephone());
            prst.setInt(5, student.getClass_id());
            prst.setInt(6, student.getId());

            int rowsUpdated = prst.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public <K> void delete(K id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try {
            Database db = Database.createInstance();
            PreparedStatement prst = db.getPreparedStatement(sql);
            if (id instanceof Integer) {
                prst.setInt(1, (Integer) id);
                prst.executeUpdate();
                System.out.println("Student with id " + id + " has been deleted.");
            } else {
                throw new IllegalArgumentException("ID must be an integer.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public boolean delete(Student student) {
        String sql = "DELETE FROM students WHERE id = ?";
        try {
            Database db = Database.createInstance();
            PreparedStatement prst = db.getPreparedStatement(sql);

            prst.setInt(1, student.getId());
            int rowsAffected = prst.executeUpdate();

            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    @Override
    public <K> Student find(K id) {
        return null;
    }
}
