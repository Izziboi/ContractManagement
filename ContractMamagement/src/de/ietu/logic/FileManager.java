package de.ietu.logic;

import de.ietu.model.House;
import de.ietu.text.AppText;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * <ul>
 *     <li>Diese Klasse enthält die Schreiber- und Leser-Methoden.</li>
 *     <li>Sie umsetzt die Schreiben und Lesen Tätigkeiten mit einem Singleton.</li>
 * </ul>
 *
 */

public class FileManager {

    //Region Konstanten
    private static final String FILE_ADDRESS = "resources/houseSpecs.csv";
    //Ende der Region Konstanten

    //Region Attribute
    private static FileManager instance;
    //Ende der Region Attribute

    //Region Konstruktoren
    private FileManager() {
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    //Singleton Instanz
    public static synchronized FileManager getInstance() {
        if (instance == null) instance = new FileManager();
        return instance;
    }

    //Übergibt Daten an der 'houseSpecs.csv' Datei
    public synchronized void sendDataToFile(List<House> houses) {

        try (
                FileWriter streamWriter = new FileWriter(FILE_ADDRESS, StandardCharsets.UTF_8); //Schreiber Instanz
                BufferedWriter outputStream = new BufferedWriter(streamWriter);
        ) {
            for (House house : houses) {
                outputStream.write(house.csvDataLine()); //Die 'csvDataLine()' von der House Klasse ist hier eingefügt
            }

        } catch (Exception e) {
            System.out.println(AppText.DATA_SAVING_NOT_SUCCESSFUL);
        }
    }

    //Ladet Daten von der 'houseSpecs.csv' Datei
    public synchronized List<House> retrieveClientData() {
        List<House> recentHouse = new ArrayList<>();

        try (FileReader streamReader = new FileReader(FILE_ADDRESS, StandardCharsets.UTF_8);
             BufferedReader inputStream = new BufferedReader(streamReader);
        ){
            String recentCsvRecord;
            while ((recentCsvRecord = inputStream.readLine()) != null) {

                House house = new House(recentCsvRecord); //Der 'House' Konstruktor, der 'fetchFromCsv()' Methode aufruft
                recentHouse.add(house); //List 'recentHouse' übernimmt die Eigenschaften des 'House' Konstruktors
            }

        } catch (Exception e) {
            System.out.println(AppText.DATA_FETCHING_NOT_SUCCESSFUL);
        }
        return recentHouse;
    }
//Ende der Region Methoden
}
