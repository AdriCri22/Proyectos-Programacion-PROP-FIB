package test;

import classes.Funcions;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Representa els tests de la classe funcio.
 * Es testeja la correctesa de les funcions.
 * @author adrian.cristian.crisan
 */
class FuncionsTest {

    // Funcions Unàries Valor

    /**
     * Test que comprova que truncar el valor 1.9 dona com a resultat 1.0.
     * */
    @Test
    void truncarValor_uComaNou_u() throws Exception {
        assertEquals("1.0", Funcions.calcula(Funcions.TRUNCARVALOR, "1.9"));
    }

    /**
     * Test que comprova que arrodonir el valor 1.4 dona com a resultat 1.0.
     * */
    @Test
    void arrodonirValor_uComaQuatre_u() throws Exception {
        assertEquals("1.0", Funcions.calcula(Funcions.ARRODONIRVALOR, "1.4"));
    }

    /**
     * Test que comprova que arrodonir el valor 1.5 dona com a resultat 2.0.
     * */
    @Test
    void arrodonirValor_uComaCinc_dos() throws Exception {
        assertEquals("2.0", Funcions.calcula(Funcions.ARRODONIRVALOR, "1.5"));
    }

    /**
     * Test que comprova que el valor en decimal 2 és 10 en binari.
     * */
    @Test
    void decimalABinari_dos_uZero() throws Exception {
        assertEquals("10", Funcions.calcula(Funcions.DECIMALABINARI, "2"));
    }

    /**
     * Test que comprova que el valor 010 en binari és 2 en decimal.
     * */
    @Test
    void binariADecimal_zeroUZero_dos() throws Exception {
        assertEquals("2", Funcions.calcula(Funcions.BINARIADECIMAL, "010"));
    }

    /**
     * Test que comprova que el valor 10 en binari és 2 en decimal.
     * */
    @Test
    void binariADecimal_UZero_dos() throws Exception {
        assertEquals("2", Funcions.calcula(Funcions.BINARIADECIMAL, "10"));
    }

    /**
     * Test que comprova que el valor 10 en decimal és A en hexadecimal.
     * */
    @Test
    void decimalAHexhadecimal_deu_A() throws Exception {
        assertEquals("A", Funcions.calcula(Funcions.DECIMALAHEXADECIMAL, "10"));
    }

    /**
     * Test que comprova que el valor F en hexadecimal és 15 en decimal.
     * */
    @Test
    void hexadecimalADecimal_F_quinze() throws Exception {
        assertEquals("15", Funcions.calcula(Funcions.HEXADECIMALADECIMAL, "F"));
    }

    /**
     * Test que comprova que un euro en dòlars és 1.09.
     * */
    @Test
    void eurAusd_1eur_1point09usd() throws Exception {
        assertEquals("1.09", Funcions.calcula(Funcions.EURAUSD, "1"));
    }

    /**
     * Test que comprova que un dòlar en euros és 0.92.
     * */
    @Test
    void usdAeur_1usd_0point92eur() throws Exception {
        assertEquals("0.92", Funcions.calcula(Funcions.USDAEUR, "1"));
    }

    /**
     * Test que comprova que l'increment de valor de -1 retorna 0.
     * */
    @Test
    void incrementaValor_menysU_zero() throws Exception {
        assertEquals("0", Funcions.calcula(Funcions.INCREMENTAVALOR, "-1"));
    }

    /**
     * Test que comprova que l'increment de valor d'1 retorna 2.
     * */
    @Test
    void incrementaValor_u_dos() throws Exception {
        assertEquals("2", Funcions.calcula(Funcions.INCREMENTAVALOR, "1"));
    }

    /**
     * Test que comprova que el decrement de valor de -1 retorna -2.
     * */
    @Test
    void decrementaValor_menysU_menysDos() throws Exception {
        assertEquals("-2", Funcions.calcula(Funcions.DECREMENTAVALOR, "-1"));
    }

    /**
     * Test que comprova que el decrement de valor de 2 retorna 1.
     * */
    @Test
    void decrementaValor_dos_u() throws Exception {
        assertEquals("1", Funcions.calcula(Funcions.DECREMENTAVALOR, "2"));
    }

    /**
     * Test que comprova que el valor absolut de -2 retorna -2.
     * */
    @Test
    void valorAbsolut_menysDos_dos() throws Exception {
        assertEquals("2.0", Funcions.calcula(Funcions.VALORABSOLUT, "-2"));
    }

    /**
     * Test que comprova que el valor absolut de 2 retorna 2.
     * */
    @Test
    void valorAbsolut_dos_dos() throws Exception {
        assertEquals("2.0", Funcions.calcula(Funcions.VALORABSOLUT, "2"));
    }

    /**
     * Test que comprova que el canvi de majúscules a minuscules de "aAbBcC" retorna "aabbcc".
     * */
    @Test
    void majusculesAMinuscules_aAbBcC_aabbcc() throws Exception {
        assertEquals("aabbcc", Funcions.calcula(Funcions.MAJUSCULESAMINUSCULES, "aAbBcC"));
    }

    /**
     * Test que comprova que el canvi de minuscules a majúscules de "aAbBcC" retorna "AABBCC".
     * */
    @Test
    void minusculesAMajuscules_aAbBcC_AABBCC() throws Exception {
        assertEquals("AABBCC", Funcions.calcula(Funcions.MINUSCULESAMAJUSCULES, "aAbBcC"));
    }

    /**
     * Test que comprova que l'intercanvi de minuscules per majúscules i al revés de "aAbBcC" retorna "AaBbCc".
     * */
    @Test
    void intercanviaMinusculesAMajuscules_aAbBcC_AaBbCc() throws Exception {
        assertEquals("AaBbCc", Funcions.calcula(Funcions.INTERCANVIMINUSCULESMAJUSCULES, "aAbBcC"));
    }

    /**
     * Test que comprova que el número de caràcters de "aAbBcC" retorna 6.
     * */
    @Test
    void comptarCaracters_aAbBcC_sis() throws Exception {
        assertEquals("6", Funcions.calcula(Funcions.COMPTARCARACTERS, "aAbBcC"));
    }

    /**
     * Test que comprova que el número de bytes de "aAbBcC" retorna 12.
     * */
    @Test
    void comptarBytes_aAbBcC_dotze() throws Exception {
        assertEquals("12", Funcions.calcula(Funcions.COMPTARBYTES, "aAbBcC"));
    }

    /**
     * Test que comprova que el número de bytes de "A" retorna 0.
     * */
    @Test
    void comptarBytes_A_zero() throws Exception {
        assertEquals("2", Funcions.calcula(Funcions.COMPTARBYTES, "A"));
    }

    /**
     * Test que comprova que el número de bytes de "" retorna 0.
     * */
    @Test
    void comptarBytes_buit_zero() throws Exception {
        assertEquals("0", Funcions.calcula(Funcions.COMPTARBYTES, ""));
    }

    /**
     * Test que comprova que el dia de la data "01-07-2021" retorna 1.
     * */
    @Test
    void getDia_uDeJuliolDelDosMilVintIU_u() throws Exception {
        assertEquals("1", Funcions.calcula(Funcions.OBTENIRDIA, "01-07-2021"));
    }

    /**
     * Test que comprova que el dia de la data "22-07-2021" retorna 22.
     * */
    @Test
    void getDia_vintIDosDeJuliolDelDosMilVintIU_u() throws Exception {
        assertEquals("22", Funcions.calcula(Funcions.OBTENIRDIA, "22-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "19-07-2021" retorna Dilluns.
     * */
    @Test
    void getDiaSetmana_dinouDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Dilluns", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "19-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "20-07-2021" retorna Dimarts.
     * */
    @Test
    void getDiaSetmana_vintDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Dimarts", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "20-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "21-07-2021" retorna Dimecres.
     * */
    @Test
    void getDiaSetmana_vintIUDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Dimecres", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "21-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "22-07-2021" retorna Dijous.
     * */
    @Test
    void getDiaSetmana_vintIDosDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Dijous", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "22-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "23-07-2021" a la funció retorna Divendres.
     * */
    @Test
    void getDiaSetmana_vintITresDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Divendres", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "23-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "24-07-2021" retorna Dissabte.
     * */
    @Test
    void getDiaSetmana_vintIQuatreDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Dissabte", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "24-07-2021"));
    }

    /**
     * Test que comprova que el dia de la setmana de la data "24-07-2021" retorna Diumenge.
     * */
    @Test
    void getDiaSetmana_vintICincDeJuliolDelDosMilVintIU_Dijous() throws Exception {
        assertEquals("Diumenge", Funcions.calcula(Funcions.OBTENIRDIASETMANA, "25-07-2021"));
    }

    /**
     * Test que comprova que el mes de la data "22-07-2021" retorna 7.
     * */
    @Test
    void getMes_uJuliolDosMilVintIU_u() throws Exception {
        assertEquals("7", Funcions.calcula(Funcions.OBTENIRMES, "22-07-2021"));
    }

    /**
     * Test que comprova que l'any de la data "22-07-2021" retorna 2021.
     * */
    @Test
    void getAny_uJuliolDosMilVintIU_u() throws Exception {
        assertEquals("2021", Funcions.calcula(Funcions.OBTENIRANY, "22-07-2021"));
    }

    // Funcions Unàries Llista

    /**
     * Test que comprova que la mitjana de la llista dona com a resultat 4.5.
     * */
    @Test
    void mitjana_DonadaLlistaInt_CuatreComaZero() throws Exception {
        ArrayList<String> X = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7"));
        assertEquals("4.0", Funcions.calcula(Funcions.MITJANA, X));
    }

    /**
     * Test que comprova que la mitjana de la llista dona com a resultat 5.2.
     * */
    @Test
    void mitjana_DonadaLlistaDouble_CincComaDos() throws Exception {
        ArrayList<String> X = new ArrayList<>(Arrays.asList("5.5", "4.6", "8.9", "1.8"));
        assertEquals("5.2", Funcions.calcula(Funcions.MITJANA, X));
    }

    /**
     * Test que comprova que la mediana de la llista dona com a resultat 0.55.
     * */
    @Test
    void mediana_DonadaLlistaPar_ZeroComaCinquantaCinc() throws Exception {
        ArrayList<String> X = new ArrayList<>(Arrays.asList("0.42", "0.52", "0.58", "0.62"));
        assertEquals("0.55", Funcions.calcula(Funcions.MEDIANA, X));
    }

    /**
     * Test que comprova que la mediana de la llista dona com a resultat 0.55.
     * */
    @Test
    void mediana_DonadaLlistaImpar_ZeroComaCinquantaCinc() throws Exception {
        ArrayList<String> X = new ArrayList<>(Arrays.asList("0.40", "0.52", "0.58", "0.64"));
        assertEquals("0.55", Funcions.calcula(Funcions.MEDIANA, X));
    }

    /**
     * Test que comprova que la variància de la llista dona com a resultat 3.5.
     * */
    @Test
    void variancia_DonadaLlista_tresComaCinc() throws Exception {
        ArrayList<String> X = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        assertEquals("3.5", Funcions.calcula(Funcions.VARIANCIA, X));
    }

    /**
     * Test que comprova que la desviació estàndard de la llista dona com a resultat 1.871, només té en compte els primers quatre decimals.
     * */
    @Test
    void desviacioEstandard_DonadaLlista_uComaVuitSetZeroVuit() throws Exception {
        ArrayList<String> X = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        assertEquals("1.8708", Funcions.calcula(Funcions.DESVIACIOESTANDARD, X).substring(0, 6));
    }

    // Funcions Binàries Valors

    /**
     * Test que comprova que sumar 5 + 5 és igual a 10.0.
     * */
    @Test
    void suma_cincMesCinc_deu() throws Exception {
        assertEquals("10.0", Funcions.calcula(Funcions.SUMA, "5", "5"));
    }

    /**
     * Test que comprova que sumar -5 + 5 és igual a 0.0.
     * */
    @Test
    void suma_menyscincMesCinc_zero() throws Exception {
        assertEquals("0.0", Funcions.calcula(Funcions.SUMA, "-5", "5"));
    }

    /**
     * Test que comprova que restar -5 - 5 és igual a -10.0.
     * */
    @Test
    void resta_menyscincMenysCinc_deu() throws Exception {
        assertEquals("-10.0", Funcions.calcula(Funcions.RESTA, "-5", "5"));
    }

    /**
     * Test que comprova que restar 5 - 5 és igual a 0.0.
     * */
    @Test
    void resta_cincMenysCinc_zero() throws Exception {
        assertEquals("0.0", Funcions.calcula(Funcions.RESTA, "5", "5"));
    }

    /**
     * Test que comprova que dividir 5 entre 5 és igual a 1.0.
     * */
    @Test
    void divideix_cincDividitCinc_u() throws Exception {
        assertEquals("1.0", Funcions.calcula(Funcions.DIVIDEIX, "5", "5"));
    }

    /**
     * Test que comprova que dividir 3 entre 5 és igual a 0.6.
     * */
    @Test
    void divideix_tresDividitCinc_zeroComaSis() throws Exception {
        assertEquals("0.6", Funcions.calcula(Funcions.DIVIDEIX, "3", "5"));
    }

    /**
     * Test que comprova que multiplicar 3 per 5 és igual a 15.0.
     * */
    @Test
    void multiplica_tresPerCinc_quinze() throws Exception {
        assertEquals("15.0", Funcions.calcula(Funcions.MULTIPLICA, "3", "5"));
    }

    /**
     * Test que comprova que multiplicar 0.6 per 5 és igual a 3.0.
     * */
    @Test
    void multiplica_zeroComaSisPerCinc_quinze() throws Exception {
        assertEquals("3.0", Funcions.calcula(Funcions.MULTIPLICA, "0.6", "5"));
    }

    /**
     * Test que comprova que concatenar "Hola" i "Adeu" és igual a "HolaAdeu".
     * */
    @Test
    void concatena_HolaIAdeu_HolaAdeu() throws Exception {
        assertEquals("HolaAdeu", Funcions.calcula(Funcions.CONCATENA, "Hola", "Adeu"));
    }

    /**
     * Test que comprova que concatenar "Hola    " i "   Adeu" és igual a "Hola       Adeu".
     * */
    @Test
    void concatena_HolaIAdeuAmbEspais_HolaAdeuAmbEspais() throws Exception {
        assertEquals("Hola       Adeu", Funcions.calcula(Funcions.CONCATENA, "Hola    ", "   Adeu"));
    }

    /**
     * Test que comprova que entre 01-12-2021 i 10-12-2021 hi han 9 dies de diferència.
     * */
    @Test
    void diesDiferencia_uDelDotzeDelDosMilVintIUIDeuDelDotzeDelDosMilVintIU_Nou() throws Exception {
        assertEquals("9", Funcions.calcula(Funcions.DIESDIFERENCIA, "01-12-2021", "10-12-2021"));
    }

    // Funcions Binàries Llistes

    /**
     * Test que comprova que el resultat de fer la covariància entre les llistes dona com a resultat 2.25.
     * */
    @Test
    void covariancia_calcula_dosComaVintICinc() throws Exception {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("2", "5", "6", "8", "9"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("4", "3", "7", "5", "6"));
        assertEquals("2.25", Funcions.calcula(Funcions.COVARIANCIA, valors1, valors2));
    }

    /**
     * Test que comprova que el resultat de fer la covariància entre les llistes dona com a resultat -0.39.
     * */
    @Test
    void covariancia_calcula_menysZeroComaTrentaNou() throws Exception {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("5", "6", "8", "11", "4", "6"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("1", "4", "3", "7", "9", "12"));
        assertEquals("-0.39", Funcions.calcula(Funcions.COVARIANCIA, valors1, valors2).substring(0, 5));
    }

    /**
     * Test que comprova que el resultat de fer la covariància entre les llistes dona com a resultat 5.449, només té en compte els tres primers decimals.
     * */
    @Test
    void covariancia_calcula_quatreComaQuatreQuatreNou() throws Exception {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("13", "15", "17", "18", "19"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("10", "11", "12", "14", "16"));
        assertEquals("5.449", Funcions.calcula(Funcions.COVARIANCIA, valors1, valors2).substring(0, 5));
    }

    /**
     * Test que comprova que salta l'excepció de manera correcta en aplicar la covariància a dues llistes de diferents mides.
     * */
    @Test
    void covariancia_calculaDiferentMidaLlistes_llencaExcepcio() {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("13", "15", "17", "18", "19"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("10", "11", "12", "14", "16", "55"));
        Throwable error = assertThrows(Exception.class, () -> Funcions.calcula(Funcions.COVARIANCIA, valors1, valors2));
        assertEquals("El nombre de variables no és igual", error.getMessage());

    }

    /**
     * Test que comprova que el resultat de fer la correlació entre les llistes dona com a resultat 1.0.
     * */
    @Test
    void correlacio_calcula_u() throws Exception {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("4", "8", "12", "16"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("5", "10", "15", "20"));
        assertEquals("1.0", Funcions.calcula(Funcions.CORRELACIO, valors1, valors2));
    }

    /**
     * Test que comprova que el resultat de fer la correlació entre les llistes dona com a resultat 0.9923, només té en compte els quatre primers decimals.
     * */
    @Test
    void correlacio_calcula_zeroComaNouNouDosTres() throws Exception {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("25", "30", "36", "43"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("30", "44", "52", "70"));
        assertEquals("0.9923", Funcions.calcula(Funcions.CORRELACIO, valors1, valors2).substring(0, 6));
    }

    /**
     * Test que comprova que el resultat de fer la correlació entre les llistes dona com a resultat -0.424, només té en compte els tres primers decimals.
     * */
    @Test
    void correlacio_calcula_menysZeroComaQuatreCentsVintIQuatre() throws Exception {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("16", "15", "12", "10", "8"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("11", "18", "10", "20", "17"));
        assertEquals("-0.424", Funcions.calcula(Funcions.CORRELACIO, valors1, valors2).substring(0, 6));
    }

    /**
     * Test que comprova que salta l'excepció de manera correcta en aplicar la correlació a dues llistes de diferents mides.
     * */
    @Test
    void correlacio_calculaDiferentMidaLlistes_llencaExcepcio() {
        ArrayList<String> valors1 = new ArrayList<>(Arrays.asList("13", "15", "17", "18", "19"));
        ArrayList<String> valors2 = new ArrayList<>(Arrays.asList("10", "11", "12", "14", "16", "55"));
        Throwable error = assertThrows(Exception.class, () -> Funcions.calcula(Funcions.CORRELACIO, valors1, valors2));
        assertEquals("El nombre de variables no és igual", error.getMessage());
    }

    /**
     * Test que comprova que si cridem una funció unària d'un valor que no existeix, salta l'excepció corresponent.
     * */
    @Test
    void calculaFuncioUnariaValor_noExisteix_saltaExcepcio() {
        Throwable error = assertThrows(Exception.class, () -> Funcions.calcula("abc", ""));
        assertEquals("No existeix cap funció amb aquestes característiques", error.getMessage());
    }

    /**
     * Test que comprova que si cridem una funció unària d'una llista que no existeix, salta l'excepció corresponent.
     * */
    @Test
    void calculaFuncioUnariaLlista_noExisteix_saltaExcepcio() {
        ArrayList<String> valors = new ArrayList<>();
        Throwable error = assertThrows(Exception.class, () -> Funcions.calcula("abc", valors));
        assertEquals("No existeix cap funció amb aquestes característiques", error.getMessage());
    }

    /**
     * Test que comprova que si cridem una funció binària de dos valors que no existeix, salta l'excepció corresponent.
     * */
    @Test
    void calculaFuncioBinariaValors_noExisteix_saltaExcepcio() {
        Throwable error = assertThrows(Exception.class, () -> Funcions.calcula("abc", "", ""));
        assertEquals("No existeix cap funció amb aquestes característiques", error.getMessage());
    }

    /**
     * Test que comprova que si cridem una funció binària de dues llistes que no existeix, salta l'excepció corresponent.
     * */
    @Test
    void calculaFuncioBinariaLlistes_noExisteix_saltaExcepcio() {
        ArrayList<String> valors1 = new ArrayList<>();
        ArrayList<String> valors2 = new ArrayList<>();
        Throwable error = assertThrows(Exception.class, () -> Funcions.calcula("abc", valors1, valors2));
        assertEquals("No existeix cap funció amb aquestes característiques", error.getMessage());
    }
}