package Model;

import Application.Application;
import org.apache.commons.math.util.MathUtils;
import org.hibernate.criterion.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class FlightOrderGenerator {

    ArrayList<FlightOrder> flightOrders;
    ArrayList<FlightOrder> takenFlightOrders;

    public FlightOrder generateOrder()
    {
        int drawnNumber;

        Airport from;
        Airport destination;
        int daysToExpiration;
        int amountOfPassengers;
        int prize;
        int penalty;
        Double distance;

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
        flightOrders.add(newOrder);
        return newOrder;
    }

    private Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2, char unit) {
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
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
