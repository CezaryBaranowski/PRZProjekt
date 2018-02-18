package Application;

import Language.Language;
import Model.Airport;
import Model.FlightOrder;
import Model.Plane;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    private static final String pathLanguageEN = "src/main/resources/dictionary/languageEN.xml";
    private static final String pathLanguagePL = "src/main/resources/dictionary/languagePL.xml";;

    private static Language languageEN;
    private static Language languagePL;

    private static Language activeLanguagePack;

    public Application() {

        airports = new ArrayList<Airport>();
        planes = new ArrayList<Plane>();
        airportHeaders = new Vector<String>();
        planeHeaders = new Vector<String>();
        ordersHeaders = new Vector<String>();

        loadLanguagePackFromXml();
        activeLanguagePack = languagePL;

        sim = Simulation.getInstance();
        sim.runSimulation();

        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.initUI();
        });

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                readDataFromDatabase();
                return null;
            }
        };
        worker.execute();

    }

    public static void main(String[] args) {

        Application app = new Application();
    }

    public void readDataFromDatabase() throws IOException, SQLException {

        DBConnector dbc = new DBConnector();
        airports =  dbc.getAirportData();
        planes = dbc.getPlaneData();
        setAirportHeaders();
        setPlaneHeaders();
        setOrderHeaders();

 /*       try {
            Thread.sleep(3000);
        } catch (InterruptedException ignore) {}*/

    }

    public static Language getActiveLanguagePack() { return activeLanguagePack; }

    public static void setActiveLanguagePack(Language activeLanguagePack) { Application.activeLanguagePack = activeLanguagePack; }

    public static ArrayList<Airport> getAirports() { return airports; }

    public static ArrayList<Plane> getPlanes() { return planes; }

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
        planeHeaders.addElement(activeLanguagePack.getNumberOfPlane());
        planeHeaders.addElement(activeLanguagePack.getBrand());
        planeHeaders.addElement(activeLanguagePack.getModel());
        planeHeaders.addElement(activeLanguagePack.getRange());
        planeHeaders.addElement(activeLanguagePack.getLocation());
        planeHeaders.addElement(activeLanguagePack.getCapacity());
        planeHeaders.addElement(activeLanguagePack.getProdyear());
        planeHeaders.addElement(activeLanguagePack.getCostfactor());
        planeHeaders.addElement(activeLanguagePack.getPrice());
        planeHeaders.addElement(activeLanguagePack.getAvailable());
        return null;
    }

    public static Void setOrderHeaders()
    {
        ordersHeaders.addElement(activeLanguagePack.getNumberOfOrder());
        ordersHeaders.addElement(activeLanguagePack.getFrom());
        ordersHeaders.addElement(activeLanguagePack.getDestination());
        ordersHeaders.addElement(activeLanguagePack.getPassengers());
        ordersHeaders.addElement(activeLanguagePack.getDistance());
        ordersHeaders.addElement(activeLanguagePack.getPrize());
        ordersHeaders.addElement(activeLanguagePack.getPenalty());
        ordersHeaders.addElement(activeLanguagePack.getDaysToExpire());
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

    public static Vector<String> getOrdersHeaders() { return ordersHeaders; }

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
        Integer i = 1;
        for(FlightOrder fo: list)
        {
            Vector<Object> row = new Vector<>();
            row.addElement(i.toString());
            row.addElement(fo.getFrom().getCity());
            row.addElement(fo.getDestination().getCity());
            row.addElement(fo.getAmountOfPassengers());
            row.addElement(fo.getDistance());
            row.addElement(fo.getPrize());
            row.addElement(fo.getPenalty());
            row.addElement(fo.getDaysToExpiration());
            data.addElement(row);
            i++;
        }
        return data;
    }

    public static Vector getVectorsFromPlanes(ArrayList<Plane> list)
    {
        Vector data = new Vector();
        Integer i = 1;
        for(Plane p: list)
        {
            Vector<Object> row = new Vector<>();
            row.addElement(i.toString());
            row.addElement(p.getBrand());
            row.addElement(p.getModel());
            row.addElement(p.getRange());
            row.addElement(p.getLocation().getCity());
            row.addElement(p.getCapacity());
            row.addElement(p.getProductionYear());
            row.addElement(p.getCostFactor());
            row.addElement(p.getPrice());
            row.addElement(p.getAvailableToString());
            data.addElement(row);
            i++;
        }
        return data;
    }


    public void loadLanguagePackFromXml() {

        XmlMapper mapper = new XmlMapper();
        byte[] xml = null;
        try {
            xml = Files.readAllBytes(Paths.get(pathLanguagePL));
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        try {
            languagePL = mapper.readValue(new String(xml, StandardCharsets.UTF_8), Language.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try {
            xml = Files.readAllBytes(Paths.get(pathLanguageEN));
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        try {
            languageEN = mapper.readValue(new String(xml, StandardCharsets.UTF_8), Language.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}