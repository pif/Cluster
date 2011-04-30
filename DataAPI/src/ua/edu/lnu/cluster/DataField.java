/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pif
 */
public class DataField<T> {
    
    private List<T> data = new ArrayList<T>();
    
    public T getData(int observation) {
        return data.get(observation);
    }
    
    /**
     * 
     * @param observation
     * @param value
     * @return previous value
     */
    public T setData(int observation, T value) {
        return data.set(observation, value);
    }
    
    /**
     * 
     * @param observation
     * @return previous value
     */
    public Object removeData(int observation) {
        return data.remove(observation);
    }
    
    public void addData(T value) {
        data.add(value);
    }
}
