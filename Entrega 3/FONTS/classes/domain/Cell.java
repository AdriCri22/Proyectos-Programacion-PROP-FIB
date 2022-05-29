package classes.domain;

import classes.Parser;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Representa una cel·la.
 * @author alex.moreno.baeza
 */
public class Cell implements Cloneable {
    /**
     * Representa la posició de la cel·la.
     * */
    private String position;
    /**
     * Representa el full on es troba la cel·la.
     * */
    private final Sheet sheet;
    /**
     * Representa el valor de la cel·la.
     * */
    private String value;
    /**
     * Representa el contingut de la cel·la.
     * */
    private String content;
    /**
     * Representa les referències que conté aquesta cel·la.
     * */
    private final HashSet<Cell> myReferences = new HashSet<>();
    /**
     * Representa les cel·les que referencien a aquesta cel·la.
     * */
    private final HashSet<Cell> referencesToMe = new HashSet<>();

    /**
     * Constructora amb el full, la posició i el contingut.
     *
     * @param sheet full on es troba la cel·la.
     * @param position posició de la cel·la.
     * @param content contingut de la cel·la.
     * @throws Exception si el contingut de la cel·la no es pot parsejar.
     * */
    public Cell(Sheet sheet, String position, String content) throws Exception {
        this.sheet = sheet;
        this.position = position;
        setCell(content);
    }

    /**
     * Constructora amb el full, la posició, el valor i el contingut.
     *
     * @param sheet full on es troba la cel·la.
     * @param position posició de la cel·la.
     * @param value valor de la cel·la.
     * @param content contingut de la cel·la.
     * */
    public Cell(Sheet sheet, String position, String value, String content) {
        this.sheet = sheet;
        this.position = position;
        this.value = value;
        this.content = content;
    }

    /**
     * Modifica la cel·la i actualitza el contingut de les cel·les que contenen a aquesta referenciada.
     *
     * @param content contingut de la cel·la.
     * @throws Exception si el contingut no és correcte.
     * */
    public void setCell(String content) throws Exception {
        this.content = content;
        update(true);
    }

    /**
     * Obté el valor de la cel·la.
     *
     * @return retorna el valor de la cel·la.
     * */
    public String getValue() {
        return this.value;
    }

    /**
     * Obté el valor de la cel·la referenciada.
     *
     * @param reference referencia.
     * @return retorna el valor de la cel·la referenciada.
     * @throws Exception la cel·la referenciada no és correcte.
     * */
    public String getValue(String reference) throws Exception {
        return this.sheet.getCell(reference).getValue();
    }

    /**
     * Obté el contingut de la cel·la.
     *
     * @return retorna el contingut de la cel·la.
     * */
    public String getContent() {
        return this.content;
    }

    /**
     * Modifica la posició de la cel·la.
     *
     * @param pos és la nova posició.
     * */
    public void setPosition(String pos) {
        this.position = pos;
    }

    /**
     * Obté una còpia de la cel·la.
     *
     * @return retorna una còpia de la cel·la.
     * */
    @Override
    public Cell clone() {
        try {
            return (Cell) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /**
     * Obté la cel·la en format json.
     *
     * @return retorna la cel·la en format json.
     * */
    public JsonObjectBuilder cell2json() {
        JsonObjectBuilder cela = Json.createObjectBuilder();
        cela.add("value", this.value);
        if (this.content != null)
            cela.add("content", this.content);

        return cela;
    }

    /**
     * Esborra la cel·la si té valor per defecte.
     * */
    private void deleteDefaultCell() {
        if (this.referencesToMe.isEmpty() && this.myReferences.isEmpty() && this.content.equals("0"))
            this.sheet.deleteCell(this.position);
    }

    /**
     * Afegeix una referència a aquesta cel·la.
     *
     * @param cell cel·la a afegir.
     * */
    private void addReferenceToMe(Cell cell) {
        referencesToMe.add(cell);
    }

    /**
     * Esborra una referència a aquesta cel·la.
     *
     * @param cell cel·la a suprimir.
     * */
    private void deleteReferenceToMe(Cell cell) {
        this.referencesToMe.remove(cell);
    }

    /**
     * Esborra totes les referencies d'aquesta cel·la.
     * */
    private void deleteAllMyReferences() {
        for (Cell cell : this.myReferences) {
            cell.deleteReferenceToMe(this);
            cell.deleteDefaultCell();
        }
        this.myReferences.clear();
    }

    /**
     * Actualitza les cel·les que contenen referenciades a aquesta.
     *
     * @throws Exception si existeix algun problema amb les referències.
     * */
    private void updateAll() throws Exception {
        for (Cell cell : this.referencesToMe)
            cell.update(false);
    }

    /**
     * Actualitza la cel·la.
     *
     * @throws Exception si existeix algun problema amb les referències.
     * */
    private void update(boolean change) throws Exception {
        Parser parser = new Parser(this, this.content);
        this.value = parser.getResult();
        if (change) {
            HashSet<String> referencies = parser.getReferences();
            deleteAllMyReferences();
            for (String position : referencies) {
                Cell cell = this.sheet.getCell(position);
                this.myReferences.add(cell);
                cell.addReferenceToMe(this);
            }
        }
        updateAll();
    }

    /**
     * Modifica les referències si han canviat.
     *
     * @param sheetName nom del full.
     * @param oldPos posició antiga.
     * @param newPos posició nova.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReference(String sheetName, String oldPos, String newPos) throws Exception {
        Parser parser = new Parser(this.content);
        if (this.myReferences.size() == 1 || this.myReferences.size() == 2) {
            if (this.content.charAt(0) == '$') {
                String ref = '$' + sheetName + '-' + oldPos;
                if (ref.equals(this.content))
                    setCell('$' + sheetName + '-' + newPos);
            } else if (this.content.charAt(0) == '=') {
                String ref = '$' + sheetName + '-' + oldPos;
                if (this.content.indexOf(',') == -1) {
                    if (ref.equals(parser.getReference()))
                        setCell("=" + parser.getFunction() + "($" + sheetName + '-' + newPos + ")");
                } else {
                    String op1 = parser.getFirstOperator();
                    String op2 = parser.getSecondOperator();
                    if (op1.charAt(0) == '$') {
                        String pos1 = parser.getPosition(op1);
                        String sheet1 = parser.getSheetName(op1);
                        if (sheet1.equals(sheetName) && oldPos.equals(pos1)) {
                            op1 = '$' + sheetName + '-' + newPos;
                            setCell("=" + parser.getFunction() + '(' + op1 + "," + op2 + ")");
                        }
                    }
                    if (op2.charAt(0) == '$') {
                        String pos2 = parser.getPosition(op2);
                        String sheet2 = parser.getSheetName(op2);
                        if (sheet2.equals(sheetName) && oldPos.equals(pos2)) {
                            setCell("=" + parser.getFunction() + "(" + op1 + ",$" + sheetName + '-' + newPos + ")");
                        }
                    }
                }
            }
        }
    }

    /**
     * Modifica les referències si han canviat.
     *
     * @param sheetName nom del full.
     * @param oldPositions posicions antigues.
     * @param newPositions posicions noves.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReferences(String sheetName, ArrayList<String> oldPositions, ArrayList<String> newPositions) throws Exception {
        Parser parser = new Parser(this.content);
        if (this.myReferences.size() == 1 || this.myReferences.size() == 2) {
            if (this.content.charAt(0) == '$') {
                for (int i = 0; i < oldPositions.size(); i++) {
                    String ref = '$' + sheetName + '-' + oldPositions.get(i);
                    if (ref.equals(this.content)) {
                        setCell('$' + sheetName + '-' + newPositions.get(i));
                        break;
                    }
                }
            } else if (this.content.charAt(0) == '=') {
                if (this.content.indexOf(',') == -1) {
                    for (int i = 0; i < oldPositions.size(); i++) {
                        String ref = '$' + sheetName + '-' + oldPositions.get(i);
                        if (ref.equals(parser.getReference())) {
                            setCell("=" + parser.getFunction() + "($" + sheetName + '-' + newPositions.get(i) + ")");
                            break;
                        }
                    }
                } else {
                    boolean change = false;
                    String op1 = parser.getFirstOperator();
                    String op2 = parser.getSecondOperator();
                    if (op1.charAt(0) == '$') {
                        String pos1 = parser.getPosition(op1);
                        String sheet1 = parser.getSheetName(op1);
                        if (sheet1.equals(sheetName) && oldPositions.contains(pos1)) {
                            int i = oldPositions.indexOf(pos1);
                            op1 = '$' + sheetName + '-' + newPositions.get(i);
                            change = true;
                        }
                    }
                    if (op2.charAt(0) == '$') {
                        String pos2 = parser.getPosition(op2);
                        String sheet2 = parser.getSheetName(op2);
                        if (sheet2.equals(sheetName) && oldPositions.contains(pos2)) {
                            int i = oldPositions.indexOf(pos2);
                            change = true;
                            op2 = '$' + sheetName + '-' + newPositions.get(i);
                        }
                    }
                    if (change)
                        setCell("=" + parser.getFunction() + '(' + op1 + "," + op2 + ")");
                }
            }
        } else if (this.myReferences.size() > 2) {
            if (this.content.indexOf(',') == -1) {
                String op = parser.getOperator();
                String sheet = parser.getSheetName(op);
                String initialPosition = op.substring(op.indexOf('-') + 1, op.indexOf(':'));
                String finalPosition = op.substring(op.indexOf(':') + 1);
                if (sheet.equals(sheetName) && oldPositions.contains(initialPosition) && oldPositions.contains(finalPosition)) {
                    int startIndex = oldPositions.indexOf(initialPosition);
                    int endIndex = oldPositions.indexOf(finalPosition);
                    setCell('=' + parser.getFunction() + "($" + sheetName + '-' + newPositions.get(startIndex) + ':' + newPositions.get(endIndex) + ')');
                }
            } else {
                boolean change = false;
                String op1 = parser.getFirstOperator();
                String op2 = parser.getSecondOperator();
                String sheet = parser.getSheetName(op1);
                String initialPosition1 = op1.substring(op1.indexOf('-') + 1, op1.indexOf(':'));
                String finalPosition1 = op1.substring(op1.indexOf(':') + 1);
                if (sheet.equals(sheetName) && oldPositions.contains(initialPosition1) && oldPositions.contains(finalPosition1)) {
                    int startIndex = oldPositions.indexOf(initialPosition1);
                    int endIndex = oldPositions.indexOf(finalPosition1);
                    op1 = '$' + sheetName + '-' + newPositions.get(startIndex) + ':' + newPositions.get(endIndex);
                    change = true;
                }
                String sheet2 = parser.getSheetName(op2);
                String initialPosition2 = op2.substring(op2.indexOf('-') + 1, op2.indexOf(':'));
                String finalPosition2 = op2.substring(op2.indexOf(':') + 1);
                if (sheet2.equals(sheetName) && oldPositions.contains(initialPosition2) && oldPositions.contains(finalPosition2)) {
                    int startIndex = oldPositions.indexOf(initialPosition2);
                    int endIndex = oldPositions.indexOf(finalPosition2);
                    op2 = '$' + sheetName + '-' + newPositions.get(startIndex) + ':' + newPositions.get(endIndex);
                    change = true;
                }

                if (change)
                    setCell('=' + parser.getFunction() + '(' + op1 + ',' + op2 + ')');
            }
        }
    }

    /**
     * Modifica les referències en afegir files.
     *
     * @param sheetName nom del full.
     * @param row fila des de la qual s'afegeix.
     * @param shift nombre de files afegides.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReferencesAddRows(String sheetName, int row, int shift) throws Exception {
        Parser parser = new Parser(this.content);
        if (this.myReferences.size() == 1 || this.myReferences.size() == 2) {
            if (this.content.charAt(0) == '$') {
                String sheet = parser.getSheetName();
                String position = parser.getPosition();
                int actualRow = parser.getRow(position);
                String actualColumn = parser.getColumn(position);
                if (sheet.equals(sheetName) && actualRow >= row)
                    setCell('$' + sheetName + '-' + actualColumn + (actualRow + shift));
            } else if (this.content.charAt(0) == '=') {
                if (this.content.indexOf(',') == -1) {
                    String sheet = parser.getSheetName();
                    String position = parser.getPosition();
                    int actualRow = parser.getRow(position);
                    String actualColumn = parser.getColumn(position);
                    if (sheet.equals(sheetName) && actualRow >= row)
                        setCell('=' + parser.getFunction() + "($" + sheetName + '-' + actualColumn + (actualRow + shift) + ')');
                } else {
                    boolean change = false;
                    String op1 = parser.getFirstOperator();
                    String op2 = parser.getSecondOperator();
                    if (op1.charAt(0) == '$') {
                        String pos1 = parser.getPosition(op1);
                        String sheet1 = parser.getSheetName(op1);
                        int actualRow1 = parser.getRow(pos1);
                        String actualColumn1 = parser.getColumn(pos1);
                        if (sheet1.equals(sheetName) && actualRow1 >= row) {
                            change = true;
                            op1 = '$' + sheetName + '-' + actualColumn1 + (actualRow1 + shift);
                        }
                    }
                    if (op2.charAt(0) == '$') {
                        String pos2 = parser.getPosition(op2);
                        String sheet2 = parser.getSheetName(op2);
                        int actualRow2 = parser.getRow(pos2);
                        String actualColumn2 = parser.getColumn(pos2);
                        if (sheet2.equals(sheetName) && actualRow2 >= row) {
                            change = true;
                            op2 = '$' + sheetName + '-' + actualColumn2 + (actualRow2 + shift);
                        }
                    }
                    if (change)
                        setCell("=" + parser.getFunction() + '(' + op1 + "," + op2 + ")");
                }
            }
        } else if (this.myReferences.size() > 2) {
            if (this.content.indexOf(',') == -1) {
                String op = parser.getOperator();
                String sheet = parser.getSheetName(op);
                String initialPosition = op.substring(op.indexOf('-') + 1, op.indexOf(':'));
                String finalPosition = op.substring(op.indexOf(':') + 1);
                int initialRow = parser.getRow(initialPosition);
                int finalRow = parser.getRow(finalPosition);
                String initialColumn = parser.getColumn(initialPosition);
                String finalColumn = parser.getColumn(finalPosition);
                if (sheet.equals(sheetName) && initialRow == finalRow && initialRow >= row)
                    setCell('=' + parser.getFunction() + "($" + sheetName + '-' + initialColumn + (initialRow + shift) + ':' + finalColumn + (finalRow + shift) + ')');
            } else {
                String op1 = parser.getFirstOperator();
                String op2 = parser.getSecondOperator();
                boolean change = false;
                if (op1.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op1);
                    String initialPosition = op1.substring(op1.indexOf('-') + 1, op1.indexOf(':'));
                    String finalPosition = op1.substring(op1.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    String initialColumn = parser.getColumn(initialPosition);
                    String finalColumn = parser.getColumn(finalPosition);
                    if (sheet.equals(sheetName) && initialRow == finalRow && initialRow >= row) {
                        change = true;
                        op1 = '$' + sheetName + '-' + initialColumn + (initialRow + shift) + ':' + finalColumn + (finalRow + shift);
                    }
                } if (op2.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op2);
                    String initialPosition = op2.substring(op2.indexOf('-') + 1, op2.indexOf(':'));
                    String finalPosition = op2.substring(op2.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    String initialColumn = parser.getColumn(initialPosition);
                    String finalColumn = parser.getColumn(finalPosition);
                    if (sheet.equals(sheetName) && initialRow == finalRow && initialRow >= row) {
                        change = true;
                        op2 = '$' + sheetName + '-' + initialColumn + (initialRow + shift) + ':' + finalColumn + (finalRow + shift);
                    }
                }
                if (change)
                    setCell('=' + parser.getFunction() + '(' + op1 + ',' + op2 + ')');
            }
        }
    }

    /**
     * Modifica les referències en afegir columnes.
     *
     * @param sheetName nom del full.
     * @param columnName columna des de la qual s'afegeix.
     * @param shift nombre de columnes afegides.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReferencesAddColumns(String sheetName, String columnName, int shift) throws Exception {
        Parser parser = new Parser(this.content);
        int column = parser.lettersToNumber(columnName);
        if (this.myReferences.size() == 1 || this.myReferences.size() == 2) {
            if (this.content.charAt(0) == '$') {
                String sheet = parser.getSheetName();
                String position = parser.getPosition();
                int actualRow = parser.getRow(position);
                int actualColumn = parser.lettersToNumber(parser.getColumn(position));
                if (sheet.equals(sheetName) && actualColumn >= column)
                    setCell('$' + sheetName + '-' + parser.numberToLetters(actualColumn + shift) + actualRow);
            } else if (this.content.charAt(0) == '=') {
                if (this.content.indexOf(',') == -1) {
                    String sheet = parser.getSheetName();
                    String position = parser.getPosition();
                    int actualRow = parser.getRow(position);
                    int actualColumn = parser.lettersToNumber(parser.getColumn(position));
                    if (sheet.equals(sheetName) && actualColumn >= column)
                        setCell('=' + parser.getFunction() + "($" + sheetName + '-' + parser.numberToLetters(actualColumn + shift) + actualRow);
                } else {
                    boolean change = false;
                    String op1 = parser.getFirstOperator();
                    String op2 = parser.getSecondOperator();
                    if (op1.charAt(0) == '$') {
                        String pos1 = parser.getPosition(op1);
                        String sheet1 = parser.getSheetName(op1);
                        int actualRow1 = parser.getRow(pos1);
                        int actualColumn1 = parser.lettersToNumber(parser.getColumn(pos1));
                        if (sheet1.equals(sheetName) && actualColumn1 >= column) {
                            change = true;
                            op1 = '$' + sheetName + '-' + parser.numberToLetters(actualColumn1 + shift) + actualRow1;
                        }
                    }
                    if (op2.charAt(0) == '$') {
                        String pos2 = parser.getPosition(op2);
                        String sheet2 = parser.getSheetName(op2);
                        int actualRow2 = parser.getRow(pos2);
                        int actualColumn2 = parser.lettersToNumber(parser.getColumn(pos2));
                        if (sheet2.equals(sheetName) && actualColumn2 >= column) {
                            change = true;
                            op2 = '$' + sheetName + '-' + parser.numberToLetters(actualColumn2 + shift) + actualRow2;
                        }
                    }
                    if (change)
                        setCell("=" + parser.getFunction() + '(' + op1 + "," + op2 + ")");
                }
            }
        } else if (this.myReferences.size() > 2) {
            if (this.content.indexOf(',') == -1) {
                String op = parser.getOperator();
                String sheet = parser.getSheetName(op);
                String initialPosition = op.substring(op.indexOf('-') + 1, op.indexOf(':'));
                String finalPosition = op.substring(op.indexOf(':') + 1);
                int initialRow = parser.getRow(initialPosition);
                int finalRow = parser.getRow(finalPosition);
                int initialColumn = parser.lettersToNumber(parser.getColumn(initialPosition));
                int finalColumn = parser.lettersToNumber(parser.getColumn(finalPosition));
                if (sheet.equals(sheetName) && initialColumn == finalColumn && initialColumn >= column)
                    setCell('=' + parser.getFunction() + "($" + sheetName + '-' + parser.numberToLetters(initialColumn + shift) + initialRow + ':' + parser.numberToLetters(finalColumn + shift) + finalRow + ')');
            } else {
                String op1 = parser.getFirstOperator();
                String op2 = parser.getSecondOperator();
                boolean change = false;
                if (op1.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op1);
                    String initialPosition = op1.substring(op1.indexOf('-') + 1, op1.indexOf(':'));
                    String finalPosition = op1.substring(op1.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    int initialColumn = parser.lettersToNumber(parser.getColumn(initialPosition));
                    int finalColumn = parser.lettersToNumber(parser.getColumn(finalPosition));
                    if (sheet.equals(sheetName) && initialColumn == finalColumn && initialColumn >= column) {
                        change = true;
                        op1 = '$' + sheetName + '-' + parser.numberToLetters(initialColumn + shift) + initialRow + ':' + parser.numberToLetters(finalColumn + shift) + finalRow;
                    }
                } if (op2.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op2);
                    String initialPosition = op2.substring(op2.indexOf('-') + 1, op2.indexOf(':'));
                    String finalPosition = op2.substring(op2.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    int initialColumn = parser.lettersToNumber(parser.getColumn(initialPosition));
                    int finalColumn = parser.lettersToNumber(parser.getColumn(finalPosition));
                    if (sheet.equals(sheetName) && initialColumn == finalColumn && initialColumn >= column) {
                        change = true;
                        op2 = '$' + sheetName + '-' + parser.numberToLetters(initialColumn + shift) + initialRow + ':' + parser.numberToLetters(finalColumn + shift) + finalRow;
                    }
                }
                if (change)
                    setCell('=' + parser.getFunction() + '(' + op1 + ',' + op2 + ')');
            }
        }
    }

    /**
     * Modifica les referències en eliminar files.
     *
     * @param sheetName nom del full.
     * @param row fila des de la qual s'elimina.
     * @param shift nombre de files eliminades.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReferencesDeleteRows(String sheetName, int row, int shift) throws Exception {
        Parser parser = new Parser(this.content);
        if (this.myReferences.size() == 1 || this.myReferences.size() == 2) {
            if (this.content.charAt(0) == '$') {
                String sheet = parser.getSheetName();
                String position = parser.getPosition();
                int actualRow = parser.getRow(position);
                String actualColumn = parser.getColumn(position);
                if (sheet.equals(sheetName)) {
                    if (actualRow > row && actualRow - shift > 0)
                        setCell('$' + sheetName + '-' + actualColumn + (actualRow + shift));
                    else if (actualRow == row || actualRow - shift <= 0)
                        setCell("0");
                }
            } else if (this.content.charAt(0) == '=') {
                if (this.content.indexOf(',') == -1) {
                    String sheet = parser.getSheetName();
                    String position = parser.getPosition();
                    int actualRow = parser.getRow(position);
                    String actualColumn = parser.getColumn(position);
                    if (sheet.equals(sheetName)) {
                        if (actualRow > row && actualRow - shift > 0)
                            setCell('=' + parser.getFunction() + "($" + sheetName + '-' + actualColumn + (actualRow + shift) + ')');
                        else if (actualRow == row || actualRow - shift <= 0)
                            setCell("0");
                    }
                } else {
                    boolean change = false;
                    boolean delete = false;
                    String op1 = parser.getFirstOperator();
                    String op2 = parser.getSecondOperator();
                    if (op1.charAt(0) == '$') {
                        String pos1 = parser.getPosition(op1);
                        String sheet1 = parser.getSheetName(op1);
                        int actualRow1 = parser.getRow(pos1);
                        String actualColumn1 = parser.getColumn(pos1);
                        if (sheet1.equals(sheetName)) {
                            if (actualRow1 > row && actualRow1 - shift > 0) {
                                change = true;
                                op1 = '$' + sheetName + '-' + actualColumn1 + (actualRow1 + shift);
                            } else if (actualRow1 == row || actualRow1 - shift <= 0)
                                delete = true;
                        }
                    }
                    if (op2.charAt(0) == '$') {
                        String pos2 = parser.getPosition(op2);
                        String sheet2 = parser.getSheetName(op2);
                        int actualRow2 = parser.getRow(pos2);
                        String actualColumn2 = parser.getColumn(pos2);
                        if (sheet2.equals(sheetName)) {
                            if (actualRow2 > row && actualRow2 - shift > 0) {
                                change = true;
                                op2 = '$' + sheetName + '-' + actualColumn2 + (actualRow2 + shift);
                            } else if (actualRow2 == row || actualRow2 - shift <= 0)
                                delete = true;
                        }
                    }
                    if (delete)
                        setCell("0");
                    else if (change)
                        setCell("=" + parser.getFunction() + '(' + op1 + "," + op2 + ")");
                }
            }
        } else if (this.myReferences.size() > 2) {
            if (this.content.indexOf(',') == -1) {
                String op = parser.getOperator();
                String sheet = parser.getSheetName(op);
                String initialPosition = op.substring(op.indexOf('-') + 1, op.indexOf(':'));
                String finalPosition = op.substring(op.indexOf(':') + 1);
                int initialRow = parser.getRow(initialPosition);
                int finalRow = parser.getRow(finalPosition);
                String initialColumn = parser.getColumn(initialPosition);
                String finalColumn = parser.getColumn(finalPosition);
                if (sheet.equals(sheetName) && initialRow == finalRow) {
                    if (initialRow > row && initialRow - shift > 0)
                        setCell('=' + parser.getFunction() + "($" + sheetName + '-' + initialColumn + (initialRow + shift) + ':' + finalColumn + (finalRow + shift) + ')');
                    else if (initialRow == row || initialRow - shift <= 0)
                        setCell("0");
                }
            } else {
                String op1 = parser.getFirstOperator();
                String op2 = parser.getSecondOperator();
                boolean change = false;
                boolean delete = false;
                if (op1.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op1);
                    String initialPosition = op1.substring(op1.indexOf('-') + 1, op1.indexOf(':'));
                    String finalPosition = op1.substring(op1.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    String initialColumn = parser.getColumn(initialPosition);
                    String finalColumn = parser.getColumn(finalPosition);
                    if (sheet.equals(sheetName) && initialRow == finalRow) {
                        if (initialRow > row && initialRow - shift > 0) {
                            change = true;
                            op1 = '$' + sheetName + '-' + initialColumn + (initialRow + shift) + ':' + finalColumn + (finalRow + shift);
                        } else if (initialRow == row || initialRow - shift <= 0)
                            delete = true;
                    }
                } if (op2.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op2);
                    String initialPosition = op2.substring(op2.indexOf('-') + 1, op2.indexOf(':'));
                    String finalPosition = op2.substring(op2.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    String initialColumn = parser.getColumn(initialPosition);
                    String finalColumn = parser.getColumn(finalPosition);
                    if (sheet.equals(sheetName) && initialRow == finalRow) {
                        if (initialRow > row && initialRow - shift > 0) {
                            change = true;
                            op2 = '$' + sheetName + '-' + initialColumn + (initialRow + shift) + ':' + finalColumn + (finalRow + shift);
                        } else if (initialRow == row || initialRow - shift <= 0)
                            delete = true;
                    }
                }
                if (delete)
                    setCell("0");
                else if (change)
                    setCell('=' + parser.getFunction() + '(' + op1 + ',' + op2 + ')');
            }
        }

        deleteDefaultCell();
    }

    /**
     * Modifica les referències en eliminar columnes.
     *
     * @param sheetName nom del full.
     * @param columnName columna des de la qual s'elimina.
     * @param shift nombre de columnes eliminades.
     * @throws Exception llença una excepció si es produeix algun error en canviar el contingut.
     * */
    public void changeReferencesDeleteColumns(String sheetName, String columnName, int shift) throws Exception {
        Parser parser = new Parser(this.content);
        int column = parser.lettersToNumber(columnName);
        if (this.myReferences.size() > 0 && this.content.indexOf(':') == -1) {
            if (this.content.charAt(0) == '$') {
                String sheet = parser.getSheetName();
                String position = parser.getPosition();
                int actualRow = parser.getRow(position);
                int actualColumn = parser.lettersToNumber(parser.getColumn(position));
                if (sheet.equals(sheetName)) {
                    if (actualColumn > column && actualColumn - shift > 0)
                        setCell('$' + sheetName + '-' + parser.numberToLetters(actualColumn - shift) + actualRow);
                    else if (actualColumn == column || actualColumn - shift <= 0)
                        setCell("0");
                }
            } else if (this.content.charAt(0) == '=') {
                if (this.content.indexOf(',') == -1) {
                    String sheet = parser.getSheetName();
                    String position = parser.getPosition();
                    int actualRow = parser.getRow(position);
                    int actualColumn = parser.lettersToNumber(parser.getColumn(position));
                    if (sheet.equals(sheetName)) {
                        if (actualColumn > column && actualColumn - shift > 0)
                            setCell('=' + parser.getFunction() + "($" + sheetName + '-' + parser.numberToLetters(actualColumn - shift) + actualRow);
                        else if (actualColumn == column || actualColumn - shift <= 0)
                            setCell("0");
                    }
                } else {
                    boolean change = false;
                    boolean delete = false;
                    String op1 = parser.getFirstOperator();
                    String op2 = parser.getSecondOperator();
                    if (op1.charAt(0) == '$') {
                        String pos1 = parser.getPosition(op1);
                        String sheet1 = parser.getSheetName(op1);
                        int actualRow1 = parser.getRow(pos1);
                        int actualColumn1 = parser.lettersToNumber(parser.getColumn(pos1));
                        if (sheet1.equals(sheetName)) {
                            if (actualColumn1 > column && actualColumn1 - shift > 0) {
                                change = true;
                                op1 = '$' + sheetName + '-' + parser.numberToLetters(actualColumn1 - shift) + actualRow1;
                            } else if (actualColumn1 == column || actualColumn1 - shift <= 0)
                                delete = true;
                        }
                    }
                    if (op2.charAt(0) == '$') {
                        String pos2 = parser.getPosition(op2);
                        String sheet2 = parser.getSheetName(op2);
                        int actualRow2 = parser.getRow(pos2);
                        int actualColumn2 = parser.lettersToNumber(parser.getColumn(pos2));
                        if (sheet2.equals(sheetName)) {
                            if (actualColumn2 > column && actualColumn2 - shift > 0) {
                                change = true;
                                op2 = '$' + sheetName + '-' + parser.numberToLetters(actualColumn2 - shift) + actualRow2;
                            } else if (actualColumn2 == column || actualColumn2 - shift <= 0)
                                delete = true;
                        }
                    }
                    if (delete)
                        setCell("0");
                    else if (change)
                        setCell("=" + parser.getFunction() + '(' + op1 + "," + op2 + ")");
                }
            }
        } else if (this.myReferences.size() > 1 && this.content.indexOf(':') != -1) {
            if (this.content.indexOf(',') == -1) {
                String op = parser.getOperator();
                String sheet = parser.getSheetName(op);
                String initialPosition = op.substring(op.indexOf('-') + 1, op.indexOf(':'));
                String finalPosition = op.substring(op.indexOf(':') + 1);
                int initialRow = parser.getRow(initialPosition);
                int finalRow = parser.getRow(finalPosition);
                int initialColumn = parser.lettersToNumber(parser.getColumn(initialPosition));
                int finalColumn = parser.lettersToNumber(parser.getColumn(finalPosition));
                if (sheet.equals(sheetName) && initialColumn == finalColumn) {
                    if (initialColumn > column && initialColumn - shift > 0)
                        setCell('=' + parser.getFunction() + "($" + sheetName + '-' + parser.numberToLetters(initialColumn - shift) + initialRow + ':' + parser.numberToLetters(finalColumn - shift) + finalRow + ')');
                    else if (initialColumn == column || initialColumn - shift <= 0)
                        setCell("0");
                }
            } else {
                String op1 = parser.getFirstOperator();
                String op2 = parser.getSecondOperator();
                boolean change = false;
                boolean delete = false;
                if (op1.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op1);
                    String initialPosition = op1.substring(op1.indexOf('-') + 1, op1.indexOf(':'));
                    String finalPosition = op1.substring(op1.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    int initialColumn = parser.lettersToNumber(parser.getColumn(initialPosition));
                    int finalColumn = parser.lettersToNumber(parser.getColumn(finalPosition));
                    if (sheet.equals(sheetName) && initialColumn == finalColumn) {
                        if (initialColumn > column && initialColumn - shift > 0) {
                            change = true;
                            op1 = '$' + sheetName + '-' + parser.numberToLetters(initialColumn - shift) + initialRow + ':' + parser.numberToLetters(finalColumn - shift) + finalRow;
                        } else if (initialColumn == column || initialColumn - shift <= 0)
                            delete = true;
                    }
                } if (op2.charAt(0) == '$') {
                    String sheet = parser.getSheetName(op2);
                    String initialPosition = op2.substring(op2.indexOf('-') + 1, op2.indexOf(':'));
                    String finalPosition = op2.substring(op2.indexOf(':') + 1);
                    int initialRow = parser.getRow(initialPosition);
                    int finalRow = parser.getRow(finalPosition);
                    int initialColumn = parser.lettersToNumber(parser.getColumn(initialPosition));
                    int finalColumn = parser.lettersToNumber(parser.getColumn(finalPosition));
                    if (sheet.equals(sheetName) && initialColumn == finalColumn) {
                        if (initialColumn > column && initialColumn - shift > 0) {
                            change = true;
                            op2 = '$' + sheetName + '-' + parser.numberToLetters(initialColumn - shift) + initialRow + ':' + parser.numberToLetters(finalColumn - shift) + finalRow;
                        } else if (initialColumn == column || initialColumn - shift <= 0)
                            delete = true;
                    }
                }
                if (delete)
                    setCell("0");
                else if (change)
                    setCell('=' + parser.getFunction() + '(' + op1 + ',' + op2 + ')');
            }
        }

        System.out.println(this.position);
        deleteDefaultCell();
    }
}
