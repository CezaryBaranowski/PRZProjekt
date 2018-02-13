package Application;

import Model.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

// Singleton
public class Simulation {

    private static Double balance;
    private static ArrayList<Plane> boughtPlanes = new ArrayList<Plane>();
    private static ArrayList<Plane> availablePlanes = new ArrayList<Plane>();
    private static ArrayList<FlightOrder> availableFlightOrders = new ArrayList<FlightOrder>();
    private static ArrayList<FlightOrder> takenFlightOrders = new ArrayList<FlightOrder>();
    private static Airport startAirport = Application.getAirports().get(42);

    private static Simulation simulation = null;
    protected Simulation()
    {
        balance = 0.0;
    }
    public static Simulation getInstance()
    {
        if(simulation == null){
            simulation = new Simulation();
        }
        return simulation;
    }

    public void runSimulation()
    {
        generateStartOrders(startAirport);
        generateStartPlanes();
    }

    public static Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public static ArrayList<FlightOrder> getAvailableFlightOrders()
    {
        return availableFlightOrders;
    }

    public static ArrayList<Plane> getAvailablePlanes()
    {
        return availablePlanes;
    }

    public static void generateStartPlanes()
    {
       boughtPlanes.add(PlaneGenerator.generatePlane());
       boughtPlanes.add(PlaneGenerator.generatePlane());
       availablePlanes.add(PlaneGenerator.generatePlane());
       availablePlanes.add(PlaneGenerator.generatePlane());
    }

    public static void generateStartOrders(Airport ap)
    {
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
    }

    public static void updateDailyAvailableOrders()
    {
        for(FlightOrder fo : availableFlightOrders)
        {
            fo.setDaysToExpiration(fo.getDaysToExpiration()-1);
            if(fo.getDaysToExpiration()<1) availableFlightOrders.remove(fo);
        }
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
    }

    public static void updateDailyAvailablePlanes()
    {
        availablePlanes.add(PlaneGenerator.generatePlane());
    }


}
