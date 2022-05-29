package classes.persistence;

import classes.Parser;
import javax.json.*;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.*;

/**
 * Representa l'exportació d'un dull d'un document a format csv.
 * @author carla.canovas.iglesias
 */
public class ExportToCsv {

    /**
     * Constructora d'un full en csv.
     *
     * @param full és el full en format Json a exportar.
     * */
    public void exportCSV(String docName, String sheetName, JsonArray full) throws Exception {
        StringBuilder csv = new StringBuilder();
        Parser parser = new Parser();
        HashMap<String, String> cells = new HashMap<>();
        int maxColumn = 0, maxFila = 0;
        for (JsonValue celaJsonValue : full) {
            JsonObject celaJson = celaJsonValue.asJsonObject();
            String pos = celaJson.getString("position");
            String valor = celaJson.getString("value");
            cells.put(pos, valor);

            int column = parser.lettersToNumber(parser.getColumn(pos));
            if (column > maxColumn)
                maxColumn = column;

            int fila = parser.getRow(pos);
            if (fila > maxFila)
                maxFila = fila;
        }

        for (int fila = 1; fila <= maxFila; fila++) {
            for (int columna = 1; columna <= maxColumn; columna++) {
                String pos = parser.numberToLetters(columna) + fila;
                if (cells.containsKey(pos))
                    csv.append(cells.get(pos));
                if (columna != maxColumn)
                    csv.append(";");
            }
            csv.append("\n");
        }

        try (PrintWriter out = new PrintWriter(docName + "_" + sheetName + ".csv")) {
            out.println(csv);
        } catch (FileNotFoundException e) {
            throw new Exception("El document no s'ha pogut exportar a csv");
        }
    }
}
