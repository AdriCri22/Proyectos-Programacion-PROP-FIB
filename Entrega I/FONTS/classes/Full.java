package classes;

import classes.Cela;
import java.util.*;

/**
 * Representa un full del document.
 * Un full conté un conjunt de cel·les con cadascuna té una posició <col (string),fila (número)>.
 * @author carla.canovas.iglesias
 */

public class Full {

    /**
     * Representa el conjunt de cel·les del full amb la seva localització.
     * */
    public Map<String, Cela> celes;

    /**
     * Constructora d'un full amb el seu conjunt de cel·les, que inicialment serà buit fins que aquestes no es modifiquin.
     * */
    public Full () {
        this.celes = new HashMap<>();
    }

    /**
     * Obté la fila de la posició d'una cel·la.
     *
     * @param pos és la posició de la cel·la.
     * @return Retorna la fila en la qual es troba la cel·la en format string.
     */
    private String getSubStringFila(String pos) {
        StringBuilder res = new StringBuilder();
        int i = 0;

        while(pos.charAt(i) >= 'A' && pos.charAt(i) <= 'Z') {
            ++i;
        }
        while(i < pos.length()) {
            res.append(pos.charAt(i));
            ++i;
        }

        return res.toString();
    }

    /**
     * Indica si existeix o no una cel·la en la posició donada.
     *
     * @param pos és la posició de la cel·la que volem comprovar si existeix.
     * @return Retorna un booleà que indica si existeix la cel·la o no.
     * */
    private boolean existCela(String pos){
        return celes.containsKey(pos);
    }

    /**
     * Obté la columna de la posició d'una cel·la.
     *
     * @param referencia String amb la posició d'una cel·la.
     * @return Retorna la columna en la qual es troba la cel·la en format string.
     * */
    private String getPartAlfabetica(String referencia) {
        StringBuilder lletres = new StringBuilder();
        for (int i = 0; i < referencia.length(); i++) {
            char c = referencia.charAt(i);
            if ('A' <= c && c <= 'Z')
                lletres.append(c);
            else
                break;
        }

        return String.valueOf(lletres);
    }

    /**
     * Obté la fila de la posició d'una cel·la.
     *
     * @param referencia String amb la posició d'una cel·la.
     * @return Retorna la fila en la qual es troba la cel·la en format int.
     * */
    private int getPartNumerica(String referencia) {
        StringBuilder numeros = new StringBuilder();
        for (int i = 0; i < referencia.length(); i++) {
            char c = referencia.charAt(i);
            if ('0' <= c && c <= '9')
                numeros.append(c);
        }

        return Integer.parseInt(String.valueOf(numeros));
    }

    /**
     * Reverteix un string.
     *
     * @param str és el string a revertir.
     * @return Retorna el string revertit.
     * */
    private String reverse(String str) {
        StringBuilder str2 = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            str2.append(str.charAt(i));
        }

        return str2.toString();
    }

    /**
     * Obté la columna següent a la donada.
     *
     * @param columna és la columna de la qual volem obtenir la seva següent.
     * @return Retorna la columna següent a la donada.
     * */
    private String nextColumna(String columna) {
        char c = columna.charAt(columna.length() - 1);
        if (c == 'Z') {
            StringBuilder pos = new StringBuilder();
            int plus = 0;
            boolean count = true;
            for (int i = columna.length() - 1; i >= 0; i--) {
                if (columna.charAt(i) == 'Z' && count) {
                    pos.append("A");
                    plus = 1;
                }
                else {
                    pos.append((char) (columna.charAt(i) + plus));
                    plus = 0;
                    count = false;
                }
            }

            if (plus == 1) {
                pos = new StringBuilder("A");
                pos.append("A".repeat(columna.length()));

                return pos.toString();
            }
            else {
                return reverse(pos.toString());
            }
        }
        else
            return columna.substring(0, columna.length() - 1) + (char) (c + 1);
    }

    /**
     * Donada una columna en format alfabètic en majúscules retorna el seu valor en decimal.
     *
     * @param str és la columna en format alfabètic en majúscules.
     * @return Retorna el valor enter corresponent al número de columna.
     * */
    private int lettersToNumber(String str) {
        int num = 0;
        for (int i = 0; i < str.length(); i++) {
            num *= 26;
            num += str.charAt(i) - 'A' + 1;
        }

        return num;
    }

    /**
     * Donada una columna en format numèric retorna el seu valor en format alfabètic en majúscules.
     *
     * @param columnNumber és la columna en format numèric.
     * @return Retorna una string corresponent a la columna en format alfabètic en majúscules.
     * */
    private String numberToLetters(int columnNumber) {
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
     * Compta quantes columnes de separació hi ha entre les dues donades.
     *
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     *
     * @return Retorna el número de columnes que hi ha entremig de les dues donades.
     * */
    private int cuentaCol(String posIni,String posFi) {
        int i = 1;
        StringBuilder aux = new StringBuilder(posIni);
        boolean found = false;
        while(!found) {
            char c = aux.charAt(aux.length()-1);
            if(c == 'Z') {
                aux.append('A');
            }
            else {
                int al = c;
                al++;
                char val = (char)al;
                aux = new StringBuilder(aux.substring(0, aux.length() - 1));
                aux.append(val);
            }
            if (aux.toString().equals(posFi)) found = true;
            ++i;
        }
        return i;
    }

    /**
     * Modifica una cel·la del document.
     *
     * @param posicio és la posició de la cel·la que volem modificar.
     * @param valor és el nou valor que volem donar a la cel·la.
     * @param contingut és el nou contingut que volem donar a la cel·la.
     */
    public void setCela(String posicio, String valor, String contingut) {
        if (celes.containsKey(posicio)) {
            celes.get(posicio).deleteReferenciesMeves();
            celes.get(posicio).setValor(valor);
            celes.get(posicio).setContingut(contingut);
        }
        else
            celes.put(posicio, new Cela(valor, contingut));
    }

    /**
     * Obté la cel·la de la posició donada.
     *
     * @param pos és la posició de la cel·la que volem obtenir.
     * @return Retorna la cel·la que es troba a la posició donada.
     * */
    public Cela getCela(String pos) {
        if (existCela(pos))
            return this.celes.get(pos);
        else
            return null;
    }

    /**
     * Obté el número de cel·les del full.
     *
     * @return Retorna el nombre de cel·les amb valor diferent del de per defecte que té el full.
     * */
    public int sizeCeles() {
        return this.celes.size();
    }

    /**
     * Afegeix les corresponents referències entre cel·les.
     *
     * @param posicioReferenciadora és la posició de la cel·la que fa la referència.
     * @param fullReferenciat és el full de la cel·la referenciada.
     * @param posicioReferenciada és la posició dins del full de la cel·la referenciada.
     */
    public void addReferencia(String posicioReferenciadora, Full fullReferenciat, String posicioReferenciada) {
        if (!existCela(posicioReferenciadora))
            this.celes.put(posicioReferenciadora, new Cela("0"));
        if (!fullReferenciat.existCela(posicioReferenciada))
            fullReferenciat.addCela(posicioReferenciada, "0");
        this.celes.get(posicioReferenciadora).addReferenciaMeva(fullReferenciat.getCela(posicioReferenciada));
        fullReferenciat.celes.get(posicioReferenciada).addReferenciaAMi(this.getCela(posicioReferenciadora));
    }

    /**
     * Afegeix una cel·la amb valor diferent del de per defecte al full.
     *
     * @param posicio és la posició de la cel·la a afegir.
     * @param valor és el valor de la cel·la a afegir.
     */
    public void addCela(String posicio, String valor) {
        this.celes.put(posicio, new Cela(valor));
    }

    /**
     * Retorna el valor d'una cel·la donada la seva posició.
     *
     * @param posicio és la posició de la cel·la de la qual volem obtenir el valor.
     * @return Retorna el valor de la cel·la.
     */
    public String getValorCela(String posicio) {
        if (existCela(posicio))
            return celes.get(posicio).getValor();
        else
            return null;
    }

    /**
     * Retorna el contingut d'una cel·la donada la seva posició.
     *
     * @param posicio és la posició de la cel·la de la qual volem obtenir el contingut.
     * @return Retorna el contingut de la cel·la.
     */
    public String getContingutCela(String posicio) {
        return celes.get(posicio).getContingut();
    }

    /**
     * Elimina les referències d'una cel·la.
     *
     * @param posicio és la posició de la cel·la de la qual volem eliminar les seves referències.
     */
    public void eliminaReferencies(String posicio) {
        if (existCela(posicio))
            this.celes.get(posicio).deleteReferenciesMeves();
        else
            this.celes.put(posicio, new Cela("0"));
    }

    /**
     * Obté totes les cel·les que no tenen valor per defecte del full.
     *
     * @return Retorna totes les cel·les del full.
     */
    public Map<String, Cela> getCeles() {
        return this.celes;
    }

    /**
     * Obté totes les posicions de cel·les en el full que tenen valor diferent al de per defecte.
     *
     * @return Retorna un set amb totes les posicions de cel·les amb valor útil.
     */
    public Set<String> getPosicionsCelesFull() {
        return this.celes.keySet();
    }

    /**
     * Mou el contingut d'una cel·la a una altra.
     *
     * @param posAntiga és la posició de la cel·la que es vol moure.
     * @param posNova és la nova posició on es vol moure la cel·la.
     */
    public void moveCela(String posAntiga,String posNova) {
        celes.put(posNova,celes.get(posAntiga));
        celes.remove(posAntiga);
    }

    /**
     * Copia i enganxa una cel·la en una altra.
     *
     * @param posAntiga és la cel·la que es copia.
     * @param posNova és la cel·la on volem copiar l'altra cel·la.
     */
    public void copyCela(String posAntiga, String posNova) {
        celes.put(posNova,celes.get(posAntiga));
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat.
     *
     * @param valor és el valor que es vol buscar.
     * @return Retorna un map amb totes les cel·les que contenen aquest valor.
     */
    public HashSet<String> buscarValor(String valor) {
        HashSet<String> result = new HashSet<>();
        for(String key:celes.keySet())
            if(getValorCela(key).equals(valor))
                result.add(key);

        return result;
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat i se'ls assigna el nou valor donat.
     *
     * @param valor és el valor que es busca.
     * @param nouValor és el nou valor que es vol assignar a totes les cel·les que tenen el valor indicat.
     * @return Retorna un map amb totes les cel·les que contenen aquest valor canviat pel nou valor.
     */
    public HashMap<String,Cela> buscarValorIReemplacar(String valor, String nouValor) {
        HashMap<String,Cela> result = new HashMap<>();
        for(String key : celes.keySet()) {
            if(getValorCela(key).equals(valor)) {
                result.put(key, celes.get(key));
                setCela(key, nouValor, null );
            }
        }

        return result;
    }

    /**
     * Mou una fila de cel·les i l'enganxa en una altra.
     *
     * @param fila és la fila del bloc de cel·les.
     * @param columnnaInicial és la columna inicial del bloc de cel·les.
     * @param columnaFinal és la columna final del bloc de cel·les.
     * @param shift és el nombre de posicions que es vol desplaçar.
     */
    private void moveFila(int fila, String columnnaInicial, String columnaFinal, int shift) throws Exception {
        if (fila + shift < 1)
            throw new Exception("El desplaçament que vol realitzar provoca error");

        int colInicial = lettersToNumber(columnnaInicial);
        int colFinal = lettersToNumber(columnaFinal);
        for (int col = colInicial; col <= colFinal; col++) {
            String newPos = numberToLetters(col) + (fila + shift);
            String oldPos = numberToLetters(col) + fila;
            this.celes.put(newPos, this.celes.get(oldPos));
            this.celes.remove(oldPos);
        }
    }

    /**
     * Mou una columna de cel·les i l'enganxa en una altra.
     *
     * @param columna és la columna del bloc de cel·les.
     * @param filaInicial és la fila inicial del bloc de cel·les.
     * @param filaFinal és la fila final del bloc de cel·les.
     * @param shift és el nombre de posicions que es vol desplaçar.
     */
    private void moveColumna(String columna, int filaInicial, int filaFinal, int shift) throws Exception {
        if (lettersToNumber(columna) + shift < 1)
            throw new Exception("El desplaçament que vol realitzar provoca error");

        for (int fila = filaInicial; fila <= filaFinal; fila++) {
            String newPos = numberToLetters(lettersToNumber(columna) + shift) + fila;
            String oldPos = columna + fila;
            this.celes.put(newPos, this.celes.get(oldPos));
            this.celes.remove(oldPos);
        }
    }

    /**
     * Modifica la posició del bloc de cel·les donat.
     *
     * @param posicioInicial és la posició inicial del bloc de cel·les original.
     * @param posicioFinal és la posició final del bloc de cel·les original.
     * @param shift és el nombre de posicions que es vol desplaçar.
     * */
    public void moveBloc(String posicioInicial, String posicioFinal, int shift) throws Exception {
        int filaIni = getPartNumerica(posicioInicial);
        int filaFi = getPartNumerica(posicioFinal);
        String colIni = getPartAlfabetica(posicioInicial);
        String colFi = getPartAlfabetica(posicioFinal);

        if (filaIni == filaFi) moveFila(filaIni, colIni, colFi, shift);
        else if (colIni.equals(colFi)) moveColumna(colIni, filaIni, filaFi, shift);
        else throw new Exception("El bloc conté més d'una fila i columna");
    }

    /**
     * Ordena la fila de cel·les donada segons l'ordre especificat.
     *
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     * @param ordre és l'ordre d'ordenació de les cel·les. Pot ser ascendent o descendent.
     * */
    private void ordenaFila(String posIni, String posFi,String ordre) {
        String colIni = getPartAlfabetica(posIni);
        String colFi  = getPartAlfabetica(posFi);
        String fila = getSubStringFila(posIni);

        ArrayList<String> contingut = new ArrayList<>();

        String posNueva = posIni;
        int numCols = cuentaCol(colIni,colFi);
        for(int i = 0; i < numCols; ++i) {

            if (celes.containsKey(posNueva)) {
                contingut.add(celes.get(posNueva).getValor());
                celes.remove(posNueva);
            }

            String nextCol = nextColumna(getPartAlfabetica(posNueva));
            posNueva = nextCol+fila;
        }
        if (ordre.equals("ASC")) {
            Collections.sort(contingut);
        } else {
            contingut.sort(Collections.reverseOrder());
        }
        posNueva = posIni;
        for (String s : contingut) {
            Cela celaNova = new Cela(s);
            celes.put(posNueva, celaNova);
            String nextCol = nextColumna(getPartAlfabetica(posNueva));
            posNueva = nextCol + fila;
        }
    }

    /**
     * Obté els valors de les cel·les que es troben entre les posicions donades.
     *
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     *
     * @return Retorna una llista amb els valors de les cel·les.
     * */
    private ArrayList<String> getValors(String posIni, String posFi) {
        int filaIni = getPartNumerica(posIni);
        int filaFi = getPartNumerica(posFi);
        String col = getPartAlfabetica(posIni);

        ArrayList<String> contingut = new ArrayList<>();

        String posNueva = posIni;
        int numFiles = filaFi-filaIni;
        numFiles++;
        int nextFila = filaIni;
        for (int i = 0; i < numFiles; ++i) {
            if (celes.containsKey(posNueva)) {
                contingut.add(celes.get(posNueva).getValor());
                celes.remove(posNueva);
            }
            nextFila++;
            String fila = String.valueOf(nextFila);
            posNueva = col+ fila;
        }

        return contingut;
    }

    /**
     * Ordena la columna de cel·les donada segons l'ordre especificat.
     *
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     * @param ordre és l'ordre d'ordenació de les cel·les. Pot ser ascendent o descendent.
     * */
    private void ordenaColumna(String posIni, String posFi, String ordre) {
        int filaIni = getPartNumerica(posIni);
        String col = getPartAlfabetica(posIni);

        ArrayList<String> contingut = getValors(posIni, posFi);

        if(ordre.equals("ASC")) {
            Collections.sort(contingut);
        }else {
            contingut.sort(Collections.reverseOrder());
        }
        int nextFila = filaIni;
        String posNueva = posIni;
        for (String s : contingut) {
            Cela celaNova = new Cela(s);
            celes.put(posNueva, celaNova);
            nextFila++;
            String fila = String.valueOf(nextFila);
            posNueva = col + fila;
        }
    }

    /**
     * Ordena el bloc de cel·les donat segons l'ordre especificat.
     *
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     * @param ordre és l'ordre d'ordenació de les cel·les. Pot ser ascendent o descendent.
     * */
    public void ordenaBloc(String posIni, String posFi,String ordre) throws Exception {
        int filaIni = getPartNumerica(posIni);
        int filaFi = getPartNumerica(posFi);
        String colIni = getPartAlfabetica(posIni);
        String colFi = getPartAlfabetica(posFi);

        if (filaIni == filaFi) ordenaFila(posIni, posFi, ordre);
        else if (colIni.equals(colFi)) ordenaColumna(posIni, posFi, ordre);
        else throw new Exception("El bloc conté més d'una fila i columna");
    }

    /**
     * Copia una fila de cel·les i l'enganxa en una altra.
     *
     * @param fila és la fila del bloc de cel·les.
     * @param columnnaInicial és la columna inicial del bloc de cel·les.
     * @param columnaFinal és la columna final del bloc de cel·les.
     * @param shift és el nombre de posicions que es vol desplaçar per copiar.
     */
    private void copiaFila(int fila, String columnnaInicial, String columnaFinal, int shift) throws Exception {
        if (fila + shift < 1)
            throw new Exception("El desplaçament que vol realitzar provoca error");

        int colInicial = lettersToNumber(columnnaInicial);
        int colFinal = lettersToNumber(columnaFinal);
        for (int col = colInicial; col <= colFinal; col++) {
            String newPos = numberToLetters(col) + (fila + shift);
            String oldPos = numberToLetters(col) + fila;
            this.celes.put(newPos, this.celes.get(oldPos).cloneCela());
        }
    }

    /**
     * Copia una columna de cel·les i l'enganxa en una altra.
     *
     * @param columna és la columna del bloc de cel·les.
     * @param filaInicial és la fila inicial del bloc de cel·les.
     * @param filaFinal és la fila final del bloc de cel·les.
     * @param shift és el nombre de posicions que es vol desplaçar per copiar.
     */
    private void copiaColumna(String columna, int filaInicial, int filaFinal, int shift) throws Exception {
        if (lettersToNumber(columna) + shift < 1)
            throw new Exception("El desplaçament que vol realitzar provoca error");

        for (int fila = filaInicial; fila <= filaFinal; fila++) {
            String newPos = numberToLetters(lettersToNumber(columna) + shift) + fila;
            String oldPos = columna + fila;
            this.celes.put(newPos, this.celes.get(oldPos).cloneCela());
        }
    }

    /**
     * Copia un bloc de cel·les i l'enganxa en un altre.
     *
     * @param posIniAntiga és la posició inicial del bloc de cel·les que volem copiar.
     * @param posFiAntiga és la posició final del bloc de cel·les que volem copiar.
     * @param shift és el nombre de posicions que es vol desplaçar per copiar.
     */
    public void copiaBloc(String posIniAntiga, String posFiAntiga, int shift) throws Exception {
        int filaIni = getPartNumerica(posIniAntiga);
        int filaFi = getPartNumerica(posFiAntiga);
        String colIni = getPartAlfabetica(posIniAntiga);
        String colFi = getPartAlfabetica(posFiAntiga);

        if (filaIni == filaFi) copiaFila(filaIni, colIni, colFi, shift);
        else if (colIni.equals(colFi)) copiaColumna(colIni, filaIni, filaFi, shift);
        else throw new Exception("El bloc conté més d'una fila i columna");
    }

    /**
     * Elimina un cert nombre de files del full.
     *
     * @param row és la fila inicial que volem eliminar.
     * @param shift són el nombre de files a eliminar.
     */
    public void deleteFiles(int row, int shift) {
        HashMap<String, Cela> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cela>> it = this.celes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cela> entry = it.next();
            int actualRow = getPartNumerica(entry.getKey());

            if (actualRow > row + shift - 1) {      // Rows to shift
                String newPosition = getPartAlfabetica(entry.getKey()) + (actualRow - shift);
                tmp.put(newPosition, entry.getValue());
            }

            if (actualRow >= row)
                it.remove();
        }

        this.celes.putAll(tmp);
    }

    /**
     * Elimina un cert nombre de columnes del full.
     *
     * @param columnName és la columna inicial que volem eliminar.
     * @param shift són el nombre de files a eliminar.
     */
    public void deleteColumnes(String columnName, int shift) {
        int column = lettersToNumber(columnName);
        HashMap<String, Cela> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cela>> it = this.celes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cela> entry = it.next();
            int actualColumn = lettersToNumber(getPartAlfabetica(entry.getKey()));

            if (actualColumn > column + shift - 1) {      // Columns to shift
                String newPosition = numberToLetters(actualColumn - shift) + getPartNumerica(entry.getKey());
                tmp.put(newPosition, entry.getValue());
            }

            if (actualColumn >= column)
                it.remove();
        }

        this.celes.putAll(tmp);
    }

    /**
     * Afegeix un cert nombre de files al full.
     *
     * @param row és la fila inicial des que volem afegir.
     * @param shift són el nombre de files a afegir.
     */
    public void addFiles(int row, int shift) {
        HashMap<String, Cela> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cela>> it = this.celes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cela> entry = it.next();
            int actualRow = getPartNumerica(entry.getKey());

            if (actualRow >= row) {      // Rows to shift
                String newPosition = getPartAlfabetica(entry.getKey()) + (actualRow + shift);
                tmp.put(newPosition, entry.getValue());
                it.remove();

            }
        }

        this.celes.putAll(tmp);
    }

    /**
     * Afegeix un cert nombre de columnes al full.
     *
     * @param columnName és la columna inicial des que volem afegir.
     * @param shift són el nombre de columnes a afegir.
     */
    public void addColumnes(String columnName, int shift) {
        int column = lettersToNumber(columnName);
        HashMap<String, Cela> tmp = new HashMap<>();
        Iterator<Map.Entry<String, Cela>> it = this.celes.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cela> entry = it.next();
            int actualColumn = lettersToNumber(getPartAlfabetica(entry.getKey()));

            if (actualColumn >= column) {      // Columns to shift
                String newPosition = numberToLetters(actualColumn + shift) + getPartNumerica(entry.getKey());
                tmp.put(newPosition, entry.getValue());
                it.remove();

            }
        }
        this.celes.putAll(tmp);
    }
}
