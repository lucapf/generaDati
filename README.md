# generaDati
il progetto è pensato per generare dati informato testuale in base alla sola configurazione.
## utilizzi principali
* valorizzazione base dati
* generazione file csv, json etc.

## come iniziare

* download sorgenti (via git clone)
* compilazione (via maven) `mvn clean install`
* esecuzione esempio `java -jar target\velocityDatabaseValidator-1.0-SNAPSHOT-jar-with-dependencies.jar test.properties`

#come funziona
tutte le informazioni per la generazione del tracciato sono contenute all'interno del file di properties utilizzato. In particolare leggendo il contenuto del file test.properties:

* `setCF.output=setCF.csv`  : fornisce 2 idicazioni:
   **  `setCF.` indica il nome dell'entità che si sta referenziando (setCF appunto) 
   **  `= setCF.csv` indica il nome del file su cui saranno scritti i dati.
* `date.delimiter=` indica il delimitatore per il campo date (opzionale)
* `template.name=template.vsl` : indica il nome del template velocity utilizzato (in questo caso template.vsl si riferisce ad un file csv)
*  `table.setCF.numero.record=1000000` : indica il numero di record da generare
*  `table.setCF.TIPO_SOGGETTO=1;STRING;FIXED@Persona Fisica` : 
    ** `table.setCF` : nome tabella
	** `TIPO_SOGGETTO` : tipo soggetto 
	** `1` : ordinale 
	** `STRING` : tipo di dato  
	** `FIXED`  : valore fisso 
    ** `@....`  : valore restituito







