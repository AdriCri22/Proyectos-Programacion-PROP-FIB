package test;

import classes.Document;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class DocumentTest {

    /**
     * Test que comprova que donat un document retorna correctament el seu nom.
     */
    @Test
    void getNomDocument_retornaNomDocument() {
        Document doc = new Document("nomDoc");
        assertEquals("nomDoc", doc.getNomDocument());
    }

    /**
     * Test que comprova que donat un nou nom per un document aquest es canvia correctament.
     */
    @Test
    void setNomDocument_retornaNouNomDoc() {
        Document doc = new Document("nomDoc");
        doc.setNomDocument("nomDocNou");
        assertEquals("nomDocNou", doc.getNomDocument());
    }

    /**
     * Test que comprova que s'afegeix un nou full a un document correctament.
     */
    @Test
    void addFull_fullAfegit() {
        Document doc = new Document("nomDoc");
        doc.addFull();
        assertEquals(2, doc.getNombreFulls());
    }

    /**
     * Test que comprova que s'ha eliminat un full del document correctament.
     */
    @Test
    void deleteFull_fullEliminat() throws Exception {
        Document doc = new Document("nomDoc");
        doc.addFull();
        doc.addFull();
        doc.deleteFull(1);
        assertEquals(2, doc.getNombreFulls());
    }

    /**
     * Test que comprova que si es vol esborrar un full i només hi ha un, salta l'excepció corresponent.
     */
    @Test
    void deleteFull_ExcepcioFullUnic() {
        Document doc = new Document("nomDoc");
        Throwable exception = assertThrows(Exception.class, () ->  doc.deleteFull(0));
        String expectedMessage = "No es pot eliminar l'únic full existent al document";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test que comprova que es retorna correctament el nombre de fulls d'un document.
     */
    @Test
    void getNombreFulls_nombreFulls() {
        Document doc = new Document("nomDoc");
        assertEquals(1, doc.getNombreFulls());
    }

    /**
     * Test que comprova que s'inicialitza una cel·la d'un full d'un document correctament.
     */
    @Test
    void setCela_afegeixCelaAunFull () {
        Document doc = new Document("nomDoc");
        doc.setCela(0,"A1", "1", "func");
        assertEquals("1", doc.getValorCela(0, "A1"));
    }

    /**
     * Test que comprova que s'obté el valor d'una cel·la d'un full d'un document correctament.
     */
    @Test
    void getValorCela_obteCelaFull () {
        Document doc = new Document("nomDoc");
        doc.setCela(0,"B1", "2", "func");
        assertEquals("2", doc.getValorCela(0, "B1"));
    }

    /**
     * Test que comprova que es copia una cel·la d'un full d'un document correctament.
     */
    @Test
    void copiaCela_posicioNova(){
        Document doc = new Document("nomfull");
        doc.addFull();
        doc.setCela(0,"A1","1",null);
        doc.copiaCela(0,"A1","A2");
        assertEquals("1",doc.getFull(0).getValorCela("A1"));
        assertEquals(doc.getFull(0).getValorCela("A1"),doc.getFull(0).getValorCela("A1"));
    }

    /**
     * Test que comprova que es mou una cel·la d'un full d'un document correctament.
     */
    @Test
    void mouCela_posicioNova(){
        Document doc = new Document("nomfull");
        doc.addFull();
        doc.setCela(0,"A1","1",null);
        doc.mouCela(0,"A1","A2");
        assertEquals("1",doc.getFull(0).getValorCela("A2")); //comprobar que con el push de Adri de cambiar remove por put funciona
        assertNull(doc.getFull(0).getCela("A1"));
    }

    /**
     * Test que comprova que es copia un bloc de cel·les d'un full d'un document correctament.
     */
    @Test
    void copiaBloc_posicioNova() throws Exception {
        Document doc = new Document("nomfull");
        doc.addFull();
        doc.setCela(0,"A1","1",null);
        doc.setCela(0,"A2","2",null);
        doc.setCela(0,"A3","3",null);

        doc.copiaBlocCeles(0,"A1","A3",3);
        assertEquals("1",doc.getValorCela(0,"A1"));
        assertEquals("2",doc.getValorCela(0,"A2"));
        assertEquals("3",doc.getValorCela(0,"A3"));
        assertEquals("1",doc.getValorCela(0,"D1"));
        assertEquals("2",doc.getValorCela(0,"D2"));
        assertEquals("3",doc.getValorCela(0,"D3"));
    }

    /**
     * Test que comprova que es mou un bloc de cel·les d'un full d'un document correctament.
     */
    @Test
    void mouBloc_posicioNova() throws Exception {
        Document doc = new Document("nomfull");
        doc.addFull();
        doc.setCela(0,"A1","1",null);
        doc.setCela(0,"A2","2",null);
        doc.setCela(0,"A3","3",null);

        doc.mouBlocCeles(0,"A1","A3",3);
        assertNull(doc.getValorCela(0,"A1"));
        assertNull(doc.getValorCela(0,"A2"));
        assertNull(doc.getValorCela(0,"A3"));
        assertEquals("1",doc.getValorCela(0,"D1"));
        assertEquals("2",doc.getValorCela(0,"D2"));
        assertEquals("3",doc.getValorCela(0,"D3"));
    }

    /**
     * Test que comprova que s'ordena un bloc de cel·les d'un full d'un document correctament.
     */
    @Test
    void ordenaBlocColumna_ordreAscendent() throws  Exception{
        Document doc = new Document("nomfull");
        doc.addFull();

        doc.setCela(0,"A5","alex",null);
        doc.setCela(0,"A2","azreau",null);
        doc.setCela(0,"A4","bobo",null);
        doc.ordenaBlocCeles(0,"A1","A7","ASC");

        assertEquals("alex",doc.getValorCela(0,"A1"));
        assertEquals("azreau",doc.getValorCela(0,"A2"));
        assertEquals("bobo",doc.getValorCela(0,"A3"));
        assertNull(doc.getValorCela(0,"A4"));
        assertNull(doc.getValorCela(0,"A5"));
        assertNull(doc.getValorCela(0,"A6"));
        assertNull(doc.getValorCela(0,"A7"));
    }

    /**
     * Test que comprova que es retornen les posicions de les cel·les d'un full d'un document que contenen un valor determinat, correctament.
     */
    @Test
    void cerca_retornaCelesAmbValor(){
        Document doc = new Document("nomfull");
        doc.addFull();
        doc.setCela(0,"A1","1",null);
        doc.setCela(0,"A2","1",null);

        HashSet<String> res = doc.cerca(0,"1");
        assertTrue(res.contains("A1"));
        assertTrue(res.contains("A2"));
        assertFalse(res.contains("A3"));
    }

    /**
     * Test que comprova que es retornen les posicions de les cel·les d'un full d'un document que contenen un valor determinat amb aquest reemplaçat, correctament.
     */
    @Test
    void cercaIReemplaça_retornaCelesReemplaçades(){
        Document doc = new Document("nomfull");
        doc.addFull();
        doc.setCela(0,"A1","1",null);
        doc.setCela(0,"A2","1",null);
        doc.setCela(0,"A3","2",null);

        doc.cercaIReemplaca(0,"1","3");

        assertEquals("3",doc.getValorCela(0,"A2"));
        assertEquals("3",doc.getValorCela(0,"A1"));

    }
}
