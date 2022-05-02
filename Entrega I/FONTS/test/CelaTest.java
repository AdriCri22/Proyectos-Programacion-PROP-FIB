package test;

import classes.Cela;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Representa els tests de la classe cela.
 * Es testeja la correctesa dels mètodes públics de la classe.
 * @author alex.moreno.baeza
 */

public class CelaTest {

    /**
     * Test que comprova que donada una cel·la amb valor 2 retorna el valor esperat, és a dir, 2.
     */
    @Test
    public void obteValor_retornaValorNumeric() {
        Cela cell = new Cela("2");
        String cont = "2";
        assertEquals(cont, cell.getValor());
    }

    /**
     * Test que comprova que donada una cel·la amb valor "HOLA" el valor esperat, és a dir, "HOLA".
     */
    @Test
    public void obteValor_retornaValorAlfabetic() {
        Cela cell = new Cela("HOLA");
        String cont = "HOLA";
        assertEquals(cont, cell.getValor());
    }

    /**
     * Test que comprova que donada una cel·la amb contingut d'una funció, el retorna correctament.
     */
    @Test
    public void obteContingut_retornaContingutFuncio() {
        Cela cell = new Cela("1");
        cell.setContingut("=funcio(1)");
        String cont = "=funcio(1)";
        assertEquals(cont, cell.getContingut());
    }

    /**
     * Test que comprova que donada una cel·la amb contingut null, el retorna correctament.
     */
    @Test
    public void obteContingut_retornaContingutNull() {
        Cela cell = new Cela("1");
        assertNull(cell.getContingut());
    }

    /**
     * Test que comprova que donada una cel·la amb un valor 5, aquest el canviem per 38 i el valor es modifica correctament.
     * */
    @Test
    public void modificaValor_donatUnNouValor() {
        Cela cel = new Cela("5");
        String cont = "38";
        cel.setValor("38");
        assertEquals(cont, cel.getValor());
        assertNull(cel.getContingut());
    }

    /**
     * Test que comprova que donada una cel·la amb el contingut d'una funció, aquest el canviem per una altra i el contingut es modifica correctament.
     * */
    @Test
    public void modificaContingut_donatUnContingutNou() {
        Cela cel = new Cela("1");
        String cont = "=funcio1(1)";
        cel.setContingut("=funcio1(1)");
        assertEquals("1", cel.getValor());
        assertEquals(cont, cel.getContingut());
    }

    /**
     * Test que comprova que donades dues cel·les on la primera referència a la segona, s'afegeix correctament la cel·la2 a la llista de referencies de la ce·la1.
     * */
    @Test
    void addReferenciesMeves_donadaUnaCela(){
        Cela cel = new Cela("1");
        Cela celRef = new Cela("1");
        cel.addReferenciaMeva(celRef);
        assertTrue(cel.getReferenciesMeves().contains(celRef));
    }

    /**
     * Test que comprova que donades dues cel·les on la primera referència a la segona, s'afegeix correctament la cel·la1 a la llista de cel·les que referencien la cel·la2.
     * */
    @Test
    void addReferenciesAMi_donadaUnaCela(){
        Cela cel = new Cela("1");
        Cela celRef = new Cela("1");
        cel.addReferenciaAMi(celRef);
        assertTrue(cel.getReferenciesAMi().contains(celRef));
    }

    /**
     * Test que comprova que donada una cel·la es borra correctament la llista de les seves referències.
     * */
    @Test
    void deleteReferenciesMeves_donadaUnaCela(){
        Cela cel = new Cela("1");
        Cela celRef = new Cela("1");
        cel.addReferenciaMeva(celRef);
        assertFalse(cel.getReferenciesMeves().isEmpty());
        cel.deleteReferenciesMeves();
        assertTrue(cel.getReferenciesMeves().isEmpty());
    }

    /**
     * Test que comprova que donada una cel·la es borra correctament la llista de cel·les que la tenen com a referència.
     * */
    @Test
    void esUtil_donadaUnaCelaAmbValor() {
        Cela cel = new Cela("1");
        assertTrue(cel.esUtil());
    }

    /**
     * Test que comprova que donada una cel·la sense valor, aquesta retorna que no és útil
     * */
    @Test
    void esUtil_donadaUnaCelaSenseValor() {
        Cela cel = new Cela("0");
        assertFalse(cel.esUtil());
    }

    /**
     * Test que comprova que donada una cel·la amb valor, aquesta retorna que és útil
     * */
    @Test
    void esUtil_donadaUnaCelaSenseContingut() {
        Cela cel = new Cela("1");
        assertTrue(cel.esUtil());
    }

    /**
     * Test que comprova que donada una cel·la amb una llista de cel·les que la referencien, es retorna correctament la llista de referències.
     * */
    @Test
    void getReferenciesAMi_donadaUnaCela(){
        Cela cel = new Cela("1");
        Cela celRef1 = new Cela("1");
        Cela celRef2 = new Cela("2");
        cel.addReferenciaAMi(celRef1);
        cel.addReferenciaAMi(celRef2);
        assertEquals(2, cel.getReferenciesAMi().size());
        assertTrue(cel.getReferenciesAMi().contains(celRef1));
        assertTrue(cel.getReferenciesAMi().contains(celRef2));
    }

    /**
     * Test que comprova que donada una cel·la amb referències a altres cel·les, es retorna correctament la llista de referències.
     * */
    @Test
    void getReferenciesMeves_donadaUnaCela(){
        Cela cel = new Cela("1");
        Cela celRef1 = new Cela("1");
        Cela celRef2 = new Cela("2");
        cel.addReferenciaMeva(celRef1);
        cel.addReferenciaMeva(celRef2);
        assertEquals(2, cel.getReferenciesMeves().size());
        assertTrue(cel.getReferenciesMeves().contains(celRef1));
        assertTrue(cel.getReferenciesMeves().contains(celRef2));
    }
}