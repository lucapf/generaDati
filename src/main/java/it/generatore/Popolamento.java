/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author palancaf
 */
public class Popolamento implements Serializable{
    
    Map<String, Table> tables = new HashMap<>();

    public Table findByName(String tablename) {
        return tables.get(tablename);
    }

    Table checkAndCreateTable(String nome, String repeaterKey) {
        if (tables.get(nome) == null) {
            tables.put(nome, new Table(nome, repeaterKey));
        }
        return tables.get(nome);
    }
    public Collection<Table> tableCollection(){
        return tables.values();
    }

   
    
}
