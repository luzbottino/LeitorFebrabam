/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tst.leitorfebrabam.view;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luis
 */
public class MyTableModel extends AbstractTableModel {

    final Vector<Vector> rowData;

    final Vector<String> columnNames;

    public MyTableModel(Vector<Vector> rowData, Vector<String> columnNames) {
        this.rowData = rowData;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return rowData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rowData.get(rowIndex).get(columnIndex);
    }

}
