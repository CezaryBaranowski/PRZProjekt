package Application;

import API.Weather;
import Model.*;

import java.util.ArrayList;
import java.util.function.Predicate;

// Singleton
public class Simulation {

    private static Integer balance;
    private static ArrayList<Plane> boughtPlanes = new ArrayList<Plane>();
    private static ArrayList<Plane> availablePlanes = new ArrayList<Plane>();
    private static ArrayList<FlightOrder> availableFlightOrders = new ArrayList<FlightOrder>();
    private static ArrayList<FlightOrder> takenFlightOrders = new ArrayList<FlightOrder>();
    private static Airport startAirport = new Airport();//Application.getAirports().get(42);

    private static Integer day;

    private static Simulation simulation = null;

    protected Simulation()
    {
        balance = Integer.parseInt(Application.getProperties().getProperty("startmoney")) ;
        day = 0;
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
        if(Application.getAirports().size()==49) {
            day = 1;
            setStartAirport();
            generateStartOrders(startAirport);
            generateStartPlanes();

        }
    }

    public static Integer getBalance() {
        return balance;
    }

    public static void setBalance(Integer bal) {
        balance = bal;
    }

    public static Integer getDay() { return day; }

    public static void setDay(Integer day) { Simulation.day = day; }

    public static ArrayList<FlightOrder> getAvailableFlightOrders()
    {
        return availableFlightOrders;
    }

    public static ArrayList<Plane> getAvailablePlanes()
    {
        return availablePlanes;
    }

    public static ArrayList<Plane> getBoughtPlanes()
    {
        return boughtPlanes;
    }

    public static ArrayList<FlightOrder> getTakenFlightOrders()
    {
        return takenFlightOrders;
    }

    public static void setStartAirport()
    {
        startAirport = Application.getAirports().get(42);
    }

    public static void generateStartPlanes()
    {
       boughtPlanes.add(PlaneGenerator.generatePlane());
       boughtPlanes.add(PlaneGenerator.generatePlane());
       availablePlanes.add((PlaneGenerator.generatePlane()));
       availablePlanes.add((PlaneGenerator.generatePlane()));
    }

    public static void generateStartOrders(Airport ap)
    {
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
    }

    public static void dailyUpdate()
    {
        day++;
        updateDailyAvailableOrders();
        updateDailyAvailablePlanes();
        updateDailyTakenOrders();
    }

    public static void updateDailyAvailableOrders()
    {
        for(FlightOrder fo : availableFlightOrders)
        {
            fo.setDaysToExpiration(fo.getDaysToExpiration()-1);
        }
        availableFlightOrders.removeIf(new Predicate<FlightOrder>() {
            @Override
            public boolean test(FlightOrder flightOrder) {
                if(flightOrder.getDaysToExpiration()<1)
                return true;
                else return false;
            }
        });
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
    }

    public static void updateDailyTakenOrders()
    {
        for(FlightOrder fo : takenFlightOrders)
        {
            fo.setDaysToExpiration(fo.getDaysToExpiration()-1);
        }
        takenFlightOrders.removeIf(new Predicate<FlightOrder>() {
            @Override
            public boolean test(FlightOrder flightOrder) {
                if(flightOrder.getDaysToExpiration()<1) {
                    Simulation.setBalance(Simulation.getBalance() - flightOrder.getPenalty());
                    return true;
                }
                else return false;
            }
        });

    }

    public static void updateDailyAvailablePlanes()
    {
        availablePlanes.add(PlaneGenerator.generatePlane());

        for(Plane p : boughtPlanes)
        {
            if(p.getAvailable() == false)
            {
                if(!p.getLocation().equals(p.getCurrentlyAssignedOrder().getFrom()))
                p.setLocation(p.getCurrentlyAssignedOrder().getFrom());
                else {
                    p.setLocation(p.getCurrentlyAssignedOrder().getDestination());
                    Simulation.balance += p.getCurrentlyAssignedOrder().getPrize();
                    Simulation.balance -= calculateFlyCost(p.getCurrentlyAssignedOrder(),p.getCostFactor());
                    Simulation.takenFlightOrders.remove(p.getCurrentlyAssignedOrder());
                    p.setCurrentlyAssignedOrder(null);
                    p.setAvailable(true);
                }
            }
        }
    }

    public static Integer calculateFlyCost(FlightOrder order, double costFactor)
    {
        String cond = Weather.getConditions(order.getDestination().getCity());
        if(cond.equals("Rain"))
            costFactor += 0.1;
        if(cond.equals("Fog"))
            costFactor += 0.2;
        Double totalCost = (order.getDistance()*costFactor*100.0);
        return totalCost.intValue();
    }

}
