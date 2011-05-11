/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.interpreters.api.DataInterpreter;

/**
 *
 * @author pif
 */
@ServiceProvider(service=DataInterpreter.class)
public class DoubleInterpreter extends  DataInterpreter {

    @Override
    public String getDisplayName() {
        return "Double values";
    }
    
    private List<Double> res = new ArrayList<Double>();
    
    @Override
    public double convertValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return Double.NaN;
        }
    }

    @Override
    public void preprocessData(List<String> data) {
        // nothing to do.
    }

    @Override
    public Class<?> getColumnClass() {
        return Double.class;
    }

    @Override
    public String reverseTranslate(Object value) {
        return Double.toString((Double)value);
    }
    
    

}
