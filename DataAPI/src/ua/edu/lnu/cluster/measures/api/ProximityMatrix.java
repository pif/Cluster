/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.measures.api;

import ua.edu.lnu.cluster.DataModel;

/**
 *
 * @author pif
 */
public interface ProximityMatrix {
    double[][] calculate(DataModel model, ProximityMeasure measure);
}
