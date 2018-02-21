package API;

import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class Weather {

    public static final String url = "http://api.openweathermap.org/data/2.5/weather?APPID=0f1a6667fb936ff89f7c0ac7332de088&q=";
    private static String conditions;

    public static String getConditions(String city)
    {
        String output = "";
        city = city.replace(" ","%20");

        try {

            HttpClient httpClient = HttpClients.createDefault();

            HttpGet getRequest = new HttpGet(
                    url + city);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("Blad odpowiedzi");
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            StringBuilder sbd = new StringBuilder();
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                sbd.append(output);

            }
            System.out.println(sbd);
            output = sbd.toString();

            JSONObject weatherObject = new JSONObject(output);
            JSONArray weatherArray = weatherObject.getJSONArray("weather");
            weatherObject = weatherArray.getJSONObject(0);
            conditions = weatherObject.get("main").toString();
            output = weatherObject.toString();

            httpClient.getConnectionManager().shutdown();

        } catch (ClientProtocolException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException ex)
        {
            ex.printStackTrace();
        }

        return conditions;
    }
}

