package Application;

import Exceptions.*;
import Language.Language;
import Model.Airport;
import Model.FlightOrder;
import Model.Plane;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    private static ArrayList<Plane> planes = new ArrayList<Plane>();
    private static Vector <String> airportHeaders = new Vector<String>();
    private static Vector <String> planeHeaders = new Vector<String>();
    private static Vector <String> ordersHeaders = new Vector<String>();
    private Simulation sim;

    private static Properties properties = new Properties();
    private static final String pathLanguageEN = "src/main/resources/dictionary/languageEN.xml";
    private static final String pathLanguagePL = "src/main/resources/dictionary/languagePL.xml";;

    private static Language languageEN;
    private static Language languagePL;

    private static Language activeLanguagePack;

    public Application() {

        try {
            readProperties();
            if(Integer.parseInt((properties.getProperty("frameheight"))) > (Integer.parseInt(properties.getProperty("framewidth"))))
            {
                throw new BadFrameSizeException("Invalid framesize, check properties for details");
            }
            logger.info("Properties file loaded succesfully ");
        }
        catch(ReadPropertiesException | BadFrameSizeException rpe)
        {
            System.out.println(rpe.getMessage());
            rpe.printStackTrace();
            logger.error("Error while reading properties file");
        }

        try {
            loadLanguagePackFromXml();
            logger.info("Language packs loaded succesfully ");
        }
        catch(XMLReadException | LanguageException le)
        {
            le.getMessage();
            logger.error("Error while reading language packs");
        }

        activeLanguagePack = languagePL;

        sim = Simulation.getInstance();
        sim.runSimulation();
        logger.info("Simulation started");

        EventQueue.invokeLater(() -> {
            GUI gui = new GUI();
            gui.initUI();
        });

        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    readDataFromDatabase();
                    logger.info("Succesfully loaded data from database");
                }
                catch (InvalidDatabaseDataValueException ide)
                {
                    System.out.println( ide.getMessage());
                    logger.error("Database data read error");
                }
                return null;
            }
        };
        worker.execute();

    }

    public static void main(String[] args) {

        Application app = new Application();
    }

    public void readDataFromDatabase() throws IOException, SQLException, InvalidDatabaseDataValueException {

        DBConnector dbc = new DBConnector();
        airports =  dbc.getAirportData();
        planes = dbc.getPlaneData();
        if(!((airports.size()>0)&&(planes.size()>0)))
        throw new InvalidDatabaseDataValueException("Failed to succesfully load all the data from database");
        setAirportHeaders();
        setPlaneHeaders();
        setOrderHeaders();

    }
    public static Properties getProperties() { return properties; }

    public static Language getActiveLanguagePack() { return activeLanguagePack; }

    public static void setActiveLanguagePack(Language activeLanguagePack) { Application.activeLanguagePack = activeLanguagePack; }

    public static ArrayList<Airport> getAirports() { return airports; }

    public static ArrayList<Plane> getPlanes() { return planes; }

    public static Vector<String> getAirportHeaders()
    {
        return airportHeaders;
    }

    public static Vector<String> getPlaneHeaders()
    {
        return planeHeaders;
    }

    public static Vector<String> getOrdersHeaders() { return ordersHeaders; }

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

    public void readProperties() throws ReadPropertiesException
    {
        try {
            InputStream input = null;
            input = new FileInputStream("properties");
            properties.load(input);
        }
        catch (IOException ioe)
        {
            throw new ReadPropertiesException("Error while loading properties file");
        }
    }

    public void loadLanguagePackFromXml() throws XMLReadException, LanguageException {

        XmlMapper mapper = new XmlMapper();
        byte[] xml = null;
        try
        {
            xml = Files.readAllBytes(Paths.get(pathLanguagePL));
        }
        catch (IOException ioe)
        {
            throw new XMLReadException("Error while reading dictionary with polish language");
        }
        try
        {
            languagePL = mapper.readValue(new String(xml, StandardCharsets.UTF_8), Language.class);
            logger.info("Polish pack loaded");
        }
        catch (Exception e)
        {
            logger.error("Error while parsing dictionary of polish language");
            throw new LanguageException("Error while parsing dictionary of polish language");
        }
        try
        {
            xml = Files.readAllBytes(Paths.get(pathLanguageEN));
        }
        catch (IOException ioe)
        {
            throw new XMLReadException("Error while reading dictionary with english language");
        }
        try
        {
            languageEN = mapper.readValue(new String(xml, StandardCharsets.UTF_8), Language.class);
            logger.info("English pack loaded");
        }
        catch (Exception e)
        {
            logger.error("Error while parsing dictionary of english language");
            throw new LanguageException("Error while parsing dictionary of english language");
        }
    }

    public static void changeActiveLanguagePack()
    {
        if(getActiveLanguagePack().equals(languageEN))
            setActiveLanguagePack(languagePL);
        else setActiveLanguagePack(languageEN);
        logger.info("Changed language pack");
    }

}