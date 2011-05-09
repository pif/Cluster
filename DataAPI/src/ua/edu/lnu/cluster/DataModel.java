/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class which represents clustering data
 * @author pif
 */
public class DataModel implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String PROP_NAME = "name";
    public static final String PROP_DATA = "dataChange";
    private List<DataColumn> dataColumns = new ArrayList<DataColumn>();
    private List<Observation> observations = new ArrayList<Observation>();
    private String name = "Dataset";
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public DataModel(List<String[]> rawData) {
        this(rawData, Collections.EMPTY_LIST);
    }
    
    public DataModel(List<String[]> rawData, List<String> headers) {
        if (!rawData.isEmpty()) {
            String[] observation = rawData.get(0);
            int featureCount = observation.length;

            for (int i = 0; i < featureCount; i++) {
                DataColumn column = new DataColumn();
                String header = i < headers.size() ? headers.get(i) : "Column " + i;
                column.setName(header);

                dataColumns.add(column);
            }

            for (int i = 0; i < rawData.size(); i++) {
                String[] chunks = rawData.get(i);
                for (int j = 0; j < chunks.length; j++) {
                    dataColumns.get(j).addData(chunks[j]);
                }

                observations.add(new Observation(chunks));
            }


        }
    }

    public DataModel() {
    }

    public int getObservationCount() {
        return observations.size();
    }

    public int getFeaturesCount() {
        return dataColumns.size();
    }

    public void removeObservation(int id) {
        for (DataColumn dataField : dataColumns) {
            dataField.removeData(id);
        }
        observations.remove(id);

        pcs.firePropertyChange(PROP_DATA, null, observations);
    }

//    public void addObservation(Object[] observation) {
//        if (dataColumns.size() != observation.length) {
//            throw new RuntimeException("Observation has wrong number of fields.");
//        }
//        
//        for (int i = 0; i < observation.length; i++) {
//            dataColumns.get(i).addData(observation[i]);
//        }
//        
//        observations.add(new Observation(observation));
//    }
    public Observation getObservation(int index) {
        return observations.get(index);
    }

    public DataColumn getDataColumn(int index) {
        return dataColumns.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name == null ? "" : name;
        pcs.firePropertyChange(PROP_NAME, oldName, this.name);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

    public List<DataColumn> getDataColumns() {
        return Collections.unmodifiableList(dataColumns);
    }
}
