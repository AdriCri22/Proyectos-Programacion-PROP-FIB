package classes.domain;

import classes.Parser;

import javax.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa un document.
 * @author irene.bertolin.rico
 */
public class Document {
    /**
     * Representa el nom del document
     * */
    private String name;
    /**
     * Representa els fulls del document.
     * */
    private final HashMap<String, Sheet> sheets = new HashMap<>();

    /**
     * Constructora del document amb un nom.
     *
     * @param name nom del document.
     * */
    public Document(String name) {
        this.name = name;
        sheets.put("Full 0", new Sheet(this, "Full 0"));
    }

    /**
     * Obté el nom del document.
     *
     * @return retorna el nom del document.
     * */
    public String getName() {
        return this.name;
    }

    /**
     * Modifica el nom del document.
     *
     * @param name nom del document.
     * */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obté el valor d'una cel·la donada una referència.
     *
     * @param reference referència de la cel·la.
     * @return retorna el valor de la cel·la.
     * @throws Exception si la referencia no és correcte.
     * @throws Exception si la posició no és vàlida.
     * @throws Exception si el full no existeix.
     * */
    public String getValue(String reference) throws Exception {
        Parser parser = new Parser();
        int index = reference.indexOf('-');
        String sheet = reference.substring(0, index);
        String pos = reference.substring(index+1);
        if (parser.validPosition(pos))
            throw new Exception("La posició no és vàlida");
        if (!this.sheets.containsKey(sheet))
            throw new Exception("El full no existeix");

        return this.sheets.get(sheet).getValue(pos);
    }

    /**
     * Afegeix un full al document.
     *
     * @param sheetName nom del full.
     * @throws Exception si ja existeix un full amb aquest nom.
     * */
    public void addSheet(String sheetName) throws Exception {
        if (this.sheets.containsKey(sheetName))
            throw new Exception("Ja existeix un full amb aquest nom");

        this.sheets.put(sheetName, new Sheet(this, sheetName));
    }

    /**
     * Esborra un full donat el nom d'aquest.
     *
     * @param sheetName nom del full.
     * @throws Exception si el full no existeix.
     * @throws Exception si aquest és l'únic full del document.
     * */
    public void deleteSheet(String sheetName) throws Exception {
        if (this.sheets.size() == 1)
            throw new Exception("No es pot eliminar l'únic full del document");
        else if (!this.sheets.containsKey(sheetName))
            throw new Exception("No existeix cap full amb aquest nom");
        else
            this.sheets.remove(sheetName);
    }

    /**
     * Obté un full donat el nom d'aquest.
     *
     * @param sheetName nom del full.
     * @return retorna el full.
     * @throws Exception si el full no existeix.
     * */
    public Sheet getSheet(String sheetName) throws Exception {
        if (!this.sheets.containsKey(sheetName))
            throw new Exception("El full no existeix");
        else
            return this.sheets.get(sheetName);
    }

    /**
     * Modifica les referències en moure un bloc de cel·les.
     *
     * @param sheetName nom del full.
     * @param oldPositions posicions antigues.
     * @param newPositions posicions noves.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReferences(String sheetName, ArrayList<String> oldPositions, ArrayList<String> newPositions) throws Exception {
        for (Map.Entry<String, Sheet> entry : this.sheets.entrySet())
            entry.getValue().changeReferences(sheetName, oldPositions, newPositions);
    }

    /**
     * Modifica les referències en moure una cel·la.
     *
     * @param sheetName nom del full.
     * @param oldPos posició antiga.
     * @param newPos posició nova.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReference(String sheetName, String oldPos, String newPos) throws Exception {
        for (Map.Entry<String, Sheet> entry : this.sheets.entrySet())
            entry.getValue().changeReference(sheetName, oldPos, newPos);
    }

    /**
     * Obté el document en format json.
     *
     * @return retorna el document en format json.
     * */
    public JsonObjectBuilder doc2json() {
        JsonObjectBuilder doc = Json.createObjectBuilder();
        doc.add("name", this.name);
        JsonArrayBuilder fulls = Json.createArrayBuilder();
        for (Map.Entry<String, Sheet> entry : this.sheets.entrySet()) {
            JsonObjectBuilder full = Json.createObjectBuilder();
            full.add("sheetName", entry.getKey());
            full.add("cells", entry.getValue().sheet2json());
            fulls.add(full);
        }
        doc.add("sheets", fulls);

        return doc;
    }

    /**
     * Carrega un document en format json.
     *
     * @param documentJson document en format json.
     * @throws Exception si existeix algun problema amb el document.
     * */
    public void json2doc(JsonObject documentJson) throws Exception {
        sheets.clear();
        this.name = documentJson.getString("name");
        JsonArray fulls = documentJson.getJsonArray("sheets");
        for (JsonValue fullJSON : fulls) {
            String sheetName = fullJSON.asJsonObject().getString("sheetName");
            this.sheets.put(sheetName, new Sheet(this, sheetName));
        }

        for (JsonValue fullJson : fulls) {
            String nomFull = fullJson.asJsonObject().getString("sheetName");
            JsonArray celes = fullJson.asJsonObject().getJsonArray("cells");
            for (JsonValue celaJsonValue : celes) {
                JsonObject celaJson = celaJsonValue.asJsonObject();
                String posicio = celaJson.getString("position");
                String valor = celaJson.getString("value");
                String contingut = null;
                if (celaJson.containsKey("content")) {
                    contingut = celaJson.getString("content");
                }
                this.sheets.get(nomFull).setCell(posicio, valor, contingut);
            }
        }
    }

    /**
     * Obte una llista amb els noms dels fulls.
     * @return Llista de tots els fulls.
     */
    public ArrayList<String> getAllSheets() {
        ArrayList<String> res = new ArrayList<>();
        for (Map.Entry<String, Sheet> entry : this.sheets.entrySet()) {
            res.add(entry.getKey());
        }

        return res;
    }
}


