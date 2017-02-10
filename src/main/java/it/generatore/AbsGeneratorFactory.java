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
public abstract class AbsGeneratorFactory {
    public  abstract IGenerator  getGenerator( EProvider eProvider, String generatorParameters) throws ValidationException;
    
}
