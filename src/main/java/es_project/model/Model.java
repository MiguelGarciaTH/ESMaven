package es_project.model;


import es_project.sql.interfaces.DataLayerInterface;
import es_project.sql.implementation.MyDataLayerImplementation;
import es_project.students_project.vehicle.Combustiveis;
import es_project.students_project.vehicle.Vehicle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Model {

    private DataLayerInterface db;


    public Model(String database, String user, String pass) {
        this.db = new MyDataLayerImplementation();
        this.db.connectDB(database, user, pass);
    }

    public void getAllVehicles(DefaultTableModel dataTableModel, String brand, String model) {
        dataTableModel.setColumnIdentifiers(Vehicle.getVehicleDefinition());
        dataTableModel.addRow(Vehicle.getVehicleDefinition());
        ResultSet rs = db.getAllVehicles(brand, model);
        try {
            while (rs.next()) {
                Vehicle vehicle = createVehicle(rs);
                dataTableModel.addRow(vehicle.vehicleToArray());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<String> getAllCarBrands() {
        return db.getAllVehicleBrands();
    }

    public void deleteCar(int id) {
        db.deleteVehicle(id);
    }

    public LinkedList<String> getAllCarModels(String brand) {
        return db.getAllVehicleModels(brand);
    }

    public double getCarPrice(int id) {
        ResultSet rs = db.getVehicle(id);
        try {
            while (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    private Vehicle getVehicle(int id) {
        Vehicle vehicle = new Vehicle();
        try {
            ResultSet rs = db.getVehicle(id);
            while (rs.next()) {
                return createVehicle(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle;
    }

    private Vehicle createVehicle(ResultSet rs) {
        Vehicle vehicle = new Vehicle();
        try {
            int id = rs.getInt("id");
            String marca = rs.getString("marca");
            String modelo = rs.getString("modelo");
            double cilindrada = rs.getDouble("cilindrada");
            int cavalos = rs.getInt("cavalos");
            double preco = rs.getDouble("preco");
            int quilometros = rs.getInt("quilometros");
            java.sql.Date date = rs.getDate("ano");
            String combustivel = rs.getString("combustivel");

            //ADICIONAR
            vehicle.setId(id);
            vehicle.setMarca(marca);
            vehicle.setModelo(modelo);
            vehicle.setCilindrada(cilindrada);
            vehicle.setCavalos(cavalos);
            vehicle.setPreco(preco);
            vehicle.setDate(date);
            vehicle.setQuilometros(quilometros);
            vehicle.setCombustivel(Combustiveis.valueOf(combustivel));

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
        Vehicle v = getVehicle(id);

        JTextField brandField = fields.get("brand");
        brandField.setText(v.getMarca());
        fields.put("brand", brandField);

        JTextField modelField = fields.get("model");
        modelField.setText(v.getModelo());
        fields.put("model", modelField);

        JTextField priceField = fields.get("price");
        priceField.setText(v.getPreco() + "");
        fields.put("price", priceField);

        JTextField cylindersField = fields.get("cylinders");
        cylindersField.setText(v.getCilindrada() + "");
        fields.put("cylinders", cylindersField);

        JTextField horsepowerField = fields.get("horsepower");
        horsepowerField.setText(v.getCavalos() + "");
        fields.put("horsepower", horsepowerField);

        JTextField kilometersField = fields.get("kilometers");
        kilometersField.setText(v.getQuilometros() + "");
        fields.put("kilometers", kilometersField);

        JTextField combustivelField = fields.get("combustivel");
        combustivelField.setText(v.getCombustivel().name());
        fields.put("combustivel", combustivelField);

        JTextField dateField = fields.get("date");
        dateField.setText(v.getDate().toString());
        fields.put("date", dateField);
    }

    public void createVehicle(String brand, String model, String price, String horsepower, String cylinders, String kilometers, String date, String fueltype) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = null;
        try {
            utilDate = format.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        db.insertVehicle(brand, model, Double.parseDouble(price), Double.parseDouble(cylinders), Integer.parseInt(horsepower), Integer.parseInt(kilometers), utilDate, fueltype);
    }

    public LinkedList<String> getAllFuels() {
        LinkedList<String> types = new LinkedList<>();
        for (Combustiveis type : Combustiveis.values()) {
            types.add(type.name());
        }
        return types;
    }
}
