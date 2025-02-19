package de.ietu.model;

import de.ietu.text.AppText;

/**
 * Dies ist die Klasse, die das Haus erstellt.
 * Sie enthält alle Hausattribute, die vom Kunden benötigt werden.
 */

public class House {

//Region Attribute
    protected String clientName;
    protected String clientAddress;
    protected double landArea;
    protected int numberOfFloors;
//Ende der Region Attribute

//Region Konstruktoren
    public House(String csvStream) {
        fetchFromCsv(csvStream); //Die Methode 'fetchFromCsv()' unten
    }

    public House(String clientName, String clientAddress, double landArea, int numberOfFloors) {
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.landArea = landArea;
        this.numberOfFloors = numberOfFloors;
    }

//Ende der Region Konstruktoren

//Region Methoden

    //Ordnet die Daten mit Komma, um csv vorzubereiten
    public String csvDataLine() {
        return clientName + AppText.DELIMITER + clientAddress + AppText.DELIMITER +
                landArea + AppText.DELIMITER + numberOfFloors + "\n";
    }


    //Erhaltet Daten, nachdem der 'FileReader' sie aus der csv-Datei gelesen hat
    public void fetchFromCsv(String csvStream) {
        String[] specData = csvStream.split(AppText.DELIMITER);
        clientName = specData[0];
        clientAddress = specData[1];
        landArea = Double.parseDouble(specData[2]);
        numberOfFloors = Integer.parseInt(specData[3]);
    }

    //Getters und Setters
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public double getLandArea() {
        return landArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    //Test Drucker Methode
    public void printCustomerSpecs() {
        System.out.printf("\nName: %s\tAddress: %s" +
                "\tLand Area: %s\t\tNumber of floors: %s\n",
                clientName, clientAddress, landArea, numberOfFloors);
    }

//Ende der Region Methoden
}
