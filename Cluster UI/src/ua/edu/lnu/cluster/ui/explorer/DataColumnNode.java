/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.Lookup;
import org.openide.util.WeakListeners;
import org.openide.util.lookup.Lookups;
import ua.edu.lnu.cluster.DataColumn;

/**
 * Visual representation of DataModel.class
 * @author pif
 */
public class DataColumnNode extends AbstractNode implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        if (DataColumn.PROP_NAME.equals(pce.getPropertyName())) {
            this.fireDisplayNameChange(null, getDisplayName());            
        } else if (DataColumn.PROP_USED.equals(pce.getPropertyName())) {
            this.fireDisplayNameChange(null, getDisplayName());            
        } else if (DataColumn.PROP_TYPE.equals(pce.getPropertyName())) {
            this.fireIconChange();
        } 
    }

    public DataColumnNode(DataColumn column) {
        super(Children.LEAF, Lookups.singleton(column));
        column.addPropertyChangeListener(WeakListeners.propertyChange(this, column));
    }

    @Override
    public String getHtmlDisplayName() {
        DataColumn column = getLookup().lookup(DataColumn.class);
        if (column != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(column.isUsedInCalculations() ? 
                    "<font color='!textText'>" :
                    "<font size='8' color='!controlShadow'>");
            sb.append(column.getName());
            sb.append("</font>");
            
            return sb.toString();
        } else {
            return null;
        }        
    }
}
