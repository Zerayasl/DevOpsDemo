package ch.zhaw.iwi.devops.demo;

//Ideen-Quelle: Alltagsbeispiel Parkgebuehren.
//Requirements: Bis 30 Minuten gratis. Ab 31 Minuten kostet die erste angefangene Stunde 2.0 CHF. Negative Minuten sind ungueltig.
//Komplexität: Grenzwerte, Preislogik und Fehlerfall.

public class Pricing {

    private static final int FREE_MINUTES = 30;
    private static final int FIRST_TIER_MAX = 120;
    private static final double FREE_PRICE = 0.0;
    private static final double FIRST_TIER_PRICE = 2.0;
    private static final double SECOND_TIER_PRICE = 5.0;


    public double calculateFee(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("minutes must not be negative");
        }

        if (minutes <= FREE_MINUTES) {
            return FREE_PRICE;
        }
        if (minutes <= FIRST_TIER_MAX) {
            return FIRST_TIER_PRICE;
        }

        return SECOND_TIER_PRICE;
    }
}


