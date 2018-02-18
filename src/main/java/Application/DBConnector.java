package Application;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import Model.Airport;
import Model.Plane;

public class DBConnector {

    private String provider;
    private String url;
    private String login;
    private String pass;
    private Connection connection = null;
    private static Statement statement = null;

    public DBConnector()
    {
        try
        {
            connection = getConnection();
            List<Airport> ap = getAirportData();
            for(Airport a: ap)
            {
                System.out.println(a.toString());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
    }

    public Connection getConnection() throws IOException, SQLException
    {
        Properties properties = new Properties();
        InputStream input = null;
        input = new FileInputStream("properties");

        properties.load(input);
        provider = properties.getProperty("driver");
        url = properties.getProperty("adress");
        login = properties.getProperty("user");
        pass = properties.getProperty("password");

        System.out.println("Połaczone");
        return DriverManager.getConnection(url, login, pass);

    }

    public ArrayList<Airport> getAirportData() throws IOException, SQLException {

        statement = connection.createStatement();

        final String selectTableSQL = "Select *" +
                "from airport  " +
                "order by airport.code ";

        ArrayList<Airport> airports = new ArrayList<Airport>();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        while (rs.next()) {
            Airport airport = new Airport(rs.getString("code"),rs.getString("name"),
                    rs.getString("city"),rs.getString("country"),rs.getString("longtitude"),
                    rs.getString("latitude"));
            airports.add(airport);
        }

        return airports;
    }


    public ArrayList<Plane> getPlaneData() throws IOException, SQLException {

        statement = connection.createStatement();

        final String selectTableSQL = "Select *" +
                "from airplane  " +
                "order by airplane.brand ";

        ArrayList<Plane> planes = new ArrayList<Plane>();
        ResultSet rs = statement.executeQuery(selectTableSQL);
        while (rs.next()) {
            Plane plane = new Plane(rs.getString("brand"),rs.getString("model"),
                    rs.getInt("prodstart"),rs.getInt("prodend"),rs.getInt("capacity"),
                    rs.getInt("range"), rs.getDouble("costfactor"),rs.getInt("price"));
            planes.add(plane);
        }

        return planes;
    }
}