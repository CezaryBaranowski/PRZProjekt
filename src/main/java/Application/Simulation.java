package Application;

import API.Weather;
import Model.*;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Simulation {

    private Integer balance;
    private ArrayList<Plane> boughtPlanes = new ArrayList<Plane>();
    private ArrayList<Plane> availablePlanes = new ArrayList<Plane>();
    private ArrayList<FlightOrder> availableFlightOrders = new ArrayList<FlightOrder>();
    private ArrayList<FlightOrder> takenFlightOrders = new ArrayList<FlightOrder>();
    private Airport startAirport = new Airport();//Application.getAirports().get(42);

    private Integer day;

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

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer bal) {
        balance = bal;
    }

    public Integer getDay() { return day; }

    public void setDay(Integer day) { Simulation.getInstance().day = day; }

    public ArrayList<FlightOrder> getAvailableFlightOrders()
    {
        return availableFlightOrders;
    }

    public ArrayList<Plane> getAvailablePlanes()
    {
        return availablePlanes;
    }

    public ArrayList<Plane> getBoughtPlanes()
    {
        return boughtPlanes;
    }

    public ArrayList<FlightOrder> getTakenFlightOrders()
    {
        return takenFlightOrders;
    }

    public void setStartAirport()
    {
        startAirport = Application.getAirports().get(42);
    }

    public void generateStartPlanes()
    {
       boughtPlanes.add(PlaneGenerator.generatePlane());
       boughtPlanes.add(PlaneGenerator.generatePlane());
       availablePlanes.add((PlaneGenerator.generatePlane()));
       availablePlanes.add((PlaneGenerator.generatePlane()));
    }

    public void generateStartOrders(Airport ap)
    {
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder(ap));
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
        availableFlightOrders.add(FlightOrderGenerator.generateOrder());
    }

    public void dailyUpdate()
    {
        day++;
        updateDailyAvailableOrders();
        updateDailyAvailablePlanes();
        updateDailyTakenOrders();
    }

    public void updateDailyAvailableOrders()
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

    public void updateDailyTakenOrders()
    {
        for(FlightOrder fo : takenFlightOrders)
        {
            fo.setDaysToExpiration(fo.getDaysToExpiration()-1);
        }
        takenFlightOrders.removeIf(new Predicate<FlightOrder>() {
            @Override
            public boolean test(FlightOrder flightOrder) {
                if(flightOrder.getDaysToExpiration()<1) {
                    Simulation.getInstance().setBalance(Simulation.getInstance().getBalance() - flightOrder.getPenalty());
                    return true;
                }
                else return false;
            }
        });

    }

    public void updateDailyAvailablePlanes()
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
                    Simulation.getInstance().balance += p.getCurrentlyAssignedOrder().getPrize();
                    Simulation.getInstance().balance -= calculateFlyCost(p.getCurrentlyAssignedOrder(),p.getCostFactor());
                    Simulation.getInstance().takenFlightOrders.remove(p.getCurrentlyAssignedOrder());
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
