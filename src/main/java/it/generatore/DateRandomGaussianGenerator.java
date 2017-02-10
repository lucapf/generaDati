/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.util.Date;

/**
 *
 * @author palancaf
 */
public class DateRandomGaussianGenerator extends DateRandomGenerator{
    
    public DateRandomGaussianGenerator(String patterns) throws ValidationException {
        super(patterns);
    }
    
    @Override
    public String getDate()  {
        long middle= (maxDate - minDate)/2;
        long actualValue = minDate + middle + Double.valueOf(RANDOM.nextGaussian() * middle/1.5).longValue();
        return  sdfOut.format(new Date(actualValue)) ;
    }
}
