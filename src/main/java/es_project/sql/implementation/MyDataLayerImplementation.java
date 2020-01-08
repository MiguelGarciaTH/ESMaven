package es_project.sql.implementation;

import es_project.sql.interfaces.DataLayerInterface;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;

public class MyDataLayerImplementation implements DataLayerInterface {

    private Connection con;

    public MyDataLayerImplementation() {

    }

    public boolean connectDB(String databaseName, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, username, password);
            return !con.isClosed();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean disconnectDB() {
        try {
            con.close();
            return con.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public LinkedList<String> getAllVehicleBrands() {
        LinkedList<String> list = new LinkedList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select distinct marca from veiculo order by marca");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedList<String> getAllPaymentModes() {
        LinkedList<String> list = new LinkedList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select distinct mododecompra from mododecompra");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void deleteVehicle(int id) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("delete from veiculo where id=?");
            preparedStmt.setInt(1, id);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertVehicle(String brand, String model, double preco, double cilindra, int cavalos, int quilometros, Date date, String fuel) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("insert into veiculo (marca, modelo, cilindrada, cavalos, preco, ano, quilometros, combustivel) values (?,?,?,?,?,?,?,?)");
            preparedStmt.setString(1, brand);
            preparedStmt.setString(2, model);
            preparedStmt.setDouble(3, cilindra);
            preparedStmt.setInt(4, cavalos);
            preparedStmt.setDouble(5, preco);
            preparedStmt.setDate(6, new java.sql.Date(date.getTime()));
            preparedStmt.setInt(7, quilometros);
            preparedStmt.setString(8, fuel);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getAllVehicleModels(String brand) {
        LinkedList<String> list = new LinkedList<>();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select distinct modelo from veiculo where marca=?");
            stmt.setString(1, brand);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ResultSet getVehicle(int id) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from veiculo where id=?");
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultSet getAllVehicles(String brand, String model) {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("select * from veiculo where marca=? and modelo=?");
            stmt.setString(1, brand);
            stmt.setString(2, model);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
