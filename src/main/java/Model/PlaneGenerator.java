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
        Airport location;

        int maxAmountOfAirports = Application.getAirports().size();
        Random rand = new Random();
        drawnNumber = rand.nextInt(maxAmountOfAirports);            //  generuj lotnisko startowe
        from = Application.getAirports().get(drawnNumber);
        drawnNumber = rand.nextInt(maxAmountOfAirports);            // generuj lotnisko docelowe
        destination = Application.getAirports().get(drawnNumber);
        if(from.equals(destination)) from = Application.getAirports().get(0);
        drawnNumber = rand.nextInt(6) + 1;                  // generuje ile dnie wazne
        daysToExpiration = drawnNumber;
        drawnNumber = rand.nextInt(2990000) + 10000;         // generuj nagrode
        prize = (int) MathUtils.round((double)drawnNumber, -1); // zaokraglij ja do 10
        penalty = (int) (prize * (rand.nextDouble()*2.0));          // generuj kare

        drawnNumber = rand.nextInt(310) + 90;                // generuj liczbe pasazerow
        amountOfPassengers = drawnNumber;
        distance = calculateDistance(Double.parseDouble(from.getLatitude()),Double.parseDouble(from.getLongtitude()),Double.parseDouble(destination.getLatitude()),Double.parseDouble(destination.getLongtitude()),'K');

        FlightOrder newOrder = new FlightOrder(from,destination,daysToExpiration,amountOfPassengers,prize,penalty,distance);
        return newOrder;
    }


}
