package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

public class Airport implements Serializable {

    private String code;
    private String name;
    private String city;
    private String country;
    private String longtitude;          // dlugosc geograficzna
    private String latitude;

    public Airport()
    {

    };

    public Airport(String code, String name, String city, String country, String longtitude, String latitude) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", longtitude='" + longtitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }
}
