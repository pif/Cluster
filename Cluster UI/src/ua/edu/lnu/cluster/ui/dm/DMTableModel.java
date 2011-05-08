/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.dm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import ua.edu.lnu.cluster.DataColumn;
import ua.edu.lnu.cluster.DataModel;

/**
 *
 * @author pif
 */
public class DMTableModel extends AbstractTableModel implements PropertyChangeListener {

    private DataModel dataModel = null;

    public DMTableModel(DataModel dataModel) {
        this.dataModel = dataModel;
        for (DataColumn dataColumn : dataModel.getDataColumns()) {
            dataColumn.addPropertyChangeListener(this);
        }
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

    @Override
    public String getColumnName(int i) {
        return dataModel.getDataColumn(i).getName();
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (DataColumn.PROP_NAME.equals(pce.getPropertyName())) {
            fireTableChanged(new TableModelEvent(this, TableModelEvent.HEADER_ROW));
        }
    }
    
    
}
