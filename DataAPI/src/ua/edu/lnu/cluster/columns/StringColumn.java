/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.columns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author pif
 */
public class StringColumn implements DataColumn {
    
    private List<String> data = new ArrayList<String>();

    public StringColumn() {
    }

    
    public StringColumn(Collection<String> chunks) {
        data.addAll(chunks);
    }
    
    @Override
    public void setData(int observation, String value) {
        data.set(observation, value);
    }
    
    @Override
    public void removeData(int observation) {
        data.remove(observation);
    }
    
    @Override
    public void addData(String value) {
        data.add(value);
    }

    @Override
    public String getStringValue(int observation) {
        return data.get(observation);
    }

    @Override
    public double getNormalizedValue(int observation) {
        return 0;
    }
}
