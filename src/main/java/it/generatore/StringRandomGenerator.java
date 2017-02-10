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
class StringRandomGenerator implements IGenerator {

    private static final String ALFABETO_MAIUSCOLO = "ABCDEFGHILMNOPQRSTUVZ";
    private static final String NUMERICO = "0123456789";
    private static final Random RANDOM= new Random();
    String pattern;

    public StringRandomGenerator(String generatorParameters) {
        pattern = generatorParameters;
    }

    @Override
    public String get() throws ValidationException {
        String retValue = "";
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            switch(c){
                case 'A': retValue += generateRandom(ALFABETO_MAIUSCOLO); break;
                case 'N': retValue += generateRandom(NUMERICO); break;
                default: throw  new ValidationException ("il carattere '" + c + "' non  è gestito." );
            }
        }
        return retValue;
    }
   private String generateRandom(String dominio){
       return  String.valueOf(dominio.charAt(RANDOM.nextInt(dominio.length())));
   }
}
