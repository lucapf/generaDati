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
class StringGeneratorFactory extends AbsGeneratorFactory {

    public StringGeneratorFactory() {
    }

    @Override
    public IGenerator getGenerator(EProvider eProvider, String generatorParameters) throws ValidationException {
        switch (eProvider) {
            case RANDOM:
                return new StringRandomGenerator(generatorParameters);
            case DOMAIN:
                return new StringDomainGenerator(generatorParameters);
            case FIXED:
                return new StringFixedValueGenerator(generatorParameters);
        }
        return new NullGenerator(eProvider, generatorParameters);
    }

}
