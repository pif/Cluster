/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.explorer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.WeakListeners;
import org.openide.util.lookup.Lookups;
import ua.edu.lnu.cluster.DataModel;

/**
 * Visual representation of DataModel.class
 * @author pif
 */
public class DataModelNode extends AbstractNode implements PropertyChangeListener {

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        
    }

    public DataModelNode(DataModel data) {
        super(Children.create(new DataColumnsChildren(data), true), Lookups.singleton(data));
        setDisplayName("Data set [" + data.toString()+"]");
        data.addPropertyChangeListener(WeakListeners.propertyChange(this, data));
    }   
}
