/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.loaders;

import java.io.BufferedReader;
import java.util.List;

/**
 *
 * @author pif
 */
public interface Loader {
    String[] getObservation(String data);
    
    List<String[]> getData(BufferedReader reader);
}
