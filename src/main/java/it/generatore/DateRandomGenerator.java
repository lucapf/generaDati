/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author palancaf
 */
public class DateRandomGenerator implements IGenerator {

    private static final String OUTPUT_DATE_FORMAT = "yyyy-MM-dd";
    private static final String INPUT_DATE_FORMAT = "yyyyMMdd";
    static final String OUTPUT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String INPUT_DATE_TIME_FORMAT = "yyyyMMddHHmm";
//    private static final String OUTPUT_SQL_DATE_TIME_FORMAT = "YYYYMMDDHH24MISS";
//    private static final String OUTPUT_SQL_DATE_FORMAT = "YYYYMMDD";

//    static final String OUTPUT_SQL_FORMAT="TIMESTAMP_FORMAT('${VALUE}','${FORMAT}')";
    static final String OUTPUT_SQL_QUERY_TIMESTAMP_FORMAT = "TIMESTAMP ('${VALUE}')";
//    static final String OUTPUT_SQL_QUERY_DATE_FORMAT = "DATE ('${VALUE}')";
    static final String OUTPUT_SQL_QUERY_DATE_FORMAT = "${VALUE}";
    static final Random RANDOM = new Random();
    long minDate = 0;
    long maxDate = System.currentTimeMillis();
    SimpleDateFormat sdfOut = null;
    private static String ACTUAL_FORMAT = "DATE";

    public DateRandomGenerator(String patterns) throws ValidationException {

        if (patterns == null) {
            return;
        }
        String[] bounds = patterns.split("-");
        if (bounds[0].length() != bounds[1].length()) {
            throw new ValidationException("patterns " + patterns + "  errati, debbono avere la stessa lunghezza ");
        }
        if (bounds[0].length() != 8 && bounds[0].length() != 12) {
            throw new ValidationException("patterns " + patterns + "  errati. Debbono essere nella forma: " + INPUT_DATE_FORMAT + " oppure" + INPUT_DATE_TIME_FORMAT);
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(INPUT_DATE_FORMAT);
            sdfOut = new SimpleDateFormat(OUTPUT_DATE_FORMAT);
            if (bounds[0].length() == 12) {
                sdf = new SimpleDateFormat(INPUT_DATE_TIME_FORMAT);
                sdfOut = new SimpleDateFormat(OUTPUT_DATE_TIME_FORMAT);
                ACTUAL_FORMAT = "DATE_TIME";
            }
            minDate = sdf.parse(bounds[0]).getTime();
            if (bounds.length == 2) {
                maxDate = sdf.parse(bounds[1]).getTime();
            }
        } catch (ParseException pe) {
            throw new ValidationException("patterns " + patterns + "  errati. Debbono essere nella forma: " + INPUT_DATE_FORMAT + " oppure" + INPUT_DATE_TIME_FORMAT);
        }
    }

    @Override
    public String get() throws ValidationException {
        String getString = new StringBuffer()
                .append(Table.getDateDelimiter())
                .append(OUTPUT_SQL_QUERY_DATE_FORMAT.replace("${VALUE}", getDate()))
                .append(Table.getDateDelimiter()).toString();
        switch (ACTUAL_FORMAT) {
            case "DATE_TIME":
                getString = getString.replace("${FORMAT}", OUTPUT_DATE_TIME_FORMAT);
                break;
            case "DATE":
            default:
                getString = getString.replace("${FORMAT}", OUTPUT_DATE_FORMAT);
        }

        return getString;
    }

    public String getDate() {
        return sdfOut.format(new Date(minDate + Double.valueOf(RANDOM.nextDouble() * (maxDate - minDate)).longValue()));
    }
}
