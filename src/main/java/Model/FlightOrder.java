package Model;

import java.util.Date;

public class FlightOrder {

    private String from;
    private String destination;
    private Date fromDate;
    private Date toDate;
    private int amountOfPassengers;
    private int prize;
    private int penalty;
    private Double distance;

    public FlightOrder(String from, String destination, Date fromDate, Date toDate, int amountOfPassengers, int prize, int penalty, Double distance) {
        this.from = from;
        this.destination = destination;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.amountOfPassengers = amountOfPassengers;
        this.prize = prize;
        this.penalty = penalty;
        this.distance = distance;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
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
