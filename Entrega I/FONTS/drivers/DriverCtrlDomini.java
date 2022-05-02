package drivers;

import controladors.CtrlDomini;
import classes.Funcions;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

/**
 * És el driver del controlador de domini.
 * @author adrian.cristian.crisan
 */
public class DriverCtrlDomini {
    /**
     * Representa la comanda per crear un document.
     * */
    private static final String CREADOCUMENT            = "0";
    /**
     * Representa la comanda per modificar el nom del document.
     * */
    private static final String SETNOMDOCUMENT          = "1";
    /**
     * Representa la comanda per obtenir el nom del document.
     * */
    private static final String GETNOMDOCUMENT          = "2";
    /**
     * Representa la comanda per obtenir el nombre de fulls.
     * */
    private static final String GETNOMBREFULLS          = "3";
    /**
     * Representa la comanda per afegir un full.
     * */
    private static final String ADDFULL                 = "4";
    /**
     * Representa la comanda per eliminar un full.
     * */
    private static final String DELETEFULL              = "5";
    /**
     * Representa la comanda per afegir una columna.
     * */
    private static final String AFEGIRCOLUMNA           = "6";
    /**
     * Representa la comanda per afegir múltiples columnes.
     * */
    private static final String AFEGIRCOLUMNES          = "7";
    /**
     * Representa la comanda per eliminar una columna.
     * */
    private static final String ELIMINARCOLUMNA         = "8";
    /**
     * Representa la comanda per eliminar múltiples columnes.
     * */
    private static final String ELIMINARCOLUMNES        = "9";
    /**
     * Representa la comanda per afegir una fila.
     * */
    private static final String AFEGIRFILA              = "10";
    /**
     * Representa la comanda per afegir múltiples files.
     * */
    private static final String AFEGIRFILES             = "11";
    /**
     * Representa la comanda per eliminar una fila.
     * */
    private static final String ELIMINARFILA            = "12";
    /**
     * Representa la comanda per eliminar múltiples files.
     * */
    private static final String ELIMINARFILES           = "13";
    /**
     * Representa la comanda per modificar una cel·la.
     * */
    private static final String SETCELA                 = "14";
    /**
     * Representa la comanda per obtenir el valor d'una cel·la.
     * */
    private static final String GETVALORCELA            = "15";
    /**
     * Representa la comanda per copiar una cel·la.
     * */
    private static final String COPIACELA               = "16";
    /**
     * Representa la comanda per moure una cel·la.
     * */
    private static final String MOUCELA                 = "17";
    /**
     * Representa la comanda per copiar un bloc de cel·les.
     * */
    private static final String COPIABLOCCELES          = "18";
    /**
     * Representa la comanda per moure un bloc de cel·les.
     * */
    private static final String MOUBLOCCELES            = "19";
    /**
     * Representa la comanda per ordenar un bloc de cel·les.
     * */
    private static final String ORDENABLOCCELES         = "20";
    /**
     * Representa la comanda per cercar en un full.
     * */
    private static final String CERCA                   = "21";
    /**
     * Representa la comanda per cercar i reemplaçar per valor en un full.
     * */
    private static final String CERCAIREMPLACA          = "22";
    /**
     * Representa la comanda d'ajuda.
     * */
    private static final String HELP                    = "help";
    /**
     * Representa la comanda de sortida del programa.
     * */
    private static final String EXIT                    = "exit";

    /**
     * Representa el panell d'ajuda.
     * */
    private static final String HELPTEXT = "Introduïu un dels següents números per executar la corresponent comanda:\n" +
            "   " + CREADOCUMENT        + " - Crea un document\n" +
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
            "   " + COPIACELA           + " - Copia i enganxa una cel·la\n" +
            "   " + MOUCELA             + " - Mou una cel·la\n" +
            "   " + COPIABLOCCELES      + " - Copia i enganxa un bloc de cel·les\n" +
            "   " + MOUBLOCCELES        + " - Mou un bloc de cel·les\n" +
            "   " + ORDENABLOCCELES     + " - Ordena un bloc cel·les\n" +
            "   " + CERCA               + " - Obté les posicions de les cel·les que contenen cert valor\n" +
            "   " + CERCAIREMPLACA      + " - Reemplaça les cel·les que contenen cert valor\n" +
            "   " + HELP                + " - Mostra totes les comandes disponibles\n" +
            "   " + EXIT                + " - Sortir del programa\n" +
            "Per escriure una referència:       $númeroFull-posicióCela\n" +
            "   On el número de full és un valor enter entre el 0 i el número de fulls que tingui el document\n" +
            "   On la posició de la cel·la es representa amb caràcters alfabètics en majúscula per la columna i \n" +
            "       números per representar la fila, alguns exemples: $0-A1, $1-AA12\n" +
            "Per escriure un bloc de cel·les:   $númeroFull-posicióInicial:posicióFinal\n" +
            "Per escriure una funció unària:    =nomFuncio(paràmetre)\n" +
            "Per escriure una funció binària:   =nomFuncio(paràmetre1,paràmetre2)\n";

    /**
     * Representa totes les funcions unàries amb un valor com a paràmetre.
     * */
    private static final HashSet<String> funcionsUnariesValor = new HashSet<>(Arrays.asList(
            Funcions.TRUNCARVALOR,
            Funcions.ARRODONIRVALOR,
            Funcions.DECIMALABINARI,
            Funcions.DECIMALAHEXADECIMAL,
            Funcions.BINARIADECIMAL,
            Funcions.HEXADECIMALADECIMAL,
            Funcions.USDAEUR,
            Funcions.EURAUSD,
            Funcions.INCREMENTAVALOR,
            Funcions.DECREMENTAVALOR,
            Funcions.VALORABSOLUT,
            Funcions.MAJUSCULESAMINUSCULES,
            Funcions.MINUSCULESAMAJUSCULES,
            Funcions.INTERCANVIMINUSCULESMAJUSCULES,
            Funcions.COMPTARCARACTERS,
            Funcions.COMPTARBYTES,
            Funcions.OBTENIRDIA,
            Funcions.OBTENIRDIASETMANA,
            Funcions.OBTENIRMES,
            Funcions.OBTENIRANY
    ));

    /**
     * Representa totes les funcions unàries amb una llista com a paràmetre.
     * */
    private static final HashSet<String> funcionsUnariesLlista = new HashSet<>(Arrays.asList(
            Funcions.MITJANA,
            Funcions.MEDIANA,
            Funcions.VARIANCIA,
            Funcions.DESVIACIOESTANDARD
    ));

    /**
     * Representa totes les funcions binàries amb dos valors com a paràmetres.
     * */
    private static final HashSet<String> funcionsBinariesValors = new HashSet<>(Arrays.asList(
            Funcions.SUMA,
            Funcions.RESTA,
            Funcions.DIVIDEIX,
            Funcions.CONCATENA,
            Funcions.DIESDIFERENCIA
    ));

    /**
     * Representa totes les funcions binàries amb dues llistes com a paràmetres.
     * */
    private static final HashSet<String> funcionsBinariesLlistes = new HashSet<>(Arrays.asList(
            Funcions.COVARIANCIA,
            Funcions.CORRELACIO
    ));

    private static final String ESCRIUNOMDOCUMENT = "Escrigui el nom del document:";
    private static final String ESCRIUFULL = "Escrigui el número del full:";
    private static final String ESCRIUPOSICIO = "Escrigui la posició de la cel·la:";
    private static final String ESCRIUCONTINGUT = "Escrigui contingut de la cel·la:";
    private static final String ERRORDOCUMENTINEXISTENT = "ERROR: Document inexistent.";
    private static final String ERRORPOSICIO = "ERROR: Posició no vàlida, torni a introduir una posició:";
    private static final String ERRORFULL = "ERROR: Full no vàlid";
    private static final String ERRORNOENTER = "ERROR: El valor introduir no és un enter";
    private static final String ERRORCONTINGUT = "ERROR: Contingut en blanc";

    /**
     * Representa el controlador de domini.
     * */
    private static CtrlDomini ctrlDomini;

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
            System.out.println(ERRORNOENTER);
            return readInteger(scanner);
        }
    }

    /**
     * Imprimeix per terminal totes les comandes disponibles
     * */
    private static void printHelp() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(HELPTEXT);
        System.out.println("La següent llista mostra les funcions disponibles:");
        System.out.println("    Funcions unàries amb un valor o referència com a paràmetre:");
        for (String str : funcionsUnariesValor)
            System.out.println("        " + str);
        System.out.println("    Funcions unàries amb una bloc de cel·les com a paràmetre:");
        for (String str : funcionsUnariesLlista)
            System.out.println("        " + str);
        System.out.println("    Funcions binàries amb dos valors o referències com a paràmetres:");
        for (String str : funcionsBinariesValors)
            System.out.println("        " + str);
        System.out.println("    Funcions binàries amb dos blocs de cel·les com a paràmetres:");
        for (String str : funcionsBinariesLlistes)
            System.out.println("        " + str);
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    /**
     * Llegeix una posició per la terminal.
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
                    System.out.println(ERRORPOSICIO);
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
            System.out.println(ERRORPOSICIO);
            return getPosicio(scanner);
        }
    }

    /**
     * Crea un document.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCreaDocument(Scanner scanner) {
        System.out.println(ESCRIUNOMDOCUMENT);
        ctrlDomini = new CtrlDomini(readLine(scanner));
        System.out.println("Document creat correctament");
    }

    /**
     * Modifica el nom del document.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSetNomDocument(Scanner scanner) {
        if (ctrlDomini != null) {
            System.out.println(ESCRIUNOMDOCUMENT);
            ctrlDomini.setNomDocument(readLine(scanner));
            System.out.println("El nom del document ara és: " + ctrlDomini.getNomDocument());
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Obté el nom del document.
     * */
    public static void testGetNomDocument() {
        if (ctrlDomini != null)
            System.out.println(ctrlDomini.getNomDocument());
        else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Obté el nombre de fulls del document.
     * */
    public static void testGetNombreFulls() {
        if (ctrlDomini != null)
            System.out.println(ctrlDomini.getNombreFulls());
        else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Afegeix un full al document.
     * */
    public static void testAddFull() {
        if (ctrlDomini != null) {
            ctrlDomini.addFull();
            System.out.println("Full afegit correctament, correspon al full: " + (ctrlDomini.getNombreFulls() - 1));
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Elimina un full del document.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testDeleteFull(Scanner scanner) {
        if (ctrlDomini != null) {
            System.out.println(ESCRIUFULL);
            int full = readInteger(scanner);
            if (full >= ctrlDomini.getNombreFulls() || full < 0) {
                System.out.println(ERRORFULL);
                return;
            }
            try {
                ctrlDomini.deleteFull(full);
                System.out.println("Full número " + full + " s'ha eliminat correctament");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Modifica una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testSetCela(Scanner scanner) {
        if (ctrlDomini != null) {
            System.out.println(ESCRIUFULL);
            int full = getFull(scanner);
            System.out.println(ESCRIUPOSICIO);
            String posicio = getPosicio(scanner);
            System.out.println(ESCRIUCONTINGUT);
            String contingut = readLine(scanner);

            if (contingut.isBlank()) {
                System.out.println(ERRORCONTINGUT);
                return;
            }

            try {
                ctrlDomini.setCela(full, posicio, contingut);
                System.out.println("La cel·la " + posicio + " del full " + full + " ara té valor " + ctrlDomini.getValorCela(full, posicio) + " com a resultat de " + contingut);
            } catch (Exception e) {
                if (e.getMessage().equals("null"))
                    System.out.println("ERROR: La cel·la/es referenciada/es no existeix/en");
                else
                    System.out.println("ERROR: " + e.getMessage());
            }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Obté el valor d'una cel·la.
     * */
    public static void testGetValorCela(Scanner scanner) {
        if (ctrlDomini != null) {
            System.out.println(ESCRIUFULL);
            int full = readInteger(scanner);
            System.out.println(ESCRIUPOSICIO);
            String posicio = getPosicio(scanner);
            String valor = ctrlDomini.getValorCela(full, posicio);
            System.out.println(Objects.requireNonNullElse(valor, "0"));
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
        if (full >= ctrlDomini.getNombreFulls() || full < 0) {
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol afegir la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de columna que afegir:");
            String columna = getColumna(scanner);
            ctrlDomini.addColumnes(full, columna, 1);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol afegir les columnes:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la primera columna que vol afegir:");
            String columna = getColumna(scanner);
            System.out.println("Escrigui el nombre de columnes a afegir:");
            int shift = readInteger(scanner);
            ctrlDomini.addColumnes(full, columna, shift);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol eliminar la columna:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de columna que vol eliminar:");
            String columna = getColumna(scanner);
            ctrlDomini.deleteColumnes(full, columna, 1);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol eliminar les columnes:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la primera columna que vol eliminar:");
            String columna = getColumna(scanner);
            System.out.println("Escrigui el nombre de columnes a eliminar:");
            int shift = readInteger(scanner);
            ctrlDomini.deleteColumnes(full, columna, shift);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol afegir la fila:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de fila que vol afegir:");
            int fila = readInteger(scanner);
            ctrlDomini.addFiles(full, fila, 1);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol afegir les files:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la primera fila que vol afegir:");
            int fila = readInteger(scanner);
            System.out.println("Escrigui el nombre de files a afegir:");
            int shift = readInteger(scanner);
            ctrlDomini.addFiles(full, fila, shift);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol eliminar la fila:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de fila que vol eliminar:");
            int fila = readInteger(scanner);
            ctrlDomini.deleteFiles(full, fila, 1);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol eliminar les files:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la primera fila que vol eliminar:");
            int fila = readInteger(scanner);
            System.out.println("Escrigui el nombre de files a eliminar:");
            int shift = readInteger(scanner);
            ctrlDomini.deleteFiles(full, fila, shift);
            System.out.println("Files eliminades correctament.");
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Copia una cel·la.
     *
     * @param scanner mètode per fer l'input.
     * */
    public static void testCopyCela(Scanner scanner) {
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol copiar la cel·la:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la cel·la que es desitja copiar:");
            String posicioCopiada = getPosicio(scanner);
            System.out.println("Escrigui la posició de la cel·la que es desitja enganxar:");
            String posicioEnganxada = getPosicio(scanner);
            ctrlDomini.copyCela(full, posicioCopiada, posicioEnganxada);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol moure la cel·la:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició de la cel·la que es desitja moure:");
            String posicioMoure = getPosicio(scanner);
            System.out.println("Escrigui la posició de la cel·la que on es desitja moure:");
            String posicioEnganxada = getPosicio(scanner);
            ctrlDomini.moveCela(full, posicioMoure, posicioEnganxada);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol copiar el bloc de cel·les:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició inicial del bloc de cel·les:");
            String posicioInicial = getPosicio(scanner);
            System.out.println("Escrigui la posició final del bloc de cel·les:");
            String posicioFinal = getPosicio(scanner);
            System.out.println("Escrigui el nombre de columna o columnes a desplaçar:");
            int shift = readInteger(scanner);

            try {
                ctrlDomini.copyBlocCeles(full, posicioInicial, posicioFinal, shift);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol moure el bloc de cel·les:");
            int full = getFull(scanner);
            System.out.println("Escrigui la posició inicial del bloc de cel·les:");
            String posicioInicial = getPosicio(scanner);
            System.out.println("Escrigui la posició final del bloc de cel·les:");
            String posicioFinal = getPosicio(scanner);
            System.out.println("Escrigui el nombre de columna o columnes a desplaçar:");
            int shift = readInteger(scanner);

            try {
                ctrlDomini.moveBlocCeles(full, posicioInicial, posicioFinal, shift);
                System.out.println("S'ha mogut el bloc de cel·les correctament.");
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } else
            System.out.println(ERRORDOCUMENTINEXISTENT);
    }

    /**
     * Funció que llegeix el tipus d'ordenació.
     *
     * @param scanner ordre que es desitja.
     * @return string del tipus d'ordenació.
     */
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
        if (ctrlDomini != null) {
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
                    ctrlDomini.sortBlocCeles(full, posicioInicial, posicioFinal, sortType);
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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol cercar:");
            int full = getFull(scanner);
            System.out.println("Escrigui el valor a cercar:");
            String valor = readLine(scanner);
            HashSet<String> posicions = ctrlDomini.search(full, valor);

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
        if (ctrlDomini != null) {
            System.out.println("Escrigui el full on vol cercar:");
            int full = getFull(scanner);
            System.out.println("Escrigui el valor a cercar:");
            String valorCercar = readLine(scanner);
            System.out.println("Escrigui el valor pel qual reemplaçar:");
            String valorReemplacar = readLine(scanner);
            ctrlDomini.searchReplace(full, valorCercar, valorReemplacar);
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
                break;
            case SETCELA:
                testSetCela(scanner);
                break;
            case GETVALORCELA:
                testGetValorCela(scanner);
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
                printHelp();
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

        if (run) printHelp();
        Scanner in = new Scanner(System.in);
        while (run)
            run = commands(in.nextLine(), in);

        System.out.println("Gràcies per utilitzar el programa DriverCtrlDomini!");

    }
}
