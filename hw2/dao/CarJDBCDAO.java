package hw2.dao;

import hw2.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CarJDBCDAO implements CarDAO {
    @Override
    public void add(Car car) {
        Connection con = null;
        con = getConnection();

        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement("insert into car (model, model_year) values (?, ?)");
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getModelYear());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getModel(String model, Connection con) throws SQLException {

        PreparedStatement ps = con.prepareStatement("select model from car where model = ?");
        ps.setString(1, model);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }

        return "";
    }


    @Override
    public List<Car> getAll() {
        Connection con = getConnection();
        Statement s = null;

        try {
            s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from car");

            List<Car> list = new ArrayList<>();

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setModel(rs.getString("model"));
                car.setModelYear(rs.getInt("model_year"));

                list.add(car);
            }

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public Car getById(int id) {
        Connection con = getConnection();
        PreparedStatement ps = null;

        try {

            ps =  con.prepareStatement("select * from car where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setModel(rs.getString("model"));
                car.setModelYear(rs.getInt("model_year"));
                return car;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        Connection con = getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement("delete from car where id = ?");
            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Car car, int id) {

        Connection con = getConnection();
        PreparedStatement ps = null;

        try {

            ps = con.prepareStatement("update car set model = ?, model_year = ? where id = ?");
            ps.setString(1, car.getModel());
            ps.setInt(2, car.getModelYear());
            ps.setInt(3, id);
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
        Connection con = null;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_db", "root", "root");
            return con;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}