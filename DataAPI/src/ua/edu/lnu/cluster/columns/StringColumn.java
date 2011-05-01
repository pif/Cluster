/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.columns;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pif
 */
public class StringColumn implements DataColumn {
    
    private List<String> data = new ArrayList<String>();
    
    /**
     * 
     * @param observation
     * @param value
     * @return previous value
     */
    @Override
    public String setData(int observation, String value) {
        return data.set(observation, value);
    }
    
    /**
     * 
     * @param observation
     * @return previous value
     */
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
