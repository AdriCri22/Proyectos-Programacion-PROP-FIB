package controllers;

import classes.presentation.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa el controlador de presentació.
 * @author alex.moreno.baeza
 * @author irene.bertolin.rico
 * @author carla.canovas.iglesias
 */
public class CtrlPresentation {

    /**
     * Representa el controlador de domini.
     * */
    private CtrlDomain ctrlDomain;

    /**
     * Representa la vista principal del full de càlcul.
     * */
    private final MainView vistaPrincipal;

    // Constructora i mètodes d'inicialització

    /**
     * Constructora del controlador de presentació.
     *
     * @throws Exception si es produeix un error en crear el document.
     * */
    public CtrlPresentation() throws Exception {
        this.ctrlDomain = new CtrlDomain("El nostre full de càlcul");
        this.vistaPrincipal = new MainView(this);
    }


    /**
     * Fa visible la vista principal.
     * */
    public void openMainView() {
        vistaPrincipal.setVisible();
    }

    /**
     * Esborra la Cel·la.
     *
     * @param sheetName nom del full.
     * @param pos nom de la posició que es vol eliminar.
     */
    public void deleteCell(String sheetName, String pos) throws Exception {
        ctrlDomain.changeSheet(sheetName);
        ctrlDomain.deleteCell(pos);
    }


    /**
     * Crea un nou document.
     *
     * @param name nom del Document.
     * */
    public void callNewDocument (String name) throws Exception {
        ctrlDomain = new CtrlDomain(name);
    }

    /**
     * Obtenir totes les Cel·les amb el seu valor.
     *
     * @param sheetName es el nom del full.
     * @return Retorna totes les posicions de les cel·les que tenen valor i el seu valor.
     */
    public HashMap<String,String> getAllCells(String sheetName) throws Exception {
        return ctrlDomain.getAllCells(sheetName);
    }
    /**
     * Obre un document existent.
     *
     * @path path del document que es vol obrir.
     * @throws Exception si es troba un error en llegir el document.
     * */
    public void callOpenDocument (String path) throws Exception{
         ctrlDomain.readDocument(path);
    }

    /**
     * Guarda un document.
     *
     * @throws FileNotFoundException si es troba un error en desar el document.
     * */
    public void callSaveDocument () throws FileNotFoundException {
        ctrlDomain.saveDocument();
    }

    /**
     * Exporta el full actual del document a csv.
     *
     * @param full Full que es vol exportar.
     * @throws Exception si no es pot exportar correctament.
     * */
    public void callExportDocument (String full) throws Exception {
        ctrlDomain.exportCSVDocument(full);
    }

    /**
     * Copia una cel·la o bloc de cel·les.
     *
     * @param fromCell és la cel·la a copiar.
     * @param toCell és la cel·la on es vol copiar.
     * @throws Exception si existeix un problema amb les referències donades.
     * */
    public void callCopy(String fromCell, String fromCellFinal, String toCell, int numRows, int numCols) throws Exception {
        if (numRows == 1 && numCols == 1) ctrlDomain.copyCell(fromCell, toCell);
        else ctrlDomain.copyBlockCells(fromCell, fromCellFinal, toCell);
    }


    /**
     * Mou una cel·la o bloc de cel·les.
     *
     * @param fromCell 3el·la inicial a moure.
     * @param fromCellFinal cel·la final a moure.
     * @param toCell cel·la inicial on es vol moure.
     * @param numRows número de files.
     * @param numCols número de columnes.
     * @throws Exception si existeix un problema amb les referències donades.
     */
    public void callMove(String fromCell, String fromCellFinal, String toCell, int numRows, int numCols) throws Exception {
        if (numRows == 1 && numCols == 1) ctrlDomain.moveCell(fromCell, toCell);
        else ctrlDomain.moveBlockCells(fromCell, fromCellFinal, toCell);
    }

    /**
     * Assigna un contingut a una cel·la.
     *
     * @param sheetName nom del full.
     * @param pos posició de la cel·la.
     * @param result nou contingut.
     * @throws Exception si el contingut no és correcte.
     */
    public void setCell(String sheetName, String pos, String result) throws Exception {
        ctrlDomain.changeSheet(sheetName);
        ctrlDomain.setCell(pos,result);
    }
    /**
     * Donat un valor busca quines cel·les el contenen.
     *
     * @param value és el valor a buscar.
     * @throws Exception no s'ha trobat el valor.
     * */
    public void callSearchValue (String value) throws Exception {
       ArrayList<String> cellsPositions = ctrlDomain.search(value);
       vistaPrincipal.paintCells(cellsPositions);
    }

    /**
     * Donat un valor busca quines cel·les el contenen i les reemplaça amb un de nou.
     *
     * @param valueMatch és el valor a buscar.
     * @param valueReplace és el valor amb el qual es vol reemplaçar.
     * @throws Exception si existeix un problema amb el contingut a substituir.
     * */
    public void callSearchAndReplaceValue (String valueMatch, String valueReplace) throws Exception {
        ctrlDomain.searchAndReplace(valueMatch, valueReplace);
    }

    /**
     * Afegeix un cert nombre de files al full.
     *
     * @param row és la fila inicial des d'on afegir.
     * @param shift és el nombre de files afegir.
     * @throws Exception si al traslladar les cel·les provoca un error.
     * */
    public void callAddRows (int row, int shift) throws Exception {
        ctrlDomain.addRows(row, shift);
    }

    /**
     * Afegeix un cert nombre de columnes.
     *
     * @param col és la columna inicial des d'on afegir.
     * @param shift és el nombre de columnes afegir.
     * @throws Exception en cas de produir-se un error al afegir columnes.
     * */
    public void callAddColumns (String col, int shift) throws Exception {
        ctrlDomain.addColumns(col, shift);
    }

    /**
     * Elimina un cert nombre de files.
     *
     * @param row és la fila inicial des d'on eliminar.
     * @param shift és el nombre de files a afegir.
     * @throws Exception en cas de produir-se un error a l'esborrar files.
     * */
    public void callDeleteRows (int row, int shift) throws Exception {
        ctrlDomain.deleteRows(row, shift);
    }

    /**
     * Elimina un cert nombre de columnes.
     *
     * @param col és la columna inicial des d'on eliminar.
     * @param shift és el nombre de columnes eliminar.
     * @throws Exception en cas de produir-se un error a l'esborrar columnes.
     * */
    public void callDeleteColumns (String col, int shift) throws Exception {
        ctrlDomain.deleteColumns(col, shift);
    }

    /**
     * Ordena un bloc de cel·les.
     *
     * @param fromPos és la posició inicial del bloc de cel·les.
     * @param toPos és la posició final del bloc de cel·les.
     * @param typeSort és el tipus d'ordenació que es vol pel bloc.
     * @throws Exception si existeix un problema amb les referències donades.
     * */
    public void callSortCellBloc (String fromPos, String toPos, String typeSort) throws Exception {
        ctrlDomain.sortBlockCells(fromPos, toPos, typeSort);
    }

    /**
     * Afegeix un full al document.
     *
     * @param sheetName és el nom que es vol donar al full.
     * @throws Exception si ja existeix un full amb aquest nom.
     * */
    public void callAddSheet(String sheetName) throws Exception {
        ctrlDomain.addSheet(sheetName);
    }

    /**
     * Elimina un full del document.
     *
     * @param sheetName és el nom del full a eliminar.
     * @throws Exception si no existeix un full amb aquest nom.
     * */
    public void callDeleteSheet (String sheetName) throws Exception {
        ctrlDomain.deleteSheet(sheetName);
    }

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                () -> {
                    CtrlPresentation ctrlPresentation = null;
                    try {
                        ctrlPresentation = new CtrlPresentation();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    assert ctrlPresentation != null;
                    ctrlPresentation.openMainView();
                });
    }

    /**
     * Obte el contingut d'una cel·la donat el seu full i la seva posició.
     * @param sheetName nom full
     * @param pos posicio de la cel·la
     * @return Retorna el contingut de la cel·la
     * @throws Exception si no existeix un full amb aquest nom
     */

    public String getContent(String sheetName, String pos) throws Exception {
        ctrlDomain.changeSheet(sheetName);
        return ctrlDomain.getContent(pos);
    }

    /**
     * Obté el nom del document actual.
     * @return Retorna el nom del document
     */
    public String getNameDoc() {
        return ctrlDomain.getDocumentName();
    }

    /**
     * Obte una llista amb els noms dels fulls.
     * @return Llista de tots els fulls.
     */
    public ArrayList<String> getAllSheets() {
        return ctrlDomain.getAllSheets();
    }
}