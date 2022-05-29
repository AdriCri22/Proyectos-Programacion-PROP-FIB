package classes.presentation;

import controllers.CtrlPresentation;
import classes.Parser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Representa la vista principal del full de càlcul.
 *
 * @author irene.bertolin.rico
 * @author alex.moreno.baeza
 */

public class MainView extends JFrame {


    private final CtrlPresentation iCtrlPresentation;

    private final JFrame frame = new JFrame("El nostre full de càlcul");
    private final JMenuBar menuBar = new JMenuBar();
    private final JButton New = new JButton("Nou");
    private final JButton Open = new JButton("Obrir");
    private final JButton Save = new JButton("Guardar");
    private final JButton Export = new JButton("Exportar");
    private final JButton AddRows = new JButton("Afegir fila");
    private final JButton DeleteRows = new JButton("Eliminar fila");
    private final JButton AddColumns = new JButton("Afegir columna");
    private final JButton DeleteColumns = new JButton("Eliminar columna");
    private final JButton Search = new JButton("Buscar");
    private final JButton SearchAndReplace = new JButton("Buscar i reemplaçar");
    private final JButton Sort = new JButton("Ordenar");
    private final JButton addSheet = new JButton("Afegir Full");
    private final JButton deleteSheet = new JButton("Elimina Full");

    private final JComboBox<String> sheets = new JComboBox<>();
    private final JPanel sheet1 = new JPanel();

    private final JTextField contentBar = new JTextField(125);
    private final DefaultTableModel model = new DefaultTableModel(1000, 1000);
    private final JTable table = new JTable(model);
    private final JScrollPane scrollPane = new JScrollPane(table);
    private final DefaultListModel<Integer> listModel = new DefaultListModel<>();
    private final JList<Integer> rowHeader = new JList<>(listModel);
    private final JPopupMenu popupMenu = new JPopupMenu();

    private final JMenuItem copy = new JMenuItem("Copiar");
    private final JMenuItem move = new JMenuItem("Moure");
    private String fromCell = "";
    private String fromCellFinal = "";
    private boolean copyValue = false;
    private boolean moveValue = false;
    private int rowsSelected = 0;
    private int columnsSelected = 0;
    private String sheetAnterior = "";
    private boolean sePuede = true;
    private boolean intent = true;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * Inicialitza els components de la Vista Principal i assigna els listeners corresponents.
     */
    private void initializeComponents() {
        initializeFrame();
        initializeMenuBar();
        initializeTabbedSheets();
        initializeTable();
        initializePopUpMenu();
        addComponentsFrame();

        assign_listenerComponents();
    }

    /**
     * Inicialitza el Frame de la Vista Principal.
     */
    private void initializeFrame() {
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setPreferredSize(new Dimension(screenSize.width, screenSize.height));
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    /**
     * Inicialitza els botons del menú de la Vista Principal.
     */
    private void initializeMenuBar() {
        menuBar.add(New);
        menuBar.add(Open);
        menuBar.add(Save);
        menuBar.add(Export);
        menuBar.add(AddRows);
        menuBar.add(DeleteRows);
        menuBar.add(AddColumns);
        menuBar.add(DeleteColumns);
        menuBar.add(Search);
        menuBar.add(SearchAndReplace);
        menuBar.add(Sort);
        menuBar.add(addSheet);
        menuBar.add(deleteSheet);
    }

    /**
     * Inicialitza les pestanyes amb els fulls de la Vista Principal.
     */
    private void initializeTabbedSheets() {
        sheets.addItem("Full 0");
        sheetAnterior = "Full 0";
        contentBar.setSize(new Dimension(sheet1.getWidth(),20));
        contentBar.setEditable(false);
        sheet1.add(sheets);
        sheet1.add(contentBar);
    }


    /**
     * Inicialitza la taula de la Vista Principal.
     */
    private void initializeTable() {
        table.setCellSelectionEnabled(true);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        table.setPreferredScrollableViewportSize(new Dimension(1400,650));

        for (int i = 1; i <= table.getRowCount(); i++) listModel.addElement(i);
        rowHeader.setFixedCellWidth(30);
        rowHeader.setFixedCellHeight(table.getRowHeight());

        scrollPane.setRowHeaderView(rowHeader);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        sheet1.add(table.getTableHeader());
        sheet1.add(scrollPane);
    }

    /**
     * Afegeix elements al frame i el fa visible.
     */
    private void addComponentsFrame() {
        frame.getContentPane().add(BorderLayout.NORTH,menuBar);
        frame.getContentPane().add(sheet1);
        frame.setVisible(true);
    }

    /**
     * Inicialitza els popUpMenu la Vista Principal.
     */
    private void initializePopUpMenu() {
        popupMenu.add(copy);
        popupMenu.add(move);
    }

    /**
     * Constructora de la Vista Principal.
     *
     * @param pCtrlPresentation controlador presentació.
     */
    public MainView(CtrlPresentation pCtrlPresentation) {
        iCtrlPresentation = pCtrlPresentation;
        initializeComponents();
    }

    /**
     * Fa visible el frame de la Vista Principal.
     */
    public void setVisible() {
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Fa visible el dialog.
     *
     * @param dialog a fer visible.
     */
    public void dialogSetVisible(JDialog dialog) {
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    /**
     * Mostra un missatge quan es produeix una excepció.
     *
     * @param msg és el missatge informatiu de l'error que s'ha produït.
     */
    private void showExceptions(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }


    /**
     * Tracta els botons del menú de la Vista Principal.
     *
     * @param e acció de l'usuari.
     * @throws Exception diferents tipus d'excepcions segons el format d'entrada.
     */
    public void actionPerformed_buttons (ActionEvent e) throws Exception {
        JDialog secundaria;
        Object source = e.getSource();
        if (New.equals(source)) {
            intent = false;
            secundaria = new AddDocNameDialogView();
            dialogSetVisible(secundaria);
            String docName = ((AddDocNameDialogView) secundaria).getInputName();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            if(!docName.isEmpty()) iCtrlPresentation.callNewDocument(docName);
            frame.setTitle(iCtrlPresentation.getNameDoc());

            sheets.removeAllItems();
            sheets.addItem("Full 0");

            HashMap<String,String> allCells = iCtrlPresentation.getAllCells("Full 0");
            update(allCells,aux);
            intent = true;
        }
        else if (Open.equals(source)) {
            intent = false;
            secundaria = new AddDocPathDialogView();
            dialogSetVisible(secundaria);
            String docPath = ((AddDocPathDialogView) secundaria).getInputPath();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);

            if(!docPath.isEmpty()) iCtrlPresentation.callOpenDocument(docPath);
            frame.setTitle(iCtrlPresentation.getNameDoc());

            sheets.removeAllItems();

            ArrayList<String> allSheets = iCtrlPresentation.getAllSheets();

            for (String allSheet : allSheets) {
                sheets.addItem(allSheet);
            }
            String sheetName = (String) sheets.getSelectedItem();
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);
            update(allCells,aux);
            intent = true;
        }
        else if (AddRows.equals(source)) {
            secundaria = new AddRowsDialogView();
            dialogSetVisible(secundaria);
            String filaIni = ((AddRowsDialogView) secundaria).getIniRow();
            String numFiles = ((AddRowsDialogView) secundaria).getNumRows();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            if (!filaIni.isEmpty() && !numFiles.isEmpty()) iCtrlPresentation.callAddRows(Integer.parseInt(filaIni), Integer.parseInt(numFiles));
            String sheetName = (String) sheets.getSelectedItem();
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);

            update(allCells,aux);
        }
        else if (DeleteRows.equals(source)) {
            secundaria = new DeleteRowsDialogView();
            dialogSetVisible(secundaria);
            String filaIni = ((DeleteRowsDialogView) secundaria).getIniRow();
            String numFiles = ((DeleteRowsDialogView) secundaria).getNumRows();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            if (!filaIni.isEmpty() && !numFiles.isEmpty()) iCtrlPresentation.callDeleteRows(Integer.parseInt(filaIni), Integer.parseInt(numFiles));
            String sheetName = (String) sheets.getSelectedItem();
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);

            update(allCells,aux);
        }
        else if (AddColumns.equals(source)) {
            secundaria = new AddColumnsDialogView();
            dialogSetVisible(secundaria);
            String colIni = ((AddColumnsDialogView) secundaria).getIniCol();
            String numCols = ((AddColumnsDialogView) secundaria).getNumCols();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);

            if (!colIni.isEmpty() && !numCols.isEmpty()) iCtrlPresentation.callAddColumns(colIni, Integer.parseInt(numCols));

            String sheetName = (String) sheets.getSelectedItem();
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);
            int iniCol = table.getColumn(colIni).getModelIndex();
            int cols = Integer.parseInt(numCols);
            table.addColumnSelectionInterval(iniCol, iniCol+cols);

            update(allCells,aux);
        }
        else if (DeleteColumns.equals(source)) {
            secundaria = new DeleteColumnsDialogView();
            dialogSetVisible(secundaria);
            String colIni = ((DeleteColumnsDialogView) secundaria).getIniCol();
            String numCols = ((DeleteColumnsDialogView) secundaria).getNumCols();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            if (!colIni.isEmpty() && !numCols.isEmpty()) iCtrlPresentation.callDeleteColumns(colIni, Integer.parseInt(numCols));

            String sheetName = (String) sheets.getSelectedItem();
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);

            update(allCells,aux);
        }
        else if (Search.equals(source)) {
            secundaria = new SearchValueDialogView();
            dialogSetVisible(secundaria);
            String valueToSearch = ((SearchValueDialogView) secundaria).getInputValue();
            if (!valueToSearch.isEmpty()) iCtrlPresentation.callSearchValue(valueToSearch);
        }
        else if (SearchAndReplace.equals(source)) {
            secundaria = new SearchAndReplaceValuesDialogView();
            dialogSetVisible(secundaria);
            String valueToSearch = ((SearchAndReplaceValuesDialogView) secundaria).getValueToSearch();
            String valueToReplace = ((SearchAndReplaceValuesDialogView) secundaria).getValueForReeplace();
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            if (!valueToSearch.isEmpty() && !valueToReplace.isEmpty()) iCtrlPresentation.callSearchAndReplaceValue(valueToSearch, valueToReplace);
            String sheetName = (String) sheets.getSelectedItem();
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);

            update(allCells,aux);
        }
        else if (Sort.equals(source)) {
            secundaria = new SortCellsBlocDialogView();
            dialogSetVisible(secundaria);
            String posIni = ((SortCellsBlocDialogView) secundaria).getInitialPos();
            String posFi = ((SortCellsBlocDialogView) secundaria).getFinalPos();
            String order = "";
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            if (((SortCellsBlocDialogView) secundaria).orderIsAsc()) order = "ASC";
            else if (((SortCellsBlocDialogView) secundaria).orderIsDesc()) order = "DESC";
            if (!posFi.isEmpty() && !posIni.isEmpty() && !order.isEmpty()) iCtrlPresentation.callSortCellBloc(posIni, posFi, order);
            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(String.valueOf(sheets.getSelectedItem()));

            update(allCells,aux);
        }
        else if (addSheet.equals(source)) {
            secundaria = new AddSheetNameDialogView();
            dialogSetVisible(secundaria);
            String name = ((AddSheetNameDialogView) secundaria).getInputName();
            if (!name.isEmpty()) iCtrlPresentation.callAddSheet(name);
            sheets.addItem(name);
            sheets.setSelectedItem(name);
            String sheetName = (String) sheets.getSelectedItem();

            HashMap<String,String> allCells = iCtrlPresentation.getAllCells(sheetName);
            HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
            update(allCells,aux);
        }
        else if (deleteSheet.equals(source)) {
            if (sheets.getItemCount() > 1) {
                String sheetToRemove = String.valueOf(sheets.getSelectedItem());
                sheets.removeItemAt(sheets.getSelectedIndex());

                HashMap<String,String> allCells = iCtrlPresentation.getAllCells(String.valueOf(sheets.getSelectedItem()));
                HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
                update(allCells,aux);

                iCtrlPresentation.callDeleteSheet(sheetToRemove);
            }
            else {
                String msg = "No es pot eliminar l'únic full del document";
                showExceptions(msg);
            }
        }

    }

    /**
     * Tracta el popUpMenu de la taula de la Vista Principal.
     *
     * @param e acció de l'usuari.
     */
    public void actionPerformed_menuPopUp (ActionEvent e) {
        Object source = e.getSource();
        if (copy.equals(source)) {
            copyValue = true;
            String col = table.getColumnName(table.getSelectedColumn());
            int row = table.getSelectedRow()+1;
            fromCell = col+row;
            rowsSelected = table.getSelectedRowCount();
            columnsSelected = table.getSelectedColumnCount();
            String colFinal = table.getColumnName(table.getSelectedColumn()+columnsSelected-1);
            int rowFinal = table.getSelectedRow()+rowsSelected;
            fromCellFinal = colFinal+rowFinal;
        }
        else if (move.equals(source)) {
            moveValue = true;
            String col = table.getColumnName(table.getSelectedColumn());
            int row = table.getSelectedRow()+1;
            fromCell = col+row;
            rowsSelected = table.getSelectedRowCount();
            columnsSelected = table.getSelectedColumnCount();
            String colFinal = table.getColumnName(table.getSelectedColumn()+columnsSelected-1);
            int rowFinal = table.getSelectedRow()+rowsSelected;
            fromCellFinal = colFinal+rowFinal;
        }
    }

    /**
     * Assigna els listeners als components corresponents.
     */
    private void assign_listenerComponents() {

        // Listener pel Frame
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frame.pack();
                frame.revalidate();
            }
        });


        New.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Open.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        Save.addActionListener(e -> {
            try {
                iCtrlPresentation.callSaveDocument();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        Export.addActionListener(e -> {
            String sheetName = (String) sheets.getSelectedItem();
            try {
                iCtrlPresentation.callExportDocument(sheetName);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        AddRows.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        DeleteRows.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        AddColumns.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        DeleteColumns.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        Search.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        SearchAndReplace.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        Sort.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        addSheet.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }

        });

        deleteSheet.addActionListener(e -> {
            try {
                actionPerformed_buttons(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }

        });


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        updateBarContent();
                    } catch (Exception ex) {
                        String msg = ex.toString();
                        showExceptions(msg);
                        ex.printStackTrace();
                    }
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
                if (copyValue) {
                    try {
                        copyValue = false;
                        String col = table.getColumnName(table.getSelectedColumn());
                        int row = table.getSelectedRow()+1;
                        String toCell = col+row;
                        HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
                        iCtrlPresentation.callCopy(fromCell, fromCellFinal, toCell, rowsSelected, columnsSelected);
                        rowsSelected = 0;
                        columnsSelected = 0;
                        HashMap<String,String> allCells = iCtrlPresentation.getAllCells(String.valueOf(sheets.getSelectedItem()));
                        update(allCells,aux);
                    } catch (Exception ex) {
                        String msg = ex.toString();
                        showExceptions(msg);
                        ex.printStackTrace();
                    }
                }
                if (moveValue) {
                    try {
                        moveValue = false;
                        String col = table.getColumnName(table.getSelectedColumn());
                        int row = table.getSelectedRow()+1;
                        String toCell = col+row;
                        HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
                        iCtrlPresentation.callMove(fromCell, fromCellFinal, toCell, rowsSelected, columnsSelected);
                        HashMap<String,String> allCells = iCtrlPresentation.getAllCells(String.valueOf(sheets.getSelectedItem()));
                        update(allCells,aux);
                    } catch (Exception ex) {
                        String msg = ex.toString();
                        showExceptions(msg);
                        ex.printStackTrace();
                    }
                }
            }
        });


        sheets.addActionListener(e -> {
            if(intent) {
                String sheetName = (String) sheets.getSelectedItem();
                try {
                    HashMap<String, String> allCells = iCtrlPresentation.getAllCells(sheetName);
                    HashMap<String, String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
                    update(allCells, aux);

                } catch (Exception ex) {
                    String msg = ex.toString();
                    showExceptions(msg);
                    ex.printStackTrace();
                }
            }
        });


        table.getModel().addTableModelListener(e -> {

            if(sePuede) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                String result = (String) table.getValueAt(row, col);

                Parser parser = new Parser();
                String column  = parser.numberToLetters(col+1);
                String roww = String.valueOf(Integer.parseInt(Integer.toString(row+1)));
                String pos = column+roww ;
                String sheetName = (String) sheets.getSelectedItem();
                try {

                    if (!result.isEmpty()) {
                        iCtrlPresentation.setCell(sheetName,pos,result);
                        HashMap<String,String> allCells =  iCtrlPresentation.getAllCells(sheetName);
                        HashMap<String,String> aux = iCtrlPresentation.getAllCells(sheetAnterior);
                        update(allCells,aux);
                    }
                    else {
                        iCtrlPresentation.deleteCell(sheetName,pos);
                    }
                } catch (Exception ex) {
                    String msg = ex.toString();
                    showExceptions(msg);
                    ex.printStackTrace();
                }
            }

        });


        copy.addActionListener(e -> {
            try {
                actionPerformed_menuPopUp(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });

        move.addActionListener(e -> {
            try {
                actionPerformed_menuPopUp(e);
            } catch (Exception ex) {
                String msg = ex.toString();
                showExceptions(msg);
                ex.printStackTrace();
            }
        });
    }

    /**
     * Mostra una llista de cel·les per pantalla.
     *
     * @param cells cel·les que s'han de mostrar.
     */
    public void paintCells(ArrayList<String> cells) {
        if (cells.size() > 0) {
            StringBuilder list = new StringBuilder();
            for (String cell : cells) list.append(cell).append(System.lineSeparator());
            JOptionPane.showMessageDialog(frame, list.toString());
        }
    }

    /**
     * Actualitza el contingut del barContent.
     *
     * @throws Exception si el full no existeix.
     */
    private void updateBarContent() throws Exception {
        int col = table.getSelectedColumn();
        int row = table.getSelectedRow();
        Parser parser = new Parser();
        String column  = parser.numberToLetters(col+1);
        String roww = String.valueOf(Integer.parseInt(Integer.toString(row+1)));
        String pos = column+roww ;
        String content = iCtrlPresentation.getContent((String) sheets.getSelectedItem(),pos);
        contentBar.setText(pos + ": " +content);
    }

    /**
     * Actualitza el contingut de la taula.
     *
     * @param cells cel·les actualitzades a escriure a la taula.
     * @param aux cel·les a esborrar de la taula.
     * @throws Exception si el full no existeix.
     */
    private void update (HashMap<String,String> cells,HashMap<String,String> aux) throws Exception {

        sePuede = false;
        Parser parser = new Parser();
        for (HashMap.Entry<String, String> entry : aux.entrySet()) {
            String pos = entry.getKey();
            int row = parser.getRow(pos);
            String col = parser.getColumn(pos);
            int column;
            column = parser.lettersToNumber(col);

            table.setValueAt("", row - 1, column - 1);
        }

        sheetAnterior = (String) sheets.getSelectedItem();

        for (HashMap.Entry<String, String> entry : cells.entrySet()) {
            String pos = entry.getKey();
            int row = parser.getRow(pos);
            String col = parser.getColumn(pos);
            int column = parser.lettersToNumber(col);

            table.setValueAt(entry.getValue(), row - 1, column - 1);
        }

        updateBarContent();
        sePuede = true;
    }
}
