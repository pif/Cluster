/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.dm.renderers;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import ua.edu.lnu.cluster.validators.api.Validator;

/**
 *
 * @author pif
 */
public class ValidCellRenderer implements TableCellRenderer {

    private final DefaultTableCellRenderer valid = new DefaultTableCellRenderer();
    private final DefaultTableCellRenderer inValid = new DefaultTableCellRenderer();
    private final Color invalidColor = Color.red;
    
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        String stEntry = o.toString();
        for (Validator validator : Lookup.getDefault().lookupAll(Validator.class)) {
            if (!validator.isEntryValid(stEntry)) {
                Component inVal = inValid.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
                inVal.setBackground(invalidColor);
                return inVal;
            }
        }
        return valid.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
    }
    
}
