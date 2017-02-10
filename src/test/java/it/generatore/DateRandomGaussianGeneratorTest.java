/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import it.generatore.DateRandomGaussianGenerator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author palancaf
 */
public class DateRandomGaussianGeneratorTest {

    private DateRandomGaussianGenerator dateRandomGaussianGenerator;
    private static final double NUMERO_RECORD = 10000d;
    private SimpleDateFormat sdf;
    private static final Pattern pattern = Pattern.compile(".*(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}).*");

    public DateRandomGaussianGeneratorTest() {
    }

    @Before
    public void setUp() throws Exception {
        dateRandomGaussianGenerator = new DateRandomGaussianGenerator("201605051000-201605051700");
        sdf = new SimpleDateFormat(DateRandomGaussianGenerator.OUTPUT_DATE_TIME_FORMAT);
    }

    @Test
    public void testGet() throws Exception {
        int i = 0;
        int inFascia = 0;
        int fuoriFascia = 0;
        Calendar cal = GregorianCalendar.getInstance();

        cal.setTimeInMillis(dateRandomGaussianGenerator.minDate);
        cal = DateUtils.truncate(cal, Calendar.DATE);
        System.out.println(sdf.format(cal.getTime()));
        Map<String, Integer> distribuzione = new HashMap<>();
        while (++i <= 24 * 60) {
            cal.add(Calendar.MINUTE, 1);
            distribuzione.put(getHourKey(cal), 0);
        }
        System.out.println(distribuzione.keySet());
        i = 0;
        while (++i <= NUMERO_RECORD) {
            String strDate = dateRandomGaussianGenerator.get();
            //System.out.println("strDate:" + strDate);
            Matcher m = pattern.matcher(strDate);
            assertTrue(m.find());
            strDate = m.group(1);
          //  System.out.println("strDate:" + strDate);
            Date date = sdf.parse(strDate);
            cal.setTime(date);
            String hourKey = getHourKey(cal);
            Integer actualValue = distribuzione.get(hourKey);
            distribuzione.remove(hourKey);
            distribuzione.put(hourKey, actualValue + 1);

            if (cal.get(Calendar.HOUR_OF_DAY) >= 9 && cal.get(Calendar.HOUR_OF_DAY) <= 18) {
                inFascia++;
            } else {
                fuoriFascia++;
            }
            //System.out.println(strDate);
        }
        System.out.println("**** terminato. In fascia: " + inFascia + " fuoriFascia: " + fuoriFascia + " % in fascia: " + (inFascia / NUMERO_RECORD));
        assertTrue(inFascia / NUMERO_RECORD >= 0.7d);
        assertTrue(fuoriFascia / NUMERO_RECORD <= 0.3d);
        assertTrue(inFascia + fuoriFascia == NUMERO_RECORD);
        
    }

    private static String getHourKey(Calendar cal) {

        return StringUtils.leftPad(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)), 2, '0')
                + ":" + StringUtils.leftPad(String.valueOf(cal.get(Calendar.MINUTE)), 2, '0');
    }

}
