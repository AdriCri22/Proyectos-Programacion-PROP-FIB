package classes.domain;

import classes.Parser;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Representa un full.
 * @author carla.canovas.iglesias
 */
public class Sheet {
    /**
     * Document al qual pertany el full.
     * */
    private final Document document;
    /**
     * Nom del Full
     * */
    private final String sheetName;
    /**
     * Cel·les que conté el full.
     * */
    private final HashMap<String, Cell> cells = new HashMap<>();

    /**
     * Constructora amb un document.
     *
     * @param document document al qual pertany el full.
     * */
    public Sheet(Document document, String sheetName) {
        this.document = document;
        this.sheetName = sheetName;
    }

    /**
     * Retorna el nom del full.
     *
     * @return String que retorna el nom del document.
     * */
    public String getSheetName() {
        return this.sheetName;
    }

    /**
     * Modifica una cel·la del full donant el valor explícitament.
     *
     * @param position posició de la cel·la.
     * @param value valor de la cel·la.
     * @param content contingut de la cel·la.
     * @throws Exception si existeix un problema en modificar una cel·la.
     * */
    public void setCell(String position, String value, String content) throws Exception {
        if (!this.cells.containsKey(position))
            this.cells.put(position, new Cell(this, position, value, content));
        else
            this.cells.get(position).setCell(content);
    }

    /**
     * Modifica una cel·la del full.
     *
     * @param position posició de la cel·la.
     * @param content contingut de la cel·la.
     * @throws Exception si existeix un problema en modificar una cel·la.
     * */
    public void setCell(String position, String content) throws Exception {
        if (!this.cells.containsKey(position))
            this.cells.put(position, new Cell(this, position, content));
        else
            this.cells.get(position).setCell(content);
    }

    /**
     * Obté el valor d'una cel·la.
     *
     * @param position posició de la cel·la.
     * @return retorna el valor de la cel·la.
     * */
    public String getValue(String position) {
        if (!this.cells.containsKey(position))
            return "0";
        return this.cells.get(position).getValue();
    }

    /**
     * Obté el contingut d'una cel·la.
     *
     * @param position posició de la cel·la.
     * @return retorna el contingut de la cel·la.
     * */
    public String getContent(String position) {
        if (!this.cells.containsKey(position))
            return null;
        return this.cells.get(position).getContent();
    }

    /**
     * Obté la cel·la donada una referencia.
     *
     * @param reference referència de la cel·la.
     * @return retorna la cel·la referenciada.
     * @throws Exception si la referència donada no és vàlida.
     * */
    public Cell getCell(String reference) throws Exception {
        if (reference.indexOf('-') != -1)
            return this.document.getSheet(new Parser(reference).getSheetName()).getCell(new Parser(reference).getPosition());
        else
        if (!this.cells.containsKey(reference))
            this.cells.put(reference, new Cell(this, reference, "0"));
        return this.cells.get(reference);
    }

    /**
     * Esborra una cel·la.
     *
     * @param position posició de la cel·la.
     * */
    public void deleteCell(String position) {
        this.cells.remove(position);
    }

    /**
     * Afegeix files al full.
     *
     * @param row posició de la fila a la qual afegir més files.
     * @param shift número de files a afegir.
     * @throws Exception si al traslladar les cel·les provoca un error.
     * */
    public void addRows(int row, int shift) throws Exception {
        HashMap<String, Cell> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cell>> it = this.cells.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cell> entry = it.next();
            Parser parser = new Parser(entry.getKey());
            int actualRow = parser.getRow((parser.getPosition()));

            if (actualRow >= row) {      // Rows to shift
                String newPosition = parser.getColumn(entry.getKey()) + (actualRow + shift);
                tmp.put(newPosition, entry.getValue());
                it.remove();

            }
        }

        this.cells.putAll(tmp);

        changeReferencesAddRows(row, shift);
    }

    /**
     * Afegeix columnes al full.
     *
     * @param columnName posició de la columna a la qual afegir més columnes.
     * @param shift número de columnes a afegir.
     * @throws Exception en cas de produir-se un error al afegir columnes.
     * */
    public void addColumns(String columnName, int shift) throws Exception {
        Parser parser = new Parser();
        int column = parser.lettersToNumber(columnName);
        HashMap<String, Cell> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cell>> it = this.cells.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cell> entry = it.next();
            int actualColumn = parser.lettersToNumber(parser.getColumn(entry.getKey()));

            if (actualColumn >= column) {      // Columns to shift
                String newPosition = parser.numberToLetters(actualColumn + shift) + parser.getRow(entry.getKey());
                tmp.put(newPosition, entry.getValue());
                it.remove();

            }
        }
        this.cells.putAll(tmp);

        changeReferencesAddColumns(columnName, shift);
    }

    /**
     * Esborra files al full.
     *
     * @param row posició de la fila a la qual eliminar més files.
     * @param shift número de files a suprimir.
     * @throws Exception en cas de produir-se un error al esborrar files.
     * */
    public void deleteRows(int row, int shift) throws Exception {
        Parser parser = new Parser();
        HashMap<String, Cell> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cell>> it = this.cells.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cell> entry = it.next();
            int actualRow = parser.getRow(entry.getKey());

            if (actualRow > row + shift - 1) {      // Rows to shift
                String newPosition = parser.getColumn(entry.getKey()) + (actualRow - shift);
                tmp.put(newPosition, entry.getValue());
                entry.getValue().setPosition(newPosition);
            }

            if (actualRow >= row)
                it.remove();
        }

        this.cells.putAll(tmp);

        changeReferencesDeleteRows(row, shift);
    }

    /**
     * Actualitza les referències en cas d'eliminar files.
     *
     * @param row fila des de la qual es comença a esborrar.
     * @param shift número de files esborrades.
     * @throws Exception si es produeix un error en actualitzar les referències.
     * */
    private void changeReferencesDeleteRows(int row, int shift) throws Exception {
        for (Map.Entry<String, Cell> entry : this.cells.entrySet())
            entry.getValue().changeReferencesDeleteRows(sheetName, row, shift);
    }

    /**
     * Actualitza les referències en cas d'afegir files.
     *
     * @param row fila des de la qual es comença a afegir.
     * @param shift número de files afegides.
     * @throws Exception si es produeix un error en actualitzar les referències.
     * */
    private void changeReferencesAddRows(int row, int shift) throws Exception {
        for (Map.Entry<String, Cell> entry : this.cells.entrySet())
            entry.getValue().changeReferencesAddRows(sheetName, row, shift);
    }

    /**
     * Actualitza les referències en cas d'esborrar columnes.
     *
     * @param columnName columna des de la qual es comença a eliminar.
     * @param shift número de columnes eliminades.
     * @throws Exception si es produeix un error en actualitzar les referències.
     * */
    private void changeReferencesDeleteColumns(String columnName, int shift) throws Exception {
        for (Map.Entry<String, Cell> entry : this.cells.entrySet())
            entry.getValue().changeReferencesDeleteColumns(sheetName, columnName, shift);
    }

    /**
     * Actualitza les referències en cas d'afegir columnes.
     *
     * @param columnName columna des de la qual es comença a afegir.
     * @param shift número de columnes afegides.
     * @throws Exception si es produeix un error en actualitzar les referències.
     * */
    private void changeReferencesAddColumns(String columnName, int shift) throws Exception {
        for (Map.Entry<String, Cell> entry : this.cells.entrySet())
            entry.getValue().changeReferencesAddColumns(sheetName, columnName, shift);
    }

    /**
     * Esborra columnes al full.
     *
     * @param columnName posició de la columna a la qual eliminar més columnes.
     * @param shift número de columnes a suprimir.
     * @throws Exception en cas de produir-se un error al esborrar columnes.
     * */
    public void deleteColumns(String columnName, int shift) throws Exception {
        Parser parser = new Parser();
        int column = parser.lettersToNumber(columnName);
        HashMap<String, Cell> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cell>> it = this.cells.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cell> entry = it.next();
            int actualColumn = parser.lettersToNumber(parser.getColumn(entry.getKey()));

            if (actualColumn > column + shift - 1) {      // Columns to shift
                String newPosition = parser.numberToLetters(actualColumn - shift) + parser.getRow(entry.getKey());
                tmp.put(newPosition, entry.getValue());
                entry.getValue().setPosition(newPosition);
            }

            if (actualColumn >= column)
                it.remove();
        }

        this.cells.putAll(tmp);

        changeReferencesDeleteColumns(columnName, shift);
    }

    /**
     * Cerca un valor al full.
     *
     * @param value valor a cercar dins del full.
     * @return retorna les posicions de les cel·les que contenen el valor donat.
     * @throws Exception no s'ha trobat el valor.
     * */
    public ArrayList<String> search(String value) throws Exception {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<String, Cell> cela : this.cells.entrySet())
            if (value.equals(cela.getValue().getValue()))
                result.add(cela.getKey());
        if (result.isEmpty()) throw new Exception("No hi ha cap cel·la amb el valor en aquest full");
        return result;
    }

    /**
     * Cerca un valor al full i les cel·les que contenen aquest valor es reemplacen per un de nou.
     *
     * @param valueMatch valor a cercar dins del full.
     * @param valueReplace valor pel qual reemplaçar.
     * @throws Exception si el valor pel qual reemplaçar no és correcte.
     * */
    public void searchAndReplace(String valueMatch, String valueReplace) throws Exception {
        for (Map.Entry<String, Cell> cela : this.cells.entrySet())
            if (valueMatch.equals(cela.getValue().getValue()))
                cela.getValue().setCell(valueReplace);
    }

    /**
     * Clona una cel·la.
     *
     * @param newPos posició nova.
     * @param oldPos posició antiga.
     * */
    public void cloneCell(String newPos, String oldPos) {
        this.cells.put(newPos, this.cells.get(oldPos).clone());
    }

    /**
     * Actualitza les referències en cas moure un bloc de cel·les.
     *
     * @param sheetName full a actualitzar.
     * @param oldPositions cel·les antigues.
     * @param newPositions cel·les noves.
     * @throws Exception si es produeix un error en actualitzar les referències.
     * */
    public void changeReferences(String sheetName, ArrayList<String> oldPositions, ArrayList<String> newPositions) throws Exception {
        for (Map.Entry<String, Cell> entry : this.cells.entrySet())
            entry.getValue().changeReferences(sheetName, oldPositions, newPositions);
    }

    /**
     * Actualitza les referències en cas moure una cel·la.
     *
     * @param sheetName full a actualitzar.
     * @param oldPos posició de la cel·la antiga.
     * @param newPos posició de la cel·la nova.
     * @throws Exception si es produeix un error en actualitzar les referències.
     * */
    public void changeReference(String sheetName, String oldPos, String newPos) throws Exception {
        for (Map.Entry<String, Cell> entry : this.cells.entrySet())
            entry.getValue().changeReference(sheetName, oldPos, newPos);
    }

    /**
     * Obté el full en format json.
     *
     * @return retorna el full en format json.
     * */
    public JsonArrayBuilder sheet2json() {
        JsonArrayBuilder celes = Json.createArrayBuilder();
        for (Map.Entry<String, Cell> entry : this.cells.entrySet()) {
            JsonObjectBuilder cela = entry.getValue().cell2json();
            cela.add("position", entry.getKey());
            celes.add(cela);
        }

        return celes;
    }

    /**
     * Obtenir totes les Cel·les amb el seu valor
     * @return Retorna totes les posicions de les cel·les que tenen valor i el seu valor
     */
    public HashMap<String, String> getAllCells() {
        HashMap<String, String> result = new HashMap<>();
        for (HashMap.Entry<String, Cell> entry : cells.entrySet()) {
            String pos = entry.getKey();
            String value = entry.getValue().getValue();
            result.put(pos, value);
        }


        return result;
    }
}
