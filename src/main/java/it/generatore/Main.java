/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import static it.generatore.TableChecker.*;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 *
 * @author palancaf
 */
public class Main {

    private static Properties prop = new Properties();
    private static Popolamento popolamento;
    private static String repeaterKey = null;

    public static void main(String[] args) {
        initialCheck(args); //verifiche preliminari
        try {
            int page = 0;
            for (String value : getRepeater()) {
                page++;
                popolamento = new Popolamento();
                initGenerator(value); //popolamento generatori 

                produceScript(page);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    private static void produceScript(int page) throws Exception {
        Velocity.init();
        VelocityContext vc = new VelocityContext();
        vc.put("p", popolamento);

        Template t = null;
        try {
            for (Table table : popolamento.tables.values()) {
                for (Colonna colonna : table.getColonne()) {
                    if (colonna.getGenerator() instanceof ISequenceGenerator) {
                        ((ISequenceGenerator) colonna.getGenerator()).setInitValue(page * table.getNumeroElementi());
                    }
                }
                String fileName = prop.getProperty(table.getNomeTabella() + ".output");
                Writer w = null;
                if (fileName == null) {
                    w = new StringWriter();
                } else {
                    w = new FileWriter(fileName, page > 1);
                }

                t = Velocity.getTemplate(Table.getTemplate());
                t.merge(vc, w);
                w.close();
            }

        } catch (ResourceNotFoundException rnfe) {
            throw new ValidationException("impossibile leggere il file insert.vsl verificare la configuraione");
        }

    }

    private static void initGenerator(String actualValue) {

        try {
            for (Iterator<Object> it = prop.keySet().iterator(); it.hasNext();) {
                String key = (String) it.next();
                String[] components = key.split("\\.");
                getTable(popolamento, components, repeaterKey)
                        .getNumberOfRecords(components, key, prop)
                        .getDateDelimiter(components, key, prop)
                        .getTemplate(components, key, prop)
                        .getSchemaName(components, key, prop)
                        .getColumnsGeneratorsAndValues(popolamento, components, key, prop, actualValue);

            }
        } catch (ValidationException va) {
            va.printStackTrace(System.err);
        }
    }

    private static void initialCheck(String[] args) {
        if (args == null || args.length == 0) {
            System.err.println("devi passare il parametro con il nome del file di properties");
        }
        try {
            prop.load(new FileReader(new File(args[0])));
        } catch (IOException e) {
            System.err.println("impossibile recuperare il file di properties: " + args[0]);
        }
    }

    private static String[] getRepeater() {

        if (prop.containsKey("utils.repeater")) {
            String[] repeater = prop.getProperty("utils.repeater").split(":");
            repeaterKey = repeater[0];
            return repeater[1].split("\\,");
        }
        return new String[]{""};
    }

}
