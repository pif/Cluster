/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster;

import java.util.List;

/**
 *
 * @author pif
 */
public interface DataInterpreter {
    double convertValue(String value);
    
    String getName();
    
    List<Double> convertData(List<String> data);
}
