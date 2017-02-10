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
public class IntDomainGenerator extends StringDomainGenerator {
    
    public IntDomainGenerator(String parameters) throws ValidationException {
        super(parameters);
    }
    public void validate()throws ValidationException {
        try{
            for (String string : strDomain) {
               Integer.valueOf(string);
            }
        }catch (NumberFormatException  nfe){
            throw  new ValidationException ("dominio ["+super.parameters+"] non valorizzato correttamente, valori ammissibili sono numerici separati da ',' ");
        }
    }
}
