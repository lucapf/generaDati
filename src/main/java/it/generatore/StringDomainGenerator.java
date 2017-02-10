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
public class StringDomainGenerator implements IGenerator {

    String parameters;
    String[] strDomain;
    private static final Random RANDOM = new Random();

    private void validate() throws ValidationException {
    }

    public StringDomainGenerator(String parameters) throws ValidationException {
        this.parameters = parameters;
        strDomain = parameters.split(",");
        validate();

    }

    @Override
    public String get() throws ValidationException {
        return strDomain[RANDOM.nextInt(strDomain.length)];
    }

}
