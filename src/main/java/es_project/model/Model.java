package es_project.model;


import es_project.model.sql.DataLayerInterface;
import es_project.model.sql.MyDataLayerImplementation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;

public class Model {

    private DataLayerInterface db;

    public Model(String database, String user, String pass) {
        this.db = new MyDataLayerImplementation();
        this.db.connectDB("es_project", "es_maven", "es_maven");
    }

    public void getAllVehicles(DefaultTableModel dataTableModel, String brand, String model) {
        dataTableModel.setColumnIdentifiers(new String[]{"ID", "Marca", "Modelo", "Cilindrada", "Cavalos", "Preço", "Data"});
        dataTableModel.addRow(new String[]{"ID", "Marca", "Modelo", "Cilindrada", "Cavalos", "Preço", "Data"});
        ResultSet rs = db.getAllCars(brand, model);
        //| id | marca   | modelo | cilindrada | cavalos | preco    | ano        |
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                double cilindrada = rs.getDouble("cilindrada");
                int cavalos = rs.getInt("cavalos");
                double preco = rs.getDouble("preco");
                Date date = rs.getDate("ano");
                Vehicle vehicle = new Vehicle();
                vehicle.setId(id);
                vehicle.setMarca(marca);
                vehicle.setModelo(modelo);
                vehicle.setCilindrada(cilindrada);
                vehicle.setCavalos(cavalos);
                vehicle.setPreco(preco);
                vehicle.setDate(date);
                dataTableModel.addRow(new Object[]{vehicle.getId(), vehicle.getMarca(), vehicle.getModelo(), vehicle.getCilindrada(), vehicle.getCavalos(), vehicle.getPreco(), vehicle.getDate()});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getAllCarBrands() {
        return db.getAllCarBrands();
    }

    public void insertCar(Vehicle vehicle) {
        db.insertCar(vehicle.getMarca(), vehicle.getModelo(), vehicle.getPreco(), vehicle.getCilindrada(), vehicle.getCavalos(), vehicle.getDate());
    }

    public void deleteCar(int id) {
        db.deleteCar(id);
    }

    public LinkedList<String> getAllCarModels(String brand) {
        return db.getAllCarModels(brand);
    }

    public double getCarPrice(int id) {
        try {
            ResultSet rs = db.getCar(id);
            while (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private Vehicle getCar(int id) {
        Vehicle vehicle = new Vehicle();
        try {
            ResultSet rs = db.getCar(id);
            //| id | marca   | modelo | cilindrada | cavalos | preco    | ano        |
            while (rs.next()) {
                String marca = rs.getString("marca");
                String modelo = rs.getString("modelo");
                //int portas = rs.getInt("portas");
                double cilindrada = rs.getDouble("cilindrada");
                int cavalos = rs.getInt("cavalos");
                double preco = rs.getDouble("preco");
                Date date = rs.getDate("ano");
                vehicle.setId(id);
                vehicle.setMarca(marca);
                vehicle.setModelo(modelo);
                vehicle.setCilindrada(cilindrada);
                vehicle.setCavalos(cavalos);
                vehicle.setPreco(preco);
                vehicle.setDate(date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    public LinkedList<String> getAllPaymentMethods() {
        return db.getAllPaymentModes();
    }

    public void disconnect() {
        db.disconnectDB();
    }

    public void getVehiclesAttributes(HashMap<String, JTextField> fields, int id) {
        Vehicle v = getCar(id);

        JTextField brandField = fields.get("brand");
        brandField.setText(v.getMarca());
        fields.put("brand", brandField);

        JTextField modelField = fields.get("model");
        modelField.setText(v.getModelo());
        fields.put("model", modelField);

        JTextField pricelField = fields.get("price");
        pricelField.setText(v.getPreco() + "");
        fields.put("price", pricelField);

        JTextField cylindersField = fields.get("cylinders");
        cylindersField.setText(v.getCilindrada() + "");
        fields.put("cylinders", cylindersField);

        JTextField horsepowerField = fields.get("horsepower");
        horsepowerField.setText(v.getCavalos() + "");
        fields.put("horsepower", horsepowerField);

        JTextField dateField = fields.get("date");
        dateField.setText(v.getDate().toString());
        fields.put("date", dateField);
    }

    public void createVehicle(String brand, String model, String price, String horsepower, String cylinders, String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Vehicle vehicle = new Vehicle();
        vehicle.setMarca(brand);
        vehicle.setModelo(model);
        vehicle.setPreco(Double.parseDouble(price));
        vehicle.setCavalos(Integer.parseInt(horsepower));
        vehicle.setCilindrada(Double.parseDouble(cylinders));
        java.util.Date utilDate = null;
        try {
            utilDate = format.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        Date sqlDate = new Date(utilDate.getTime());
        vehicle.setDate(sqlDate);
        insertCar(vehicle);
    }
}
