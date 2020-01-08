package es_project.sql.interfaces;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

public interface DataLayerInterface {

    public boolean connectDB(String databaseName, String username, String password);
    public boolean disconnectDB();

    public LinkedList<String> getAllVehicleBrands();
    public LinkedList<String> getAllVehicleModels(String brand);
    public ResultSet getAllVehicles(String brand, String model);
    public ResultSet getVehicle(int id);
    public void deleteVehicle(int id);
    public void insertVehicle(String brand, String model, double price, double cylinders, int horsepower, int quilometros, Date date, String fueltype);

    public LinkedList<String> getAllPaymentModes();


    }
