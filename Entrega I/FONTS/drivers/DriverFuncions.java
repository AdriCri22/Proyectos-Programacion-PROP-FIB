package drivers;

import classes.Funcions;
import java.io.File;
import java.text.ParseException;
import java.util.*;

/**
 * És el driver de la classe funció.
 * @author adrian.cristian.crisan
 */
public class DriverFuncions {

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

    /**
     * Representa la comanda d'ajuda.
     * */
    private static final String HELP = "help";
    /**
     * Representa la comanda de sortida del programa.
     * */
    private static final String EXIT = "exit";

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
     * Va llegint línies per la terminal fins que s'escriu un '.' (punt) per finalitzar.
     *
     * @param scanner mètode per fer l'input.
     * @return Retorna un vector amb totes les línies llegides.
     * */
    private static ArrayList<String> readMultiLine(Scanner scanner) {
        ArrayList<String> values = new ArrayList<>();
        String value = readLine(scanner);
        while (!value.equals(".")) {
            values.add(value);
            value = readLine(scanner);
        }

        return values;
    }

    /**
     * Imprimeix per terminal totes les comandes disponibles
     * */
    private static void printHelp() {
        System.out.println("La següent llista mostra les funcions disponibles:");
        System.out.println("    Funcions unàries amb un valor:");
        for (String str : funcionsUnariesValor)
            System.out.println("        " + str);
        System.out.println("    Funcions unàries amb una llista:");
        for (String str : funcionsUnariesLlista)
            System.out.println("        " + str);
        System.out.println("    Funcions binàries amb dos valors:");
        for (String str : funcionsBinariesValors)
            System.out.println("        " + str);
        System.out.println("    Funcions binàries amb dues llistes:");
        for (String str : funcionsBinariesLlistes)
            System.out.println("        " + str);
        System.out.println("Altres funcions disponibles:\n" +
                "   " + HELP + " - Mostra totes les comandes disponibles\n" +
                "   " + EXIT + " - Sortir del programa\n");
    }

    /**
     * Imprimeix per terminal el resultat d'aplicar una funció a un o dos paràmetres o a una o dues llistes.
     *
     * @param funcio funció que volem computar.
     * @param scanner mètode per fer l'input.
     * */
    public static void testCalcula(String funcio, Scanner scanner) {
        if (funcionsUnariesValor.contains(funcio)) {
            System.out.println("Introdueix el paràmetre:");
            try {
                System.out.println(Funcions.calcula(funcio, readLine(scanner)));
            } catch (Exception e) {
                System.out.println("El paràmetre introduït no correspon al tipus de paràmetre que admet la funció introduïda");
            }
        } else if (funcionsUnariesLlista.contains(funcio)) {
            System.out.println("Introdueix la llista, quan acabis introdueixi un '.' (punt):");
            try {
                System.out.println(Funcions.calcula(funcio, readMultiLine(scanner)));
            } catch (Exception e) {
                System.out.println("Algun/s del/s valor/s introduït/s no correspon al tipus de paràmetre que admet la funció introduïda");
            }
        } else if (funcionsBinariesValors.contains(funcio)) {
            System.out.println("Introdueix el primer paràmetre:");
            String value1 = readLine(scanner);
            System.out.println("Introdueix el segon paràmetre:");
            String value2 = readLine(scanner);
            try {
                System.out.println(Funcions.calcula(funcio, value1, value2));
            } catch (Exception e) {
                System.out.println("Algun/s del/s valor/s introduït/s no correspon al tipus de paràmetre que admet la funció introduïda");
            }
        } else if (funcionsBinariesLlistes.contains(funcio)) {
            System.out.println("Introdueix la primera llista, quan acabis introdueixi un '.' (punt):");
            ArrayList<String> values1 = readMultiLine(scanner);
            System.out.println("Introdueix la segona llista, quan acabis introdueixi un '.' (punt):");
            ArrayList<String> values2 = readMultiLine(scanner);
            try {
                System.out.println(Funcions.calcula(funcio, values1, values2));
            } catch (ParseException e) {
                System.out.println("Algun/s del/s valor/s introduït/s no correspon al tipus de paràmetre que admet la funció introduïda");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else
            System.out.println("No existeix cap funció equivalent");
    }

    /**
     * Representa totes les comandes que accepta el driver.
     *
     * @param command la comanda que verificarà.
     * @param scanner mètode per fer l'input.
     * */
    private static boolean commands(String command, Scanner scanner) {
            if (command.equals(HELP))
                printHelp();
            else if (command.equals(EXIT))
                return false;
            else
                testCalcula(command, scanner);
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

        System.out.println("Gràcies per utilitzar el programa DriverFuncions!");
    }
}
