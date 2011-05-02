/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.loaders.api;

import java.io.BufferedReader;
import java.util.List;
import ua.edu.lnu.cluster.DataModel;

/**
 *
 * @author pif
 */
public interface Loader {
    String[] getObservation(String data);
    
    DataModel getData(BufferedReader reader, boolean headersFirst);
    
    String getName();
    
    
}
