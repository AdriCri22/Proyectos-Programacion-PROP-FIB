package controllers;

import classes.Parser;
import classes.domain.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Representa el controlador de domini.
 * @author adrian.cristian.crisan
 */
public class CtrlDomain {
    /**
     * Representa el controlador de persistència.
     * */
    private final CtrlPersistence ctrlPersistence = new CtrlPersistence();
    /**
     * Representa el document.
     * */
    private final Document document;
    /**
     * Representa el full actual.
     * */
    private Sheet actualSheet;

    /**
     * Constructora del controlador de domini donat un nom.
     *
     * @param name nom del document.
     * @throws Exception si es produeix un error en crear el document.
     * */
    public CtrlDomain(String name) throws Exception {
        this.document = new Document(name);
        this.actualSheet = this.document.getSheet("Full 0");
    }

    /**
     * Obté el nom del document.
     *
     * @return retorna el nom del document.
     * */
    public String getDocumentName() {
        return this.document.getName();
    }

    /**
     * Modifica el nom del document.
     *
     * @param name nom del document.
     * */
    public void setDocumentName(String name) {
        this.document.setName(name);
    }

    /**
     * Obtenir totes les cel·les d'un full amb el seu valor.
     * @param sheetName nom del Full
     * @return Retorna totes les posicions de les cel·les que tenen valor  i el seu valor
     */
    public HashMap<String,String> getAllCells(String sheetName) throws Exception {
        changeSheet(sheetName);
        return actualSheet.getAllCells();
    }
    /**
     * Canvia de full.
     *
     * @param sheetName nom del full.
     * @throws Exception si el full no existeix.
     * */
    public void changeSheet(String sheetName) throws Exception {
        this.actualSheet = this.document.getSheet(sheetName);
    }

    /**
     * Afegeix un full.
     *
     * @param sheetName nom del full.
     * @throws Exception si ja existeix un full amb aquest nom.
     * */
    public void addSheet(String sheetName) throws Exception {
        this.document.addSheet(sheetName);
    }

    /**
     * Elimina un full.
     *
     * @param sheetName nom del full.
     * @throws Exception si no existeix un full amb aquest nom.
     * */
    public void deleteSheet(String sheetName) throws Exception {
        this.document.deleteSheet(sheetName);
    }

    /**
     * Obté el valor d'una cel·la.
     *
     * @param position posició de la cel·la.
     * @return retorna el valor de la cel·la.
     * */
    public String getValue(String position) {
        return this.actualSheet.getValue(position);
    }

    /**
     * Obté el contingut d'una cel·la.
     *
     * @param position posició de la cel·la.
     * @return retorna el contingut de la cel·la.
     * */
    public String getContent(String position) {
        return this.actualSheet.getContent(position);
    }

    /**
     * Modifica una cel·la.
     *
     * @param position posició de la cel·la.
     * @param content nou contingut.
     * @throws Exception si el contingut no és correcte.
     * */
    public void setCell(String position, String content) throws Exception {
        this.actualSheet.setCell(position, content);
    }

    /**
     * Afegeix múltiples files.
     *
     * @param row fila on afegir les altres files.
     * @param shift número de columnes a afegir.
     * @throws Exception si al traslladar les cel·les provoca un error.
     * */
    public void addRows(int row, int shift) throws Exception {
        this.actualSheet.addRows(row, shift);
    }

    /**
     *
     * @param pos posició de la cel·la a eliminar.
     */
    public void deleteCell(String pos) {
        this.actualSheet.deleteCell(pos);
    }

    /**
     * Afegeix múltiples columnes.
     *
     * @param column columna on afegir les altres columnes.
     * @param shift número de columnes a afegir.
     * @throws Exception en cas de produir-se un error al afegir columnes.
     * */
    public void addColumns(String column, int shift) throws Exception {
        this.actualSheet.addColumns(column, shift);
    }

    /**
     * Elimina múltiples files.
     *
     * @param row fila on començar a eliminar.
     * @param shift número de files a eliminar.
     * @throws Exception en cas de produir-se un error a l'esborrar files.
     * */
    public void deleteRows(int row, int shift) throws Exception {
        this.actualSheet.deleteRows(row, shift);
    }

    /**
     * Elimina múltiples columnes.
     *
     * @param column columna on començar a eliminar.
     * @param shift número de columnes a eliminar.
     * @throws Exception en cas de produir-se un error a l'esborrar columnes.
     * */
    public void deleteColumns(String column, int shift) throws Exception {
        this.actualSheet.deleteColumns(column, shift);
    }

    /**
     * Copia una cel·la.
     *
     * @param fromRef posició de la cel·la que es vol copiar.
     * @param toPos posició de la cel·la a la qual es vol enganxar.
     * @throws Exception si existeix un problema amb les referències donades.
     * */
    public void copyCell(String fromRef, String toPos) throws Exception {
        if (fromRef.contains("-"))
            this.actualSheet.setCell(toPos, this.document.getValue(fromRef));
        else
            this.actualSheet.setCell(toPos, this.actualSheet.getValue(fromRef));
    }

    /**
     * Copia un bloc de cel·les de la mateixa fila.
     *
     * @param row fila del bloc que es copia.
     * @param initialColumnFrom columna de la posició inicial d'on es copia.
     * @param finalColumnTo columna de la posició final d'on es copia.
     * @param initialPosTo posició inicial on s'enganxa el bloc.
     * */
    private void copyRow(int row, String initialColumnFrom, String finalColumnTo, String initialPosTo) {
        Parser parser = new Parser();
        int initialRowTo = parser.getRow(initialPosTo);
        String initialColumnTo = parser.getColumn(initialPosTo);
        int iniColTo = parser.lettersToNumber(initialColumnTo);
        int colInicial = parser.lettersToNumber(initialColumnFrom);
        int colFinal = parser.lettersToNumber(finalColumnTo);
        for (int col = colInicial; col <= colFinal; col++) {
            String newPos = parser.numberToLetters(iniColTo++) + initialRowTo;
            String oldPos = parser.numberToLetters(col) + row;
            this.actualSheet.cloneCell(newPos, oldPos);
        }
    }

    /**
     * Copia un bloc de cel·les de la mateixa columna.
     *
     * @param column columna del bloc que es copia.
     * @param initialRowFrom fila de la posició inicial d'on es copia.
     * @param finalRowFrom fila de la posició final d'on es copia.
     * @param initialPosTo posició inicial on s'enganxa el bloc.
     * */
    private void copyColumn(String column, int initialRowFrom, int finalRowFrom, String initialPosTo) {
        Parser parser = new Parser();
        int initialRowTo = parser.getRow(initialPosTo);
        String initialColumnTo = parser.getColumn(initialPosTo);

        for (int fila = initialRowFrom; fila <= finalRowFrom; fila++) {
            String newPos = initialColumnTo + (initialRowTo++);
            String oldPos = column + fila;
            this.actualSheet.cloneCell(newPos, oldPos);
        }
    }

    /**
     * Copia un bloc de cel·les.
     *
     * @param initialPosFrom posició inicial del bloc de cel·les a copiar.
     * @param finalPosFrom posició final del bloc de cel·les a copiar.
     * @param initialPosTo posició inicial del bloc de cel·les a enganxar.
     * @throws Exception si existeix un problema amb les referències donades.
     * */
    public void copyBlockCells(String initialPosFrom, String finalPosFrom, String initialPosTo) throws Exception {
        Parser parser = new Parser();
        int initialRowFrom = parser.getRow(initialPosFrom);
        int finalRowFrom = parser.getRow(finalPosFrom);
        String initialColumnFrom = parser.getColumn(initialPosFrom);
        String finalColumnFrom = parser.getColumn(finalPosFrom);

        if (initialRowFrom == finalRowFrom)
            copyRow(initialRowFrom, initialColumnFrom, finalColumnFrom, initialPosTo);
        else if (initialColumnFrom.equals(finalColumnFrom))
            copyColumn(initialColumnFrom, initialRowFrom, finalRowFrom, initialPosTo);
        else
            throw new Exception("El bloc des del que es vol copiar conté més d'una fila i columna");
    }

    /**
     * Mou una cel·la.
     *
     * @param fromPos posició de la cel·la que es vol moure.
     * @param toPos posició de la cel·la a la qual es vol enganxar.
     * @throws Exception si existeix un problema amb les referències donades.
     * */
    public void moveCell(String fromPos, String toPos) throws Exception {
        this.actualSheet.setCell(toPos, this.actualSheet.getValue(fromPos));
        this.actualSheet.deleteCell(fromPos);
        this.document.changeReference(this.actualSheet.getSheetName(), fromPos, toPos);
    }

    /**
     * Mou un bloc de cel·les de la mateixa fila.
     *
     * @param row fila del bloc que es mou.
     * @param initialColumnFrom columna de la posició inicial d'on es mou.
     * @param finalColumnTo columna de la posició final d'on es mou.
     * @param initialPosTo posició inicial on s'enganxa el bloc.
     * */
    private void moveRow(int row, String initialColumnFrom, String finalColumnTo, String initialPosTo) throws Exception {
        Parser parser = new Parser();
        int initialRowTo = parser.getRow(initialPosTo);
        String initialColumnTo = parser.getColumn(initialPosTo);
        int iniColTo = parser.lettersToNumber(initialColumnTo);
        int colInicial = parser.lettersToNumber(initialColumnFrom);
        int colFinal = parser.lettersToNumber(finalColumnTo);
        ArrayList<String> oldPositions = new ArrayList<>();
        ArrayList<String> newPositions = new ArrayList<>();
        for (int col = colInicial; col <= colFinal; col++) {
            String newPos = parser.numberToLetters(iniColTo++) + initialRowTo;
            String oldPos = parser.numberToLetters(col) + row;
            this.actualSheet.cloneCell(newPos, oldPos);
            this.actualSheet.deleteCell(oldPos);
            oldPositions.add(oldPos);
            newPositions.add(newPos);
        }

        this.document.changeReferences(this.actualSheet.getSheetName(), oldPositions, newPositions);
    }

    /**
     * Mou un bloc de cel·les de la mateixa columna.
     *
     * @param column columna del bloc que es copia.
     * @param initialRowFrom fila de la posició inicial d'on es mou.
     * @param finalRowFrom fila de la posició final d'on es mou.
     * @param initialPosTo posició inicial on s'enganxa el bloc.
     * */
    private void moveColumn(String column, int initialRowFrom, int finalRowFrom, String initialPosTo) throws Exception {
        Parser parser = new Parser();
        int initialRowTo = parser.getRow(initialPosTo);
        String initialColumnTo = parser.getColumn(initialPosTo);

        ArrayList<String> oldPositions = new ArrayList<>();
        ArrayList<String> newPositions = new ArrayList<>();
        for (int fila = initialRowFrom; fila <= finalRowFrom; fila++) {
            String newPos = initialColumnTo + (initialRowTo++);
            String oldPos = column + fila;
            this.actualSheet.cloneCell(newPos, oldPos);
            this.actualSheet.deleteCell(oldPos);
            oldPositions.add(oldPos);
            newPositions.add(newPos);
        }

        this.document.changeReferences(this.actualSheet.getSheetName(), oldPositions, newPositions);
    }

    /**
     * Mou un bloc de cel·les.
     *
     * @param initialPosFrom posició inicial del bloc de cel·les a moure.
     * @param finalPosFrom posició final del bloc de cel·les a moure.
     * @param initialPosTo posició inicial del bloc de cel·les a enganxar.
     * @throws Exception si existeix un problema amb les referències donades.
     * */
    public void moveBlockCells(String initialPosFrom, String finalPosFrom, String initialPosTo) throws Exception {
        Parser parser = new Parser();
        int initialRowFrom = parser.getRow(initialPosFrom);
        int finalRowFrom = parser.getRow(finalPosFrom);
        String initialColumnFrom = parser.getColumn(initialPosFrom);
        String finalColumnFrom = parser.getColumn(finalPosFrom);

        if (initialRowFrom == finalRowFrom)
            moveRow(initialRowFrom, initialColumnFrom, finalColumnFrom, initialPosTo);
        else if (initialColumnFrom.equals(finalColumnFrom))
            moveColumn(initialColumnFrom, initialRowFrom, finalRowFrom, initialPosTo);
        else
            throw new Exception("El bloc des del que es vol moure conté més d'una fila i columna");
    }

    /**
     * Obté la llista amb els valors de les cel·les.
     *
     * @param fromPos posició inicial del bloc.
     * @param toPos posició final del bloc.
     * @return retorna la llista amb els valors.
     * @throws Exception si existeix un problema amb les referències donades.
     */
    private ArrayList<String> getValuesFromBlock(String fromPos, String toPos) throws Exception {
        ArrayList<String> values = new ArrayList<>();
        Parser parser = new Parser();
        String initialColumn = parser.getColumn(fromPos);
        String finalColumn = parser.getColumn(toPos);
        int initialRow = parser.getRow(fromPos);
        int finalRow = parser.getRow(toPos);
        if (initialColumn.equals(finalColumn)) {
            for (int row = initialRow; row <= finalRow; row++) {
                String pos = initialColumn + row;
                values.add(this.actualSheet.getValue(pos));
            }

            return values;
        }
        else if (initialRow == finalRow) {
            int initialColumnNum = parser.lettersToNumber(initialColumn);
            int finalColumnNum = parser.lettersToNumber(finalColumn);
            for (int col = initialColumnNum; col <= finalColumnNum; col++) {
                String pos = parser.numberToLetters(col) + initialRow;
                values.add(this.actualSheet.getValue(pos));
            }

            return values;
        }
        else
            throw new Exception("El bloc conté més d'una fila i columna");
    }

    /**
     * Ordena un bloc de cel·les.
     *
     * @param fromPos posició inicial del bloc.
     * @param toPos posició final del bloc.
     * @param list llista amb els valors.
     * @throws Exception si existeix un problema amb les referències donades.
     */
    private void setBlockCells(ArrayList<String> list, String fromPos, String toPos) throws Exception {
        Parser parser = new Parser();
        int initialRow = parser.getRow(fromPos);
        int finalRow = parser.getRow(toPos);
        int initialColumn = parser.lettersToNumber(parser.getColumn(fromPos));
        int finalColumn = parser.lettersToNumber(parser.getColumn(fromPos));
        if (initialColumn == finalColumn) {
            for (int row = initialRow; row <= finalRow; row++) {
                String pos = parser.numberToLetters(initialColumn) + row;
                this.actualSheet.setCell(pos, list.get(row - initialRow));
            }
        }
        else {
            for (int col = initialColumn; col <= finalColumn; col++) {
                String pos = parser.numberToLetters(col) + initialRow;
                this.actualSheet.setCell(pos, list.get(col - initialColumn));
            }
        }
    }

    /**
     * Ordena un bloc de cel·les en funció del tipus de ordenació.
     *
     * @param fromPos posició inicial del bloc.
     * @param toPos posició final del bloc.
     * @param typeSort tipus d'ordenació alfanumèrica, ascendent (ASC) o descendent (DESC).
     * @throws Exception si existeix un problema amb les referències donades.
     */
    public void sortBlockCells(String fromPos, String toPos, String typeSort) throws Exception {
        Parser parser = new Parser();
        if (!parser.validPosition(fromPos))
            throw new Exception("La posició inicial no és vàlida");
        if (!parser.validPosition(toPos))
            throw new Exception("La posició final no és vàlida");
        ArrayList<String> list = getValuesFromBlock(fromPos, toPos);
        if (typeSort.equals("ASC"))
            Collections.sort(list);
        else
            list.sort(Collections.reverseOrder());
        setBlockCells(list, fromPos, toPos);
    }

    /**
     * Cerca les cel·les que contenen un valor.
     *
     * @param value valor a cercar.
     * @return retorna les cel·les que contenen el valor donat.
     * @throws Exception no s'ha trobat el valor.
     */
    public ArrayList<String> search(String value) throws Exception {
        return this.actualSheet.search(value);
    }

    /**
     * Cerca les cel·les que contenen un valor i reemplaça el contingut d'aquestes cel·les per un altre.
     *
     * @param valueMatch valor a cercar.
     * @param valueReplace valor a reemplaçar.
     * @throws Exception si existeix un problema amb el contingut a substituir.
     */
    public void searchAndReplace(String valueMatch, String valueReplace) throws Exception {
        this.actualSheet.searchAndReplace(valueMatch, valueReplace);
    }

    /**
     * Exporta el document en format csv.
     * @param sheetName és el full a exportar
     * @throws Exception si no es pot exportar correctament.
     * */
    public void exportCSVDocument(String sheetName) throws Exception {
        ctrlPersistence.exportCSVDocument(this.document.getName(), sheetName, this.actualSheet.sheet2json().build());
    }

    /**
     * Desa el document en format json.
     *
     * @throws FileNotFoundException si es troba un error en desar el document.
     * */
    public void saveDocument() throws FileNotFoundException {
        ctrlPersistence.saveDocument(this.document.doc2json().build());
    }

    /**
     * Carrega un document en format json.
     *
     * @param path ruta on es troba el document.
     * @throws Exception si es troba un error en llegir el document.
     * */
    public void readDocument(String path) throws Exception {
        document.json2doc(ctrlPersistence.readDocument(path));
    }


    /**
     * Obte una llista amb els noms dels fulls.
     * @return Llista de tots els fulls.
     */
    public ArrayList<String> getAllSheets() {
        return document.getAllSheets();
    }
}
