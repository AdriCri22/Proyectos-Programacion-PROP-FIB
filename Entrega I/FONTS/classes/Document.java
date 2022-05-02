package classes;

import classes.Full;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa un document de full de càlcul.
 * Un document conté un conjunt de fulls. Mínim té un full i no hi ha límit.
 * @author irene.bertolin.rico
 */

public class Document {

    /**
     * Representa el nom del document.
     * */
    public String nomDocument;

    /**
     * Representa el conjunt de fulls del document.
     * */
    private final ArrayList<Full> fulls = new ArrayList<>();

    /**
     * Constructora d'un document amb el nom indicat i amb un full.
     *
     * @param nom és el nom del document.
     */
    public Document (String nom) {
        this.nomDocument = nom;
        this.fulls.add(new Full());
    }

    /**
     * Consultora d'un full del document.
     *
     * @param nfull és el número de full que es vol seleccionar.
     * @return Retorna el full demanat.
     */
    public Full getFull(int nfull) {
        return fulls.get(nfull);
    }

    /**
     * Consultora del nom del document.
     *
     * @return Retorna el nom del document.
     */
    public String getNomDocument() {
        return this.nomDocument;
    }

    /**
     * Modifica el nom del document.
     *
     * @param nom és el nou nom que se li vol donar al document.
     */
    public void setNomDocument(String nom) {
        this.nomDocument = nom;
    }

    /**
     * Afegeix un full al document.
     */
    public void addFull() {
        this.fulls.add(new Full());
    }

    /**
     * Elimina el full indicat del document.
     *
     * @param full és el número del full a eliminar del document.
     */
    public void deleteFull(int full) throws Exception {
        if (fulls.size() == 1) {    //Mínim 1 full per document
            throw new Exception("No es pot eliminar l'únic full existent al document");
        }
        else if (full < 0 || fulls.size() < full) {
            throw new Exception("El full a eliminar no existeix al document");
        }
        else {
            this.fulls.remove(full);
        }
    }

    /**
     * Consultora del nombre de fulls del document.
     *
     * @return Retorna el nombre de fulls del document.
     */
    public int getNombreFulls() {
        return this.fulls.size();
    }

    /**
     * Modifica una cel·la d'un full del document.
     *
     * @param full és el full a la qual pertany la cel·la que volem modificar el valor.
     * @param posicio és la posició dins del full de la cel·la que volem modificar el valor.
     * @param valor és el nou valor que volem donar a la cel·la.
     * @param contingut és el nou contingut que volem donar a la cel·la.
     */
    public void setCela(int full, String posicio, String valor, String contingut) {
        this.fulls.get(full).setCela(posicio, valor, contingut);
    }

    /**
     * Retorna el valor d'una cel·la que es troba en la posició donada en el full indicat.
     *
     * @param full és el full al qual pertany la cel·la que volem obtenir el valor.
     * @param posicio és la posició dins del full de la cel·la que volem obtenir el valor.
     * @return Retorna el valor de la cel·la.
     */
    public String getValorCela(int full, String posicio) {
        return fulls.get(full).getValorCela(posicio);
    }

    /**
     * Afegeix les corresponents referències entre cel·les donats els fulls corresponents.
     *
     * @param full és el full des del qual referenciem l'altra cel·la.
     * @param posicio és la posició dins el full del qual referenciem l'altra cel·la.
     * @param fullReferenciat és el full de la cel·la referenciada.
     * @param posicioReferenciada és la posició dins del full de la cel·la referenciada.
     */
    public void addReferencia(int full, String posicio, int fullReferenciat, String posicioReferenciada) {
        this.fulls.get(full).addReferencia(posicio, this.fulls.get(fullReferenciat), posicioReferenciada);
    }

    /**
     * Elimina les referències d'una cel·la que es troba en un full del document.
     *
     * @param full és el número de full al qual pertany la cel·la.
     * @param posicio és la posició dins del full de la cel·la.
     */
    public void eliminaReferencies(int full, String posicio) {
        this.fulls.get(full).eliminaReferencies(posicio);
    }

    /**
     * Copia i enganxa una cel·la que es troba en un full en una altra.
     *
     * @param full és el full al qual pertany la cel·la que volem copiar.
     * @param antiga és la cel·la que es copia.
     * @param nova és la cel·la on volem copiar l'altra cel·la.
     */
    public void copiaCela(int full, String antiga, String nova){
        this.fulls.get(full).copyCela(antiga,nova);
    }

    /**
     * Modifica la posició de la cel·la del full donat.
     *
     * @param full és el full al qual pertany la cel·la que volem moure.
     * @param posAntiga és la posició de la cel·la que es vol moure.
     * @param posNova és la nova posició on es vol moure la cel·la.
     */
    public void mouCela(int full, String posAntiga,String posNova){
        this.fulls.get(full).moveCela(posAntiga, posNova);
    }

    /**
     * Copia el contingut d'un bloc de cel·les d'un dels fulls del document i l'enganxa en un altre bloc.
     *
     * @param full és el full a la qual pertany el bloc de cel·les que volem copiar.
     * @param posIniAntiga és la posició inicial del bloc de cel·les que volem copiar.
     * @param posFiAntiga és la posició final del bloc de cel·les que volem copiar.
     * @param desplacament és el nombre de posicions que es vol desplaçar per copiar.
     */
    public void copiaBlocCeles(int full, String posIniAntiga, String posFiAntiga, int desplacament) throws Exception {
        this.fulls.get(full).copiaBloc(posIniAntiga, posFiAntiga, desplacament);
    }

    /**
     * Modifica la posició del bloc de cel·les del full donat.
     *
     * @param full és el full a la qual pertany el bloc de cel·les que volem moure.
     * @param posicioInicial és la posició inicial del bloc de cel·les original.
     * @param posicioFinal és la posició final del bloc de cel·les original.
     * @param desplacament és el nombre de posicions que es vol desplaçar.
     * */
    public void mouBlocCeles(int full, String posicioInicial, String posicioFinal, int desplacament) throws Exception {
        this.fulls.get(full).moveBloc(posicioInicial, posicioFinal, desplacament);
    }

    /**
     * Ordena el bloc de cel·les del full donat.
     *
     * @param full és el full al qual pertany el bloc de cel·les que volem ordenar.
     * @param posIni és la posició inicial del bloc de cel·les original.
     * @param posFi és la posició final del bloc de cel·les original.
     * @param ordre és l'ordenació que es vol donar al bloc. Pot ser ascendent o descendent.
     * */
    public void ordenaBlocCeles(int full, String posIni, String posFi,String ordre) throws Exception {
        this.fulls.get(full).ordenaBloc(posIni, posFi, ordre);
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat en el full donat.
     *
     * @param full és el full on volem cercar el valor.
     * @param valor és el valor que es busca.
     * @return Retorna un map amb totes les cel·les que contenen aquest valor.
     */
    public HashSet<String> cerca(int full, String valor){
        return this.fulls.get(full).buscarValor(valor);
    }

    /**
     * Busca totes les cel·les que tenen el valor indicat i se'ls assigna el nou valor en el full donat.
     *
     * @param full és el full on volem cercar i reemplaçar un valor.
     * @param valor és el valor que es busca.
     * @param nouValor és el nou valor que es vol assignar a totes les cel·les que tenen el valor indicat.
     */
    public void cercaIReemplaca(int full, String valor, String nouValor){
        this.fulls.get(full).buscarValorIReemplacar(valor, nouValor);
    }

    /**
     * Afegeix un cert nombre de columnes a un full del document.
     *
     * @param full és el full on es vol afegir la columna.
     * @param columna és la columna inicial des que volem afegir.
     * @param shift són el nombre de columnes que es volen afegir.
     */
    public void addColumnes(int full, String columna, int shift) {
        this.fulls.get(full).addColumnes(columna, shift);
    }

    /**
     * Elimina un cert nombre de columnes d'un full del document.
     *
     * @param full és el full on es vol eliminar la columna.
     * @param columna és la columna inicial des que volem eliminar.
     * @param shift són el nombre de columnes que es volen eliminar.
     */
    public void deleteColumnes(int full, String columna, int shift) {
        this.fulls.get(full).deleteColumnes(columna, shift);
    }

    /**
     * Afegeix un cert nombre de files a un full del document.
     *
     * @param full és el full on es vol afegir la columna.
     * @param fila és la fila inicial des que volem afegir.
     * @param shift són el nombre de files que es volen afegir.
     */
    public void addFiles(int full, int fila, int shift) {
        this.fulls.get(full).addFiles(fila, shift);
    }

    /**
     * Elimina un cert nombre de files d'un full del document.
     *
     * @param full és el full on es vol eliminar la columna.
     * @param fila és la fila inicial des que volem eliminar.
     * @param shift són el nombre de files que es volen eliminar.
     */
    public void deleteFiles(int full, int fila, int shift) {
        this.fulls.get(full).deleteFiles(fila, shift);
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
        return this.fulls.get(full).getContingutCela(posicio);
    }

    /**
     * Obté totes les posicions de cel·les amb valor diferent del de per defecte d'un full del document.
     *
     * @param full és el full del qual es volen les cel·les.
     *
     * @return Retorna un set amb les posicions de les cel·les.
     */
    public Set<String> getPosicionsCelesFull(int full) {
        return this.fulls.get(full).getPosicionsCelesFull();
    }
}

