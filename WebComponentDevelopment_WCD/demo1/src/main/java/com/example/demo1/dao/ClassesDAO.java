package com.example.demo1.dao;

import com.example.demo1.database.Database;
import com.example.demo1.entity.Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClassesDAO implements DAOInterface<Classes>{

    @Override
    public List<Classes> getAll() {
        String sql = "select * from classes ORDER BY id";
        ArrayList<Classes> list = new ArrayList<>();
        try {
            Database db = Database.createInstance();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {
                list.add(new Classes(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean create(Classes classes) {
        String sql = "insert into classes(name) values(?)";
        try {
            Database db = Database.createInstance();
            PreparedStatement prst = db.getPreparedStatement(sql);
            prst.setString(1, classes.getName());
            prst.execute();
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean update(Classes aClass) {
        return false;
    }

    @Override
    public <K> void delete(K s) {

    }


    @Override
    public <K> Classes find(K id) {
        return null;
    }
}
