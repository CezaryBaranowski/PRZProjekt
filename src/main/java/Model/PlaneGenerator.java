package Model;

import Application.Application;
import org.apache.commons.math.util.MathUtils;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.Random;

public class PlaneGenerator {

    ArrayList<Plane> allPlanes;
    ArrayList<Plane> boughtPlanes;

    public Plane generatePlane()
    {
        int drawnNumber;

        String brand;
        String model;
        int productionYear;
        int capacity;
        int range;
        Double costFactor;      // wspolczynnik kosztow
        int price;
        Airport location;
        Boolean available;

        int amountOfAirplaneTypes = Application.getAirports().size();
        Random rand = new Random();
        drawnNumber = rand.nextInt(amountOfAirplaneTypes);
        brand = Application.getPlanes().get(drawnNumber).getBrand();
        model = brand = Application.getPlanes().get(drawnNumber).getModel();
        capacity = Application.getPlanes().get(drawnNumber).getCapacity();
        range = Application.getPlanes().get(drawnNumber).getRange();
        costFactor = Application.getPlanes().get(drawnNumber).getCostFactor();
        location = Application.getAirports().get(0);
        available = true;
        price = Application.getPlanes().get(drawnNumber).getPrice();

        //teraz drawnnumber to wiek samolotu
        drawnNumber = rand.nextInt(Application.getPlanes().get(drawnNumber).getProducedTo() - Application.getPlanes().get(drawnNumber).getProducedFrom());
        productionYear = drawnNumber + Application.getPlanes().get(drawnNumber).getProducedFrom();
        // 2% wartosci co rok
        double percentValue = 1.0 - (drawnNumber * 0.02);
        double priceDouble = price * percentValue;
        price = (int) priceDouble ;

        Plane newPlane = new Plane(brand,model,productionYear,capacity,range,costFactor,price,location,available);
        return newPlane;
    }


}
