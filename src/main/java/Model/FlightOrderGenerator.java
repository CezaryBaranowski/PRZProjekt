package Model;

import Application.Application;
import org.apache.commons.math.util.MathUtils;

import java.util.ArrayList;
import java.util.Random;

public class FlightOrderGenerator {

    private static ArrayList<FlightOrder> availableFlightOrders = new ArrayList<FlightOrder>();

    private static int drawnNumber;

    private static Airport from;
    private static Airport destination;
    private static int daysToExpiration;
    private static int amountOfPassengers;
    private static int prize;
    private static int penalty;
    private static Double distance;

    public static FlightOrder generateOrder()
    {

        int maxAmountOfAirports = Application.getAirports().size();
        Random rand = new Random();
        drawnNumber = rand.nextInt(maxAmountOfAirports);            //  generuj lotnisko startowe
        from = Application.getAirports().get(drawnNumber);
        drawnNumber = rand.nextInt(maxAmountOfAirports);            // generuj lotnisko docelowe
        destination = Application.getAirports().get(drawnNumber);
        if(from.equals(destination)) from = Application.getAirports().get(0);
        drawnNumber = rand.nextInt(5) + 1;                  // generuje ile dnie wazne
        daysToExpiration = drawnNumber;
        drawnNumber = rand.nextInt(2990000) + 10000;         // generuj nagrode
        prize = (int) MathUtils.round((double)drawnNumber, -1); // zaokraglij ja do 10
        penalty = (int) (prize * (rand.nextDouble()*2.0));          // generuj kare

        drawnNumber = rand.nextInt(310) + 50;                // generuj liczbe pasazerow
        amountOfPassengers = drawnNumber;
        distance = calculateDistance(Double.parseDouble(from.getLatitude()),Double.parseDouble(from.getLongtitude()),Double.parseDouble(destination.getLatitude()),Double.parseDouble(destination.getLongtitude()),'K');

        FlightOrder newOrder = new FlightOrder(from,destination,daysToExpiration,amountOfPassengers,prize,penalty,distance);
        availableFlightOrders.add(newOrder);
        return newOrder;
    }

    public static FlightOrder generateOrder(Airport ap)
    {

        int maxAmountOfAirports = Application.getAirports().size();
        Random rand = new Random();
        drawnNumber = rand.nextInt(maxAmountOfAirports);            //  generuj lotnisko startowe
        from = ap;
        drawnNumber = rand.nextInt(maxAmountOfAirports);            // generuj lotnisko docelowe
        destination = Application.getAirports().get(drawnNumber);
        if(from.equals(destination)) from = Application.getAirports().get(0);
        drawnNumber = rand.nextInt(5) + 1;                  // generuje ile dnie wazne
        daysToExpiration = drawnNumber;
        drawnNumber = rand.nextInt(2990000) + 10000;         // generuj nagrode
        prize = (int) MathUtils.round((double)drawnNumber, -1); // zaokraglij ja do 10
        penalty = (int) (prize * (rand.nextDouble()*2.0));          // generuj kare

        drawnNumber = rand.nextInt(310) + 50;                // generuj liczbe pasazerow
        amountOfPassengers = drawnNumber;
        distance = calculateDistance(Double.parseDouble(from.getLatitude()),Double.parseDouble(from.getLongtitude()),Double.parseDouble(destination.getLatitude()),Double.parseDouble(destination.getLongtitude()),'K');
        FlightOrder newOrder = new FlightOrder(from,destination,daysToExpiration,amountOfPassengers,prize,penalty,distance);
        availableFlightOrders.add(newOrder);
        return newOrder;
    }

    private static Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2, char unit) {
        Double theta = lon1 - lon2;
        Double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
