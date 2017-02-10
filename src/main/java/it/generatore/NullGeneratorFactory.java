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
public class NullGeneratorFactory extends AbsGeneratorFactory{



    @Override
    public IGenerator getGenerator(EProvider eProvider, String generatorParameters) {
        throw new UnsupportedOperationException("non è stato possibile istanziare la factory per il provider " + eProvider.name() +  " parametri: " + generatorParameters); //To change body of generated methods, choose Tools | Templates.
    }
    
}
