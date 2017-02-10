/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author palancaf
 */
public class SequenceGenerator implements ISequenceGenerator {

    private Integer atomicInteger = 0;

    public SequenceGenerator() {

    }

    public SequenceGenerator(String parameters) throws ValidationException {
        try {
            if (parameters != null) {
                atomicInteger = valuate(parameters);
            }
        } catch (ScriptException se) {
            throw new ValidationException("errore nella valutazione dello script " + se.getMessage());
        }

    }
    

    @Override
    public String get() {
        return String.valueOf(atomicInteger++);
    }

    private Integer valuate(String parameters) throws ScriptException {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("JavaScript");
        Object oRet = engine.eval(parameters);
        Integer value;
        if (Double.class.isInstance(oRet) ){
             value= ((Double)oRet).intValue();
        }else{
            value  = (Integer)oRet;
        }
        return value;

    }

    @Override
    public void setInitValue(Integer i) {
        atomicInteger = i;
    }

}
