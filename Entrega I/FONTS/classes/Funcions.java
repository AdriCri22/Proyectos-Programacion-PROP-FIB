package classes;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Representa una funció.
 * Aquestes funcions poden ser unàries o binàries admetent com a paràmetre un valor o una llista.
 * @author adrian.cristian.crisan
 */

public class Funcions {
    // FUNCIONS UNÀRIES AMB UN VALOR
    public static final String TRUNCARVALOR                     = "truncarValor";
    public static final String ARRODONIRVALOR                   = "arrodonirValor";
    public static final String DECIMALABINARI                   = "decimalABinari";
    public static final String DECIMALAHEXADECIMAL              = "decimalAHexadecimal";
    public static final String BINARIADECIMAL                   = "binariADecimal";
    public static final String HEXADECIMALADECIMAL              = "hexadecimalADecimal";
    public static final String EURAUSD                          = "eurAusd";
    public static final String USDAEUR                          = "usdAeur";
    public static final String INCREMENTAVALOR                  = "incrementaValor";
    public static final String DECREMENTAVALOR                  = "decrementaValor";
    public static final String VALORABSOLUT                     = "valorAbsolut";
    public static final String MAJUSCULESAMINUSCULES            = "majusculesAMinuscules";
    public static final String MINUSCULESAMAJUSCULES            = "minusculesAMajuscules";
    public static final String INTERCANVIMINUSCULESMAJUSCULES   = "intercanviaMinusculesAMajuscules";
    public static final String COMPTARCARACTERS                 = "comptarCaracters";
    public static final String COMPTARBYTES                     = "comptarBytes";
    public static final String OBTENIRDIA                       = "obtenirDia";
    public static final String OBTENIRDIASETMANA                = "obtenirDiaSetmana";
    public static final String OBTENIRMES                       = "obtenirMes";
    public static final String OBTENIRANY                       = "obtenirAny";
    // FUNCIONS UNÀRIES AMB UNA LLISTA
    public static final String MITJANA              = "mitjana";
    public static final String MEDIANA              = "mediana";
    public static final String VARIANCIA            = "variancia";
    public static final String DESVIACIOESTANDARD   = "desviacioEstandard";
    // FUNCIONS BINÀRIES AMB DOS VALORS
    public static final String SUMA             = "suma";
    public static final String RESTA            = "resta";
    public static final String MULTIPLICA       = "multiplica";
    public static final String DIVIDEIX         = "divideix";
    public static final String CONCATENA        = "concatena";
    public static final String DIESDIFERENCIA   = "diesDiferencia";
    // FUNCIONS BINÀRIES AMB DUES LLISTES
    public static final String COVARIANCIA  = "covariancia";
    public static final String CORRELACIO   = "correlacio";

    /**
     * Donada una llista de strings la converteix en una llista de Doubles.
     *
     * @param llista és una llista de Strings.
     * @return Retorna una llista de Doubles.
     * */
    private static ArrayList<Double> convert(ArrayList<String> llista) {
        ArrayList<Double> valors = new ArrayList<>();
        for (String str : llista)
            valors.add(Double.parseDouble(str));
        return valors;
    }

    /**
     * Donat un string que conté una data retorna aquesta en format de Calendari.
     *
     * @param str string que conté la data.
     * @return Retorna la data en format de Calendari.
     * */
    private static Calendar getDate(String str) throws Exception {
        SimpleDateFormat formatter;

        char sep = str.charAt(str.length() - 5);
        if (str.indexOf('-') != -1) {
            if (sep == '-')
                formatter = new SimpleDateFormat("dd-MM-yyyy");
            else
                formatter = new SimpleDateFormat("dd-MM-yy");

        }
        else if (str.indexOf('/') != -1) {
            if (sep == '/')
                formatter = new SimpleDateFormat("dd/MM/yyyy");
            else
                formatter = new SimpleDateFormat("dd/MM/yy");
        }
        else {
            if (sep == ' ')
                formatter = new SimpleDateFormat("dd MMMM yyyy", new Locale("ca", "Es"));
            else
                formatter = new SimpleDateFormat("dd MMMM yy", new Locale("ca", "Es"));
        }

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(formatter.parse(str));
            return calendar;
        } catch (ParseException e) {
            throw new Exception("La data introduïda no és vàlida");
        }
    }

    // Funcions unàries amb un valor

    /**
     * Trunca l'element donat.
     *
     * @param val és un double que es vol truncar.
     * @return Retorna el paràmetre truncat.
     * */
    private static double truncarValor(double val) {
        return Math.floor(val);
    }

    /**
     * Arrodoneix l'element donat.
     *
     * @param val és un double que es vol truncar.
     * @return Retorna el paràmetre arrodonit.
     * */
    private static double arrodonirValor(double val) {
        return Math.round(val);
    }

    /**
     * Converteix a binari el número en decimal donat.
     *
     * @param val és un int que es vol passar a binari.
     * @return Retorna el número a binari com a String.
     * */
    private static String decimalABinari(int val) {
        return Integer.toBinaryString(val);
    }

    /**
     * Converteix a decimal el número en binari donat.
     *
     * @param operand és valor en binari que es vol passar a decimal.
     * @return Retorna el número convertit a decimal en un int.
     * */
    private static int binariADecimal(String operand) {
        return Integer.parseInt(operand, 2);
    }

    /**
     * Converteix a hexadecimal el número en decimal donat.
     *
     * @param val és l'enter que es vol passar a hexadecimal.
     * @return Retorna el número convertit a hexadecimal en String.
     * */
    private static String decimalAHexadecimal(int val) {
        return Integer.toHexString(val).toUpperCase();
    }

    /**
     * Converteix a decimal el número en hexadecimal donat.
     *
     * @param operand és el valor en hexadecimal es vol passar a decimal.
     * @return Retorna el número convertit a decimal en int.
     * */
    private static int hexadecimalADecimal(String operand) {
        return Integer.parseInt(operand, 16);
    }

    /**
     * Retorna l'equivalent de la quantitat en euros a dòlars.
     *
     * @param val és la xifra en euros que es vol passar a dòlars.
     * @return Retorna la conversió a dòlars.
     * */
    private static double eurAusd(double val) {
        return val*1.09;
    }

    /**
     * Retorna l'equivalent de la quantitat en dòlars a euros.
     *
     * @param val és la xifra en dòlars que es vol passar a euros.
     * @return Retorna la conversió a euro.
     * */
    private static double usdAeur(double val) {
        return val*0.92;
    }

    /**
     * Incrementa en 1 el valor donat.
     *
     * @param val és un int que es vol incrementar en 1.
     * @return Retorna un int on s'incrementa en 1 val.
     * */
    private static int incrementaValor(int val) {
        return val+1;
    }

    /**
     * Decrementa en 1 el valor donat.
     *
     * @param val és un int que es vol decrementar en 1.
     * @return Retorna un int on es decrementa en 1 val.
     * */
    private static int decrementaValor(int val) {
        return val-1;
    }

    /**
     * Donat un valor es retorna el seu valor absolut.
     *
     * @param val és un int del que es vol el seu valor absolut.
     * @return Retorna un int del resultat d'aplicar el valor absolut a val.
     * */
    private static double valorAbsolut(double val) {
        return Math.abs(val);
    }

    /**
     * Donada una concatenació de caràcters canvia totes les lletres de majúscules a minúscules.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna un string on totes les lletres majúscules passen a ser minúscules.
     * */
    private static String majusculesAMinuscules(String str) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('A' <= c && c <= 'Z')
                word.append((char) (c - ('Z' - 'z')));
            else
                word.append(c);
        }
        return String.valueOf(word);
    }

    /**
     * Donada una concatenació de caràcters canvia totes les lletres de minúscules a majúscules.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna un string on totes les lletres minúscules passen a ser majúscules.
     * */
    private static String minusculesAMajuscules(String str) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('a' <= c && c <= 'z')
                word.append((char) (c + ('Z' - 'z')));
            else
                word.append(c);
        }
        return String.valueOf(word);
    }

    /**
     * Donada una concatenació de caràcters canvia totes les lletres de majúscules a minúscules i les minúscules a majúscules.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna un string on totes les lletres majúscules passen a ser minúscules i les minúscules passen a ser majúscules.
     * */
    private static String intercanviaMinusculesAMajuscules(String str) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ('a' <= c && c <= 'z')
                word.append((char) (c + ('Z' - 'z')));
            else
                word.append((char) (c - ('Z' - 'z')));
        }
        return String.valueOf(word);
    }

    /**
     * Donada una concatenació de caràcters retorna el nombre de caràcters que conté.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna un enter amb el nombre de caràcters que conté la frase.
     * */
    private static int comptarCaracters(String str) {
        return str.length();
    }

    /**
     * Donada una concatenació de caràcters retorna el nombre de bytes en UTF-16 que conté.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna un enter amb el nombre de bytes en UTF-16 que conté la frase.
     * */
    private static int comptarBytes(String str) {
        if (str.isEmpty())
            return 0;
        else
            return str.getBytes(StandardCharsets.UTF_16).length - 2;
    }

    /**
     * Donada una data retorna el dia.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna el dia corresponent a la data.
     * */
    private static int getDia(String str) throws Exception {
        Calendar calendar = getDate(str);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Donada una data retorna el dia de la setmana.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna el dia de la setmana corresponent a la data.
     * */
    private static String getDiaSetmana(String str) throws Exception {
        Calendar calendar = getDate(str);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1)
            return "Diumenge";
        else if (day == 2)
            return "Dilluns";
        else if (day == 3)
            return "Dimarts";
        else if (day == 4)
            return "Dimecres";
        else if (day == 5)
            return "Dijous";
        else if (day == 6)
            return "Divendres";
        else
            return "Dissabte";
    }

    /**
     * Donada una data retorna el mes.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna el mes corresponent a la data.
     * */
    private static int getMes(String str) throws Exception {
        Calendar calendar = getDate(str);
        return Integer.parseInt(String.valueOf(calendar.get(Calendar.MONTH))) + 1;
    }

    /**
     * Donada una data retorna l'any.
     *
     * @param str és una concatenació de caràcters.
     * @return Retorna l'any corresponent a la data.
     * */
    private static int getAny(String str) throws Exception {
        Calendar calendar = getDate(str);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Donada una funció unària amb un valor com a paràmetre, retorna el resultat corresponent d'aplicar la funció indicada sobre el paràmetre.
     *
     * @param funcio és el nom de la funció que volem aplicar.
     * @param valor és el paràmetre al qual volem aplicar la funció.
     * @return Retorna el resultat corresponent d'aplicar la funció sobre el paràmetre.
     * */
    public static String calcula(String funcio, String valor) throws Exception {
        if (Objects.equals(funcio, TRUNCARVALOR))
            return String.valueOf(truncarValor(Double.parseDouble(valor)));
        else if (Objects.equals(funcio, ARRODONIRVALOR))
            return String.valueOf(arrodonirValor(Double.parseDouble(valor)));
        else if (Objects.equals(funcio, DECIMALABINARI))
            return decimalABinari(Integer.parseInt(valor));
        else if (Objects.equals(funcio, DECIMALAHEXADECIMAL))
            return decimalAHexadecimal(Integer.parseInt(valor));
        else if (Objects.equals(funcio, BINARIADECIMAL))
            return String.valueOf(binariADecimal(valor));
        else if (Objects.equals(funcio, HEXADECIMALADECIMAL))
            return String.valueOf(hexadecimalADecimal(valor));
        else if (Objects.equals(funcio, EURAUSD))
            return String.valueOf(eurAusd(Double.parseDouble(valor)));
        else if (Objects.equals(funcio, USDAEUR))
            return String.valueOf(usdAeur(Double.parseDouble(valor)));
        else if (Objects.equals(funcio, INCREMENTAVALOR))
            return String.valueOf(incrementaValor(Integer.parseInt(valor)));
        else if (Objects.equals(funcio, DECREMENTAVALOR))
            return String.valueOf(decrementaValor(Integer.parseInt(valor)));
        else if (Objects.equals(funcio, VALORABSOLUT))
            return String.valueOf(valorAbsolut(Integer.parseInt(valor)));
        else if (Objects.equals(funcio, MAJUSCULESAMINUSCULES))
            return majusculesAMinuscules(valor);
        else if (Objects.equals(funcio, MINUSCULESAMAJUSCULES))
            return minusculesAMajuscules(valor);
        else if (Objects.equals(funcio, INTERCANVIMINUSCULESMAJUSCULES))
            return intercanviaMinusculesAMajuscules(valor);
        else if (Objects.equals(funcio, COMPTARCARACTERS))
            return String.valueOf(comptarCaracters(valor));
        else if (Objects.equals(funcio, COMPTARBYTES))
            return String.valueOf(comptarBytes(valor));
        else if (Objects.equals(funcio, OBTENIRDIA))
            return String.valueOf(getDia(valor));
        else if (Objects.equals(funcio, OBTENIRDIASETMANA))
            return getDiaSetmana(valor);
        else if (Objects.equals(funcio, OBTENIRMES))
            return String.valueOf(getMes(valor));
        else if (Objects.equals(funcio, OBTENIRANY))
            return String.valueOf(getAny(valor));
        else
            throw new Exception("No existeix cap funció amb aquestes característiques");
    }

    // Funcions unàries amb una llista

    /**
     * Calcula la mitjana aritmètica donada una llista de valors.
     *
     * @param valors és una llista de doubles.
     * @return Retorna la mitjana aritmètica.
     */
    private static double mitjana(ArrayList<Double> valors) {
        double sum = 0;
        for (Double valor : valors) {
            sum += valor;
        }
        return sum/(double) valors.size();
    }

    /**
     * Calcula la mediana donada una llista de valors.
     *
     * @param valors és una llista de doubles.
     * @return Retorna la mediana.
     */
    private static double mediana(ArrayList<Double> valors) {
        Collections.sort(valors);
        int mig = valors.size()/2;
        if (valors.size() % 2 != 0) {
            return valors.get(mig);
        }
        else {
            return (valors.get(mig-1) + valors.get(mig)) / 2.0;
        }
    }

    /**
     * Calcula la variància donada una llista de valors.
     *
     * @param valors és una llista de doubles.
     * @return Retorna la variància.
     */
    private static double variancia(ArrayList<Double> valors) {
        double mitjana = mitjana(valors);
        double sqDiff = 0;
        for (Double valor : valors)
            sqDiff += (valor - mitjana) * (valor - mitjana);

        return sqDiff / (valors.size() - 1);
    }

    /**
     * Calcula la desviació estàndard donada una llista de valors.
     *
     * @param valors és una llista de doubles.
     * @return Retorna la desviació estàndard.
     */
    private static double desviacioEstandard(ArrayList<Double> valors) {
        return Math.sqrt(variancia(valors));
    }

    /**
     * Donada una funció unària amb una llista com a paràmetre, retorna el resultat corresponent d'aplicar la funció indicada sobre la llista.
     *
     * @param funcio és el nom de la funció que volem aplicar.
     * @param valors és el paràmetre al qual volem aplicar la funció.
     * @return Retorna el resultat corresponent d'aplicar la funció sobre la llista.
     * */
    public static String calcula(String funcio, ArrayList<String> valors) throws Exception {
        if (Objects.equals(funcio, MITJANA))
            return String.valueOf(mitjana(convert(valors)));
        else if (Objects.equals(funcio, MEDIANA))
            return String.valueOf(mediana(convert(valors)));
        else if (Objects.equals(funcio, VARIANCIA))
            return String.valueOf(variancia(convert(valors)));
        else if (Objects.equals(funcio, DESVIACIOESTANDARD))
            return String.valueOf(desviacioEstandard(convert(valors)));
        else
            throw new Exception("No existeix cap funció amb aquestes característiques");
    }

    // Funcions binàries amb dos valors

    /**
     * Suma els dos paràmetres donats.
     *
     * @param x és l'operand 1.
     * @param y és l'operand 2.
     * @return Retorna la suma dels dos paràmetres.
     * */
    private static double suma(double x, double y) {
        return x + y;
    }

    /**
     * Resta els dos paràmetres donats.
     *
     * @param x és l'operand 1.
     * @param y és l'operand 2.
     * @return Retorna la resta dels dos paràmetres.
     * */
    private static double resta(double x, double y) {
        return x - y;
    }

    /**
     * Divideix els dos paràmetres donats.
     *
     * @param x és el dividend.
     * @param y és el divisor.
     * @return Retorna la divisió dels dos paràmetres.
     * */
    private static double divideix(double x, double y) {
        return x / y;
    }

    /**
     * Multiplica els dos paràmetres donats.
     *
     * @param x és l'operand 1.
     * @param y és l'operand 2.
     * @return Retorna la multiplicació dels dos paràmetres.
     * */
    private static double multiplica(double x, double y) {
        return x * y;
    }

    /**
     * Concatena els dos paràmetres donats.
     *
     * @param str1 és la concatenació de caràcters que anirà al principi.
     * @param str2 és la concatenació de caràcters que anirà al final.
     * @return Retorna la concatenació dels dos strings.
     * */
    private static String concatena(String str1, String str2) {
        return str1 + str2;
    }

    /**
     * Calcula la diferència en dies entre dues dates.
     *
     * @param cal1 és la data inicial.
     * @param cal2 és la data final.
     * @return Retorna la diferència en dies de les dates donades.
     * */
    private static int getDies(Calendar cal1, Calendar cal2) {
        Date date1 = cal1.getTime();
        Date date2 = cal2.getTime();
        long diff = date2.getTime() - date1.getTime();

        return (int) (diff / 1000 / 60 / 60 / 24);
    }

    /**
     * Donada una funció binària de dos valors com a paràmetre, retorna el resultat corresponent d'aplicar la funció indicada sobre els dos valors.
     *
     * @param funcio és el nom de la funció que volem aplicar.
     * @param valor1 és el primer paràmetre al qual volem aplicar la funció.
     * @param valor2 és el segon paràmetre al qual volem aplicar la funció.
     * @return Retorna el resultat corresponent d'aplicar la funció sobre els dos valors.
     * */
    public static String calcula(String funcio, String valor1, String valor2) throws Exception {
        if (Objects.equals(funcio, SUMA))
            return String.valueOf(suma(Double.parseDouble(valor1), Double.parseDouble(valor2)));
        else if (Objects.equals(funcio, RESTA))
            return String.valueOf(resta(Double.parseDouble(valor1), Double.parseDouble(valor2)));
        else if (Objects.equals(funcio, MULTIPLICA))
            return String.valueOf(multiplica(Double.parseDouble(valor1), Double.parseDouble(valor2)));
        else if (Objects.equals(funcio, DIVIDEIX))
            return String.valueOf(divideix(Double.parseDouble(valor1), Double.parseDouble(valor2)));
        else if (Objects.equals(funcio, CONCATENA))
            return concatena(valor1, valor2);
        else if (Objects.equals(funcio, DIESDIFERENCIA))
            return String.valueOf(getDies(getDate(valor1), getDate(valor2)));
        else
            throw new Exception("No existeix cap funció amb aquestes característiques");
    }

    // Funcions binàries amb dues llistes

    /**
     * Donades dues llistes de doubles retorna la covariància.
     *
     * @param X és una llista de doubles.
     * @param Y és una llista de doubles.
     * @return Retorna la covariància.
     * */
    private static double covariancia(ArrayList<Double> X, ArrayList<Double> Y) throws Exception {
        if (X.size() != Y.size())
            throw new Exception("El nombre de variables no és igual");

        double mitjanaX = mitjana(X);
        double mitjanaY = mitjana(Y);
        double sum = 0.0;
        for (int i = 0; i < X.size(); i++)
            sum = (X.get(i) - mitjanaX) * (Y.get(i) - mitjanaY) + sum;
        return sum / (X.size() - 1);
    }

    /**
     * Donades dues llistes de doubles retorna la correlació.
     *
     * @param X és una llista de doubles.
     * @param Y és una llista de doubles.
     * @return Retorna el coeficient de correlació de Pearson.
     * */
    private static double correlacio(ArrayList<Double> X, ArrayList<Double> Y) throws Exception {
        if (X.size() != Y.size())
            throw new Exception("El nombre de variables no és igual");

        double sum_X = 0, sum_Y = 0, sum_XY = 0;
        double squareSum_X = 0, squareSum_Y = 0;
        int n = X.size();

        for (int i = 0; i < n; i++) {
            // sum of elements of array X.
            sum_X = sum_X + X.get(i);

            // sum of elements of array Y.
            sum_Y = sum_Y + Y.get(i);

            // sum of X[i] * Y[i].
            sum_XY = sum_XY + X.get(i) * Y.get(i);

            // sum of square of array elements.
            squareSum_X = squareSum_X + X.get(i) * X.get(i);
            squareSum_Y = squareSum_Y + Y.get(i) * Y.get(i);
        }

        return (n * sum_XY - sum_X * sum_Y) / Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y));
    }

    /**
     * Donada una funció binàra de dues llistes com a paràmetre, retorna el resultat corresponent d'aplicar la funció indicada sobre les dues llistes.
     *
     * @param funcio és el nom de la funció que volem aplicar.
     * @param valors1 és la primera llista a la qual volem aplicar la funció.
     * @param valors2 és la segona llista a la qual volem aplicar la funció.
     * @return Retorna el resultat corresponent en aplicar la funció sobre les dues llistes.
     * */
    public static String calcula(String funcio, ArrayList<String> valors1, ArrayList<String> valors2) throws Exception {
        if (Objects.equals(funcio, COVARIANCIA))
            return String.valueOf(covariancia(convert(valors1), convert(valors2)));
        else if (Objects.equals(funcio, CORRELACIO))
            return String.valueOf(correlacio(convert(valors1), convert(valors2)));
        else
            throw new Exception("No existeix cap funció amb aquestes característiques");
    }
}
