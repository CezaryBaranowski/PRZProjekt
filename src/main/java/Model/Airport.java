package Model;

public class Airport {

    private String city;
    private String country;
    private String longtitude;          // dlugosc geograficzna
    private String latitude;

    public Airport(String city, String country, String longtitude, String latitude) {
        this.city = city;
        this.country = country;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

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

}
