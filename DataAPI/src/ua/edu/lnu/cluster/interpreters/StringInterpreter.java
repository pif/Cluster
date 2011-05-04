/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import java.util.Arrays;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.DataInterpreter;

/**
 *
 * @author pif
 */
@ServiceProvider(service=DataInterpreter.class)
public class StringInterpreter implements DataInterpreter {

    @Override
    public double convertValue(String value) {
        return -1;
    }

    @Override
    public String toString() {
        return "Simple text";
    }

    @Override
    public void processData(List<String> data) {
        // do nothing
    }
    
}
