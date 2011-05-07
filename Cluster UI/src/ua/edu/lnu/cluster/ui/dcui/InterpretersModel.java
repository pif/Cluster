/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.ui.dcui;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ListDataListener;
import org.openide.util.Lookup;
import ua.edu.lnu.cluster.DataInterpreter;

/**
 *
 * @author pif
 */
public class InterpretersModel extends DefaultComboBoxModel {

    private List<Object> interpreters = null;

    public InterpretersModel() {
        Collection<? extends DataInterpreter> lookupInterps = (Collection<? extends DataInterpreter>) Lookup.getDefault().lookupAll(DataInterpreter.class);
        interpreters = Arrays.asList(lookupInterps.toArray());
    }

    @Override
    public int getSize() {
        return interpreters.size();
    }

    @Override
    public Object getElementAt(int i) {
        return interpreters.get(i);
    }
}