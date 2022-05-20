/*
Author: Harrison Miles
Date: 18 Apr 2022
Purpose: A record to store country details
*/
public class Country {
    private String iso3;
    private String continent;
    private String countryName;
    private String nuts;
    private double lat;
    private double lon;

    //Parameters constructor
    public Country (String pIso3, String pContinent, String pCountryName, String pNuts, double pLat, double pLon) {
        iso3        = pIso3;
        continent   = pContinent;
        countryName = pCountryName;
        nuts        = pNuts;
        lat         = pLat;
        lon         = pLon;
    }

    //Copy constructor
    public Country (Country pCountry) {
        iso3        = pCountry.getIso3();
        continent   = pCountry.getContinent();
        countryName = pCountry.getCountryName();
        nuts        = pCountry.getNuts();
        lat         = pCountry.getLat();
        lon         = pCountry.getLon();
    }

    //Default constructor
    public Country() {
        continent   = "DEFAULT";
        countryName = "DEFAULT";
        nuts        = "DEFAULT";
        lat         = 0.000001;
        lon         = 0.000001;
    }

    //Accessor methods
    public String getIso3() {
        return iso3;
    }
    public String getContinent() {
        return continent;
    }
    public String getCountryName() {
        return countryName;
    }
    public String getNuts() {
        return nuts;
    }
    public double getLat() {
        return lat;
    }
    public double getLon() {
        return lon;
    }

    //Mutator methods
    public void setIso3(String pIso3) {
        iso3 = pIso3;
    }
    public void setContinent(String pContinent) {
        continent = pContinent;
    }
    public void setCountryName(String pCountryName) {
        countryName = pCountryName;
    }
    public void setNuts(String pNuts) {
        nuts = pNuts;
    }
    public void setLat(double pLat) {
        lat = pLat;
    }
    public void setLon(double pLon) {
        lon = pLon;
    }

    //toString method
    public String toString() {
        String countryString;
        countryString = "iso3: " + iso3 + 
                        ", Continent: " + continent + 
                        ", Country Name: " + countryName + 
                        ", Nuts: " + nuts + 
                        "\nLatitude: " + lat +
                        "\nLongitude: " + lon;
        return countryString;
    }
    //No use for isEqual method
}
