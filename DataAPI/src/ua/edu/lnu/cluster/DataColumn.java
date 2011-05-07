/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ua.edu.lnu.cluster.interpreters.StringInterpreter;

/**
 * provides basic data column information
 * @author pif
 */
public class DataColumn {

    private DataInterpreter interpreter = new StringInterpreter();
    private String name = "";
    private List<String> values = new ArrayList<String>();
    private List<Double> normalized = new ArrayList<Double>();
    private boolean usedInCalculations = true;

    public boolean isUsedInCalculations() {
        return usedInCalculations;
    }

    public void setUsedInCalculations(boolean usedInCalculations) {
        this.usedInCalculations = usedInCalculations;
    }
    
    public String getOriginalValue(int observation) {
        return values.get(observation);
    }

    public double getNormalizedValue(int observation) {
        return normalized.get(observation);
        //return ;
    }

    public void addData(String value) {
        if (value != null) {
            values.add(value);
            normalized.add(interpreter.convertValue(value));
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
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataInterpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(DataInterpreter interpreter) {
        this.interpreter = interpreter;
        for (int i = 0; i < values.size(); i++) {
            normalized.set(i, interpreter.convertValue(values.get(i)));            
        }
    }

    @Override
    public String toString() {
        return getName()+"/"+interpreter;
    }
    
    public List<Double> getNormalizedValues() {
        return Collections.unmodifiableList(normalized);
    }
}
