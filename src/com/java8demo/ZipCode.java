package com.java8demo;

/**
 * Created by dsherric on 10/14/15.
 */
public class ZipCode {

    private String zipCode;
    private String primaryCity;

    public ZipCode(String zipCode, String primaryCity) {
        this.zipCode = zipCode;
        this.primaryCity = primaryCity;
    }

    public static ZipCode createFromCSV(String csv) {
        String[] values = csv.split(",");
        String zipCode = values[0];
        String primaryCity = values[2];

        return new ZipCode(zipCode, primaryCity);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPrimaryCity() {
        return primaryCity;
    }

    public void setPrimaryCity(String primaryCity) {
        this.primaryCity = primaryCity;
    }
}
