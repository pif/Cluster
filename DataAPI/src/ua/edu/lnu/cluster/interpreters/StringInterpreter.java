/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.interpreters;

import ua.edu.lnu.cluster.DataInterpreter;

/**
 *
 * @author pif
 */
public class StringInterpreter implements DataInterpreter {

    @Override
    public double convertValue(String value) {
        return 0;
    }

    @Override
    public String getName() {
        return "Simple text";
    }
    
}
