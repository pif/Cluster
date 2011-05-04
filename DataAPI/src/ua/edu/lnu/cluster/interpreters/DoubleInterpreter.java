/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import java.util.ArrayList;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.DataInterpreter;

/**
 *
 * @author pif
 */
@ServiceProvider(service=DataInterpreter.class)
public class DoubleInterpreter implements DataInterpreter {

    private List<Double> res = new ArrayList<Double>();
    
    @Override
    public double convertValue(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public String toString() {
        return "Double values";
    }

    @Override
    public void processData(List<String> data) {
        // nothing to do. this 
    }

}
