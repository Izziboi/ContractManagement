package de.ietu.ui;

import de.ietu.text.AppText;
import de.rhistel.logic.ConsoleReader;

/**
 * Diese Klasse enthält Methoden, die den Namen, die Adresse,
 * die Grundstücksfläche und die Anzahl der Etagen von
 * einem Benutzer erhalten.
 */

public class UiHouseManager {

    //Region Methoden

    //Erhaltet Name von dem Benutzer
    public static String giveName() {
        System.out.println(AppText.NAME);
        return ConsoleReader.in.readMandatoryString();
    }


    //Erhaltet Adresse von dem Benutzer
    public static String giveAddress() {
        System.out.println(AppText.CLIENT_ADDRESS);
        return ConsoleReader.in.readMandatoryString();
    }


    //Erhaltet Landfläche von dem Benutzer
    public static double giveLandArea() {
        System.out.println(AppText.CLIENT_LAND_AREA);
        return ConsoleReader.in.readPositivDouble();
    }


    //Erhaltet Anzahl der Etagen von dem Benutzer
    public static int giveNumberOfFloors() {
        System.out.println(AppText.CLIENT_NUMBER_OF_FLOORS);
        return ConsoleReader.in.readPositivInt();
    }


    //Ende der Region Methoden
}
