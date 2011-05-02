/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.dm;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import ua.edu.lnu.cluster.DataModel;

/**
 *
 * @author pif
 */
public class DMTableModel extends AbstractTableModel {

    private DataModel dataModel = null;

    public DMTableModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
    
    @Override
    public int getRowCount() {
        return dataModel.getObservationCount();
    }

    @Override
    public int getColumnCount() {
        return dataModel.getFeaturesCount();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return dataModel.getObservation(row).getField(column);
    }
    
}
