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

    /**
     * 
     * @return Meaningful name of the column.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.interpreter = interpreter;
        interpreter.preprocessData(values);
        for (int i = 0; i < values.size(); i++) {
            normalized.set(i, interpreter.convertValue(values.get(i)));
        }
    }

    @Override
    public String toString() {
        return getName() + "/" + interpreter;
    }

    public List<Double> getNormalizedValues() {
        return Collections.unmodifiableList(normalized);
    }
}