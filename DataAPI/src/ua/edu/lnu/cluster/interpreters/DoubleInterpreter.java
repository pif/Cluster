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
public class DoubleInterpreter extends  DataInterpreter {

    @Override
    public String getName() {
        return "Double values";
    }
    
    private List<Double> res = new ArrayList<Double>();
    
    @Override
    public double convertValue(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public void preprocessData(List<String> data) {
        // nothing to do.
    }

}
