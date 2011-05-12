/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * class which represents clustering data
 * @author pif
 */
public class DataModel implements PropertyChangeListener {

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
                column.addPropertyChangeListener(this);
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
        //return observations.size();
        if (dataColumns.isEmpty()) {
            return 0;
        }

        return dataColumns.get(0).getSize();
    }

    /**
     * 
     * @return number of datacolumns.
     */
    public int getFeaturesCount() {
        return dataColumns.size();
    }

    /**
     * 
     * @return number of used in calculations columns
     */
    public int getEnabledFeaturesCount() {
        int count = 0;
        for (DataColumn dataColumn : dataColumns) {
            if (dataColumn.isUsedInCalculations()) {
                ++count;
            }
        }
        return count;
    }

    public void removeObservation(int id) {
        for (DataColumn dataColumn : dataColumns) {
            dataColumn.removeData(id);
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
//    public Observation getObservation(int index) {
//        return observations.get(index);
//    }
    public double[] getObservationNormalized(int index) {
        double[] observation = new double[getFeaturesCount()];
        for (int i = 0; i < getFeaturesCount(); i++) {
            observation[i] = getDataColumn(i).getNormalizedValue(index);
        }
        return observation;
    }

    /**
     * Filters only usedInCalculations features. 
     * May return NaN normalized values!
     * @param index of observation
     * @return observation which includes only features, which are used 
     * in calculations
     */
    public double[] getObservationFiltered(int index) {
        double[] observation = new double[getEnabledFeaturesCount()];
        int idx = 0;
        for (int i = 0; i < getFeaturesCount(); i++) {
            if (getDataColumn(i).isUsedInCalculations()) {
                observation[idx++] = getDataColumn(i).getNormalizedValue(index);
            }
        }
        return observation;
    }

    public String[] getObservationRaw(int index) {
        String[] observation = new String[getFeaturesCount()];
        int idx = 0;
        for (int i = 0; i < getFeaturesCount(); i++) {
            observation[i] = getDataColumn(i).getOriginalValue(index);
        }
        return observation;
    }

    /**
     * 
     * @return data, which is filled only with needed data.
     * <ul><li>features, which are used in calculations.</li>
     *  <li>all !NaN observations</li></ul>
     */
    public List<double[]> getPreparedCalculationData() {
        List<double[]> res = new ArrayList<double[]>();
        for (int i = 0; i < getObservationCount(); i++) {
            double[] observation = getObservationFiltered(i);
            if (!hasNaNs(observation)) {
                res.add(observation);
            }
        }

        return res;
    }

    private boolean hasNaNs(double[] d) {
        for (double e : d) {
            if (e == Double.NaN) {
                return true;
            }
        }
        return false;
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

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
        pcs.firePropertyChange(pce);
    }
}
