/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters.renderers;

import javax.swing.DefaultCellEditor;
import javax.swing.table.TableCellEditor;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.interpreters.api.CustomCellEditRenderer;

/**
 *
 * @author pif
 */
@ServiceProvider(service=CustomCellEditRenderer.class)
public class CategoryCellEditor implements CustomCellEditRenderer {

    private final TableCellEditor def = null;
    
    @Override
    public TableCellEditor getSpecificEditor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
