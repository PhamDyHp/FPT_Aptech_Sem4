package com.example.demo1.dao;

import com.example.demo1.database.Database;
import com.example.demo1.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements DAOInterface<Subject>{
    @Override
    public List<Subject> getAll() {
        String sql = "select * from subjects";
        ArrayList<Subject> list = new ArrayList<>();
        try {
            Database db = Database.createInstance();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new Subject(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("hours")
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean create(Subject subject) {
        String sql = "insert into subjects(name, hours) values(?, ?)";
        try {
            Database db = Database.createInstance();
            PreparedStatement prst = db.getPreparedStatement(sql);
            prst.setString(1, subject.getName());
            prst.execute();
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Subject subject) {
        return false;
    }

    @Override
    public <K> void delete(K s) {

    }


    @Override
    public <K> Subject find(K id) {
        return null;
    }
}
