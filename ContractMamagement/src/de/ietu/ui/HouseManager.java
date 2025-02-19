package de.ietu.ui;

import de.ietu.logic.FileManager;
import de.ietu.model.House;
import de.ietu.text.AppText;
import de.rhistel.logic.ConsoleReader;

import java.util.List;

/**
 * Diese Klasse dient zum Anlegen neuer Hausdetails, zum Anzeigen der Details,
 * zum Aktualisieren der Details und zum Löschen der Details.
 */

public class HouseManager {

    //Region Attribute
    private House house;
    private List<House> houseAttributes;
    //Ende der Region Attribute

    //Region Konstruktoren
    public HouseManager() {
        houseAttributes = FileManager.getInstance().retrieveClientData();
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    //beginnt die App
    public void start() {
        printApplicationName();
        controlChoice();
    }


    //Druckt den Namen der App
    private void printApplicationName() {
        System.out.println(AppText.WELCOME);
    }

    //Druckt das Hauptmenü der App
    private void printMainMenu() {
        System.out.println(AppText.MENU);
    }


    //Enthält die Optionen zur Auswahl der Maßnahmen
    private void controlChoice() {
        boolean runApp = true;
        do {
            printMainMenu();
            switch (ConsoleReader.in.readPositivInt()) {
                case AppText.CREATE -> createClientData();
                case AppText.DISPLAY -> {
                    showSavedData();
                    System.out.println(AppText.STILL_WANT_TO_DO_SOMETHING);
                }
                case AppText.UPDATE -> updateClientData();
                case AppText.DELETE -> deleteClientData();
                case AppText.EXIT -> {
                    System.out.println(AppText.APP_BEENDET);
                    runApp = false;
                }
                default -> System.out.println(AppText.WRONG_TASK_CHOICE);
            }

        } while (runApp);
    }


    //Übergibt die eingetragenen Daten an dem Dateischreiber (von FileManager Klasse)
    public void createClientData() {

        System.out.println(AppText.YOU_CHOSE_TO_INPUT_NEW_HOUSE_DETAILS);
        String clientName = UiHouseManager.giveName();

        String address = UiHouseManager.giveAddress();

        double landArea = UiHouseManager.giveLandArea();

        int numberOfFloors = UiHouseManager.giveNumberOfFloors();

        house = new House(clientName, address, landArea, numberOfFloors);
        houseAttributes.add(house); //Übergibt die Daten an der 'houseAttributes' List

        FileManager.getInstance().sendDataToFile(houseAttributes); //Aufruf des Schreibers
        System.out.println(AppText.DATA_SAVED_SUCCESSFULLY);

        System.out.println(AppText.STILL_WANT_TO_DO_SOMETHING);
    }


    //Zeigt die Daten an
    public void showSavedData() {
        System.out.printf(AppText.DATA_FORMAT,
                AppText.INDEX, AppText.CLIENT_NAME, AppText.ADDRESS, AppText.LAND_AREA, AppText.NUMBER_OF_FLOORS);

        for (int i = 0; i < houseAttributes.size(); i++) {

            House csvHouse = houseAttributes.get(i);

            System.out.printf(AppText.DATA_FORMAT,
                    i, csvHouse.getClientName(), csvHouse.getClientAddress(),
                    csvHouse.getLandArea(), csvHouse.getNumberOfFloors());
        }

    }


    //Bearbeitet ausgewählten Daten
    public void updateClientData() {
        System.out.println(AppText.CHOOSE_DATA_TO_UPDATE);

        showSavedData();

        int chosenIndex = ConsoleReader.in.readPositivInt(); //Wählt den Index der Daten zu bearbeiten aus
        House choiceToUpdate = houseAttributes.get(chosenIndex); //Fügt den Index ein, um ganzen Daten auszuwählen

        //Verwendet Setter, Daten zu bearbeiten
        String clientName = UiHouseManager.giveName();
        choiceToUpdate.setClientName(clientName);

        String address = UiHouseManager.giveAddress();
        choiceToUpdate.setClientAddress(address);

        double landArea = UiHouseManager.giveLandArea();
        choiceToUpdate.setLandArea(landArea);

        int numberOfFloors = UiHouseManager.giveNumberOfFloors();
        choiceToUpdate.setNumberOfFloors(numberOfFloors);

        FileManager.getInstance().sendDataToFile(houseAttributes); //Aktualisiert die Daten in der csv Datei

        System.out.println(AppText.DATA_UPDATE_SUCCESSFUL);

        System.out.println(AppText.STILL_WANT_TO_DO_SOMETHING);
    }


    //Löscht ausgewählten Daten
    public void deleteClientData(){
        System.out.println(AppText.CHOOSE_DATA_TO_DELETE);

        showSavedData();

        int chosenIndex = ConsoleReader.in.readPositivInt(); //Wählt den Index der Daten zu löschen aus
        House choiceToDelete = houseAttributes.get(chosenIndex); //Fügt den Index ein, um ganzen Daten auszuwählen

        System.out.println(AppText.CONFIRM_DELETE_OF_DATA);
        System.out.printf(AppText.DATA_FORMAT, chosenIndex,
                choiceToDelete.getClientName(), choiceToDelete.getClientAddress(),
                choiceToDelete.getLandArea(), choiceToDelete.getNumberOfFloors());

        if (ConsoleReader.in.readMandatoryAnswerToBinaryQuestion()) {
            houseAttributes.remove(choiceToDelete); //Daten sind von der List 'houseAttributes' gelöscht

            FileManager.getInstance().sendDataToFile(houseAttributes); //Aktualisiert die Daten in der csv Datei

            System.out.println(AppText.DATA_DELETE_SUCCESSFUL);
        }
        else System.out.println(AppText.DATA_DELETE_DECLINED);

        System.out.println(AppText.STILL_WANT_TO_DO_SOMETHING);
    }
    //Ende der Region Methoden

}
