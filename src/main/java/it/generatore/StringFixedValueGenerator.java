/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 *
 * @author palancaf
 */
public class StringFixedValueGenerator implements IGenerator {

    private static final Map<String,String> PARAMETRIZED=new HashMap<>();
    static{
        PARAMETRIZED.put("${BLANK}", "");
    }
    private String value;
    private static final Random RANDOM = new Random();

    private void validate() throws ValidationException {
    }

    public StringFixedValueGenerator(String parameters) throws ValidationException {
        value = PARAMETRIZED.containsKey(parameters)?PARAMETRIZED.get(parameters):parameters;
        

    }

    @Override
    public String get() throws ValidationException {
        return value;
    }

}
