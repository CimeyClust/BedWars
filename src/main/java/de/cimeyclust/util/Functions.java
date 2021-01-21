package de.cimeyclust.util;

import de.cimeyclust.BedWars;

public class Functions
{
    private BedWars plugin;

    public Functions(BedWars plugin) {
        this.plugin = plugin;
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
