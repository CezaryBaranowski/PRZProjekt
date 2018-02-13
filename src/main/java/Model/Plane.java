package Model;

import java.io.Serializable;

public class Plane implements Serializable {

    private String brand;
    private String model;
    private int producedFrom;
    private int producedTo;
    private int productionYear;
    private int capacity;
    private int range;
    private Double costFactor;      // wspolczynnik kosztow
    private int price;
    private Airport location;
    private Boolean available;

    public Plane(String brand, String model, int productionYear, int capacity, int range, Double costFactor, int price, Airport location, Boolean available) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.capacity = capacity;
        this.range = range;
        this.costFactor = costFactor;
        this.price = price;
        this.location = location;
        this.available = available;
    }

    public Plane(String brand, String model, int producedFrom, int producedTo, int capacity, int range, Double costFactor, int price) {
        this.brand = brand;
        this.model = model;
        this.producedFrom = producedFrom;
        this.producedTo = producedTo;
        this.capacity = capacity;
        this.range = range;
        this.costFactor = costFactor;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Double getCostFactor() {
        return costFactor;
    }

    public void setCostFactor(Double costFactor) {
        this.costFactor = costFactor;
    }

    public Airport getLocation() { return location; }

    public void setLocation(Airport location) { this.location = location; }

    public Boolean getAvailable() { return available; }

    public void setAvailable(Boolean available) { this.available = available; }

    public int getProducedFrom() { return producedFrom; }

    public void setProducedFrom(int producedFrom) { this.producedFrom = producedFrom; }

    public int getProducedTo() { return producedTo; }

    public void setProducedTo(int producedTo) { this.producedTo = producedTo; }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price; }
}
