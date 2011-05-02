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
public class DoubleColumn extends  DataColumn {

    private List<Double> data = new ArrayList<Double>();
    
    public DoubleColumn(Collection<Double> chunks) {
        data.addAll(chunks);
    }
    
    @Override
    public String getStringValue(int observation) {
        return data.get(observation).toString();
    }

    @Override
    public double getNormalizedValue(int observation) {
        return data.get(observation);
    }

    @Override
    public void addData(String value) {
        data.add(Double.parseDouble(value));
    }

    @Override
    public void removeData(int observation) {
        data.remove(observation);
    }

    @Override
    public void setData(int observation, String value) {
        data.set(observation, Double.parseDouble(value));
    }
    
}
