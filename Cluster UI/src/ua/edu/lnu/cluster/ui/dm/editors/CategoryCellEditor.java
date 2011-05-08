/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.dm.editors;

import java.awt.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import ua.edu.lnu.cluster.ui.dm.DMTableModel;

/**
 *
 * @author pif
 */
public class CategoryCellEditor extends AbstractCellEditor
        implements TableCellEditor {

    private JComboBox combobox = new JComboBox();
    
    @Override
    public Object getCellEditorValue() {
        return combobox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        List<String> values = ((DMTableModel)jtable.getModel()).getDataModel().getDataColumn(i1).getOriginalValues();
        Set<String> unique = new HashSet<String>(values);
        DefaultComboBoxModel model = new DefaultComboBoxModel(unique.toArray());
        combobox.setModel(model);
        
        return new DefaultCellEditor(combobox).getTableCellEditorComponent(jtable, o, bln, i, i1);
    }
}