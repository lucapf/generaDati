/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author palancaf
 */
public class Table {

    int numeroElementi;
    static String dateDelimiter = "\"";
    static String template="insert.vsl";
    String nomeTabella;
    String schemaName = "";
    List<Colonna> colonne = new ArrayList<Colonna>();
    String repeaterKey;
    private static final int POSITION_ORDER = 0;
    private static final int POSITION_TYPE = 1;
    private static final int POSITION_PROVIDER_AND_PARAMETERS = 2;

    public Table(String nomeTabella, String repeaterKey) {
        this.nomeTabella = nomeTabella;
        this.repeaterKey = repeaterKey;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public int getNumeroElementi() {
        return numeroElementi;
    }

    public void setNumeroElementi(int numeroElementi) {
        this.numeroElementi = numeroElementi;
    }

    public String getNomeTabella() {
        return nomeTabella;
    }

    public void setNomeTabella(String nomeTabella) {
        this.nomeTabella = nomeTabella;
    }

    public static String getDateDelimiter() {
        return dateDelimiter;
    }

    public static String getTemplate() {
        return template;
    }

    public List<Colonna> getColonne() {
        Collections.sort(colonne, new Comparator<Colonna>() {
            @Override
            public int compare(Colonna o1, Colonna o2) {
                return o1.ordine - o2.ordine;
            }
        });
        return colonne;
    }

    public void setColonne(List<Colonna> colonne) {
        this.colonne = colonne;
    }

    Table getNumberOfRecords(String[] components, String key, Properties prop) throws ValidationException {
        if (components.length == 4
                && components[0].equalsIgnoreCase("table")
                && components[2].equalsIgnoreCase("numero")
                && components[3].equalsIgnoreCase("record")) {
            String value = prop.getProperty(key);
            try {
                numeroElementi = Integer.valueOf(value);
            } catch (NumberFormatException nfe) {
                throw new ValidationException("check numeroRecord. il valore della chiave " + key + " non è un intero");
            }
        }
        return this;
    }

    Table getDateDelimiter(String[] components, String key, Properties prop) throws ValidationException {
        if (components.length == 2
                && components[0].equalsIgnoreCase("date")
                && components[1].equalsIgnoreCase("delimiter")) {
            dateDelimiter = prop.getProperty(key);
        }
        return this;
    }
    Table getTemplate(String[] components, String key, Properties prop) throws ValidationException {
        if (components.length == 2
                && components[0].equalsIgnoreCase("template")
                && components[1].equalsIgnoreCase("name")) {
            template = prop.getProperty(key);
        }
        return this;
    }
    Table getColumnsGeneratorsAndValues(Popolamento popolamento, String[] components, String key, Properties prop, String actualValue) throws ValidationException {

        if (components.length == 3
                && components[0].equalsIgnoreCase("table")
                && components[1].equalsIgnoreCase(nomeTabella)) {
            String[] columnKeyValue = prop.getProperty(key).split(";");
            ETipo tipo;
            EProvider provider;
            String parameters = null;
            String providerName;
            try {
                providerName = columnKeyValue[POSITION_PROVIDER_AND_PARAMETERS].split("@")[0];
            } catch (IndexOutOfBoundsException ioobe) {
                throw new ValidationException("parametro:" + Arrays.toString(columnKeyValue) + " non configurato correttamente. impossibile recuperare il provider (STRING,DATE...)");
            }

            try {
                tipo = ETipo.valueOf(columnKeyValue[POSITION_TYPE].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new ValidationException("valore[" + columnKeyValue[POSITION_TYPE] + "] impostato nella chiave " + key + " non valido. valori ammissibili: " + Arrays.asList(ETipo.values()));
            }
            try {
                provider = EProvider.valueOf(providerName);
            } catch (IllegalArgumentException e) {
                throw new ValidationException("valore [" + providerName + "] impostato nella chiave " + key + " non valido. valori ammissibili: " + Arrays.asList(EProvider.values()));
            }
            if (columnKeyValue[POSITION_PROVIDER_AND_PARAMETERS].split("@").length == 2) {
                parameters = columnKeyValue[POSITION_PROVIDER_AND_PARAMETERS].split("@")[1];
                if (repeaterKey != null) {
                    parameters = parameters.replace("${" + repeaterKey + "}", actualValue);
                }
            }
            Integer ordinamento = -1;
            try {
                ordinamento = Integer.valueOf(columnKeyValue[POSITION_ORDER]);
            } catch (NumberFormatException nfe) {
                throw new ValidationException("il parametro ordinale per la chiave " + key + " non è corretto");
            }
            popolamento.findByName(nomeTabella).colonne.add(new Colonna(ordinamento, components[2],
                    tipo,
                    GeneratorFactoryProducer.getFactory(tipo)
                    .getGenerator(provider, parameters)));
        }
        return this;
    }

    public String getSchemaName() {
        return schemaName;
    }

    Table getSchemaName(String[] components, String key, Properties prop) throws ValidationException {
        if (components.length == 4
                && components[0].equalsIgnoreCase("table")
                && components[1].equalsIgnoreCase(nomeTabella)
                && components[2].equalsIgnoreCase("schema")
                && components[3].equalsIgnoreCase("name")) {
            String value = prop.getProperty(key);
            schemaName = value + ".";

        }
        return this;
    }

}
