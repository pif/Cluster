/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import ua.edu.lnu.cluster.interpreters.api.DataInterpreter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.edu.lnu.cluster.interpreters.StringInterpreter;

/**
 * provides basic data column information
 * @author pif
 */
public class DataColumn implements Serializable{
    private static final long serialVersionUID = 1L;

    
    private DataInterpreter interpreter = new StringInterpreter();
    private String name = "";
    private List<String> values = new ArrayList<String>();
    private List<Double> normalized = new ArrayList<Double>();
    private boolean usedInCalculations = true;
    public static final String PROP_NAME = "name";
    public static final String PROP_TYPE = "type";
    public static final String PROP_VALUES = "values";
    public static final String PROP_USED = "used";
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public boolean isUsedInCalculations() {
        return usedInCalculations;
    }

    public void setUsedInCalculations(boolean usedInCalculations) {
        boolean old = this.usedInCalculations;
        this.usedInCalculations = usedInCalculations;
        pcs.firePropertyChange(PROP_USED, old, this.usedInCalculations);
    }

    /**
     * 
     * @param observation index
     * @return original value, from which everything was created
     */
    public String getOriginalValue(int observation) {
        return values.get(observation);
    }

    /**
     * 
     * @param observation
     * @return value, which can be used in calculations. that's original value, 
     * parsed through interpreter
     */
    public double getNormalizedValue(int observation) {
        return normalized.get(observation);
        //return ;
    }

    public void addData(String value) {
        if (value != null) {
            values.add(value);
            normalized.add(interpreter.convertValue(value));
            pcs.firePropertyChange(PROP_VALUES, null, values);
        }
    }

    public int getSize() {
        return values.size();
    }

    /**
     *
     * @param observation
     * @return previous value
     */
    public void removeData(int observation) {
        values.remove(observation);
        normalized.remove(observation);
        pcs.firePropertyChange(PROP_VALUES, null, values);
    }

    /**
     *
     * @param observation
     * @param value
     * @return previous value
     */
    public void setData(int observation, String value) {
        if (value != null) {
            values.set(observation, value);
            normalized.set(observation, interpreter.convertValue(value));
            pcs.firePropertyChange(PROP_VALUES, null, values);
        }
    }

    public void setTranslatedData(int observation, Object value) {
        if (value != null) {
            String str = interpreter.reverseTranslate(value);
            values.set(observation, str);
            normalized.set(observation, interpreter.convertValue(str));
            pcs.firePropertyChange(PROP_VALUES, null, values);
        }
    }

    /**
     * 
     * @return Meaningful name of the column.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        String old = this.name;
        this.name = name;
        pcs.firePropertyChange(PROP_NAME, old, this.name);
    }

    public DataInterpreter getInterpreter() {
        return interpreter;
    }

    /**
     * Sets an interpreter, which is used to parse raw data 
     * in the corresponding manner: as categories, as strings, 
     * as double values and so on.
     * @param interpreter implementation of DataInterpreter interface.
     */
    public void setInterpreter(DataInterpreter interpreter) {
        DataInterpreter old = this.interpreter;
        this.interpreter = interpreter;
        interpreter.preprocessData(values);
        for (int i = 0; i < values.size(); i++) {
            normalized.set(i, interpreter.convertValue(values.get(i)));
        }
        pcs.firePropertyChange(PROP_TYPE, old, this.interpreter);
    }

    @Override
    public String toString() {
        return getName() + "/" + interpreter;
    }

    public List<Double> getNormalizedValues() {
        return Collections.unmodifiableList(normalized);
    }

    public List<String> getOriginalValues() {
        return Collections.unmodifiableList(values);
    }

    public synchronized void addPropertyChangeListener(
            PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public synchronized void removePropertyChangeListener(
            PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
}
