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

    @Override
    public double convertValue(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public String getName() {
        return "Double values";
    }

    @Override
    public List<Double> convertData(List<String> data) {
        List<Double> res = new ArrayList<Double>(data.size());
        
        for (int i = 0; i < data.size(); i++) {
            res.set(i, convertValue(data.get(i)));
        }
        
        return res;
    }

}
