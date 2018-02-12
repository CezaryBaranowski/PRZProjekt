package Application;

import Model.Airport;

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

    private static ArrayList<Airport>airports;
    private static Vector <String> airportHeaders;

    public Application() {

        airports = new ArrayList<Airport>();
        airportHeaders = new Vector<String>();
        readDataFromDatabase();

        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.initUI();
        });

      //  readDataFromDatabase();
     //   setAirportHeaders();

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

    public static Vector<String> getAirportHeaders()
    {
        return airportHeaders;
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