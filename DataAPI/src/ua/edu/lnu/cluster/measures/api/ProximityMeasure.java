/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.measures.api;

/**
 *
 * @author pif
 */
public interface ProximityMeasure {
    String getDisplayName();
    double distance(double[] elem1, double[] elem2);
}
