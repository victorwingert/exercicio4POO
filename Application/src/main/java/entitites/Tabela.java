package entitites;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Tabela extends JTable {

    private DefaultTableModel tableModel;

    public Tabela() {
        initiateTable();
        this.getTableHeader().setReorderingAllowed(false);
        setColumnWidths(new int[]{150, 200, 50, 150});
        for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setResizable(false);
        }
    }

    public void initiateTable() {
        String[] columns = {"Nome", "Data de Nascimento", "Idade", "CPF"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.setModel(tableModel);
    }

    public void setColumnWidths(int[] widths) {
        for (int i = 0; i < widths.length; i++) {
            TableColumn column = this.getColumnModel().getColumn(i);
            column.setPreferredWidth(widths[i]);
        }
    }

    public void addRow(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void removeSelectedRow() throws CamposInvalidosException {
        int selectedRow = this.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            throw new CamposInvalidosException("Selecione um usário para remover!");
        }
    }

    public void editSelectedRow(Object[] rowData) throws CamposInvalidosException {
        int selectedRow = this.getSelectedRow();
        if (selectedRow != -1 && rowData.length == tableModel.getColumnCount()) {
            for (int i = 0; i < rowData.length; i++) {
                tableModel.setValueAt(rowData[i], selectedRow, i);
            }
        } else {
            throw new CamposInvalidosException("Selecione um usuário para editar!");
        }
    }
}
