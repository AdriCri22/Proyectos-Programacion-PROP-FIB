package drivers;

import classes.Full;
import classes.Cela;
import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

/**
 * És el driver de la classe full.
 * @author adrian.cristian.crisan
 */
public class DriverFull {
    /**
     * Representa la comanda per crear un full.
     * */
    private static final String CONSTRUCTORA            = "0";
    /**
     * Representa la comanda per afegir una columna.
     * */
    private static final String AFEGIRCOLUMNA           = "1";
    /**
     * Representa la comanda per afegir múltiples columnes.
     * */
    private static final String AFEGIRCOLUMNES          = "2";
    /**
     * Representa la comanda per eliminar una columna.
     * */
    private static final String ELIMINARCOLUMNA         = "3";
    /**
     * Representa la comanda per eliminar múltiples columnes.
     * */
    private static final String ELIMINARCOLUMNES        = "4";
    /**
     * Representa la comanda per afegir una fila.
     * */
    private static final String AFEGIRFILA              = "5";
    /**
     * Representa la comanda per afegir múltiples files.
     * */
    private static final String AFEGIRFILES             = "6";
    /**
     * Representa la comanda per eliminar una fila.
     * */
    private static final String ELIMINARFILA            = "7";
    /**
     * Representa la comanda per eliminar múltiples files.
     * */
    private static final String ELIMINARFILES           = "8";
    /**
     * Representa la comanda per modificar una cel·la.
     * */
    private static final String MODIFICACELA            = "9";
    /**
     * Representa la comanda per imprimir una cel·la.
     * */
    private static final String GETCELA                 = "10";
    /**
     * Representa la comanda per afegir una referència.
     * */
    private static final String ADDREFERENCIA           = "11";
    /**
     * Representa la comanda per eliminar les referències d'una cel·la.
     * */
    private static final String DELETEREFERENCIA        = "12";
    /**
     * Representa la comanda per copiar una cel·la.
     * */
    private static final String COPIACELA               = "13";
    /**
     * Representa la comanda per moure una cel·la.
     * */
    private static final String MOUCELA                 = "14";
    /**
     * Representa la comanda per copiar un bloc de cel·les.
     * */
    private static final String COPIABLOCCELES          = "15";
    /**
     * Representa la comanda per moure un bloc de cel·les.
     * */
    private static final String MOUBLOCCELES            = "16";
    /**
     * Representa la comanda per ordenar un bloc de cel·les.
     * */
    private static final String ORDENABLOCCELES         = "17";
    /**
     * Representa la comanda per cercar en un full.
     * */
    private static final String CERCA                   = "18";
    /**
     * Representa la comanda per cercar i reemplaçar per valor en un full.
     * */
    private static final String CERCAIREMPLACA          = "19";
    /**
     * Representa la comanda per imprimir el full.
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
            "   " + CONSTRUCTORA        + " - Constructora d'un full\n" +
            "   " + AFEGIRCOLUMNA       + " - Afegeix una columna\n" +
            "   " + AFEGIRCOLUMNES      + " - Afegeix múltiples columnes\n" +
            "   " + ELIMINARCOLUMNA     + " - Elimina una columna\n" +
            "   " + ELIMINARCOLUMNES    + " - Elimina una columna\n" +
            "   " + AFEGIRFILA          + " - Afegeix una fila\n" +
            "   " + AFEGIRFILES         + " - Afegeix múltiples files\n" +
            "   " + ELIMINARFILA        + " - Elimina una fila\n" +
            "   " + ELIMINARFILES       + " - Elimina una files\n" +
            "   " + MODIFICACELA        + " - Modifica una cel·la donada la posició d'aquesta\n" +
            "   " + GETCELA             + " - Retorna una cel·la donada una posició\n" +
            "   " + ADDREFERENCIA       + " - Afegeix una referencia\n" +
            "   " + DELETEREFERENCIA    + " - Elimina les referencies d'una cel·la\n" +
            "   " + COPIACELA           + " - Copia i enganxa una cel·la\n" +
            "   " + MOUCELA             + " - Mou una cel·la\n" +
            "   " + COPIABLOCCELES      + " - Copia i enganxa un bloc de cel·les\n" +
            "   " + MOUBLOCCELES        + " - Mou un bloc de cel·les\n" +
            "   " + ORDENABLOCCELES     + " - Ordena un bloc cel·les\n" +
            "   " + CERCA               + " - Obté les posicions de les cel·les que contenen cert valor\n" +
            "   " + CERCAIREMPLACA      + " - Reemplaça les cel·les que contenen cert valor\n" +
            "   " + PRINT               + " - Imprimeix el full\n" +
            "   " + HELP                + " - Mostra totes les comandes disponibles\n" +
            "   " + EXIT                + " - Sortir del programa\n";


    private static final String ERRORPOSICIO = "ERROR: Posició no vàlida, torni a introduir una posició:";
    private static final String ERRORFULL = "ERROR: Per realitzar aquesta comanda s'ha d'haver creat un full.";

    /**
     * Representa un full.
     * */
    static Full full;

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
     * Llegeix una un enter per la terminal.
     *
     * @param scanner mètode per fer l'input.
     * @return Retorna un enter que s'ha llegit per la terminal.
     * */
    private static int readInteger(Scanner scanner) {
        int value;
        try {
            value = Integer.parseInt(readLine(scanner));
            return value;
        } catch (Exception e) {
            System.out.println("ERROR: El valor que ha introduït no és un enter, torni a intentar:");
            return readInteger(scanner);
        }
    }

    /**
     * Imprimeix el full.
     * */
    public static void printAll() {
        Map<String, Cela> celes = full.getCeles();
        if (celes.isEmpty())
            System.out.println("No existeix cap cel·la que no tingui valor nul");

        for (Map.Entry<String, Cela> entry : celes.entrySet())
            printCela(entry.getKey(), entry.getValue());
    }

    /**
     * Llegeix posició per la terminal.
     *
     * @param scanner mètode per fer l'input.
     * @return Retorna una posició vàlida que s'ha llegit per la terminal.
     * */
    private static String getPosicio(Scanner scanner) {
        try {
            String posicio = readLine(scanner);
            int sep = 0;
            for (int i = 0; i < posicio.length(); i++) {
                if ('0' <= posicio.charAt(i) && '9' >= posicio.charAt(i)) {
                    sep = i;
                    break;
                } else if ('A' > posicio.charAt(i) || posicio.charAt(i) > 'Z') {
                    System.out.println("ERROR: Posicio no vàlida, torni a introduir la posició:");
                    return getPosicio(scanner);
                }
            }

            String partNumerica = posicio.substring(sep);
            Integer.parseInt(partNumerica);
            if (partNumerica.length() == 0) {
                System.out.println(ERRORPOSICIO);
                return getPosicio(scanner);
            } else if (sep == 0) {
                System.out.println(ERRORPOSICIO);
                return getPosicio(scanner);
            }

            return posicio;
        } catch (Exception e) {
            System.out.println("ERROR: La posició introduïda no és vàlida, torni a introduir la posició:");
            return getPosicio(scanner);
        }
    }

    /**
     * Imprimeix una cel·la.
     *
     * @param posicio posició de la cel·la.
     * @param cela cela que volem imprimir.
     * */
    private static void printCela(String posicio, Cela cela) {
        System.out.println("Cela:     " + posicio);
        if (cela != null) {
            System.out.println("    Valor de la cel·la:     " + cela.getValor());
            System.out.println("    Contingut de la cel·la: " + cela.getContingut());
        } else {
            System.out.println("    Valor de la cel·la:     " + null);
            System.out.println("    Contingut de la cel·la: " + null);
        }
    }

    /**
     * Crea un full.
     * */
    public static void testConstructoraFull() {
        full = new Full();
        System.out.println("Full creat correctament");
    }

    /**
     * Obté una columna.
     *
     *  @param scanner mètode per fer l'input.
     * */
    private static String getColumna(Scanner scanner) {
        String columna = readLine(scanner);
        for (int i = 0; i < columna.length(); i++) {
            if (!(columna.charAt(i) >= 'A' && columna.charAt(i) <= 'Z')) {
                System.out.println("Columna no vàlida, torni a intentar:");
                return getColumna(scanner);
            }
        }

        return columna;
    }

    /**
     * Afegeix una columna a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddColumna(Scanner scanner) {
        System.out.println("Escrigui la posició de columna on vol afegir la nova columna:");
        String columna = getColumna(scanner);
        full.addColumnes(columna, 1);
        System.out.println("Columna afegida correctament.");
    }

    /**
     * Afegeix múltiples columnes a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddColumnes(Scanner scanner) {
        System.out.println("Escrigui la posició de columna on vol afegir la nova columna:");
        String columna = getColumna(scanner);
        System.out.println("Escrigui el nombre de columnes a afegir:");
        int shift = readInteger(scanner);
        full.addColumnes(columna, shift);
        System.out.println("Columnes afegides correctament.");
    }

    /**
     * Elimina una columna d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteColumna(Scanner scanner) {
        System.out.println("Escrigui la posició de columna on vol eliminar la nova columna:");
        String columna = getColumna(scanner);
        full.deleteColumnes(columna, 1);
        System.out.println("Columna eliminada correctament.");
    }

    /**
     * Elimina múltiples columnes d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteColumnes(Scanner scanner) {
        System.out.println("Escrigui la posició de columna on vol eliminar la nova columna:");
        String columna = getColumna(scanner);
        System.out.println("Escrigui el nombre de columnes a eliminar:");
        int shift = readInteger(scanner);
        full.deleteColumnes(columna, shift);
        System.out.println("Columnes eliminades correctament.");
    }

    /**
     * Afegeix una fila a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddFila(Scanner scanner) {
        System.out.println("Escrigui la posició de fila on vol afegir la nova fila:");
        int fila = readInteger(scanner);
        full.addFiles(fila, 1);
        System.out.println("Fila afegida correctament.");
    }

    /**
     * Afegeix múltiples files a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddFiles(Scanner scanner) {
        System.out.println("Escrigui la posició de fila on vol afegir la nova fila:");
        int fila = readInteger(scanner);
        System.out.println("Escrigui el nombre de columnes a afegir:");
        int shift = readInteger(scanner);
        full.addFiles(fila, shift);
        System.out.println("Files afegides correctament.");
    }

    /**
     * Elimina una fila d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteFila(Scanner scanner) {
        System.out.println("Escrigui la posició de fila on vol eliminar la nova fila:");
        int fila = readInteger(scanner);
        full.deleteFiles(fila, 1);
        System.out.println("Fila eliminada correctament.");
    }

    /**
     * Elimina múltiples files d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteFiles(Scanner scanner) {
        System.out.println("Escrigui la posició de fila on vol eliminar la nova fila:");
        int fila = readInteger(scanner);
        System.out.println("Escrigui el nombre de columnes a eliminar:");
        int shift = readInteger(scanner);
        full.deleteFiles(fila, shift);
        System.out.println("Files eliminades correctament.");
    }

    /**
     * Modifica una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testModificaCela(Scanner scanner) {
        System.out.println("Introdueix la posició de la cel·la que vols modificar:");
        String posicio = getPosicio(scanner);
        System.out.println("Introdueix el valor de la cel·la:");
        String valor = readLine(scanner);
        System.out.println("Introdueix el contingut de la cel·la, si no vol cap contingut deixia-ho en blanc:");
        String contingut = readLine(scanner);

        if (valor.isBlank()) {
            System.out.println("ERROR: valor no vàlid");
            return;
        }
        if (contingut.isBlank())
            contingut = null;

        full.setCela(posicio, valor, contingut);
    }

    /**
     * Imprimeix una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testGetCela(Scanner scanner) {
        System.out.println("Introdueix la posició de la cel·la que vols modificar:");
        String posicio = getPosicio(scanner);
        printCela(posicio, full.getCela(posicio));
    }

    /**
     * Afegeix una referència.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddReferencia(Scanner scanner) {
        System.out.println("Introdueix la posició de la cel·la referenciadora:");
        String posicioReferenciadora = getPosicio(scanner);
        System.out.println("Introdueix la posició de la cel·la referenciada:");
        String posicioReferenciada = getPosicio(scanner);

        full.addReferencia(posicioReferenciadora, full, posicioReferenciada);
        System.out.println("S'ha afegit la referencia correctament");
    }

    /**
     * Elimina les referències d'una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteReferencies(Scanner scanner) {
        System.out.println("Introdueix la posició de la cel·la:");
        String posicio = getPosicio(scanner);

        full.eliminaReferencies(posicio);
        System.out.println("S'han eliminat les referencies correctament");
    }

    /**
     * Copia una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCopyCela(Scanner scanner) {
        System.out.println("Escrigui la posició de la cel·la que es desitja copiar:");
        String posicioCopiada = getPosicio(scanner);
        System.out.println("Escrigui la posició de la cel·la que es desitja enganxar:");
        String posicioEnganxada = getPosicio(scanner);
        full.copyCela(posicioCopiada, posicioEnganxada);
        System.out.println("Copia realitzada correctament.");
    }

    /**
     * Mou una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testMoveCela(Scanner scanner) {
        System.out.println("Escrigui la posició de la cel·la que es desitja moure:");
        String posicioMoure = getPosicio(scanner);
        System.out.println("Escrigui la posició de la cel·la que on es desitja moure:");
        String posicioEnganxada = getPosicio(scanner);
        full.moveCela(posicioMoure, posicioEnganxada);
        System.out.println("Copia realitzada correctament.");
    }

    /**
     * Copia un bloc de cel·les.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCopyBlocCeles(Scanner scanner) {
        System.out.println("Escrigui la posició inicial del bloc de cel·les:");
        String posicioInicial = getPosicio(scanner);
        System.out.println("Escrigui la posició final del bloc de cel·les:");
        String posicioFinal = getPosicio(scanner);
        System.out.println("Escrigui el nombre de columnes o columnes a desplaçar:");
        int shift = readInteger(scanner);

        try {
            full.copiaBloc(posicioInicial, posicioFinal, shift);
            System.out.println("Copia realitzada correctament.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }


    /**
     * Mou un bloc de cel·les.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testMoveBlocCeles(Scanner scanner) {
        System.out.println("Escrigui la posició inicial del bloc de cel·les:");
        String posicioInicial = getPosicio(scanner);
        System.out.println("Escrigui la posició final del bloc de cel·les:");
        String posicioFinal = getPosicio(scanner);
        System.out.println("Escrigui el nombre de columnes o columnes a desplaçar:");
        int shift = readInteger(scanner);

        try {
            full.moveBloc(posicioInicial, posicioFinal, shift);
            System.out.println("S'ha mogut el bloc de cel·les correctament.");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private static String sortType(Scanner scanner) {
        String sortType = readLine(scanner);
        if (sortType.equals("ASC") || sortType.equals("DESC"))
            return sortType;
        else {
            System.out.println("No s'ha introduït cap dels tipus admesos ('ASC', 'DESC), torni a intentar:");
            return sortType(scanner);
        }
    }

    /**
     * Ordena un bloc de cel·les.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSortBlocCeles(Scanner scanner) {
        System.out.println("Escrigui la posició inicial del bloc de cel·les:");
        String posicioInicial = getPosicio(scanner);
        System.out.println("Escrigui la posició final del bloc de cel·les:");
        String posicioFinal = getPosicio(scanner);
        System.out.println("Escrigui 'ASC' si vol ordenar de manera ascendent o 'DESC' si vol ordenar descendentment:");
        String sortType = sortType(scanner);

        if (sortType.equals("ASC") || sortType.equals("DESC"))
            try {
                full.ordenaBloc(posicioInicial, posicioFinal, sortType);
                System.out.println("S'ha ordenat el bloc de cel·les correctament.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
    }

    /**
     * Cerca un valor en un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSearch(Scanner scanner) {
        System.out.println("Escrigui el valor a cercar:");
        String valor = readLine(scanner);
        HashSet<String> posicions = full.buscarValor(valor);

        for (String pos : posicions)
            System.out.println(pos);
    }

    /**
     * Cerca i reemplaça un valor d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSearchReplace(Scanner scanner) {
        System.out.println("Escrigui el valor a cercar:");
        String valorCercar = readLine(scanner);
        System.out.println("Escrigui el valor pel qual reemplaçar:");
        String valorReemplacar = readLine(scanner);
        full.buscarValorIReemplacar(valorCercar, valorReemplacar);
        System.out.println("Cerca i reemplaçament realitzat correctament.");
    }

    /**
     * Representa totes les comandes que accepta el driver.
     *
     * @param command la comanda que verificarà.
     * @param scanner mètode per fer l'input.
     * */
    public static boolean commands(String command, Scanner scanner) {
        switch (command) {
            case CONSTRUCTORA:
                testConstructoraFull();
                break;
            case AFEGIRCOLUMNA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testAddColumna(scanner);
                break;
            case AFEGIRCOLUMNES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testAddColumnes(scanner);
                break;
            case ELIMINARCOLUMNA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testDeleteColumna(scanner);
                break;
            case ELIMINARCOLUMNES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testDeleteColumnes(scanner);
                break;
            case AFEGIRFILA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testAddFila(scanner);
                break;
            case AFEGIRFILES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testAddFiles(scanner);
                break;
            case ELIMINARFILA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testDeleteFila(scanner);
                break;
            case ELIMINARFILES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testDeleteFiles(scanner);
            case MODIFICACELA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testModificaCela(scanner);
                break;
            case GETCELA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testGetCela(scanner);
                break;
            case ADDREFERENCIA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testAddReferencia(scanner);
                break;
            case DELETEREFERENCIA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testDeleteReferencies(scanner);
                break;
            case COPIACELA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testCopyCela(scanner);
                break;
            case MOUCELA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testMoveCela(scanner);
                break;
            case COPIABLOCCELES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testCopyBlocCeles(scanner);
                break;
            case MOUBLOCCELES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testMoveBlocCeles(scanner);
                break;
            case ORDENABLOCCELES:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testSortBlocCeles(scanner);
                break;
            case CERCA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testSearch(scanner);
                break;
            case CERCAIREMPLACA:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    testSearchReplace(scanner);
                break;
            case PRINT:
                if (full == null)
                    System.out.println(ERRORFULL);
                else
                    printAll();
                break;
            case HELP:
                System.out.println(HELP);
                break;
            case EXIT:
                return false;
            default:
                System.out.println("ERROR: La comanda introduïda no és vàlida");
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
                System.out.println("ERROR: Arxiu no admès");
            }
        }

        if (run) System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run)
            run = commands(in.nextLine(), in);

        System.out.println("Gràcies per utilitzar el programa DriverFull!");

    }
}
