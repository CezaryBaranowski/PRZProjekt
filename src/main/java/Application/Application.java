package Application;

import Model.Airport;
import Model.FlightOrder;
import Model.Plane;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class Application {

    private static ArrayList<Airport> airports;
    private static ArrayList<Plane> planes;
    private static Vector <String> airportHeaders;
    private static Vector <String> planeHeaders;
    private static Vector <String> ordersHeaders;
    private Simulation sim;

    public Application() {

        airports = new ArrayList<Airport>();
        planes = new ArrayList<Plane>();
        airportHeaders = new Vector<String>();
        planeHeaders = new Vector<String>();
        try {
            readDataFromDatabase();
        }
        catch (IOException e){e.printStackTrace();}
        catch (SQLException se ){se.printStackTrace();}

        sim = Simulation.getInstance();
        sim.runSimulation();

        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.initUI();
        });

    }

    public static void main(String[] args) {

        Application app = new Application();
    }

    public void readDataFromDatabase() throws IOException, SQLException {
   /*     SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DBConnector dbc = new DBConnector();
                airports =  dbc.getAirportData();
                planes = dbc.getPlaneData();
                setAirportHeaders();
                setPlaneHeaders();
                sim = Simulation.getInstance();
                sim.runSimulation();

                return null;
            }
        };

        worker.execute();*/

        DBConnector dbc = new DBConnector();
        airports =  dbc.getAirportData();
        planes = dbc.getPlaneData();
        setAirportHeaders();
        setPlaneHeaders();

 /*       try {
            Thread.sleep(3000);
        } catch (InterruptedException ignore) {}*/

    }

    public static ArrayList<Airport> getAirports() {
        return airports;
    }

    public static ArrayList<Plane> getPlanes() {
        return planes;
    }

    public static Void setAirportHeaders()
    {
        airportHeaders.addElement("code");
        airportHeaders.addElement("name");
        airportHeaders.addElement("city");
        airportHeaders.addElement("country");
        airportHeaders.addElement("latitude");
        airportHeaders.addElement("longtitude");
        return null;
    }

    public static Void setPlaneHeaders()
    {
        planeHeaders.addElement("brand");
        planeHeaders.addElement("model");
        planeHeaders.addElement("range");
        planeHeaders.addElement("location");
        planeHeaders.addElement("capacity");
        planeHeaders.addElement("prodyear");
        planeHeaders.addElement("costfactor");
        planeHeaders.addElement("price");
        return null;
    }

    public static Void setOrderHeaders()
    {
        ordersHeaders.addElement("from");
        ordersHeaders.addElement("destination");
        ordersHeaders.addElement("passengers");
        ordersHeaders.addElement("distance");
        ordersHeaders.addElement("prize");
        ordersHeaders.addElement("penalty");
        ordersHeaders.addElement("daystoexpire");
        return null;
    }

    public static Vector<String> getAirportHeaders()
    {
        return airportHeaders;
    }

    public static Vector<String> getPlaneHeaders()
    {
        return planeHeaders;
    }

    public static Vector<String> getOrdersHeaders()
    {
        return ordersHeaders;
    }

    public static Vector getAirportsVectors()
    {
        Vector data = new Vector();
        for(Airport ap: airports)
        {
            Vector<Object> row = new Vector<>();
            row.addElement(ap.getCode());
            row.addElement(ap.getName());
            row.addElement(ap.getCity());
            row.addElement(ap.getCountry());
            row.addElement(ap.getLatitude());
            row.addElement(ap.getLongtitude());
            data.addElement(row);
        }
        return data;
    }

    public static Vector getVectorsFromOrders(ArrayList<FlightOrder> list)
    {
        Vector data = new Vector();
        for(FlightOrder fo: list)
        {
            Vector<Object> row = new Vector<>();
            row.addElement(fo.getFrom());
            row.addElement(fo.getDestination());
            row.addElement(fo.getAmountOfPassengers());
            row.addElement(fo.getDistance());
            row.addElement(fo.getPrize());
            row.addElement(fo.getPenalty());
            row.addElement(fo.getDaysToExpiration());
            data.addElement(row);
        }
        return data;
    }

    public static Vector getVectorsFromPlanes(ArrayList<Plane> list)
    {
        Vector data = new Vector();
        for(Plane p: list)
        {
            Vector<Object> row = new Vector<>();
            row.addElement(p.getBrand());
            row.addElement(p.getModel());
            row.addElement(p.getRange());
            row.addElement(p.getLocation());
            row.addElement(p.getCapacity());
            row.addElement(p.getProductionYear());
            row.addElement(p.getCostFactor());
            row.addElement(p.getPrice());
            data.addElement(row);
        }
        return data;
    }

}