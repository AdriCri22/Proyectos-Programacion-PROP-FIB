package drivers;

import controladors.CtrlDocument;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;

/**
 * És el driver del controlador de document.
 * @author adrian.cristian.crisan
 */
public class DriverCtrlDocument {
    /**
     * Representa la comanda per crear un document.
     * */
    private static final String CREADOCUMENT            = "0";
    /**
     * Representa la comanda per obtenir si hi ha un document existent.
     * */
    private static final String EXSITDOCUMENT           = "1";
    /**
     * Representa la comanda que modifica el nom del document.
     * */
    private static final String SETNOMDOCUMENT          = "2";
    /**
     * Representa la comanda que obté el nom del document.
     * */
    private static final String GETNOMDOCUMENT          = "3";
    /**
     * Representa la comanda per obtenir el nombre de fulls.
     * */
    private static final String GETNOMBREFULLS          = "4";
    /**
     * Representa la comanda per afegir un full.
     * */
    private static final String ADDFULL                 = "5";
    /**
     * Representa la comanda per eliminar un full.
     * */
    private static final String DELETEFULL              = "6";
    /**
     * Representa la comanda per afegir una columna.
     * */
    private static final String AFEGIRCOLUMNA           = "7";
    /**
     * Representa la comanda per afegir múltiples columnes.
     * */
    private static final String AFEGIRCOLUMNES          = "8";
    /**
     * Representa la comanda per eliminar una columna.
     * */
    private static final String ELIMINARCOLUMNA         = "9";
    /**
     * Representa la comanda per eliminar múltiples columnes.
     * */
    private static final String ELIMINARCOLUMNES        = "10";
    /**
     * Representa la comanda per afegir una fila.
     * */
    private static final String AFEGIRFILA              = "11";
    /**
     * Representa la comanda per afegir múltiples files.
     * */
    private static final String AFEGIRFILES             = "12";
    /**
     * Representa la comanda per eliminar una fila.
     * */
    private static final String ELIMINARFILA            = "13";
    /**
     * Representa la comanda per eliminar múltiples files.
     * */
    private static final String ELIMINARFILES           = "14";
    /**
     * Representa la comanda per modificar una cel·la.
     * */
    private static final String SETCELA                 = "15";
    /**
     * Representa la comanda per obtenir el valor d'una cel·la.
     * */
    private static final String GETVALORCELA            = "16";
    /**
     * Representa la comanda per afegir una referència.
     * */
    private static final String ADDREFERENCIA           = "17";
    /**
     * Representa la comanda per eliminar les referències d'una cel·la.
     * */
    private static final String DELETEREFERENCIES       = "18";
    /**
     * Representa la comanda per copiar una cel·la.
     * */
    private static final String COPIACELA               = "19";
    /**
     * Representa la comanda per moure una cel·la.
     * */
    private static final String MOUCELA                 = "20";
    /**
     * Representa la comanda per copiar un bloc de cel·les.
     * */
    private static final String COPIABLOCCELES          = "21";
    /**
     * Representa la comanda per moure un bloc de cel·les.
     * */
    private static final String MOUBLOCCELES            = "22";
    /**
     * Representa la comanda per ordenar un bloc de cel·les.
     * */
    private static final String ORDENABLOCCELES         = "23";
    /**
     * Representa la comanda per cercar en un full.
     * */
    private static final String CERCA                   = "24";
    /**
     * Representa la comanda per cercar i reemplaçar per valor en un full.
     * */
    private static final String CERCAIREMPLACA          = "25";
    /**
     * Representa la comanda per obtenir les comandes disponibles.
     * */
    private static final String HELP                    = "help";
    /**
     * Representa la comanda per sortir del programa.
     * */
    private static final String EXIT                    = "exit";

    private static final String ERRORDOCUMENTINEXISTENT = "ERROR: Document inexistent.";
    private static final String ERRORFULL = "ERROR: full no vàlid";
    private static final String ESCRIUFULL = "Escrigui el número del full:";


    /**
     * Representa el panell d'ajuda.
     * */
    private static final String HELPTEXT = "Introduïu un dels següents números per executar la corresponent comanda:\n" +
            "   " + CREADOCUMENT        + " - Crea un document\n" +
            "   " + EXSITDOCUMENT       + " - Informa de si existeix un document\n" +
            "   " + SETNOMDOCUMENT      + " - Modifica el nom del document\n" +
            "   " + GETNOMDOCUMENT      + " - Obté el nom del document\n" +
            "   " + GETNOMBREFULLS      + " - Obté el nombre de fulls del document\n" +
            "   " + ADDFULL             + " - Afegeix un full en blanc al document\n" +
            "   " + DELETEFULL          + " - Elimina un full del document\n" +
            "   " + AFEGIRCOLUMNA       + " - Afegeix una columna\n" +
            "   " + AFEGIRCOLUMNES      + " - Afegeix múltiples columnes\n" +
            "   " + ELIMINARCOLUMNA     + " - Elimina una columna\n" +
            "   " + ELIMINARCOLUMNES    + " - Elimina una columna\n" +
            "   " + AFEGIRFILA          + " - Afegeix una fila\n" +
            "   " + AFEGIRFILES         + " - Afegeix múltiples files\n" +
            "   " + ELIMINARFILA        + " - Elimina una fila\n" +
            "   " + ELIMINARFILES       + " - Elimina una files\n" +
            "   " + SETCELA             + " - Modifica el valor d'una cel·la\n" +
            "   " + GETVALORCELA        + " - Obté el valor d'una cel·la\n" +
            "   " + ADDREFERENCIA       + " - Afegeix una referencia entre dues cel·les\n" +
            "   " + DELETEREFERENCIES   + " - Elimina les referencies d'una cel·la\n" +
            "   " + COPIACELA           + " - Copia i enganxa una cel·la\n" +
            "   " + MOUCELA             + " - Mou una cel·la\n" +
            "   " + COPIABLOCCELES      + " - Copia i enganxa un bloc de cel·les\n" +
            "   " + MOUBLOCCELES        + " - Mou un bloc de cel·les\n" +
            "   " + ORDENABLOCCELES     + " - Ordena un bloc cel·les\n" +
            "   " + CERCA               + " - Obté les posicions de les cel·les que contenen cert valor\n" +
            "   " + CERCAIREMPLACA      + " - Reemplaça les cel·les que contenen cert valor\n" +
            "   " + HELP                + " - Mostra totes les comandes disponibles\n" +
            "   " + EXIT                + " - Sortir del programa\n";

    /**
     * Representa controlador del document.
     * */
    private static final CtrlDocument ctrlDocument = new CtrlDocument();

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
                    System.out.println("ERROR: Posició no vàlida, torni a introduir la posició:");
                    return getPosicio(scanner);
                }
            }

            String partNumerica = posicio.substring(sep);
            Integer.parseInt(partNumerica);
            if (partNumerica.length() == 0) {
                System.out.println("ERROR: Posició no vàlida, torni a introduir la posició:");
                return getPosicio(scanner);
            } else if (sep == 0) {
                System.out.println("ERROR: Posició no vàlida, torni a introduir la posició:");
                return getPosicio(scanner);
            }

            return posicio;
        } catch (Exception e) {
            System.out.println("ERROR: La posició introduïda no és vàlida, torni a introduir la posició:");
            return getPosicio(scanner);
        }
    }

    /**
     * Crea un document.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCreaDocument(Scanner scanner) {
        System.out.println("Escrigui el nom del document:");
        ctrlDocument.crearDocument(readLine(scanner));
    }

    /**
     * Comprova si existeix un document creat.
     * */
    public static void testExistDocument() {
        if (ctrlDocument.existDocument())
            System.out.println("El document existeix");
        else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Modifica el nom del document.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSetNomDocument(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el nom del document:");
            ctrlDocument.setNomDocument(readLine(scanner));
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Obté el nom del document.
     * */
    public static void testGetNomDocument() {
        if (ctrlDocument.existDocument())
            System.out.println(ctrlDocument.getNomDocument());
        else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Obté el nombre de fulls del document.
     * */
    public static void testGetNombreFulls() {
        if (ctrlDocument.existDocument())
            System.out.println("El document té " + ctrlDocument.getNombreFulls() + " full(s).");
        else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Afegeix un full al document.
     * */
    public static void testAddFull() {
        if (ctrlDocument.existDocument()) {
            ctrlDocument.addFull();
            System.out.println("Full afegit correctament, correspon al full: " + (ctrlDocument.getNombreFulls() - 1));
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina un full del document.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteFull(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println(ESCRIUFULL);
            int full = readInteger(scanner);
            if (full >= ctrlDocument.getNombreFulls() || full < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            try {
                ctrlDocument.deleteFull(full);
                System.out.println("Full número " + full + " s'ha eliminat correctament");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
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
     * Obté un full.
     *
     *  @param scanner mètode per fer l'input.
     * */
    private static int getFull(Scanner scanner) {
        int full = readInteger(scanner);
        if (full >= ctrlDocument.getNombreFulls() || full < 0) {
            System.out.println(ERRORFULL);
            return getFull(scanner);
        }

        return full;
    }

    /**
     * Afegeix una columna a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddColumna(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol afegir la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de columna on vol afegir la nova columna:");
            String columna = getColumna(scanner);
            ctrlDocument.addColumnes(full, columna, 1);
            System.out.println("Columna afegida correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Afegeix múltiples columnes a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddColumnes(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol afegir la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de columna on vol afegir la nova columna:");
            String columna = getColumna(scanner);
            System.out.println("Escrigui el nombre de columnes a afegir:");
            int shift = readInteger(scanner);
            ctrlDocument.addColumnes(full, columna, shift);
            System.out.println("Columnes afegides correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina una columna d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteColumna(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol eliminar la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de columna on vol eliminar la nova columna:");
            String columna = getColumna(scanner);
            ctrlDocument.deleteColumnes(full, columna, 1);
            System.out.println("Columna eliminada correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina múltiples columnes d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteColumnes(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol eliminar la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de columna on vol eliminar la nova columna:");
            String columna = getColumna(scanner);
            System.out.println("Escrigui el nombre de columnes a eliminar:");
            int shift = readInteger(scanner);
            ctrlDocument.deleteColumnes(full, columna, shift);
            System.out.println("Columnes eliminades correctament.");
        }  else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Afegeix una fila a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddFila(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol afegir la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de fila on vol afegir la nova fila:");
            int fila = readInteger(scanner);
            ctrlDocument.addFiles(full, fila, 1);
            System.out.println("Fila afegida correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Afegeix múltiples files a un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddFiles(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol afegir la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de fila on vol afegir la nova fila:");
            int fila = readInteger(scanner);
            System.out.println("Escrigui el nombre de columnes a afegir:");
            int shift = readInteger(scanner);
            ctrlDocument.addFiles(full, fila, shift);
            System.out.println("Files afegides correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina una fila d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteFila(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol eliminar la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de fila on vol eliminar la nova fila:");
            int fila = readInteger(scanner);
            ctrlDocument.deleteFiles(full, fila, 1);
            System.out.println("Fila eliminada correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina múltiples files d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteFiles(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol eliminar la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de fila on vol eliminar la nova fila:");
            int fila = readInteger(scanner);
            System.out.println("Escrigui el nombre de columnes a eliminar:");
            int shift = readInteger(scanner);
            ctrlDocument.deleteFiles(full, fila, shift);
            System.out.println("Files eliminades correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Modifica una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSetCela(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full al que pertany la cel·la:");
            int full = readInteger(scanner);
            if (full >= ctrlDocument.getNombreFulls() || full < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            System.out.println("Escrigui la posició de la cel·la:");
            String posicio = getPosicio(scanner);
            System.out.println("Escrigui el valor:");
            String valor = readLine(scanner);
            if (valor.isBlank()) {
                System.out.println("ERROR: valor no vàlid");
                return;
            }
            System.out.println("Escrigui el contingut, deixi en blanc si no vol:");
            String contingut = readLine(scanner);

            if (contingut.isBlank())
                contingut = null;
            ctrlDocument.setCela(full, posicio, valor, contingut);
            System.out.println("Cel·la modificada");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Obté el valor d'una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testGetValorCela(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full al que pertany la cel·la:");
            int full = readInteger(scanner);
            if (full >= ctrlDocument.getNombreFulls() || full < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            System.out.println("Escrigui la posició de la cel·la:");
            String posicio = getPosicio(scanner);
            System.out.println(ctrlDocument.getValorCela(full, posicio));
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Afegeix una referència a una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testAddReferencia(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full de la cel·la des de la que es vol referenciar:");
            int full = readInteger(scanner);
            if (full >= ctrlDocument.getNombreFulls() || full < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            System.out.println("Escrigui la posició de la cel·la des de la que es vol referenciar:");
            String posicio = getPosicio(scanner);
            System.out.println("Escrigui el full de la cel·la referenciada:");
            int fullReferenciat = readInteger(scanner);
            System.out.println("Escrigui la posició de la cel·la referenciada:");
            String posicioReferenciada = getPosicio(scanner);

            if (fullReferenciat >= ctrlDocument.getNombreFulls() || fullReferenciat < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            ctrlDocument.addReferencia(full, posicio, fullReferenciat, posicioReferenciada);
            System.out.println("Referencia afegida correctament");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina les referències que conté una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteReferencies(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full de la cel·la que es vol eliminar les referències:");
            int full = readInteger(scanner);
            if (full >= ctrlDocument.getNombreFulls() || full < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            System.out.println("Escrigui la posició de la cel·la que es vol eliminar les referències:");
            String posicio = getPosicio(scanner);
            ctrlDocument.deleteReferencies(full, posicio);
            System.out.println("Referencies eliminades correctament");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Copia una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCopyCela(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol moure la cel·la:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la cel·la que es desitja copiar:");
            String posicioCopiada = getPosicio(scanner);
            System.out.println("Escrigui la posició de la cel·la que es desitja enganxar:");
            String posicioEnganxada = getPosicio(scanner);
            ctrlDocument.copyCela(full, posicioCopiada, posicioEnganxada);
            System.out.println("Copia realitzada correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Mou una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testMoveCela(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol moure la cel·la:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la cel·la que es desitja moure:");
            String posicioMoure = getPosicio(scanner);
            System.out.println("Escrigui la posició de la cel·la que on es desitja moure:");
            String posicioEnganxada = getPosicio(scanner);
            ctrlDocument.moveCela(full, posicioMoure, posicioEnganxada);
            System.out.println("Copia realitzada correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Copia un bloc de cel·les.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCopyBlocCeles(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol copiar el bloc de cel·les:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició inicial del bloc de cel·les:");
            String posicioInicial = getPosicio(scanner);
            System.out.println("Escrigui la posició final del bloc de cel·les:");
            String posicioFinal = getPosicio(scanner);
            System.out.println("Escrigui el nombre de columnes o columnes a desplaçar:");
            int shift = readInteger(scanner);

            try {
                ctrlDocument.copyBlocCeles(full, posicioInicial, posicioFinal, shift);
                System.out.println("Copia realitzada correctament.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }


    /**
     * Mou un bloc de cel·les.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testMoveBlocCeles(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol moure el bloc de cel·les:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició inicial del bloc de cel·les:");
            String posicioInicial = getPosicio(scanner);
            System.out.println("Escrigui la posició final del bloc de cel·les:");
            String posicioFinal = getPosicio(scanner);
            System.out.println("Escrigui el nombre de columnes o columnes a desplaçar:");
            int shift = readInteger(scanner);

            try {
                ctrlDocument.moveBlocCeles(full, posicioInicial, posicioFinal, shift);
                System.out.println("S'ha mogut el bloc de cel·les correctament.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
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
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol ordenar el bloc de cel·les:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició inicial del bloc de cel·les:");
            String posicioInicial = getPosicio(scanner);
            System.out.println("Escrigui la posició final del bloc de cel·les:");
            String posicioFinal = getPosicio(scanner);
            System.out.println("Escrigui 'ASC' si vol ordenar de manera ascendent o 'DESC' si vol ordenar descendentment:");
            String sortType = sortType(scanner);

            if (sortType.equals("ASC") || sortType.equals("DESC"))
                try {
                    ctrlDocument.sortBlocCeles(full, posicioInicial, posicioFinal, sortType);
                    System.out.println("S'ha ordenat el bloc de cel·les correctament.");
                } catch (Exception e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Cerca un valor en un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSearch(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol cercar:");
            int full = getFull(scanner);
            System.out.println("Escrigui el valor a cercar:");
            String valor = readLine(scanner);
            HashSet<String> posicions = ctrlDocument.search(full, valor);

            for (String pos : posicions)
                System.out.println(pos);
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Cerca i reemplaça un valor d'un full.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSearchReplace(Scanner scanner) {
        if (ctrlDocument.existDocument()) {
            System.out.println("Escrigui el full on vol cercar:");
            int full = getFull(scanner);
            System.out.println("Escrigui el valor a cercar:");
            String valorCercar = readLine(scanner);
            System.out.println("Escrigui el valor pel qual reemplaçar:");
            String valorReemplacar = readLine(scanner);
            ctrlDocument.searchReplace(full, valorCercar, valorReemplacar);
            System.out.println("Cerca i reemplaçament realitzat correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Representa totes les comandes que accepta el driver.
     *
     * @param command la comanda que verificarà.
     * @param scanner mètode per fer l'input.
     * */
    private static boolean commands(String command, Scanner scanner) {
        switch (command) {
            case CREADOCUMENT:
                testCreaDocument(scanner);
                break;
            case EXSITDOCUMENT:
                testExistDocument();
                break;
            case SETNOMDOCUMENT:
                testSetNomDocument(scanner);
                break;
            case GETNOMDOCUMENT:
                testGetNomDocument();
                break;
            case GETNOMBREFULLS:
                testGetNombreFulls();
                break;
            case ADDFULL:
                testAddFull();
                break;
            case DELETEFULL:
                testDeleteFull(scanner);
                break;
            case AFEGIRCOLUMNA:
                testAddColumna(scanner);
                break;
            case AFEGIRCOLUMNES:
                testAddColumnes(scanner);
                break;
            case ELIMINARCOLUMNA:
                testDeleteColumna(scanner);
                break;
            case ELIMINARCOLUMNES:
                testDeleteColumnes(scanner);
                break;
            case AFEGIRFILA:
                testAddFila(scanner);
                break;
            case AFEGIRFILES:
                testAddFiles(scanner);
                break;
            case ELIMINARFILA:
                testDeleteFila(scanner);
                break;
            case ELIMINARFILES:
                testDeleteFiles(scanner);
            case SETCELA:
                testSetCela(scanner);
                break;
            case GETVALORCELA:
                testGetValorCela(scanner);
                break;
            case ADDREFERENCIA:
                testAddReferencia(scanner);
                break;
            case DELETEREFERENCIES:
                testDeleteReferencies(scanner);
                break;
            case COPIACELA:
                testCopyCela(scanner);
                break;
            case MOUCELA:
                testMoveCela(scanner);
                break;
            case COPIABLOCCELES:
                testCopyBlocCeles(scanner);
                break;
            case MOUBLOCCELES:
                testMoveBlocCeles(scanner);
                break;
            case ORDENABLOCCELES:
                testSortBlocCeles(scanner);
                break;
            case CERCA:
                testSearch(scanner);
                break;
            case CERCAIREMPLACA:
                testSearchReplace(scanner);
                break;
            case HELP:
                System.out.println(HELPTEXT);
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
                System.out.println("ERROR: Comanda dins l'arxiu no admès");
            }
        }

        if (run) System.out.println(HELPTEXT);
        Scanner in = new Scanner(System.in);
        while (run)
            run = commands(in.nextLine(), in);

        System.out.println("Gràcies per utilitzar el programa DriverCtrlDocument!");

    }
}
