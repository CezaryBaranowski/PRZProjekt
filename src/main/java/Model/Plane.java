package Model;

public class Plane {

    private String brand;
    private String model;
    private int productionYear;
    private int capacity;
    private int range;
    private Double costFactor;      // wspolczynnik kosztow

    public Plane(String brand, String model, int productionYear, int capacity, int range, Double costFactor) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.capacity = capacity;
        this.range = range;
        this.costFactor = costFactor;
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
}
