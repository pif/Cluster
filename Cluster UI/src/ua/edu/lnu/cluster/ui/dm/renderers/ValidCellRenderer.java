/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.dm.renderers;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.openide.util.Lookup;
import ua.edu.lnu.cluster.validators.api.Validator;

/**
 *
 * @author pif
 */
public class ValidCellRenderer implements TableCellRenderer {

    private Map<Integer,Color> colorMap = new HashMap<Integer, Color>(){
        {
            put(Validator.LEVEL_WARNING, Color.yellow);
            put(Validator.LEVEL_ERROR, Color.red);
        }
    };
    
    private final DefaultTableCellRenderer valid = new DefaultTableCellRenderer();
    private final DefaultTableCellRenderer inValid = new DefaultTableCellRenderer();
    
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        //String stEntry = o.toString();
        for (Validator validator : Lookup.getDefault().lookupAll(Validator.class)) {
            int res = validator.isEntryValid(o);
            if (res != Validator.LEVEL_GOOD) {
                inValid.setBackground(colorMap.get(res));
                return inValid.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        }
        return valid.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
    }
    
}
