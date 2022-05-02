package drivers;

import classes.Cela;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * És el driver de la classe funció.
 * @author adrian.cristian.crisan
 */
public class DriverCela {
    /**
     * Representa la comanda per crear una cel·la amb un valor i contingut null.
     * */
    private static final String CONSTRUCTORAVALOR       = "0";
    /**
     * Representa la comanda per crear una cel·la amb valor i contingut.
     * */
    private static final String CONSTRUCTORA            = "1";
    /**
     * Representa la comanda per obtenir el valor.
     * */
    private static final String GETVALOR                = "2";
    /**
     * Representa la comanda per obtenir el contingut.
     * */
    private static final String GETCONTINGUT            = "3";
    /**
     * Representa la comanda per modificar el valor.
     * */
    private static final String SETVALOR                = "4";
    /**
     * Representa la comanda per modificar el contingut.
     * */
    private static final String SETCONTINGUT            = "5";
    /**
     * Representa la comanda per afegir una referencia.
     * */
    private static final String ADDREFERENCIAMEVA       = "6";
    /**
     * Representa la comanda per afegir una referencia a aquesta cel·la des d'una altra.
     * */
    private static final String ADDREFERENCIAAMI        = "7";
    /**
     * Representa la comanda per eliminar les cel·les que es tenen referenciades.
     * */
    private static final String DELETEREFERENCIESMEVES  = "8";
    /**
     * Representa la comanda per imprimir les referencies de la cel·la.
     * */
    private static final String GETREFERENCIESMEVES     = "9";
    /**
     * Representa la comanda per obtenir les referències a aquesta cel·la des d'altres.
     * */
    private static final String GETREFERENCIESAMI       = "10";
    /**
     * Representa la comanda per saber si una cel·la és buida.
     * */
    private static final String ESUTIL                  = "11";
    /**
     * Representa la comanda per imprimir la cel·la.
     * */
    private static final String PRINT                   = "print";
    /**
     * Representa la comanda per obtenir ajuda.
     * */
    private static final String HELP                    = "help";
    /**
     * Representa la comanda per sortir del programa.
     * */
    private static final String EXIT                    = "exit";

    /**
     * Representa el panell d'ajuda.
     * */
    private static final String HELPTEXT = "Introduïu un dels següents números per executar la corresponent comanda:\n" +
            "   " + CONSTRUCTORAVALOR +         " - Constructora d'una cel·la amb un valor\n" +
            "   " + CONSTRUCTORA +              " - Constructora d'una cel·la amb un valor i contingut\n" +
            "   " + GETVALOR +                  " - Obtenir el valor de la cel·la\n" +
            "   " + GETCONTINGUT +              " - Obtenir el contingut de la cel·la\n" +
            "   " + SETVALOR +                  " - Modificar el valor de la cel·la\n" +
            "   " + SETCONTINGUT +              " - Modificar el contingut de la cel·la\n" +
            "   " + ADDREFERENCIAMEVA +         " - Afegir una nova cel·la dins d'aquesta\n" +
            "   " + ADDREFERENCIAAMI +          " - Afegir cel·la que referència a aquesta\n" +
            "   " + DELETEREFERENCIESMEVES +    " - Eliminar totes les cel·les que es referencien des d'aquesta\n" +
            "   " + GETREFERENCIESMEVES +       " - Obtenir referències a altres cel·les\n" +
            "   " + GETREFERENCIESAMI +         " - Obtenir cel·les que referencien a aquesta\n" +
            "   " + ESUTIL +                    " - Ens diu si la cel·la és útil o no\n" +
            "   " + PRINT +                     " - Imprimeix tota la cel·la\n" +
            "   " + HELP +                      " - Mostra totes les comandes disponibles\n" +
            "   " + EXIT +                      " - Sortir del programa\n";

    /**
     * Representa una cel·la.
     * */
    private static Cela cela;

    private static final String CELESREFERENCIADES = "Cel·les referenciades:";
    private static final String CELAREFERENCIADANUMERO = "Cel·la referenciada número ";
    private static final String VALOR = "Valor:";
    private static final String CONTIGNUT = "Contingut:";
    private static final String AQUESTACELANOCONTECAPCELA = "Aquesta cel·la no conté cap cel·la";
    private static final String CELESQUETENENCOMAREFERENCIAAQUESTA = "Cel·les que tenen com a referencia aquesta:";
    private static final String AQUESTACELAESTROBAREFERENCIADAACAPALTRACELA = "Aquesta cel·la no es troba referenciada a cap altre cel·la";
    private static final String INTRODUEIXELVALORQUEVOLSDONARALACELA = "Introdueix el valor que vols donar a la cel·la:";
    private static final String INTRODUEIXELCONTINGUTQUEVOLSDONARALACELA = "Introdueix el contingut que vols donar a la cel·la:";
    private static final String VALORDELACELA = "Valor de la cel·la:";
    private static final String CONTINGUTDELACELA = "Contingut de la cel·la:";
    private static final String SHANELIMINATTOTESLESREFERENCIESAALTRESCELES= "S'han eliminat totes les referencies a altres cel·les";
    private static final String LACELAESUTIL = "La cel·la és útil";
    private static final String LACELANOESUTIL =  "La cel·la no és útil";
    private static final String GRACIESPERUTILITZARELPROGRAMADRIVERCELA = "Gràcies per utilitzar el programa DriverCela!";
    private static final String ERRORSHADECREARUNACELAABANSDEOBTENIRVALOR = "ERROR: S'ha de crear una cel·la abans de poder obtenir el valor d'aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDEOBTENIRCONTINGUT = "ERROR: S'ha de crear una cel·la abans de poder obtenir el contingut d'aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDEMODIFICARVALOR = "ERROR: S'ha de crear una cel·la abans de poder modificar el valor d'aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDEMODIFICARCONTINGUT= "ERROR: S'ha de crear una cel·la abans de poder modificar el contingut d'aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDEAFEGIRREFERENCIA = "ERROR: S'ha de crear una cel·la abans de poder afegir una referencia a aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDEREFERENCIARAREFERENCIA = "ERROR: S'ha de crear una cel·la abans de poder referenciar a aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDELIMINARREFERENCIES = "ERROR: S'ha de crear una cel·la abans de poder eliminar referencies a aquesta cel·la";
    private static final String ERRORSHADECREARUNACLEAABANSDEIMPRIMIRLESCELESREFERENCIADES= "ERROR: S'ha de crear una cel·la abans de poder imprimir les cel·les que es tenen referenciades";
    private static final String ERRORSHADECREARUNACLEAABANSDEIMPRIMIRLESCELESREFERENCIADESAMI = "ERROR: S'ha de crear una cel·la abans de poder imprimir les referencies d'altres cel·les a aquesta";
    private static final String ERRORSHADECREARUNACELAABANSDESABERSIESUTIL= "ERROR: S'ha de crear una cel·la abans de poder saber si aquesta és útil o no.";
    private static final String ERRORSHADECREARLACELAABANSDEPODERIMPRIMIR = "ERROR: S'ha de crear la cel·la abans de poder imprimir aquesta";
    private static final String ERRORLACOMANDAINTRODUINANOESVALIDA = "ERROR: La comanda introduïda no és vàlida";
    private static final String ERRORARXIUNOADMES = "ERROR: Arxiu no admès";

    /**
     * Llegeix una línia per la terminal.
     *
     * @param scanner mètode per fer l'input.
     * @return Retorna un String del que ha llegit per la terminal.
     * */
    private static String readLine(Scanner scanner) {
        return scanner.nextLine();
    }

    /**
     * Imprimeix les referencies que conté la cel·la.
     * */
    public static void printReferenciesMeves() {
        System.out.println(CELESREFERENCIADES);
        HashSet<Cela> celes = cela.getReferenciesMeves();
        int i = 0;
        for (Cela cela : celes) {
            System.out.println("    "+CELAREFERENCIADANUMERO + i);
            System.out.println("        " + VALOR + "      " + cela.getValor());
            System.out.println("        " + CONTIGNUT + "  " + cela.getContingut());
            i++;
        }
        if (i == 0)
            System.out.println("    " + AQUESTACELANOCONTECAPCELA);
    }

    /**
     * Imprimeix cel·les que tenen referenciada a aquesta.
     * */
    public static void printReferenciesAMi() {
        System.out.println(CELESQUETENENCOMAREFERENCIAAQUESTA);
        HashSet<Cela> celes = cela.getReferenciesAMi();
        int i = 0;
        for (Cela cela : celes) {
            System.out.println("    " + CELAREFERENCIADANUMERO + i);
            System.out.println("        " + VALOR + "      " + cela.getValor());
            System.out.println("        " + CONTIGNUT + "  " + cela.getContingut());
            i++;
        }
        if (i == 0)
            System.out.println("    " + AQUESTACELAESTROBAREFERENCIADAACAPALTRACELA );
    }

    /**
     * Imprimeix la cel·la.
     * */
    public static void printAll() {
        testGetValor();
        testGetContingut();
        printReferenciesMeves();
        printReferenciesAMi();
    }

    /**
     * Crea una cel·la auxiliar.
     *
     * @param scanner mètode per fer l'input.
     * @return Retorna una cel·la.
     * */
    private static Cela crearCelaAuxiliar(Scanner scanner) {
        System.out.println(INTRODUEIXELVALORQUEVOLSDONARALACELA);
        String valor = readLine(scanner);
        System.out.println(INTRODUEIXELCONTINGUTQUEVOLSDONARALACELA);
        String contingut = readLine(scanner);

        return new Cela(valor, contingut);
    }

    /**
     * Constructora d'una cel·la amb valor però contingut null.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testConstructoraValor(Scanner scanner) {
        System.out.println(INTRODUEIXELVALORQUEVOLSDONARALACELA);
        String valor = readLine(scanner);
        cela = new Cela(valor);
        printAll();
    }

    /**
     * Constructora d'una cel·la amb valor i contingut.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testConstructoraValorContingut(Scanner scanner) {
        System.out.println(INTRODUEIXELVALORQUEVOLSDONARALACELA);
        String valor = readLine(scanner);
        System.out.println(INTRODUEIXELCONTINGUTQUEVOLSDONARALACELA);
        String contingut = readLine(scanner);
        cela = new Cela(valor, contingut);
        printAll();
    }

    /**
     * Obté el valor de la cel·La.
     * */
    public static void testGetValor() {
        String out = VALORDELACELA + "       " + cela.getValor();
        System.out.println(out);
    }

    /**
     * Obté el contingut de la cel·la.
     * */
    public static void testGetContingut() {
        String out = CONTINGUTDELACELA + "    " + cela.getContingut();

        System.out.println(out);
    }

    /**
     * Modifica el valor de la cel·la.
     *
     * @param valor nou valor.
     * */
    public static void testSetValor(String valor) {
        cela.setValor(valor);
        testGetValor();
    }

    /**
     * Modifica el contingut de la cel·la.
     *
     * @param contingut nou contingut.
     * */
    public static void testSetContingut(String contingut) {
        cela.setContingut(contingut);
        testGetContingut();
    }

    /**
     * Afegeix una referencia dins la cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAfegirReferenciesMeves(Scanner scanner) {
        cela.addReferenciaMeva(crearCelaAuxiliar(scanner));
        printReferenciesMeves();
    }

    /**
     * Afegeix una referència a aquesta cel·la des d'una altra.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAfegirReferenciesAMi(Scanner scanner) {
        cela.addReferenciaAMi(crearCelaAuxiliar(scanner));
        printReferenciesAMi();
    }

    /**
     * Elimina les referències que conté la cel·la.
     * */
    public static void testEliminarReferenciesMeves() {
        cela.deleteReferenciesMeves();
        System.out.println(SHANELIMINATTOTESLESREFERENCIESAALTRESCELES);
    }

    /**
     * Imprimeix si una cel·la es buida o no.
     * */
    public static void testCelaUtil() {
        if (cela.esUtil())
            System.out.println(LACELAESUTIL);
        else
            System.out.println(LACELANOESUTIL);
    }

    /**
     * Representa totes les comandes que accepta el driver.
     *
     * @param command la comanda que verificarà.
     * @param scanner mètode per fer l'input.
     * */
    private static boolean commands(String command, Scanner scanner) {
        switch (command) {
            case CONSTRUCTORAVALOR:
                testConstructoraValor(scanner);
                break;
            case CONSTRUCTORA:
                testConstructoraValorContingut(scanner);
                break;
            case GETVALOR:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDEOBTENIRVALOR);
                else
                    testGetValor();
                break;
            case GETCONTINGUT:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDEOBTENIRCONTINGUT);
                else
                    testGetContingut();
                break;
            case SETVALOR:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDEMODIFICARVALOR);
                else
                    testSetValor(scanner.nextLine());
                break;
            case SETCONTINGUT:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDEMODIFICARCONTINGUT);
                else
                    testSetContingut(scanner.nextLine());
                break;
            case ADDREFERENCIAMEVA:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDEAFEGIRREFERENCIA);
                else
                    testAfegirReferenciesMeves(scanner);
                break;
            case ADDREFERENCIAAMI:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDEREFERENCIARAREFERENCIA);
                else
                    testAfegirReferenciesAMi(scanner);
                break;
            case DELETEREFERENCIESMEVES:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDELIMINARREFERENCIES);
                else
                    testEliminarReferenciesMeves();
                break;
            case GETREFERENCIESMEVES:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACLEAABANSDEIMPRIMIRLESCELESREFERENCIADES);
                else
                    printReferenciesMeves();
                break;
            case GETREFERENCIESAMI:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACLEAABANSDEIMPRIMIRLESCELESREFERENCIADESAMI);
                else
                    printReferenciesAMi();
                break;
            case ESUTIL:
                if (cela == null)
                    System.out.println(ERRORSHADECREARUNACELAABANSDESABERSIESUTIL);
                else
                    testCelaUtil();
                break;
            case PRINT:
                if (cela == null)
                    System.out.println(ERRORSHADECREARLACELAABANSDEPODERIMPRIMIR);
                else
                    printAll();
                break;
            case HELP:
                System.out.println(HELPTEXT);
                break;
            case EXIT:
                return false;
            default:
                System.out.println(ERRORLACOMANDAINTRODUINANOESVALIDA);
                break;
        }
        return true;
    }

    /**
     * Punt d'inici del programa.
     *
     * @param args accepta un argument d'un path d'un arxiu txt en cas de voler introduir les comandes des d'un arxiu.
     * */
    public static void main(String[] args) {
        boolean run = true;
        if (args.length != 0) {
            try {
                File file = new File(args[0]);
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine() && run)
                    run = commands(myReader.nextLine(), myReader);
                myReader.close();
            } catch (Exception e) {
                System.out.println(ERRORARXIUNOADMES);
            }
        }

        if (run) System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run)
            run = commands(in.nextLine(), in);

        System.out.println(GRACIESPERUTILITZARELPROGRAMADRIVERCELA);

    }
}
