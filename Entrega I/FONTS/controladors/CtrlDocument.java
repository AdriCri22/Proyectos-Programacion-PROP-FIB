package controladors;

import classes.Document;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa el controlador de la classe Document.
 * @author adrian.cristian.crisan
 */

public class CtrlDocument {

    /**
     * Representa un document.
     * */
    private Document document;

    /**
     * Constructora del controlador de document.
     * */
    public CtrlDocument() {
        this.document = null;
    }

    /**
     * Comprova si tenim un document carregat.
     *
     * @return Retorna un booleà indicant si tenim un document carregat.
     * */
    public boolean existDocument() {
        return this.document != null;
    }

    /**
     * Constructora d'un Document.
     *
     * @param nom nom del document.
     */
    public void crearDocument (String nom) {
        this.document = new Document(nom);
    }

    /**
     * Modifica el nom del document.
     *
     * @param nom és el nou nom del document.
     */
    public void setNomDocument(String nom) {
        this.document.setNomDocument(nom);
    }

    /**
     * Obté el nom del document.
     *
     * @return Retorna el nom del document.
     */
    public String getNomDocument() {
        return this.document.getNomDocument();
    }

    /**
     * Obté el nombre de fulls del document.
     *
     * @return Obté el nombre de fulls del document.
     */
    public int getNombreFulls() {
        return this.document.getNombreFulls();
    }

    /**
     * Obté el valor d'una cel·la donada la seva posició en un full.
     *
     * @param full és el full a la qual pertany la cel·la que volem obtenir el valor.
     * @param posicio és la posició dins del full de la cel·la que volem obtenir el valor.
     *
     * @return Retorna el valor de la cel·la.
     */
    public String getValorCela(int full, String posicio) {
        return this.document.getValorCela(full, posicio);
    }

    /**
     * Canvia el valor i contingut d'una cel·la donada la seva posició i full en el qual es troba.
     *
     * @param full és el full a la qual pertany la cel·la que volem modificar el valor.
     * @param posicio és la posició dins del full de la cel·la que volem modificar el valor.
     * @param valor és el nou valor que volem donar a la cel·la.
     * @param contingut és el nou contingut que volem donar a la cel·la.
     */
    public void setCela(int full, String posicio, String valor, String contingut) {
        this.document.setCela(full, posicio, valor, contingut);
    }

    /**
     * Afegeix les corresponents referències entre cel·les.
     *
     * @param full és el full des del qual referenciem l'altra cel·la.
     * @param posicio és la posició dins el full del qual referenciem l'altra cel·la.
     * @param fullReferenciat és el full de la cel·la referenciada.
     * @param posicioReferenciada és la posició dins del full de la cel·la referenciada.
     */
    public void addReferencia(int full, String posicio, int fullReferenciat, String posicioReferenciada) {
        this.document.addReferencia(full, posicio, fullReferenciat, posicioReferenciada);
    }

    /**
     * Afegeix un nou full al document.
     */
    public void addFull() {
        this.document.addFull();
    }

    /**
     * Elimina un full del document.
     *
     * @param full és el número del full que volem eliminar.
     */
    public void deleteFull(int full) throws Exception{
        this.document.deleteFull(full);
    }

    /**
     * Elimina les referències d'una cel·la
     *
     * @param full és el número de full al qual pertany la cel·la.
     * @param posicio és la posició dins del full de la cel·la.
     */
    public void deleteReferencies(int full, String posicio) {
        this.document.eliminaReferencies(full, posicio);
    }

    /**
     * Copia i enganxa una cel·la d'un full en una altra.
     *
     * @param full és el full al qual pertany la cel·la que volem copiar.
     * @param antiga és la cel·la que es copia.
     * @param nova és la cel·la on volem copiar l'altra cel·la.
     */
    public void copyCela(int full, String antiga, String nova){
        this.document.copiaCela(full, antiga, nova);
    }

    /**
     * Mou el contingut d'una cel·la a una altra.
     *
     * @param full és el full al qual pertany la cel·la que volem moure.
     * @param posAntiga és la posició de la cel·la que es vol moure.
     * @param posNova és la nova posició on es vol moure la cel·la.
     */
    public void moveCela(int full, String posAntiga, String posNova){
        this.document.mouCela(full, posAntiga, posNova);
    }

    /**
     * Copia el contingut d'un bloc de cel·les d'un full i l'enganxa en un altre.
     *
     * @param full és el full a la qual pertany el bloc de cel·les que volem copiar.
     * @param posIniAntiga és la posició inicial del bloc de cel·les que volem copiar.
     * @param posFiAntiga és la posició final del bloc de cel·les que volem copiar.
     * @param desplacament nombre de posicions que es vol desplaçar per copiar.
     */
    public void copyBlocCeles(int full, String posIniAntiga, String posFiAntiga, int desplacament) throws Exception {
        this.document.copiaBlocCeles(full, posIniAntiga, posFiAntiga, desplacament);
    }

    /**
     * Modifica la posició del bloc de cel·les del full donat.
     *
     * @param full és el full a la qual pertany el bloc de cel·les que volem moure.
     * @param posicioInicial és la posició inicial del bloc de cel·les original.
     * @param posicioFinal és la posició final del bloc de cel·les original.
     * @param desplacament nombre de posicions que es vol desplaçar.
     * */
    public void moveBlocCeles(int full, String posicioInicial, String posicioFinal, int desplacament) throws Exception {
        this.document.mouBlocCeles(full, posicioInicial, posicioFinal, desplacament);
    }

    /**
     * Ordena el bloc de cel·les del full donat.
     *
     * @param full és el full al qual pertany el bloc de cel·les que volem ordenar.
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     * @param ordre és l'ordenació que es vol pel bloc. Pot ser ascendent o descendent.
     * */
    public void sortBlocCeles(int full, String posIni, String posFi, String ordre) throws Exception {
        this.document.ordenaBlocCeles(full, posIni, posFi, ordre);
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat en el full donat.
     *
     * @param full és el full on volem cercar el valor.
     * @param valor és el valor que es busca.
     *
     * @return es retorna un map amb totes les cel·les que contenen aquest valor.
     */
    public HashSet<String> search(int full, String valor){
        return this.document.cerca(full, valor);
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat i se'ls assigna el nou valor en el full donat.
     *
     * @param full és el full on volem cercar i reemplaçar un valor.
     * @param valor és el valor que es busca.
     * @param nouValor és el nou valor que es vol assignar a totes les cel·les que tenen el valor indicat.
     */
    public void searchReplace(int full, String valor, String nouValor){
        this.document.cercaIReemplaca(full, valor, nouValor);
    }

    /**
     * Afegeix un cert nombre de columnes del document.
     *
     * @param full és el full on es vol afegir la columna.
     * @param columna és la columna inicial des que volem afegir.
     * @param shift són el nombre de columnes que es volen afegir.
     */
    public void addColumnes(int full, String columna, int shift) {
        this.document.addColumnes(full, columna, shift);
    }

    /**
     * Elimina un cert nombre de columnes del document.
     *
     * @param full és el full on es vol eliminar la columna.
     * @param columna és la columna inicial des que volem eliminar.
     * @param shift són el nombre de columnes que es volen eliminar.
     */
    public void deleteColumnes(int full, String columna, int shift) {
        this.document.deleteColumnes(full, columna, shift);
    }

    /**
     * Afegeix un cert nombre de files del document.
     *
     * @param full és el full on es vol afegir la columna.
     * @param fila és la fila inicial des que volem afegir.
     * @param shift són el nombre de files que es volen afegir.
     */
    public void addFiles(int full, int fila, int shift) {
        this.document.addFiles(full, fila, shift);
    }

    /**
     * Elimina un cert nombre de files del document.
     *
     * @param full és el full on es vol eliminar la columna.
     * @param fila és la fila inicial des que volem eliminar.
     * @param shift són el nombre de files que es volen eliminar.
     */
    public void deleteFiles(int full, int fila, int shift) {
        this.document.deleteFiles(full, fila, shift);
    }

    /**
     * Obté el contingut d'una cel·la d'un full del document.
     *
     * @param full és el full on es troba la cel·la.
     * @param posicio és la localització de la cel·la de la qual es vol el contingut.
     *
     * @return Retorna el contingut de la cel·la.
     */
    public String getContingutCela(int full, String posicio) {
        return this.document.getContingutCela(full, posicio);
    }

    /**
     * Obté totes les posicions de cel·les amb valor diferent del de per defecte d'un full del document.
     *
     * @param full és el full del qual es volen les cel·les.
     *
     * @return Retorna un set amb les posicions de les cel·les.
     */
    public Set<String> getPosicionsCelesFull(int full) {
        return this.document.getPosicionsCelesFull(full);
    }
}
