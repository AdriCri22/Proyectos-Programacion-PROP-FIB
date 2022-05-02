package classes;

import java.util.HashSet;

/**
 * Representa una cel·la.
 * Una cel·la té un valor i un contingut que pot ser buit, una funció o una referència. A més, cada cel·la té informació sobre a quines cel·les referència i quines la referencien.
 * @author alex.moreno.baeza
 */

public class Cela {

    /**
     * Representa el valor d'una cel·la.
     * */
    private String valor;

    /**
     * Representa el contingut d'una cel·la.
     * */
    private String contingut;

    /**
     * Representa el conjunt de cel·les que referencien la cel·la.
     * */
    private HashSet<Cela> referenciesAMi = new HashSet<>();

    /**
     * Representa el de referències a altres cel·les que té la cel·la.
     * */
    private HashSet<Cela> referenciesMeves = new HashSet<>();

    /**
     * Constructora d'una cel·la amb un valor i contingut null.
     *
     * @param valor és el valor que volem donar a la cel·la.
     * */
    public Cela (String valor) {
        this.valor = valor;
        this.contingut = null;
    }

    /**
     * Constructora d'una cel·la amb un valor i un contingut.
     *
     * @param valor és valor que volem donar a la cel·la.
     * @param contingut és el contingut que volem donar a la cel·la.
     * */
    public Cela(String valor, String contingut) {
        this.valor = valor;
        this.contingut = contingut;
    }

    /**
     * Obté el valor de la cel·la.
     *
     * @return Retorna el valor de la cel·la.
     * */
    public String getValor() {
        return this.valor;
    }

    /**
     * Obté el contingut de la cel·la.
     *
     * @return Retorna el contingut de la cel·la.
     * */
    public String getContingut() { return this.contingut;}

    /**
     * Modifica la cel·la i hi posa un nou valor.
     *
     * @param valor és el valor que volem donar-li a la cel·la.
     * */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Modifica la cel·la i hi posa un nou contingut.
     *
     * @param contingut és el contingut que volem donar-li a la cel·la.
     * */
    public void setContingut(String contingut) {
        this.contingut = contingut;
    }

    /**
     * Modifica la cel·la i hi posa una referència a una altra cel·la.
     *
     * @param cela és la cel·la que referenciem.
     * */
    public void addReferenciaMeva(Cela cela) {
        referenciesMeves.add(cela);
    }

    /**
     * Una cel·la ha referenciat a la meva i afegim aquesta cel·la a la llista de cel·les que tenen referències de la meva.
     *
     * @param cela és la cel·la que m'ha referenciat.
     * */
    public void addReferenciaAMi(Cela cela) {
        referenciesAMi.add(cela);
    }

    /**
     * Elimina totes les meves referències a altres cel·les.
     * */
    public void deleteReferenciesMeves() {
        for (Cela cela : referenciesMeves)
            cela.deleteReferenciaAMi(this);

        referenciesMeves.clear();
    }

    /**
     * Una cel·la deixa de referenciar-me i eliminem la cel·la que ja no em té com a referència.
     *
     * @param cela és la cel·la que em referenciava.
     * */
    private void deleteReferenciaAMi(Cela cela) {
        referenciesAMi.remove(cela);
    }

    /**
     * Indica si la cel·la és útil, és a dir, si té valor diferent del de per defecte.
     *
     * @return Retorna un booleà indicant si la cel·la aporta informació o no.
     * */
    public boolean esUtil() {
        if (!(this.referenciesAMi.size() == 0))
            return true;
        else if (!(this.contingut == null))
            return true;
        else
            return !this.valor.equals("0");
    }

    /**
     * Obté totes les cel·les que referencien a aquesta.
     *
     * @return Retorna un hashSet amb totes les cel·les que referencien a aquesta.
     * */
    public HashSet<Cela> getReferenciesAMi() {
        return referenciesAMi;
    }

    /**
     * Obté totes les cel·les que referència aquesta.
     *
     * @return Retorna un hashSet amb totes les cel·les que referenciades des d'aquesta.
     * */
    public HashSet<Cela> getReferenciesMeves() {
        return referenciesMeves;
    }

    /**
     * Modifica les referències d'altres cel·les a aquesta.
     *
     * @param refs són les noves referències.
     * */
    private void setReferenciesAMi(HashSet<Cela> refs) {
        this.referenciesAMi = refs;
    }

    /**
     * Modifica les referències que conté aquesta cel·la.
     *
     * @param refs són les noves referències.
     * */
    private void setReferenciesMeves(HashSet<Cela> refs) {
        this.referenciesMeves = refs;
    }

    /**
     * Retorna una còpia d'aquesta cel·la.
     *
     * @return Retorna una cel·la igual que la actual.
     * */
    public Cela cloneCela() {
        Cela cela = new Cela(this.valor, this.contingut);
        cela.setReferenciesAMi(new HashSet<>(this.referenciesAMi));
        cela.setReferenciesMeves(new HashSet<>(this.referenciesMeves));

        return cela;
    }
}

