package Application;

import Model.Airport;
import Model.Plane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import javax.swing.*;

public class Application {

    private static ArrayList<Airport> airports;
    private static ArrayList<Plane> planes;
    private static Vector <String> airportHeaders;
    private static Vector <String> planeHeaders;
    private Vector<String> boughtPlanes;
    private Vector<String> takenOrders;

    public Application() {

        airports = new ArrayList<Airport>();
        planes = new ArrayList<Plane>();
        airportHeaders = new Vector<String>();
        boughtPlanes = new Vector<String>();
        readDataFromDatabase();

        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.initUI();
        });

    }

    public static void main(String[] args) {

        Application app = new Application();
    }

    public void readDataFromDatabase() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                DBConnector dbc = new DBConnector();
                airports =  dbc.getAirportData();
                planes = dbc.getPlaneData();
                setAirportHeaders();
                return null;
            }
        };

     /*   try {
            Thread.sleep(3000);
        } catch (InterruptedException ignore) {}*/

        worker.execute();

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
        planeHeaders.addElement("productionyear");
        planeHeaders.addElement("capacity");
        planeHeaders.addElement("range");
        planeHeaders.addElement("costfactor");
        planeHeaders.addElement("price");
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
}