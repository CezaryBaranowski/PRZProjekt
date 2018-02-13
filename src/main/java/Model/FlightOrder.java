package Model;

import java.util.Date;

public class FlightOrder {

    private Airport from;
    private Airport destination;
    private int daysToExpiration;
    private int amountOfPassengers;
    private int prize;
    private int penalty;
    private Double distance;

    public FlightOrder(Airport from, Airport destination, int daysToExpiration, int amountOfPassengers, int prize, int penalty, Double distance) {
        this.from = from;
        this.destination = destination;
        this.daysToExpiration = daysToExpiration;
        this.amountOfPassengers = amountOfPassengers;
        this.prize = prize;
        this.penalty = penalty;
        this.distance = distance;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getDestination() {
        return destination;
    }

    public void setDestination(Airport destination) {
        this.destination = destination;
    }

    public int getDaysToExpiration() {
        return daysToExpiration;
    }

    public void setDaysToExpiration(int daysToExpiration) {
        this.daysToExpiration = daysToExpiration;
    }

    public int getAmountOfPassengers() {
        return amountOfPassengers;
    }

    public void setAmountOfPassengers(int amountOfPassengers) {
        this.amountOfPassengers = amountOfPassengers;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
