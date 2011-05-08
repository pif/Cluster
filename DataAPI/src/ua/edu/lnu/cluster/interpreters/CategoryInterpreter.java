/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.interpreters.api.DataInterpreter;

/**
 *
 * @author pif
 */
@ServiceProvider(service = DataInterpreter.class)
public class CategoryInterpreter extends DataInterpreter {

    private Map<String, Double> dictionary = new HashMap<String, Double>();
    
    @Override
    public double convertValue(String value) {
        if (dictionary.containsKey(value)) {
            return dictionary.get(value);
        } else {
            return Double.NaN;
        }
    }

    @Override
    public String getName() {
        return "Categories";
    }

    @Override
    public void preprocessData(List<String> data) {
        dictionary.clear();
        double categoryCounter = 0;
        for (String string : data) {
            if (!dictionary.containsKey(string)) {
                dictionary.put(string, categoryCounter);
                categoryCounter++;
            }
        }
    }

    @Override
    public Class<?> getColumnClass() {
        return Set.class;
    }

    /**
     * it's waiting for a string representation of category.
     * CellEditor, responsible for CategoryInterpreter will show a JCombobox 
     * with available values, taken from dictionary.
     * @param value
     * @return 
     */
    @Override
    public String reverseTranslate(Object value) {
        return (String) value;
    }
}
