/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.DataInterpreter;

/**
 *
 * @author pif
 */
@ServiceProvider(service=DataInterpreter.class)
public class CategoryInterpreter implements DataInterpreter{

    private Map<String, Double> dictionary = new HashMap<String, Double>();
    
    @Override
    public double convertValue(String value) {
        if (dictionary.containsKey(value)) {
            return dictionary.get(value);
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
            return "Categories";
    }

    @Override
    public void processData(List<String> data) {
        double counter = 0;
        for (String string : data) {
            if (!dictionary.containsKey(string)) {
                dictionary.put(string, counter);
                ++counter;
            }
        }
    }
    
}
