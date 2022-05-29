package controllers;

import classes.persistence.*;
import javax.json.*;
import java.io.*;

/**
 * Representa el controlador de persistència.
 * @author adrian.cristian.crisan
 */
public class CtrlPersistence {

    /**
     * Representa exportar a csv.
     * */
    private final ExportToCsv exportToCsv;

    /**
     * Constructora del controlador de persistència.
     * */
    public CtrlPersistence() {
       this.exportToCsv = new ExportToCsv();
    }

    /**
     * Exporta un document a format csv.
     *
     * @param full full en format json.
     * */
    public void exportCSVDocument(String docName, String sheetName, JsonArray full) throws Exception {
        this.exportToCsv.exportCSV(docName, sheetName, full);
    }

    /**
     * Desa el document en format json.
     *
     * @param document document en format json.
     * @throws FileNotFoundException si en guardar el document sorgeix un problema.
     * */
    public void saveDocument(JsonObject document) throws FileNotFoundException {
        String nom = document.getString("name");
        OutputStream os = new FileOutputStream(nom + ".json");
        JsonWriter jsonWriter = Json.createWriter(os);
        jsonWriter.writeObject(document);
        jsonWriter.close();
    }

    /**
     * Llegeix un document en format json
     *
     * @param path és el path on es troba el document.
     * @return retorna el document en format JsonObject.
     * @throws FileNotFoundException si en llegir el document sorgeix un problema.
     * */
    public JsonObject readDocument(String path) throws FileNotFoundException {
        File jsonInputFile = new File(path);
        InputStream is = new FileInputStream(jsonInputFile);
        JsonReader reader = Json.createReader(is);
        JsonObject document = reader.readObject();
        reader.close();
        return document;
    }
}
