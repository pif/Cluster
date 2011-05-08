/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import java.util.Arrays;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.interpreters.api.DataInterpreter;

/**
 *
 * @author pif
 */
@ServiceProvider(service=DataInterpreter.class)
public class StringInterpreter extends  DataInterpreter {

    @Override
    public double convertValue(String value) {
        return Double.NaN;
    }

    @Override
    public String getName() {
        return "Simple text";
    }

    @Override
    public void preprocessData(List<String> data) {
        // do nothing
    }
    
}
