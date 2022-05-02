package test;

import classes.Cela;
import classes.Full;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FullTest {

    /**
     * Test que comprova que donada una posició d'una cel·la existent al full, aquesta és modificada amb un nou valor i contingut.
     */
    @Test
    void setCela_retornaCelaExistentModificada() {
        Full full = new Full();
        String valor = "2";
        String pos = "A1";
        String nouValor = "2";
        String nouContingut = "0";
        full.addCela(pos,valor);

        Cela cel = mock(Cela.class);
        doNothing().when(cel).deleteReferenciesMeves();
        doNothing().when(cel).setValor(nouValor);
        doNothing().when(cel).setContingut(nouContingut);

        full.setCela(pos,nouValor, nouContingut);

        assertEquals(nouValor, full.getValorCela(pos));
        assertEquals(nouContingut, full.getContingutCela(pos));
    }

    /**
     * Test que comprova que donada una posició d'una cel·la no existent al full, un valor i un contingut, es crea la cel·la amb els paràmetres especificats.
     */
    @Test
    void setCela_retornaCelaCreada() {
        Full full = new Full();
        String pos = "A1";
        String nouValor = "2";
        String nouContingut = "0";

        Cela cel = mock(Cela.class);
        doNothing().when(cel).deleteReferenciesMeves();
        doNothing().when(cel).setValor(nouValor);
        doNothing().when(cel).setContingut(nouContingut);

        full.setCela(pos,nouValor, nouContingut);

        assertEquals(nouValor, full.getValorCela(pos));
        assertEquals(nouContingut, full.getContingutCela(pos));
    }

    /**
     * Test que comprova que donada una posició d'una cel·la existent al full, la retorna correctament.
     */
    @Test
    void obteCela_retornaCelaExistent() {
        Full full = new Full();
        String valor = "1";
        String pos = "A1";
        full.addCela(pos, valor);

        assertNotNull(full.getCela(pos));
        assertEquals("1", full.getValorCela(pos));
    }

    /**
     * Test que comprova que donada una posició d'una cel·la no existent al full, retorna null.
     */
    @Test
    void obteCela_retornaCelaNoExistent() {
        Full full = new Full();
        String pos = "A1";
        assertNull(full.getCela(pos));
    }

    /**
     * Test que comprova que donat un conjunt de cel·les del full, retorna correctament el nombre de cel·les amb valor diferent del de per defecte.
     */
    @Test
    void sizeCeles_retornaNumCels() {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("A2", "2");
        full.addCela("A3", "3");
        assertEquals(3, full.sizeCeles());
    }

    /**
     * Test que comprova que donat un full sense cel·les amb valor diferent del de per defecte, retorna que no hi ha cel·les.
     */
    @Test
    void sizeCeles_retornaNumCelsEmpty() {
        Full full = new Full();
        assertEquals(0, full.sizeCeles());
    }

    /**
     * Test que comprova que donada una cel·la1 existent que vol referenciar una cel·la2 no existent, es crea la cel·la2 i s'estableixen les referències.
     */
    @Test
    void addReferencia_ambCelaReferenciadaNoExistent() {
        Full full = new Full();
        Full fullReferenciat = new Full();
        String posReferenciada = "A1";
        String posReferenciadora = "B1";
        full.addCela(posReferenciadora, "1");

        Cela cel = mock(Cela.class);
        doNothing().when(cel).addReferenciaAMi(fullReferenciat.getCela(posReferenciada));
        doNothing().when(cel).addReferenciaMeva(full.getCela(posReferenciadora));

        full.addReferencia(posReferenciadora, fullReferenciat, posReferenciada);

        assertTrue(full.celes.get(posReferenciadora).getReferenciesMeves().contains(fullReferenciat.getCela(posReferenciada)));
        assertTrue(fullReferenciat.celes.get(posReferenciada).getReferenciesAMi().contains(full.getCela(posReferenciadora)));
    }

    /**
     * Test que comprova que donada una cel·la1 no existent que vol referenciar una cel·la2 existent, es crea la cel·la1 i s'estableixen les referències.
     */
    @Test
    void addReferencia_ambCelaReferenciadoraNoExistent() {
        Full full = new Full();
        Full fullReferenciat = new Full();
        String posReferenciada = "A1";
        String posReferenciadora = "B1";
        fullReferenciat.addCela(posReferenciada, "1");

        Cela cel = mock(Cela.class);
        doNothing().when(cel).addReferenciaAMi(fullReferenciat.getCela(posReferenciada));
        doNothing().when(cel).addReferenciaMeva(full.getCela(posReferenciadora));

        full.addReferencia(posReferenciadora, fullReferenciat, posReferenciada);

        assertTrue(full.celes.get(posReferenciadora).getReferenciesMeves().contains(fullReferenciat.getCela(posReferenciada)));
        assertTrue(fullReferenciat.celes.get(posReferenciada).getReferenciesAMi().contains(full.getCela(posReferenciadora)));
    }

    /**
     * Test que comprova que donada una cel·la1 no existent que vol referenciar una cel·la2 no existent, es creen les cel·les i s'estableixen les referències.
     */
    @Test
    void addReferencia_ambCelesNoExistents() {
        Full full = new Full();
        Full fullReferenciat = new Full();
        String posReferenciada = "A1";
        String posReferenciadora = "B1";

        Cela cel = mock(Cela.class);
        doNothing().when(cel).addReferenciaAMi(fullReferenciat.getCela(posReferenciada));
        doNothing().when(cel).addReferenciaMeva(full.getCela(posReferenciadora));

        full.addReferencia(posReferenciadora, fullReferenciat, posReferenciada);

        assertTrue(full.celes.get(posReferenciadora).getReferenciesMeves().contains(fullReferenciat.getCela(posReferenciada)));
        assertTrue(fullReferenciat.celes.get(posReferenciada).getReferenciesAMi().contains(full.getCela(posReferenciadora)));
    }

    /**
     * Test que comprova que donada una posició i un valor es crea la cel·la corresponent i s'afegeix al full.
     */
    @Test
    void addCela_retornaCelaAfegida() {
        Full full = new Full();
        String v = "2";

        full.addCela("A1", v);
        assertEquals(1, full.sizeCeles());
        assertEquals(v, full.getValorCela("A1"));
    }

    /**
     * Test que comprova que donada cel·la, es retorna el seu valor correctament.
     */
   @Test
    void getValorCela_retornaValor() {
        Full full = new Full();
        String v = "2";
        full.addCela("A1", v);

        Cela cel = mock(Cela.class);
        when(cel.getValor()).thenReturn("2");

        assertEquals(v, full.getValorCela("A1"));
    }

    /**
     * Test que comprova que donada cel·la, es retorna el seu contingut correctament.
     */
    @Test
    void getContingutCela_retornaContingut() {
        Full full = new Full();
        String v = "2";
        String c = "=func(2)";
        full.setCela("A1", v, c);

        Cela cel = mock(Cela.class);
        when(cel.getContingut()).thenReturn("=func(2)");

        assertEquals(c, full.getContingutCela("A1"));
    }

    /**
     * Test que comprova que donada cel·la, s'esborren les seves referències.
     */
   @Test
    void eliminaReferencies_referenciesBuides() {
        Full full = new Full();
        Full fullRef = new Full();
        String pos = "A1";
        full.addReferencia(pos, fullRef, "B1");

        Cela cel = mock(Cela.class);
        doNothing().when(cel).deleteReferenciesMeves();

        full.eliminaReferencies(pos);
        assertTrue(full.celes.get(pos).getReferenciesMeves().isEmpty());
    }

    /**
     * Test que comprova que es retornen correctament les cel·les del full.
     */
    @Test
    void getCeles_retornaCelesFull() {
        Full full = new Full();
        full.addCela("A1", "H");
        full.addCela("B2", "2");
        full.addCela("A3", "17");

        assertEquals(3, full.getCeles().size());
    }

    /**
     * Test que comprova que es retornen correctament les posicions de les cel·les del full.
     */
    @Test
    void getPosicions_retornaPosicionsCelesFull() {
        Full full = new Full();
        full.addCela("A1", "H");
        full.addCela("B2", "2");
        full.addCela("A3", "17");

        Set<String> posicions = new HashSet<>();
        posicions.add("A1");
        posicions.add("B2");
        posicions.add("A3");

        assertEquals(posicions, full.getPosicionsCelesFull());
    }

    /**
     * Test que comprova que una cel·la s'ha mogut de posició correctament a una posició on no hi havia cap cel·la.
     */
    @Test
    void moveCela_ambPosicioNovaBuida () {
        Full full = new Full();
        full.addCela("A3","1");
        full.moveCela("A3","AB7");

        assertNull(full.getCela("A3"));
        assertNotNull(full.getCela("AB7"));
        assertEquals("1",full.getValorCela("AB7"));
    }

    /**
     * Test que comprova que una cel·la s'ha mogut de posició correctament a una posició on hi havia una altra cel·la.
     */
    @Test
    void moveCela_ambPosicioNovaOcupada () {
        Full full = new Full();
        full.addCela("A3","1");
        full.addCela("AB7","8");
        full.moveCela("A3","AB7");

        assertNull(full.getCela("A3"));
        assertNotNull(full.getCela("AB7"));
        assertEquals("1",full.getValorCela("AB7"));
    }

    /**
     * Test que comprova que una cel·la s'ha copiat correctament a una posició on no hi havia una altra cel·la.
     */
    @Test
    void copyCela_ambPosicioNovaBuida() {
        Full full = new Full();
        full.addCela("A3","1");
        full.copyCela("A3","AB7");

        assertNotNull(full.getCela("A3"));
        assertNotNull(full.getCela("AB7"));
        assertEquals("1",full.getValorCela("A3"));
        assertEquals("1",full.getValorCela("AB7"));
    }

    /**
     * Test que comprova que una cel·la s'ha copiat correctament a una posició on hi havia una altra cel·la.
     */
    @Test
    void copyCela_ambPosicioNovaOcupada() {
        Full full = new Full();
        full.addCela("A3","1");
        full.addCela("AB7", "2");
        full.copyCela("A3","AB7");

        assertNotNull(full.getCela("A3"));
        assertNotNull(full.getCela("AB7"));
        assertEquals("1",full.getValorCela("A3"));
        assertEquals("1",full.getValorCela("AB7"));
    }

    /**
     * Test que comprova que es retornen totes les posicions de cel·les que contenen un valor numèric determinat.
     */
    @Test
    void buscaValorNumeric_retornaLlistaPosicionsTrobades() {
        Full full = new Full();
        full.addCela("A1","1");
        full.addCela("A2","1");
        full.addCela("A3","2");

        HashSet<String> res = full.buscarValor("1");
        assertTrue(res.contains("A1"));
        assertTrue(res.contains("A2"));
        assertFalse(res.contains("A3"));
    }

    /**
     * Test que comprova que es retornen totes les posicions de cel·les que contenen un valor alfabètic determinat.
     */
    @Test
    void buscaValorAlfabetic_retornaLlistaPosicionsTrobades() {
        Full full = new Full();
        full.addCela("A1","HOLA");
        full.addCela("A2","HO");
        full.addCela("A3","LA");

        HashSet<String> res = full.buscarValor("HO");
        assertTrue(res.contains("A2"));
        assertFalse(res.contains("A1"));
        assertFalse(res.contains("A3"));
    }

    /**
     * Test que comprova que es retornen totes les posicions de cel·les que contenen un valor numèric determinat que ha sigut reemplaçat per un altre.
     */
    @Test
    void buscaValorRemplacarNumeric_retornaLlistaPosicionsTrobadesIModificades() {
        Full full = new Full();
        full.addCela("A1","1");
        full.addCela("A2","1");
        full.addCela("A3","2");

        HashMap<String,Cela> res = full.buscarValorIReemplacar("1","5");
        assertTrue(res.containsKey("A1"));
        assertTrue(res.containsKey("A2"));
        assertFalse(res.containsKey("A3"));
        assertEquals("5",res.get("A1").getValor());
        assertEquals("5",res.get("A2").getValor());
    }

    /**
     * Test que comprova que es retornen totes les posicions de cel·les que contenen un valor alfabètic determinat que ha sigut reemplaçat per un altre.
     */
    @Test
    void buscaValorRemplacarAlfabetic_retornaLlistaPosicionsTrobadesIModificades() {
        Full full = new Full();
        full.addCela("A1","HOL?");
        full.addCela("A2","A");
        full.addCela("A3","B");

        HashMap<String,Cela> res = full.buscarValorIReemplacar("HOL?","HOLA");
        assertTrue(res.containsKey("A1"));
        assertFalse(res.containsKey("A2"));
        assertFalse(res.containsKey("A3"));
        assertEquals("HOLA",res.get("A1").getValor());
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha mogut cap a la dreta correctament.
     */
    @Test
    void moveBloc_novaPosicioColumnesDreta() throws Exception {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("A2", "2");
        full.addCela("A3", "3");
        String posIni = "A1";
        String posFi = "A3";
        full.moveBloc(posIni, posFi, 2);
        assertEquals("1", full.getValorCela("C1"));
        assertEquals("2", full.getValorCela("C2"));
        assertEquals("3", full.getValorCela("C3"));
        assertNull(full.getValorCela("A1"));
        assertNull(full.getValorCela("A2"));
        assertNull(full.getValorCela("A3"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha mogut cap a baix correctament.
     */
    @Test
    void moveBloc_novaPosicioFilaABaix() throws Exception {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("B1", "2");
        full.addCela("C1", "3");
        String posIni = "A1";
        String posFi = "C1";
        full.moveBloc(posIni, posFi, 2);
        assertEquals("1", full.getValorCela("A3"));
        assertEquals("2", full.getValorCela("B3"));
        assertEquals("3", full.getValorCela("C3"));
        assertNull(full.getValorCela("A1"));
        assertNull(full.getValorCela("B1"));
        assertNull(full.getValorCela("C1"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha mogut cap a l'esquerra correctament.
     */
    @Test
    void moveBloc_novaPosicioColumnesEsquerra() throws Exception {
        Full full = new Full();
        full.celes.put("F1", new Cela("F1"));
        full.celes.put("F2", new Cela("F2"));
        full.celes.put("F3", new Cela("F3"));
        full.celes.put("F4", new Cela("F4"));
        full.celes.put("F5", new Cela("F5"));
        full.moveBloc("F1", "F5", -5);

        assertEquals("F1", full.getValorCela("A1"));
        assertEquals("F2", full.getValorCela("A2"));
        assertEquals("F3", full.getValorCela("A3"));
        assertEquals("F4", full.getValorCela("A4"));
        assertEquals("F5", full.getValorCela("A5"));

        assertNull(full.getValorCela("F1"));
        assertNull(full.getValorCela("F2"));
        assertNull(full.getValorCela("F3"));
        assertNull(full.getValorCela("F4"));
        assertNull(full.getValorCela("F5"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha mogut cap a la dreta vàries columnes correctament.
     */
    @Test
    void moveBloc_novaPosicioVariesColumnesDreta() throws Exception {
        Full full = new Full();
        full.celes.put("F1", new Cela("F1"));
        full.celes.put("F2", new Cela("F2"));
        full.celes.put("F3", new Cela("F3"));
        full.celes.put("F4", new Cela("F4"));
        full.celes.put("F5", new Cela("F5"));
        full.moveBloc("F1", "F5", 5);

        assertEquals("F1", full.getValorCela("K1"));
        assertEquals("F2", full.getValorCela("K2"));
        assertEquals("F3", full.getValorCela("K3"));
        assertEquals("F4", full.getValorCela("K4"));
        assertEquals("F5", full.getValorCela("K5"));

        assertNull(full.getValorCela("F1"));
        assertNull(full.getValorCela("F2"));
        assertNull(full.getValorCela("F3"));
        assertNull(full.getValorCela("F4"));
        assertNull(full.getValorCela("F5"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha mogut cap a baix correctament.
     */
    @Test
    void moveBloc_novaPosicioFilesABaix() throws Exception {
        Full full = new Full();
        full.celes.put("A6", new Cela("A6"));
        full.celes.put("B6", new Cela("B6"));
        full.celes.put("C6", new Cela("C6"));
        full.celes.put("D6", new Cela("D6"));
        full.celes.put("E6", new Cela("E6"));
        full.moveBloc("A6", "E6", 5);

        assertEquals("A6", full.getValorCela("A11"));
        assertEquals("B6", full.getValorCela("B11"));
        assertEquals("C6", full.getValorCela("C11"));
        assertEquals("D6", full.getValorCela("D11"));
        assertEquals("E6", full.getValorCela("E11"));

        assertNull(full.getValorCela("A6"));
        assertNull(full.getValorCela("B6"));
        assertNull(full.getValorCela("C6"));
        assertNull(full.getValorCela("D6"));
        assertNull(full.getValorCela("E6"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha mogut cap a dalt correctament.
     */
    @Test
    void moveBloc_novaPosicioFilaADalt() throws Exception {
        Full full = new Full();
        full.celes.put("A6", new Cela("A6"));
        full.celes.put("B6", new Cela("B6"));
        full.celes.put("C6", new Cela("C6"));
        full.celes.put("D6", new Cela("D6"));
        full.celes.put("E6", new Cela("E6"));
        full.moveBloc("A6", "E6", -5);

        assertEquals("A6", full.getValorCela("A1"));
        assertEquals("B6", full.getValorCela("B1"));
        assertEquals("C6", full.getValorCela("C1"));
        assertEquals("D6", full.getValorCela("D1"));
        assertEquals("E6", full.getValorCela("E1"));

        assertNull(full.getValorCela("A6"));
        assertNull(full.getValorCela("B6"));
        assertNull(full.getValorCela("C6"));
        assertNull(full.getValorCela("D6"));
        assertNull(full.getValorCela("E6"));
    }

    /**
     * Test que comprova que salta l'excepció quan el bloc de cel·les seleccionat per moure conté més d'una fila o columna.
     */
    @Test
    void moveBloc_novaPosicioExcepcio() {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("A2", "2");
        full.addCela("B3", "3");
        String posIni = "A1";
        String posFi = "B3";
        Throwable exception = assertThrows(Exception.class, () -> full.moveBloc(posIni, posFi, 2));
        String expectedMessage = "El bloc conté més d'una fila i columna";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha ordenat alfanumèricament de forma ascendent correctament.
     */
    @Test
    void ordenaFila_donatOrdreAscendent() throws Exception {
        Full full = new Full();
        full.addCela("B1","3");
        full.addCela("C1","H");
        full.addCela("G1","89");
        full.addCela("D1", ">");
        full.ordenaBloc("A1","G1","ASC");
        assertNull(full.getCela("G1"));
        assertNull(full.getCela("E1"));
        assertNull(full.getCela("F1"));
        assertEquals("H",full.getValorCela("D1"));
        assertEquals("89",full.getValorCela("B1"));
        assertEquals(">",full.getValorCela("C1"));
        assertEquals("3",full.getValorCela("A1"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha ordenat alfanumèricament de forma ascendent correctament.
     */
    @Test
    void ordenaFila_donatOrdreAscendentVariant() throws Exception {
        Full full = new Full();
        full.addCela("B1","3");
        full.addCela("C1","HOLA");
        full.addCela("G1","89");
        full.ordenaBloc("A1","G1","ASC");
        assertNull(full.getCela("G1"));
        assertNull(full.getCela("E1"));
        assertNull(full.getCela("F1"));
        assertNull(full.getCela("D1"));
        assertEquals("89",full.getValorCela("B1"));
        assertEquals("HOLA",full.getValorCela("C1"));
        assertEquals("3",full.getValorCela("A1"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha ordenat alfanumèricament de forma descendent correctament.
     */
    @Test
    void ordenaFila_donatOrdreDescendent() throws Exception {
        Full full = new Full();
        full.addCela("B1","3");
        full.addCela("C1","1");
        full.addCela("G1","89");
        full.ordenaBloc("A1","G1","DESC");
        assertNull(full.getCela("G1"));
        assertNull(full.getCela("E1"));
        assertNull(full.getCela("F1"));
        assertNull(full.getCela("D1"));
        assertEquals("3",full.getValorCela("B1"));
        assertEquals("89",full.getValorCela("A1"));
        assertEquals("1",full.getValorCela("C1"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha ordenat alfanumèricament de forma ascendent correctament.
     */
    @Test
    void ordenaColumna_donatOrdreAscendent() throws Exception {
        Full full = new Full();
        full.addCela("A1","3");
        full.addCela("A3","H");
        full.addCela("A4","89");
        full.addCela("A5", ">");
        full.ordenaBloc("A1","A6","ASC");
        assertNull(full.getCela("A5"));
        assertNull(full.getCela("A6"));
        assertEquals("3",full.getValorCela("A1"));
        assertEquals("89",full.getValorCela("A2"));
        assertEquals(">",full.getValorCela("A3"));
        assertEquals("H",full.getValorCela("A4"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha ordenat alfanumèricament de forma ascendent correctament.
     */
    @Test
    void ordenaColumna_donatOrdreAscendentVariant() throws Exception {
        Full full = new Full();
        full.addCela("A1","3");
        full.addCela("A2","HOLA");
        full.addCela("A3","89");
        full.ordenaBloc("A1","A5","ASC");
        assertNull(full.getCela("A4"));
        assertNull(full.getCela("A5"));
        assertEquals("89",full.getValorCela("A2"));
        assertEquals("HOLA",full.getValorCela("A3"));
        assertEquals("3",full.getValorCela("A1"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha ordenat alfanumèricament de forma descendent correctament.
     */
    @Test
    void ordenaColumna_donatOrdreDescendent() throws Exception {
        Full full = new Full();
        full.addCela("B1","3");
        full.addCela("B2","1");
        full.addCela("B3","89");
        full.ordenaBloc("B1","B4","DESC");
        assertNull(full.getCela("B4"));
        assertEquals("3",full.getValorCela("B2"));
        assertEquals("89",full.getValorCela("B1"));
        assertEquals("1",full.getValorCela("B3"));
    }

    /**
     * Test que comprova que al voler ordenar un bloc de cel·les compost per més d'una fila o columna salta l'excepció corresponent.
     */
    @Test
    void ordenaBloc_saltaExcepcio() {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("A2", "2");
        full.addCela("B3", "3");
        String posIni = "A1";
        String posFi = "B3";
        Throwable exception = assertThrows(Exception.class, () -> full.ordenaBloc(posIni, posFi, "ASC"));
        String expectedMessage = "El bloc conté més d'una fila i columna";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha copiat a una altra columna de la dreta correctament.
     */
    @Test
    void copiaColumna_posicioColumnaDreta () throws Exception {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("A2", "2");
        full.addCela("A3", "3");
        String posIniAntiga = "A1";
        String posFiAntiga = "A3";
        full.copiaBloc(posIniAntiga, posFiAntiga, 2);
        assertEquals("1", full.getValorCela("C1"));
        assertEquals("2", full.getValorCela("C2"));
        assertEquals("3", full.getValorCela("C3"));
        assertEquals("1", full.getValorCela("A1"));
        assertEquals("2", full.getValorCela("A2"));
        assertEquals("3", full.getValorCela("A3"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha copiat a una altra fila de més a baix correctament.
     */
    @Test
    void copiaFila_posicioFilaABaix() throws Exception {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("B1", "2");
        full.addCela("C1", "3");
        String posIniAntiga = "A1";
        String posFiAntiga = "C1";
        full.copiaBloc(posIniAntiga, posFiAntiga, 2);
        assertEquals("1", full.getValorCela("A3"));
        assertEquals("2", full.getValorCela("B3"));
        assertEquals("3", full.getValorCela("C3"));
        assertEquals("1", full.getValorCela("A1"));
        assertEquals("2", full.getValorCela("B1"));
        assertEquals("3", full.getValorCela("C1"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha copiat a una altra columna de més a l'esquerra correctament.
     */
    @Test
    void copiaBloc_posicioColumnaEsquerra() throws Exception {
        Full full = new Full();
        full.celes.put("F1", new Cela("F1"));
        full.celes.put("F2", new Cela("F2"));
        full.celes.put("F3", new Cela("F3"));
        full.celes.put("F4", new Cela("F4"));
        full.celes.put("F5", new Cela("F5"));
        full.copiaBloc("F1", "F5", -5);

        assertEquals("F1", full.getValorCela("A1"));
        assertEquals("F2", full.getValorCela("A2"));
        assertEquals("F3", full.getValorCela("A3"));
        assertEquals("F4", full.getValorCela("A4"));
        assertEquals("F5", full.getValorCela("A5"));

        assertEquals("F1", full.getValorCela("F1"));
        assertEquals("F2", full.getValorCela("F2"));
        assertEquals("F3", full.getValorCela("F3"));
        assertEquals("F4", full.getValorCela("F4"));
        assertEquals("F5", full.getValorCela("F5"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una columna s'ha copiat a una altra columna de la dreta correctament.
     */
    @Test
    void copiaBloc_posicioColumnesVariesDreta() throws Exception {
        Full full = new Full();
        full.celes.put("F1", new Cela("F1"));
        full.celes.put("F2", new Cela("F2"));
        full.celes.put("F3", new Cela("F3"));
        full.celes.put("F4", new Cela("F4"));
        full.celes.put("F5", new Cela("F5"));
        full.copiaBloc("F1", "F5", 5);

        assertEquals("F1", full.getValorCela("K1"));
        assertEquals("F2", full.getValorCela("K2"));
        assertEquals("F3", full.getValorCela("K3"));
        assertEquals("F4", full.getValorCela("K4"));
        assertEquals("F5", full.getValorCela("K5"));

        assertEquals("F1", full.getValorCela("F1"));
        assertEquals("F2", full.getValorCela("F2"));
        assertEquals("F3", full.getValorCela("F3"));
        assertEquals("F4", full.getValorCela("F4"));
        assertEquals("F5", full.getValorCela("F5"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha copiat a una altra fila de més a baix correctament.
     */
    @Test
    void copiaBloc_posicioFilesABaix() throws Exception {
        Full full = new Full();
        full.celes.put("A6", new Cela("A6"));
        full.celes.put("B6", new Cela("B6"));
        full.celes.put("C6", new Cela("C6"));
        full.celes.put("D6", new Cela("D6"));
        full.celes.put("E6", new Cela("E6"));
        full.copiaBloc("A6", "E6", 5);

        assertEquals("A6", full.getValorCela("A11"));
        assertEquals("B6", full.getValorCela("B11"));
        assertEquals("C6", full.getValorCela("C11"));
        assertEquals("D6", full.getValorCela("D11"));
        assertEquals("E6", full.getValorCela("E11"));

        assertEquals("A6", full.getValorCela("A6"));
        assertEquals("B6", full.getValorCela("B6"));
        assertEquals("C6", full.getValorCela("C6"));
        assertEquals("D6", full.getValorCela("D6"));
        assertEquals("E6", full.getValorCela("E6"));
    }

    /**
     * Test que comprova que un bloc de cel·les que és una fila s'ha copiat a una altra fila de més a dalt correctament.
     */
    @Test
    void copiaBloc_posicioFilesADalt() throws Exception {
        Full full = new Full();
        full.celes.put("A6", new Cela("A6"));
        full.celes.put("B6", new Cela("B6"));
        full.celes.put("C6", new Cela("C6"));
        full.celes.put("D6", new Cela("D6"));
        full.celes.put("E6", new Cela("E6"));
        full.copiaBloc("A6", "E6", -5);

        assertEquals("A6", full.getValorCela("A1"));
        assertEquals("B6", full.getValorCela("B1"));
        assertEquals("C6", full.getValorCela("C1"));
        assertEquals("D6", full.getValorCela("D1"));
        assertEquals("E6", full.getValorCela("E1"));

        assertEquals("A6", full.getValorCela("A6"));
        assertEquals("B6", full.getValorCela("B6"));
        assertEquals("C6", full.getValorCela("C6"));
        assertEquals("D6", full.getValorCela("D6"));
        assertEquals("E6", full.getValorCela("E6"));
    }

    /**
     * Test que comprova que al voler copiar un bloc de cel·les compost per més d'una fila o columna salta l'excepció corresponent.
     */
    @Test
    void copiaBloc_saltaExcepcio() {
        Full full = new Full();
        full.addCela("A1", "1");
        full.addCela("A2", "2");
        full.addCela("B3", "3");
        String posIni = "A1";
        String posFi = "B3";
        Throwable exception = assertThrows(Exception.class, () -> full.copiaBloc(posIni, posFi, 2));
        String expectedMessage = "El bloc conté més d'una fila i columna";
        assertEquals(expectedMessage, exception.getMessage());
    }

    /**
     * Test que comprova que en eliminar la fila inicial de cel·les del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void deleteFiles_esborrarFilaInicial() {
        Full full = new Full();
        full.addCela("A0", "eliminado");
        full.addCela("A1", "A0");
        full.addCela("A2", "A1");
        full.addCela("A4", "A3");
        full.addCela("B0", "eliminado");
        full.addCela("B1", "B0");
        full.addCela("B2", "B1");
        full.addCela("B5", "B4");
        full.addCela("C0", "eliminado");
        full.addCela("C1", "C0");
        full.addCela("C2", "C1");

        full.deleteFiles(0, 1);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A3", full.getValorCela("A3"));
        assertNull(full.getValorCela("A4"));
        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B4", full.getValorCela("B4"));
        assertNull(full.getValorCela("B5"));
        assertEquals("C0", full.getValorCela("C0"));
        assertEquals("C1", full.getValorCela("C1"));
    }

    /**
     * Test que comprova que en eliminar una fila de cel·les intermèdia del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void deleteFiles_esborrarFilaIntermedia() {
        Full full = new Full();
        full.addCela("A0", "A0");
        full.addCela("A1", "eliminado");
        full.addCela("A2", "A1");
        full.addCela("A4", "A3");
        full.addCela("B0", "B0");
        full.addCela("B1", "eliminado");
        full.addCela("B2", "B1");
        full.addCela("B5", "B4");
        full.addCela("C0", "C0");
        full.addCela("C1", "eliminado");
        full.addCela("C2", "C1");
        full.deleteFiles(1, 1);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A3", full.getValorCela("A3"));
        assertNull(full.getValorCela("A4"));
        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B4", full.getValorCela("B4"));
        assertNull(full.getValorCela("B5"));
        assertEquals("C0", full.getValorCela("C0"));
        assertEquals("C1", full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
    }

    /**
     * Test que comprova que en eliminar dues files de cel·les del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void deleteFiles_esborrarDosFiles() {
        Full full = new Full();
        full.addCela("A0", "A0");
        full.addCela("A1", "eliminado");
        full.addCela("A2", "eliminado");
        full.addCela("A3", "A1");
        full.addCela("A4", "A2");
        full.addCela("B0", "B0");
        full.addCela("B1", "eliminado");
        full.addCela("B2", "eliminado");
        full.addCela("B5", "B3");
        full.addCela("C0", "C0");
        full.addCela("C1", "eliminado");
        full.addCela("C2", "eliminado");
        full.deleteFiles(1, 2);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A2", full.getValorCela("A2"));
        assertNull(full.getValorCela("A3"));
        assertNull(full.getValorCela("A4"));
        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B3", full.getValorCela("B3"));
        assertNull(full.getValorCela("B1"));
        assertNull(full.getValorCela("B2"));
        assertNull(full.getValorCela("B5"));
        assertEquals("C0", full.getValorCela("C0"));
        assertNull(full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
    }

    /**
     * Test que comprova que en eliminar una columna de cel·les inicial del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void deleteColumnes_esborrarColumnaInicial() {
        Full full = new Full();
        full.addCela("A0", "eliminado");
        full.addCela("A1", "eliminado");
        full.addCela("A2", "eliminado");
        full.addCela("A3", "eliminado");
        full.addCela("A4", "eliminado");
        full.addCela("B0", "A0");
        full.addCela("B1", "A1");
        full.addCela("B2", "A2");
        full.addCela("B5", "A5");
        full.addCela("C0", "B0");
        full.addCela("C1", "B1");
        full.addCela("C2", "B2");
        full.deleteColumnes("A", 1);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A2", full.getValorCela("A2"));
        assertEquals("A5", full.getValorCela("A5"));
        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B2", full.getValorCela("B2"));
        assertNull(full.getValorCela("C0"));
        assertNull(full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
    }

    /**
     * Test que comprova que en eliminar una columna de cel·les intermèdia del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void deleteColumnes_esborrarColumnaIntermedia() {
        Full full = new Full();
        full.addCela("A0", "A0");
        full.addCela("A1", "A1");
        full.addCela("A2", "A2");
        full.addCela("A3", "A3");
        full.addCela("A4", "A4");
        full.addCela("B0", "eliminado");
        full.addCela("B1", "eliminado");
        full.addCela("B2", "eliminado");
        full.addCela("B5", "eliminado");
        full.addCela("C0", "B0");
        full.addCela("C1", "B1");
        full.addCela("C2", "B2");
        full.deleteColumnes("B", 1);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A2", full.getValorCela("A2"));
        assertEquals("A3", full.getValorCela("A3"));
        assertEquals("A4", full.getValorCela("A4"));
        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B2", full.getValorCela("B2"));
        assertNull(full.getValorCela("C0"));
        assertNull(full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
        assertNull(full.getValorCela("B5"));
    }

    /**
     * Test que comprova que en eliminar dues columnes de cel·les del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void deleteColumnes_esborrarDosColumnes() {
        Full full = new Full();
        full.addCela("A0", "A0");
        full.addCela("A1", "A1");
        full.addCela("A2", "A2");
        full.addCela("B0", "eliminado");
        full.addCela("B1", "eliminado");
        full.addCela("B2", "eliminado");
        full.addCela("B5", "eliminado");
        full.addCela("C0", "eliminado");
        full.addCela("C1", "eliminado");
        full.addCela("C2", "eliminado");
        full.addCela("D1", "B1");
        full.addCela("D3", "B3");
        full.addCela("D4", "B4");
        full.deleteColumnes("B", 2);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B3", full.getValorCela("B3"));
        assertEquals("B4", full.getValorCela("B4"));
        assertNull(full.getValorCela("B0"));
        assertNull(full.getValorCela("B2"));
        assertNull(full.getValorCela("B5"));
        assertNull(full.getValorCela("C0"));
        assertNull(full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
    }

    /**
     * Test que comprova que en afegir una fila de cel·les a l'inici del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void addFiles_afegirFilaInicial() {
        Full full = new Full();
        full.addCela("A0", "A1");
        full.addCela("A1", "A2");
        full.addCela("A2", "A3");
        full.addCela("A3", "A4");
        full.addCela("A4", "A5");
        full.addCela("B0", "B1");
        full.addCela("B1", "B2");
        full.addCela("B2", "B3");
        full.addCela("B5", "B6");
        full.addCela("C0", "C1");
        full.addCela("C1", "C2");
        full.addCela("C2", "C3");
        full.addFiles(0, 1);

        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A2", full.getValorCela("A2"));
        assertEquals("A3", full.getValorCela("A3"));
        assertEquals("A4", full.getValorCela("A4"));
        assertEquals("A5", full.getValorCela("A5"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B3", full.getValorCela("B3"));
        assertEquals("B6", full.getValorCela("B6"));
        assertEquals("C1", full.getValorCela("C1"));
        assertEquals("C2", full.getValorCela("C2"));
        assertEquals("C3", full.getValorCela("C3"));
        assertNull(full.getValorCela("A0"));
        assertNull(full.getValorCela("B0"));
        assertNull(full.getValorCela("C0"));
    }

    /**
     * Test que comprova que en afegir dues files de cel·les al mig del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void addFiles_afegirFilesIntermedies() {
        Full full = new Full();
        full.addCela("A0", "A0");
        full.addCela("A1", "A3");
        full.addCela("A2", "A4");
        full.addCela("A3", "A5");
        full.addCela("B0", "B0");
        full.addCela("B1", "B3");
        full.addCela("B2", "B4");
        full.addCela("B5", "B7");
        full.addCela("C0", "C0");
        full.addCela("C1", "C3");
        full.addCela("C2", "C4");
        full.addFiles(1, 2);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A3", full.getValorCela("A3"));
        assertEquals("A4", full.getValorCela("A4"));
        assertEquals("A5", full.getValorCela("A5"));
        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B3", full.getValorCela("B3"));
        assertEquals("B4", full.getValorCela("B4"));
        assertEquals("B7", full.getValorCela("B7"));
        assertEquals("C0", full.getValorCela("C0"));
        assertEquals("C3", full.getValorCela("C3"));
        assertEquals("C4", full.getValorCela("C4"));
        assertNull(full.getValorCela("A1"));
        assertNull(full.getValorCela("A2"));
        assertNull(full.getValorCela("B1"));
        assertNull(full.getValorCela("B2"));
        assertNull(full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
    }

    /**
     * Test que comprova que en afegir una columna de cel·les a l'inici del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void addColumnes_afegirColumnaInicial() {
        Full full = new Full();
        full.addCela("A0", "B0");
        full.addCela("A1", "B1");
        full.addCela("A2", "B2");
        full.addCela("A3", "B3");
        full.addCela("A4", "B4");
        full.addCela("B0", "C0");
        full.addCela("B1", "C1");
        full.addCela("B2", "C2");
        full.addCela("B5", "C5");
        full.addCela("C0", "D0");
        full.addCela("C1", "D1");
        full.addCela("C2", "D2");
        full.addColumnes("A", 1);

        assertEquals("B0", full.getValorCela("B0"));
        assertEquals("B1", full.getValorCela("B1"));
        assertEquals("B2", full.getValorCela("B2"));
        assertEquals("B3", full.getValorCela("B3"));
        assertEquals("B4", full.getValorCela("B4"));
        assertEquals("C0", full.getValorCela("C0"));
        assertEquals("C1", full.getValorCela("C1"));
        assertEquals("C2", full.getValorCela("C2"));
        assertEquals("C5", full.getValorCela("C5"));
        assertEquals("D0", full.getValorCela("D0"));
        assertEquals("D1", full.getValorCela("D1"));
        assertEquals("D2", full.getValorCela("D2"));
        assertNull(full.getValorCela("A0"));
        assertNull(full.getValorCela("A1"));
        assertNull(full.getValorCela("A2"));
        assertNull(full.getValorCela("A3"));
        assertNull(full.getValorCela("A4"));
    }

    /**
     * Test que comprova que en afegir dues columnes de cel·les al mig del full, les altres cel·les es reorganitzen correctament.
     */
    @Test
    void test10() {
        Full full = new Full();
        full.addCela("A0", "A0");
        full.addCela("A1", "A1");
        full.addCela("A2", "A2");
        full.addCela("B0", "D0");
        full.addCela("B1", "D1");
        full.addCela("B2", "D2");
        full.addCela("B5", "D5");
        full.addCela("C0", "E0");
        full.addCela("C1", "E1");
        full.addCela("C2", "E2");
        full.addColumnes("B", 2);

        assertEquals("A0", full.getValorCela("A0"));
        assertEquals("A1", full.getValorCela("A1"));
        assertEquals("A2", full.getValorCela("A2"));
        assertEquals("D0", full.getValorCela("D0"));
        assertEquals("D1", full.getValorCela("D1"));
        assertEquals("D2", full.getValorCela("D2"));
        assertEquals("D5", full.getValorCela("D5"));
        assertEquals("E0", full.getValorCela("E0"));
        assertEquals("E1", full.getValorCela("E1"));
        assertEquals("E2", full.getValorCela("E2"));
        assertNull(full.getValorCela("B0"));
        assertNull(full.getValorCela("B1"));
        assertNull(full.getValorCela("B2"));
        assertNull(full.getValorCela("B5"));
        assertNull(full.getValorCela("C0"));
        assertNull(full.getValorCela("C1"));
        assertNull(full.getValorCela("C2"));
    }

}
