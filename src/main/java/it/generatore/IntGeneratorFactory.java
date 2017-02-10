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
class IntGeneratorFactory extends AbsGeneratorFactory {

    public IntGeneratorFactory() {
    }

    @Override
    public IGenerator getGenerator(EProvider eProvider, String generatorParameters) throws ValidationException{
        switch(eProvider){
            case SEQUENCE: return new SequenceGenerator(generatorParameters);
            case RANDOM: return new IntRandomGenerator(generatorParameters);
            case DOMAIN: return new IntDomainGenerator(generatorParameters);
        }
        return new NullGenerator(eProvider, generatorParameters);
    }
    
}
