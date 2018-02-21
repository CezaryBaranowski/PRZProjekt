package Model;

import Application.Application;
import org.apache.commons.math.util.MathUtils;
import org.hibernate.criterion.Order;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PlaneGenerator {

    private static ArrayList<Plane> availablePlanes = new ArrayList<Plane>();

    private static int drawnNumber;

    private static String brand;
    private static String model;
    private static int productionYear;
    private static int capacity;
    private static int range;
    private static Double costFactor;      // wspolczynnik kosztow
    private static int price;
    private static Airport location;
    private static Boolean available;

    public static Plane generatePlane()
    {

        int amountOfAirplaneTypes = Application.getPlanes().size();
        Random rand = new Random();
        drawnNumber = rand.nextInt(amountOfAirplaneTypes);
        brand = Application.getPlanes().get(drawnNumber).getBrand();
        model = Application.getPlanes().get(drawnNumber).getModel();
        capacity = Application.getPlanes().get(drawnNumber).getCapacity();
        range = Application.getPlanes().get(drawnNumber).getRange();
        costFactor = Application.getPlanes().get(drawnNumber).getCostFactor();
        location = Application.getAirports().get(42);
        available = true;
        price = Application.getPlanes().get(drawnNumber).getPrice();

        drawnNumber = rand.nextInt(Application.getPlanes().get(drawnNumber).getProducedTo() - Application.getPlanes().get(drawnNumber).getProducedFrom());
        productionYear = Calendar.getInstance().get(Calendar.YEAR) - drawnNumber;
        // 2% wartosci co rok
        double percentValue = 1.0 - (drawnNumber * 0.02);
        double priceDouble = price * percentValue;
        price = (int) priceDouble ;

        Plane newPlane = new Plane(brand,model,productionYear,capacity,range,costFactor,price,location,available);
        availablePlanes.add(newPlane);
        return newPlane;
    }


}
