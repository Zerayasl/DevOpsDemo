package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

//Ideen-Quelle: Alltagsbeispiel Parkgebuehren.
//Requirements: Bis 30 Minuten gratis. Ab 31 Minuten kostet die erste angefangene Stunde 2.0 CHF. Negative Minuten sind ungueltig.
//Komplexität: Grenzwerte, Preislogik und Fehlerfall.

public class PricingTest {

    @Test
    void returnsZeroForThirtyMinutesOrLess() {
        Pricing calculator = new Pricing();
        assertEquals(0.0, calculator.calculateFee(30));
    }

    @Test
    void rejectsNegativeMinutes() {
        Pricing calculator = new Pricing();
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateFee(-1));
    }

     @Test
    void chargesTwoFrancsForStartedHourAfterFreePeriod() {
        Pricing calculator = new Pricing();
        assertEquals(2.0, calculator.calculateFee(31));
    }

    @Test
    void chargesAdditionalStartedHoursAfterTwoHours() {
        Pricing calculator = new Pricing();
        assertEquals(5.0, calculator.calculateFee(121));
    }
}



//     @Test
//     void capsFeeAtDailyMaximum() {
//         Pricing calculator = new Pricing();
//         assertEquals(18.0, calculator.calculateFee(900));
//     }