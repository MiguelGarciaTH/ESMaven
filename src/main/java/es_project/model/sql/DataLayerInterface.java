package es_project.model.sql;

import java.sql.ResultSet;
import java.util.Date;
import java.util.LinkedList;

public interface DataLayerInterface {

    public boolean connectDB(String databaseName, String username, String password);
    public boolean disconnectDB();
    public LinkedList<String> getAllCarBrands();
    public LinkedList<String> getAllCarModels(String brand);
    public ResultSet getAllCars(String brand, String model);
    public ResultSet getCar(int id);
    public LinkedList<String> getAllPaymentModes();
    public void deleteCar(int id);
    public void insertCar(String brand, String model, double preco, double cilindra, int cavalos, Date date);


    }
