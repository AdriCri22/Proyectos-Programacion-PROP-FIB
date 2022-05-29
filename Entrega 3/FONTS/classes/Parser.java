package classes;

import classes.domain.Cell;
import classes.domain.Function;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Representa el parser.
 * @author adrian.cristian.crisan
 */
public class Parser {
    /**
     * Representa les funcions que es poden aplicar.
     * */
    private final Function function = new Function();
    /**
     * Representa la cel·la a la qual apliquem el parser.
     * */
    private final Cell cell;
    /**
     * Representa la cadena de caràcters a la qual hem d'aplicar el parser.
     * */
    private final String statement;
    /**
     * Representa les referències trobades.
     * */
    private final HashSet<String> references = new HashSet<>();

    /**
     * Constructora amb una cel·la i un contingut
     *
     * @param cell cel·la a la qual apliquem el parser.
     * @param content contingut a la que apliquem el parser.
     * */
    public Parser(Cell cell, String content) {
        this.cell = cell;
        this.statement = content;
    }

    /**
     * Constructora amb un contingut
     *
     * @param statement contingut a la que apliquem el parser.
     * */
    public Parser(String statement) {
        this.cell = null;
        this.statement = statement;
    }

    /**
     * Constructora buida.
     * */
    public Parser() {
        this.cell = null;
        this.statement = null;
    }

    /**
     * Comprova si el caràcter és un número o no.
     *
     * @param c caràcter que comprovem.
     * @return retorna true si el caràcter donat és un número, altrament false.
     * */
    private boolean isNumber(char c) {
        try {
            Integer.parseInt(String.valueOf(c));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Comprova si la posició donada és una posició vàlida.
     *
     * @param pos posició a comprovar.
     * @return retorna true si la posició donada és vàlida, altrament, false.
     * */
    public boolean validPosition(String pos) {
        boolean letters = true;
        for (int i = 0; i < pos.length(); i++) {
            char c = pos.charAt(i);
            if (i == 0 && (c < 'A' || c > 'Z'))
                return false;
            else if (letters && isNumber(c))
                letters = false;
            else if (letters && (c < 'A' || c > 'Z'))
                return false;
            else if (!letters && !isNumber(c))
                return false;
        }

        return !letters;
    }

    /**
     * Obté la posició donada una referència vàlida.
     *
     * @return retorna la posició.
     * */
    public String getPosition(String ref) {
        int startIndex = ref.indexOf('-') + 1;
        int endIndex = startIndex;
        while (endIndex < ref.length()) {
            char c = ref.charAt(endIndex);
            if (c >= 'A' && c <= 'Z' || isNumber(c))
                endIndex++;
            else
                break;
        }

        return ref.substring(startIndex, endIndex);
    }

    /**
     * Obté la posició.
     *
     * @return retorna la posició.
     * @throws Exception si la posició donada no és vàlida.
     * */
    public String getPosition() throws Exception {
        assert this.statement != null;
        int startIndex = this.statement.indexOf('-') + 1;
        int endIndex = startIndex;
        while (endIndex < this.statement.length()) {
            char c = this.statement.charAt(endIndex);
            if (c >= 'A' && c <= 'Z' || isNumber(c))
                endIndex++;
            else
                break;
        }

        String position = this.statement.substring(startIndex, endIndex);
        if (validPosition(position))
            return position;
        else
            throw new Exception("La posició donada no és vàlida");
    }

    /**
     * Obté el full de la posició donada.
     *
     * @return retorna el full de la cel·la referenciada.
     * */
    public String getSheetName(String ref) {
        int startIndex = ref.indexOf('$') + 1;
        int endIndex = ref.indexOf('-');

        return ref.substring(startIndex, endIndex);
    }

    /**
     * Obté el full.
     *
     * @return retorna el full de la cel·la referenciada.
     * */
    public String getSheetName() {
        assert this.statement != null;
        int startIndex = this.statement.indexOf('$') + 1;
        int endIndex = this.statement.indexOf('-');

        return this.statement.substring(startIndex, endIndex);
    }

    /**
     * Obté la funció.
     *
     * @return retorna la funció que volem aplicar.
     * */
    public String getFunction() {
        assert this.statement != null;
        int endIndex = this.statement.indexOf('(');

        return this.statement.substring(1, endIndex);
    }

    /**
     * Obté l'operador, situat entre parèntesis.
     *
     * @return retorna l'operador al qual apliquem la funció.
     * */
    public String getOperator() {
        assert this.statement != null;
        int startIndex = this.statement.indexOf('(') + 1;
        int endIndex = this.statement.indexOf(')');

        return this.statement.substring(startIndex, endIndex);
    }

    /**
     * Obté el primer operador d'una funció binària.
     *
     * @return retorna el primer operador d'una funció binària.
     * */
    public String getFirstOperator() {
        assert this.statement != null;
        int startIndex = this.statement.indexOf('(') + 1;
        int endIndex = this.statement.indexOf(',');

        return this.statement.substring(startIndex, endIndex);
    }

    /**
     * Obté el segon operador d'una funció binària.
     *
     * @return retorna el segon operador d'una funció binària.
     * */
    public String getSecondOperator() {
        assert this.statement != null;
        int startIndex = this.statement.indexOf(',') + 1;
        while (this.statement.charAt(startIndex) == ' ')
            startIndex++;
        int endIndex = this.statement.indexOf(')');

        return this.statement.substring(startIndex, endIndex);
    }

    /**
     * Obté la referència.
     *
     * @return retorna la referència.
     * */
    public String getReference() {
        assert this.statement != null;
        int startIndex = this.statement.indexOf('(') + 1;
        int endIndex = this.statement.indexOf(',');
        if (endIndex == -1)
            endIndex = this.statement.indexOf(')');

        return this.statement.substring(startIndex, endIndex);
    }

    /**
     * Transforma la columna del format alfabètic a número.
     *
     * @param str columna en format alfabètic.
     * @return retorna la columna en format numèric.
     * */
    public int lettersToNumber(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num *= 26;
            num += str.charAt(i) - 'A' + 1;
        }

        return num;
    }

    /**
     * Transforma la columna del format de numèric a alfabètic.
     *
     * @param columnNumber columna en format numèric.
     * @return retorna la columna en format alfabètic.
     * */
    public String numberToLetters(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber > 0) {
            int rem = columnNumber % 26;   // Find remainder

            if (rem == 0) {                // If remainder is 0, then a
                res.append("Z");           // 'Z' must be there in output
                columnNumber = (columnNumber / 26) - 1;
            }
            else {                          // If remainder is non-zero
                res.append((char)((rem - 1) + 'A'));
                columnNumber = columnNumber / 26;
            }
        }

        return String.valueOf(res.reverse());
    }

    /**
     * Obté la columna donada una posició.
     *
     * @param pos posició.
     * @return retorna la columna d'una posició
     * */
    public String getColumn(String pos) {
        StringBuilder lletres = new StringBuilder();
        for (int i = 0; i < pos.length(); i++) {
            char c = pos.charAt(i);
            if ('A' <= c && c <= 'Z')
                lletres.append(c);
            else
                break;
        }

        return String.valueOf(lletres);
    }

    /**
     * Obté la fila donada una posició.
     *
     * @param pos posició.
     * @return retorna la fila d'una posició
     * */
    public int getRow(String pos) {
        StringBuilder numeros = new StringBuilder();
        for (int i = 0; i < pos.length(); i++) {
            char c = pos.charAt(i);
            if ('0' <= c && c <= '9')
                numeros.append(c);
        }

        return Integer.parseInt(String.valueOf(numeros));
    }

    /**
     * Obté els valors d'un bloc de cel·les, d'una mateixa columna.
     *
     * @param column columna.
     * @param initialRow fila inicial.
     * @param finalRow fila final.
     * @return retorna els valors del bloc de cel·les.
     * @throws Exception si el full referenciat no existeix.
     * */
    private ArrayList<String> getColumnValues(String column, int initialRow, int finalRow) throws Exception {
        ArrayList<String> values = new ArrayList<>();
        for (int row = initialRow; row <= finalRow; row++) {
            String pos = getSheetName() + "-" + column + row;
            assert this.cell != null;
            values.add(this.cell.getValue(pos));
            this.references.add(pos);
        }

        return values;
    }

    /**
     * Obté els valors d'un bloc de cel·les, d'una mateixa fila.
     *
     * @param row fila.
     * @param initialColumnS columna inicial.
     * @param finalColumnS columna final.
     * @return retorna els valors del bloc de cel·les.
     * @throws Exception si el full referenciat no existeix.
     * */
    private ArrayList<String> getRowValues(int row, String initialColumnS, String finalColumnS) throws Exception {
        ArrayList<String> values = new ArrayList<>();
        int initialColumn = lettersToNumber(initialColumnS);
        int finalColumn = lettersToNumber(finalColumnS);
        for (int col = initialColumn; col <= finalColumn; col++) {
            String pos = getSheetName() + "-" + numberToLetters(col) + row;
            assert this.cell != null;
            values.add(this.cell.getValue(pos));
            this.references.add(pos);
        }

        return values;
    }

    /**
     * Obté els valors d'un bloc de cel·les.
     *
     * @param operator operador.
     * @return retorna els valors del bloc de cel·les.
     * @throws Exception si el bloc de cel·les no és correcte.
     * */
    private ArrayList<String> getValues(String operator) throws Exception {
        String initialPosition = operator.substring(operator.indexOf('-')+1, operator.indexOf(':'));
        String finalPosition = operator.substring(operator.indexOf(':')+1);
        if (!validPosition(initialPosition))
            throw new Exception("La posició inicial no és valida");
        if (!validPosition(finalPosition))
            throw new Exception("La posició final no és vàlida");
        String initialColumn = getColumn(initialPosition);
        String finalColumn = getColumn(finalPosition);
        int initialRow = getRow(initialPosition);
        int finalRow = getRow(finalPosition);
        if (Objects.equals(initialColumn, finalColumn))
            return getColumnValues(initialColumn, initialRow, finalRow);
        else if (initialRow == finalRow)
            return getRowValues(initialRow, initialColumn, finalColumn);
        else
            throw new Exception("El bloc conté múltiples files i columnes");
    }

    /**
     * Obté el resultat d'aplicar una funció binària.
     *
     * @return retorna el resultat d'aplicar una funció binària.
     * @throws Exception la funció donada conté algun paràmetre que no és correcte.
     * */
    private String binaryFunction() throws Exception {
        String operator1 = getFirstOperator();
        String operator2 = getSecondOperator();
        if (operator1.indexOf(':') != -1 && operator2.indexOf(':') != -1)
            return this.function.calculate(getFunction(), getValues(operator1), getValues(operator2));
        else if (operator1.indexOf(':') != -1 && operator2.indexOf(':') == -1)
            throw new Exception("El segon bloc de cel·les no és correcte");
        else if (operator1.indexOf(':') == -1 && operator2.indexOf(':') != -1)
            throw new Exception("El primer bloc de cel·les no és correcte");

        String value1 = operator1;
        if (operator1.charAt(0) == '$') {
            this.references.add(operator1);
            assert this.cell != null;
            value1 = this.cell.getValue(operator1);
        }

        String value2 = operator2;
        if (operator2.charAt(0) == '$') {
            this.references.add(operator2);
            assert this.cell != null;
            value2 = this.cell.getValue(operator2);
        }

        return this.function.calculate(getFunction(), value1, value2);
    }

    /**
     * Obté el resultat d'aplicar una funció unària amb un bloc de cel·les.
     *
     * @return retorna el resultat d'aplicar una funció unària al bloc de cel·les.
     * @throws Exception la funció donada conté algun paràmetre que no és correcte.
     * */
    private String unaryBlockFunction() throws Exception {
        return this.function.calculate(getFunction(), getValues(getOperator()));
    }

    /**
     * Obté el resultat d'aplicar una funció unària amb una referència.
     *
     * @return retorna el resultat d'aplicar una funció unària a la referència.
     * @throws Exception la funció donada conté algun paràmetre que no és correcte.
     * */
    private String unaryReferencedFunction() throws Exception {
        this.references.add(getReference());
        assert this.cell != null;
        return this.function.calculate(getFunction(), this.cell.getValue(getReference()));
    }

    /**
     * Obté el resultat d'aplicar una funció unària.
     *
     * @return retorna el resultat d'aplicar una funció unària.
     * @throws Exception la funció donada conté algun paràmetre que no és correcte.
     * */
    private String unaryFunction() throws Exception {
        String operator = getOperator();
        if (operator.charAt(0) == '$')
            if (operator.indexOf(':') != -1)
                return unaryBlockFunction();
            else
                return unaryReferencedFunction();
        else
            return this.function.calculate(getFunction(), operator);
    }

    /**
     * Obté el resultat d'aplicar una funció.
     *
     * @return retorna el resultat d'aplicar una funció.
     * @throws Exception la funció donada conté algun paràmetre que no és correcte.
     * */
    private String calculateFunction() throws Exception {
        assert this.statement != null;
        if (this.statement.indexOf(',') != -1)
            return binaryFunction();
        else
            return unaryFunction();
    }

    /**
     * Obté el resultat d'aplicar el parser.
     *
     * @return retorna el resultat d'aplicar el parser.
     * @throws Exception la sentència donada conté algun paràmetre que no és correcte.
     * */
    public String getResult() throws Exception {
        assert this.statement != null;
        if (this.statement.charAt(0) == '=')
            return calculateFunction();
        else if (this.statement.charAt(0) == '$') {
            assert this.cell != null;
            this.references.add(this.statement.substring(1));
            return this.cell.getValue(this.statement.substring(1));
        }
        else
            return statement;
    }

    /**
     * Obté les referències trobades.
     *
     * @return retorna les referències donades.
     * */
    public HashSet<String> getReferences() {
        return this.references;
    }
}
