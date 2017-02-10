/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

/**
 *
 * @author palancaf
 */
class NullGenerator implements IGenerator {
    String provider;
    String parameters;
    public NullGenerator(EProvider eProvider, String generatorParameters) {
        provider= eProvider.name();
        parameters=generatorParameters;
    }

    @Override
    public String get() {
        throw new UnsupportedOperationException(
                "impossibile recuperare il generator per il tipo " 
                        + provider  + " parametri " + parameters); 
    }
    
}
