/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.util.Random;

/**
 *
 * @author palancaf
 */
public class IntRandomGenerator implements IGenerator {

    private static final Random RANDOM = new Random();
    private int lowerBound = 0;
    private int upperBound = Integer.MAX_VALUE;

    public IntRandomGenerator(String parameters) throws ValidationException {
        if (parameters == null) {
            return;
        }
        String[] bounds = parameters.split("\\-");
        try {
            if (bounds.length == 2) {
                lowerBound = Integer.valueOf(bounds[0]);
                upperBound = Integer.valueOf(bounds[1]);
            } else {
                upperBound = Integer.valueOf(parameters);
            }
        } catch (NumberFormatException nfe) {
            throw new ValidationException("intervallo " + parameters + " non valido. Valori ammissibili sono ad esempio: 10 (da 0 a 10 ) e 10-100 (da 10 a 100) oppure nulla e il numero è casuale tra 0 e Integer.MaxValue");
        }
    }

    @Override
    public String get() throws ValidationException {
        int intResult = 0;
        while ((intResult = RANDOM.nextInt(upperBound)) < lowerBound);
        return String.valueOf(intResult);
    }

}
