/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.generatore;

import static it.generatore.TableChecker.*;

/**
 *
 * @author palancaf
 */
class TableChecker {

    static Table getTable(Popolamento popolamento, String[] key,String repeaterKey) {
       return popolamento.checkAndCreateTable(key[1], repeaterKey);    
    }

}
