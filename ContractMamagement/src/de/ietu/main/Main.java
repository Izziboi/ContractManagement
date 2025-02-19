package de.ietu.main;

import de.ietu.model.House;
import de.ietu.ui.HouseManager;

/**
 * Diese App hilft einer Baufirma, von ihrem Kunden Informationen
 * über die Art des Hauses zu erhalten, das der Kunde wünscht.
 * Der Nutzer der App wählt eine Funktion aus dem Hauptmenü aus,
 * indem er nur die entsprechende Nummer eingibt.
 * Dann folgt er der Aufforderung, die erforderlichen Informationen einzugeben.
 */

public class Main {


    public static void main(String[] args) {
        HouseManager houseManager = new HouseManager();
        houseManager.start();

    }




}