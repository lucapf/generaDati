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
public class GeneratorFactoryProducer {
    public static AbsGeneratorFactory getFactory(ETipo tipo){
        switch (tipo){
            case INT: return (AbsGeneratorFactory) new IntGeneratorFactory();            
            case STRING: return (AbsGeneratorFactory) new StringGeneratorFactory();            
            case DATE: return (AbsGeneratorFactory) new DateGeneratorFacotry();            
        }
        return null;
    }
}
