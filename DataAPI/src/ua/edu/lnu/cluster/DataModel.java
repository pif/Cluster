/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import ua.edu.lnu.cluster.columns.DataColumn;
import java.util.ArrayList;
import java.util.List;

/**
 * class which represents clustering data
 * @author pif
 */
public class DataModel {
    private List<DataColumn> dataColumns = new ArrayList<DataColumn>();
    private List<Observation> observations = new ArrayList<Observation>();
    
    public void removeObservation(int id) {
        for (DataColumn dataField : dataColumns) {
            dataField.removeData(id);
        }
        observations.remove(id);
    }    
    
    public void addObservation(Object[] observation) {
        if (dataColumns.size() != observation.length) {
            throw new RuntimeException("Observation has wrong number of fields.");
        }
        
        for (int i = 0; i < observation.length; i++) {
            dataColumns.get(i).addData(observation[i]);
        }
        
        observations.add(new Observation(observation));
    }
    
    public Observation getObservation(int index) {
        return observations.get(index);
    }
 
}
