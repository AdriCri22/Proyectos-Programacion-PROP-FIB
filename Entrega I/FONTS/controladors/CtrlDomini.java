package controladors;

import controladors.CtrlDocument;
import classes.Funcions;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa el controlador de domini.
 * @author adrian.cristian.crisan
 */
public class CtrlDomini {

    /**
     * Representa el controlador de document.
     * */
    private final CtrlDocument ctrlDocument = new CtrlDocument();

    /**
     * Constructora del controlador de domini.
     * */
    public CtrlDomini(String nom) {
        this.ctrlDocument.crearDocument(nom);
    }

    /**
     * Modifica el nom del document.
     *
     * @param nom nom del document.
     * */
    public void setNomDocument(String nom) {
        this.ctrlDocument.setNomDocument(nom);
    }

    /**
     * Obté el nom del document.
     *
     * @return Retoruna el nom del document.
     * */
    public String getNomDocument() {
        return this.ctrlDocument.getNomDocument();
    }

    /**
     * Obté el nombre de fulls del document.
     *
     * @return Retorna el nombre de fulls del document.
     * */
    public int getNombreFulls() {
        return this.ctrlDocument.getNombreFulls();
    }

    /**
     * Afegeix un full al document.
     * */
    public void addFull() {
        this.ctrlDocument.addFull();
    }

    /**
     * Elimina un full al document.
     *
     * @param full és el número de full que volem eliminar.
     * */
    public void deleteFull(int full) throws Exception {
        this.ctrlDocument.deleteFull(full);
    }

    /**
     * Obté el valor d'una cel·la.
     *
     * @param full número de full de la cel·la.
     * @param posicio posició de la cel·la.
     *
     * @return Retorna el valor de la cel·la.
     * */
    public String getValorCela(int full, String posicio) {
        return this.ctrlDocument.getValorCela(full, posicio);
    }

    /**
     * Donada una referència obté el full.
     *
     * @param referencia referencia de la cel·la.
     *
     * @return Retorna el número de full de la referència.
     * */
    private int getFull(String referencia) throws Exception {
        int i = referencia.indexOf('$');
        int j = referencia.indexOf('-');
        if (i != -1 && j != -1) {
            String str2 = referencia.substring(i+1, j);
            if (str2.length() > 0) {
                int full = Integer.parseInt(str2);
                if (full >= getNombreFulls() || full < 0)
                    throw new Exception("El full de la referència donada no existeix");
                else
                    return full;
            }
        }
        throw new Exception("El full de la referència donada no existeix");
    }

    /**
     * Donada una referència obté la posició de la cel·la.
     *
     * @param referencia referència de la cel·la.
     *
     * @return Retorna la posició de la cel·la.
     * */
    private String getPosicio(String referencia) throws Exception {
        int i = referencia.indexOf('-');
        int n = referencia.length();
        if (i != -1) {
            String str2 = referencia.substring(i + 1, n);
            if (str2.length() > 0)
                return str2;
        }

        throw new Exception("La posició de cel·la de la referència donada és incorrecte");
    }

    /**
     * Afegeix una referència.
     *
     * @param full número de full de la cel·la que referencia.
     * @param posicio posició de la cel·la que referència.
     * @param fullReferenciat número de full de la cel·la que és referenciada.
     * @param posicioReferenciada posició de la cel·la que és referenciada.
     * */
    public void addReferencia(int full, String posicio, int fullReferenciat, String posicioReferenciada) {
        this.ctrlDocument.addReferencia(full, posicio, fullReferenciat, posicioReferenciada);
    }

    /**
     * Copia i enganxa una cel·la d'un full en una altra.
     *
     * @param antiga és la cel·la que es copia.
     * @param nova és la cel·la on volem copiar l'altra cel·la.
     */
    public void copyCela(int full, String antiga, String nova){
        this.ctrlDocument.copyCela(full, antiga, nova);
    }

    /**
     * Mou el contingut d'una cel·la d'un full a una altra.
     *
     * @param posAntiga és la posició de la cel·la que es vol moure.
     * @param posNova és la nova posició on es vol moure la cel·la.
     */
    public void moveCela(int full, String posAntiga, String posNova){
        this.ctrlDocument.moveCela(full, posAntiga, posNova);
    }

    /**
     * Copia el contingut d'un bloc de cel·les i l'enganxa en un altre.
     *
     * @param full és el full a la qual pertany el bloc de cel·les que volem copiar.
     * @param posIniAntiga és la posició inicial del bloc de cel·les que volem copiar.
     * @param posFiAntiga és la posició final del bloc de cel·les que volem copiar.
     * @param desplacament nombre de posicions que es vol desplaçar per copiar.
     */
    public void copyBlocCeles(int full, String posIniAntiga, String posFiAntiga, int desplacament) throws Exception {
        this.ctrlDocument.copyBlocCeles(full, posIniAntiga, posFiAntiga, desplacament);
    }

    /**
     * Modifica la posició del bloc de cel·les del full donat.
     *
     * @param posicioInicial és la posició inicial del bloc de cel·les original.
     * @param posicioFinal és la posició final del bloc de cel·les original.
     * @param desplacament és el nombre de posicions que es vol desplaçar.
     * */
    public void moveBlocCeles(int full, String posicioInicial, String posicioFinal, int desplacament) throws Exception {
        this.ctrlDocument.moveBlocCeles(full, posicioInicial, posicioFinal, desplacament );
    }

    /**
     * Ordena el bloc de cel·les del full donat.
     *
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     * @param ordre és l'ordenació que es vol pel bloc. Pot ser ascendent o descendent.
     * */
    public void sortBlocCeles(int full, String posIni, String posFi, String ordre) throws Exception {
        this.ctrlDocument.sortBlocCeles(full, posIni, posFi, ordre);
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat en el full donat.
     *
     * @param valor és el valor que es busca.
     *
     * @return es retorna un map amb totes les cel·les que contenen aquest valor.
     */
    public HashSet<String> search(int full, String valor){
        return this.ctrlDocument.search(full, valor);
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat i se'ls assigna el nou valor en el full donat.
     *
     * @param valor és el valor que es busca.
     * @param nouValor és el nou valor que es vol assignar a totes les cel·les que tenen el valor indicat.
     */
    public void searchReplace(int full, String valor, String nouValor){
        this.ctrlDocument.searchReplace(full, valor, nouValor);
    }

    /**
     * Afegeix un cert nombre de columnes del document.
     *
     * @param full és el full on es vol afegir la columna.
     * @param columna és la columna inicial des que volem afegir.
     * @param shift són el nombre de columnes que es volen afegir.
     */
    public void addColumnes(int full, String columna, int shift) {
        this.ctrlDocument.addColumnes(full, columna, shift);
    }

    /**
     * Elimina un cert nombre de columnes del document.
     *
     * @param full és el full on es vol eliminar la columna.
     * @param columna és la columna inicial des que volem eliminar.
     * @param shift són el nombre de columnes que es volen eliminar.
     */
    public void deleteColumnes(int full, String columna, int shift) {
        this.ctrlDocument.deleteColumnes(full, columna, shift);
    }

    /**
     * Afegeix un cert nombre de files del document.
     *
     * @param full és el full on es vol afegir la columna.
     * @param fila és la fila inicial des que volem afegir.
     * @param shift són el nombre de files que es volen afegir.
     */
    public void addFiles(int full, int fila, int shift) {
        this.ctrlDocument.addFiles(full, fila, shift);
    }

    /**
     * Elimina un cert nombre de files del document.
     *
     * @param full és el full on es vol eliminar la columna.
     * @param fila és la fila inicial des que volem eliminar.
     * @param shift són el nombre de files que es volen eliminar.
     */
    public void deleteFiles(int full, int fila, int shift) {
        this.ctrlDocument.deleteFiles(full, fila, shift);
    }


    // Parser
    /**
     * Obté el substring que es troba entre dos caràcters.
     *
     * @param str string del que volem obtenir el substring.
     * @param sepInicio caràcter que delimita on comença el substring (no inclòs).
     * @param sepFinal caràcter que delimita on acaba el substring (no inclòs).
     *
     * @return Retorna el substring delimitat entre els dos caràcters donats.
     * */
    private String substringEntreSeparadors(String str, char sepInicio, char sepFinal) {
        int i = str.indexOf(sepInicio);
        int j = str.indexOf(sepFinal);
        if (i != -1 && j != -1) {
            String str2 = str.substring(i+1, j);
            if (str2.length() > 0)
                return str2;
        }

        return null;
    }

    /**
     * Obté el substring que es troba a partir d'un caràcter.
     *
     * @param str string del que volem obtenir el substring.
     * @param sep caràcter que delimita on comença el substring (no inclòs).
     *
     * @return Retorna el substring delimitat a partir al caràcter donat.
     * */
    private String substringDesDeSeparador(String str, char sep) {
        int i = str.indexOf(sep);
        int n = str.length();
        if (i != -1) {
            String str2 = str.substring(i + 1, n);
            if (str2.length() > 0)
                return str2;
        }

        return null;
    }

    /**
     * Obté l'operand d'una funció.
     *
     * @param str funció.
     *
     * @return Retorna l'operand.
     * */
    private String getOperand(String str) throws Exception {
        String operand = substringEntreSeparadors(str, '(', ')');
        if (operand != null)
            return operand;

        throw new Exception("Ha escrit una funció sense operand");
    }

    /**
     * Obté el nom de la funció d'una funció.
     *
     * @param str funció.
     *
     * @return Retorna el nom de la funció.
     * */
    private String getFuncio(String str) throws Exception {
        String funcio = substringEntreSeparadors(str, '=', '(');
        if (funcio != null)
            return funcio;

        throw new Exception("Intenta escriure una funcio sense el nom d'aquesta");
    }

    /**
     * Obté el primer operand d'una funció binària.
     *
     * @param str operands.
     *
     * @return Retorna l'operand inicial.
     * */
    private String getOperandInicial(String str) throws Exception {
        int i = str.indexOf(',');
        if (i != -1) {
            String str2 = str.substring(0, i);
            if (str2.length() > 0)
                return str2;
        }

        throw new Exception("Ha escrit una funció binaria sense primer operand");
    }

    /**
     * Obté el segon operand d'una funció binària.
     *
     * @param operands operands.
     *
     * @return Retorna l'operand final.
     * */
    private String getOperandFinal(String operands) throws Exception {
        String operand = substringDesDeSeparador(operands, ',');
        if (operand != null)
            return operand;

        throw new Exception("Ha escrit una funció binaria sense segon operand");
    }

    /**
     * Obté el full donada una referència.
     *
     * @param operand referència.
     *
     * @return Retorna el full de la referència.
     * */
    private int getFullRef(String operand) throws Exception {
        String full = substringEntreSeparadors(operand, '$', '-');
        if (full != null && Integer.parseInt(full) < getNombreFulls())
            return Integer.parseInt(full);

        throw new Exception("Ha escrit una referencia sense donar el full de la posició referenciada");
    }

    /**
     * Obté la posició de la cel·la donada una referència.
     *
     * @param operand referència.
     *
     * @return Retorna la posició de la cel·la de la referència.
     * */
    private String getPosicioRef(String operand) throws Exception {
        String posicio = substringDesDeSeparador(operand, '-');
        if (posicio != null)
            return posicio;

        throw new Exception("Ha escrit una referencia sense donar la posició de la cel·la");
    }

    /**
     * Obté el resultat d'aplicar una funció binària amb dues llistes.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param funcio nom de la funció.
     * @param operandInicial bloc de cel·les.
     * @param operandFinal bloc de cel·les.
     *
     * @return Retorna el resultat d'aplicar la funció a les dues llistes.
     * */
    private String funcioBinariaLlistes(int full, String posicio, String funcio, String operandInicial, String operandFinal) throws Exception {
        ArrayList<String> valors1 = gestionaLlista(full, posicio, operandInicial);
        ArrayList<String> valors2 = gestionaLlista(full, posicio, operandFinal);
        return Funcions.calcula(funcio, valors1, valors2);
    }

    /**
     * Obté el resultat d'aplicar una funció binària a dues cel·les.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param funcio nom de la funció.
     * @param operandInicial referència 1.
     * @param operandFinal referència 2.
     *
     * @return Retorna el resultat d'aplicar la funció a les dues cel·les.
     * */
    private String funcioBinariaReferencies(int full, String posicio, String funcio, String operandInicial, String operandFinal) throws Exception {
        String valor1 = addReferencies(full, posicio, getFullRef(operandInicial), getPosicioRef(operandInicial));
        String valor2 = addReferencies(full, posicio, getFullRef(operandFinal), getPosicioRef(operandFinal));
        return Funcions.calcula(funcio, valor1, valor2);
    }

    /**
     * Obté el resultat d'aplicar una funció binària a dos valors.
     *
     * @param funcio nom de la funció.
     * @param operandInicial valor 1.
     * @param operandFinal valor 2.
     *
     * @return Retorna el resultat d'aplicar la funció a dos valors.
     * */
    private String funcioBinariaValors(String funcio, String operandInicial, String operandFinal) throws Exception {
        return Funcions.calcula(funcio, operandInicial, operandFinal);
    }

    /**
     * Obté el resultat d'aplicar una funció binària a dos paràmetres.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param funcio nom de la funció.
     * @param operand conté els dos paràmetres.
     *
     * @return Retorna el resultat d'aplicar la funció als dos paràmetres.
     * */
    private String funcioBinaria(int full, String posicio, String funcio, String operand) throws Exception {
        String operandInicial = getOperandInicial(operand);
        String operandFinal = getOperandFinal(operand);
        if (operandInicial.indexOf(':') != -1)
            return funcioBinariaLlistes(full, posicio, funcio, operandInicial, operandFinal);
        else if (operandInicial.indexOf('$') != -1)
            return funcioBinariaReferencies(full, posicio, funcio, operandInicial, operandFinal);
        else
            return funcioBinariaValors(funcio, operandInicial, operandFinal);
    }

    /**
     * Donat un bloc de cel·les obté la primera referencia.
     *
     * @param operands conté el bloc de cel·les.
     *
     * @return Retorna la referència inicial.
     * */
    private String getReferenciaInicial(String operands) {
        return substringEntreSeparadors(operands, '-', ':');
    }

    /**
     * Donat un bloc de cel·les obté la última referencia.
     *
     * @param operands conté el bloc de cel·les.
     *
     * @return Retorna la referència final.
     * */
    private String getReferenciaFinal(String operands) {
        return substringDesDeSeparador(operands, ':');
    }

    /**
     * Donada la posició d'una cel·la retorna la part alfabètica, que correspon a la columna.
     *
     * @param referencia String amb la posició d'una cel·la.
     *
     * @return Retorna la part alfabètica d'una posició, que correspon a la columna.
     * */
    private String getColumnaRef(String referencia) {
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
     * Donada la posició d'una cel·la retorna la part numèrica, que correspon a la fila.
     *
     * @param referencia String amb la posició d'una cel·la.
     *
     * @return Retorna la part numèrica d'una posició, que correspon a la fila.
     * */
    private int getFilaRef(String referencia) {
        StringBuilder numeros = new StringBuilder();
        for (int i = 0; i < referencia.length(); i++) {
            char c = referencia.charAt(i);
            if ('0' <= c && c <= '9')
                numeros.append(c);
        }

        return Integer.parseInt(String.valueOf(numeros));
    }

    /**
     * Obté els valors del bloc format per múltiples files consecutives de la mateixa columna i afegeix les referències necessàries.
     *
     * @param full full de la cel·la que conté el bloc de cel·les.
     * @param posicio posició de la cel·la que conté el bloc de cel·les.
     * @param fullReferenciat full al qual pertany el bloc de cel·les.
     * @param columna columna a la qual pertany el bloc de cel·les.
     * @param posicioInicialFila primera fila del bloc de cel·les.
     * @param posicioFinalFila última fila del bloc de cel·les.
     *
     * @return Retorna un vector amb tots els valors de les cel·les del bloc.
     * */
    private ArrayList<String> gestionaColumna(int full, String posicio, int fullReferenciat, String columna, int posicioInicialFila, int posicioFinalFila) {
        ArrayList<String> valors = new ArrayList<>();
        for (int i = posicioInicialFila; i <= posicioFinalFila; i++) {
            valors.add(addReferencies(full, posicio, fullReferenciat, columna + i));
        }

        return valors;
    }

    /**
     * Donat un string el reverteix.
     *
     * @param str String a revertir.
     *
     * @return retorna el string revertit.
     * */
    private String reverse(String str) {
        StringBuilder str2 = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            str2.append(str.charAt(i));
        }

        return str2.toString();
    }

    /**
     * Donada una columna del full, obté la columna següent.
     *
     * @param columna és la columna de la qual volem obtenir la seva següent.
     *
     * @return retorna la columna següent a la donada.
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
     * Obté els valors del bloc format per múltiples columnes consecutives de la mateixa fila i afegeix les referències necessàries.
     *
     * @param full full de la cel·la que conté el bloc de cel·les.
     * @param posicio posició de la cel·la que conté el bloc de cel·les.
     * @param fullReferenciat full al qual pertany el bloc de cel·les.
     * @param fila fila a la qual pertany el bloc de cel·les.
     * @param posicioInicialColumna primera columna del bloc de cel·les.
     * @param posicioFinalColumna última columna del bloc de cel·les.
     *
     * @return Retorna un vector amb tots els valors de les cel·les del bloc.
     * */
    private ArrayList<String> gestionaFila(int full, String posicio, int fullReferenciat, int fila, String posicioInicialColumna, String posicioFinalColumna) {
        ArrayList<String> valors = new ArrayList<>();
        while (!posicioInicialColumna.equals(posicioFinalColumna)) {
            valors.add(addReferencies(full, posicio, fullReferenciat, posicioInicialColumna + fila));
            posicioInicialColumna = nextColumna(posicioInicialColumna);
        }
        return valors;
    }

    /**
     * Comprova si una posició és vàlida.
     *
     * @param posicio posició de la cel·la.
     *
     * @return Retorna si la posició és vàlida.
     * */
    private static boolean posicioNoValida(String posicio) {
        try {
            int sep = 0;
            for (int i = 0; i < posicio.length(); i++) {
                if ('0' <= posicio.charAt(i) && '9' >= posicio.charAt(i)) {
                    sep = i;
                    break;
                } else if ('A' > posicio.charAt(i) || posicio.charAt(i) > 'Z')
                    return true;
            }

            String partNumerica = posicio.substring(sep);
            Integer.parseInt(partNumerica);
            return partNumerica.length() == 0 || sep == 0;
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Obté els valors del bloc de cel·les.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param operand bloc de cel·les referenciat.
     */
    private ArrayList<String> gestionaLlista(int full, String posicio, String operand) throws Exception {
        int fullReferenciat = getFullRef(operand);
        String posicioInicial = getReferenciaInicial(operand);
        String posicioFinal = getReferenciaFinal(operand);

        if (posicioNoValida(posicioInicial))
            throw new Exception("Posició inicial no vàlida");
        int posicioInicialFila = getFilaRef(posicioInicial);
        String posicioInicialColumna = getColumnaRef(posicioInicial);

        if (posicioNoValida(posicioFinal))
            throw new Exception("Posició final no vàlida");
        int posicioFinalFila = getFilaRef(posicioFinal);
        String posicioFinalColumna = getColumnaRef(posicioFinal);

        if (posicioInicialFila == posicioFinalFila)
            return gestionaFila(full, posicio, fullReferenciat, posicioInicialFila, posicioInicialColumna, posicioFinalColumna);
        else if (posicioInicialColumna.equals(posicioFinalColumna))
            return gestionaColumna(full, posicio, fullReferenciat, posicioInicialColumna, posicioInicialFila, posicioFinalFila);
        else
            throw new Exception("El bloc seleccionat no forma part d'una mateixa fila o columna");
    }

    /**
     * Donada una funció unària retorna el seu resultat.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param funcio nom de la funció.
     * @param operand bloc de cel·les.
     *
     * @return Retorna el resultat de la funció unària.
     * */
    private String funcioUnariaLlista(int full, String posicio, String funcio, String operand) throws Exception {
        return Funcions.calcula(funcio, gestionaLlista(full, posicio, operand));
    }

    /**
     * Afegeix les referències corresponents i obté el valor de la cel·la referenciada.
     *
     * @param full és el full des del qual referenciem l'altra cel·la.
     * @param posicio és la posició dins el full del qual referenciem l'altra cel·la.
     * @param fullReferenciat és el full de la cel·la referenciada.
     * @param posicioReferenciada és la posició dins del full de la cel·la referenciada.
     *
     * @return Retorna el valor de la cel·la referenciada.
     */
    private String addReferencies(int full, String posicio, int fullReferenciat, String posicioReferenciada) {
        addReferencia(full, posicio, fullReferenciat, posicioReferenciada);
        return getValorCela(fullReferenciat, posicioReferenciada);
    }

    /**
     * Donada una funció unària amb una referència com a paràmetre retorna el seu resultat.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param funcio nom de la funció.
     * @param operand referència.
     *
     * @return Retorna el resultat de la funció unària amb una referència com a paràmetre.
     * */
    private String funcioUnariaReferencia(int full, String posicio, String funcio, String operand) throws Exception {
        return Funcions.calcula(funcio, addReferencies(full, posicio, getFullRef(operand), getPosicioRef(operand)));
    }

    /**
     * Donada una funció unària amb un valor com a paràmetre retorna el seu resultat.
     *
     * @param funcio nom de la funció.
     * @param operand operand sobre el qual apliquem la funció.
     *
     * @return Retorna el resultat de la funció unària amb un valor com a paràmetre.
     * */
    private String funcioUnariaValor(String funcio, String operand) throws Exception {
        return Funcions.calcula(funcio, operand);
    }

    /**
     * Donada una funció unària retorna el seu resultat.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param funcio nom de la funció.
     * @param operand operand sobre el qual apliquem la funció.
     *
     * @return Retorna el resultat de la funció unària.
     * */
    private String funcioUnaria(int full, String posicio, String funcio, String operand) throws Exception {
        if (operand.indexOf(':') != -1)
            return funcioUnariaLlista(full, posicio, funcio, operand);
        else if (operand.indexOf('$') != -1)
            return funcioUnariaReferencia(full, posicio, funcio, operand);
        else
            return funcioUnariaValor(funcio, operand);
    }

    /**
     * Donada una funció retorna el seu resultat.
     *
     * @param full full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param contingut funció.
     *
     * @return Retorna el resultat de la funció.
     * */
    public String getValorFunc(int full, String posicio, String contingut) throws Exception {
        String funcio = getFuncio(contingut);
        String operand = getOperand(contingut);
        if (operand.indexOf(',') != -1)
            return funcioBinaria(full, posicio, funcio, operand);
        else
            return funcioUnaria(full, posicio, funcio, operand);
    }

    /**
     * Modifica una cel·la i actualitza el document.
     *
     * @param full número de full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param contingut nou contingut de la cel·la.
     * */
    public void setCela(int full, String posicio, String contingut) throws Exception {
        this.ctrlDocument.deleteReferencies(full, posicio);
        if (contingut.charAt(0) == '=')
            this.ctrlDocument.setCela(full, posicio, getValorFunc(full, posicio, contingut), contingut);
        else if (contingut.charAt(0) == '$') {
            int fullReferenciat = getFull(contingut);
            String posicioReferenciada = getPosicio(contingut);
            addReferencia(full, posicio, fullReferenciat, posicioReferenciada);
            this.ctrlDocument.setCela(full, posicio, getValorCela(fullReferenciat, posicioReferenciada), contingut);
        }
        else
            this.ctrlDocument.setCela(full, posicio, contingut, null);

        updateAll();
    }

    /**
     * Obté el contingut d'una cel·la d'un full del document.
     *
     * @param full és el full on es troba la cel·la.
     * @param posicio és la localització de la cel·la de la qual es vol el contingut.
     *
     * @return Retorna el contingut de la cel·la.
     */
    private String getContingutCela(int full, String posicio) {
        return ctrlDocument.getContingutCela(full, posicio);
    }

    /**
     * Actualitza un document.
     */
    private void updateAll() throws Exception {
        int n = ctrlDocument.getNombreFulls();
        for (int i = 0; i < n; i++)
            updateFull(i);
    }

    /**
     * Actualitza un full.
     */
    private void updateFull(int full) throws Exception {
        Set<String> posicions = ctrlDocument.getPosicionsCelesFull(full);
        for (String posicio : posicions) {
            String contingut = getContingutCela(full, posicio);
            if (contingut != null)
                setCelaAux(full, posicio, contingut);
        }
    }

    /**
     * Modifica una cel·la.
     *
     * @param full número de full de la cel·la.
     * @param posicio posició de la cel·la.
     * @param contingut nou contingut de la cel·la.
     * */
    private void setCelaAux(int full, String posicio, String contingut) throws Exception {
        this.ctrlDocument.deleteReferencies(full, posicio);
        if (contingut.charAt(0) == '=')
            this.ctrlDocument.setCela(full, posicio, getValorFunc(full, posicio, contingut), contingut);
        else if (contingut.charAt(0) == '$') {
            int fullReferenciat = getFull(contingut);
            String posicioReferenciada = getPosicio(contingut);
            addReferencia(full, posicio, fullReferenciat, posicioReferenciada);
            this.ctrlDocument.setCela(full, posicio, getValorCela(fullReferenciat, posicioReferenciada), contingut);
        }
        else
            this.ctrlDocument.setCela(full, posicio, contingut, null);
    }
}