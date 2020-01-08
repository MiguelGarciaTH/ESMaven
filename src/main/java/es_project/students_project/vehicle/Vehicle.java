package es_project.students_project.vehicle;

import es_project.students_project.vehicle.Combustiveis;

import java.util.Date;

public class Vehicle {

    private int id;
    private String marca;
    private String modelo;
    private double preco;
    private double cilindrada;
    private int cavalos;
    private int quilometros;
    private Combustiveis combustivel;
    private Date data;

    public Vehicle() {

    }

    public int getQuilometros() {
        return quilometros;
    }

    public void setQuilometros(int quilometros) {
        this.quilometros = quilometros;
    }

    public Combustiveis getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Combustiveis combustivel) {
        this.combustivel = combustivel;
    }

    public static String[] getVehicleDefinition() {
        return new String[]{"ID", "Marca", "Modelo", "Cilindrada", "Cavalos", "Preço", "Quilometros", "Combustivel", "Data"};
    }

    public Object[] vehicleToArray() {
        return new Object[]{this.getId(), this.getMarca(), this.getModelo(), this.getCilindrada(), this.getCavalos(), this.getPreco(), this.getQuilometros(), this.getCombustivel().name(), this.getDate()};
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", preco=" + preco +
                ", cilindrada=" + cilindrada +
                ", cavalos=" + cavalos +
                ", quilometros=" + quilometros +
                ", data=" + data +
                '}';
    }

    public Date getDate() {
        return data;
    }

    public void setDate(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public int getCavalos() {
        return cavalos;
    }

    public void setCavalos(int cavalos) {
        this.cavalos = cavalos;
    }

}
