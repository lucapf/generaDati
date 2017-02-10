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
public class DateGeneratorFacotry extends AbsGeneratorFactory{

    @Override
    public IGenerator getGenerator(EProvider eProvider, String generatorParameters) throws ValidationException {
        switch(eProvider){          
            case RANDOM: return new DateRandomGenerator(generatorParameters); 
            case GAUSSIAN: return new DateRandomGaussianGenerator(generatorParameters);
        }
        return new NullGenerator(eProvider, generatorParameters);
    }
    
}
