/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.measures;

import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.measures.api.ProximityMeasure;

/**
 *
 * @author pif
 */
@ServiceProvider(service=ProximityMeasure.class)
public class EuclidianDistance implements ProximityMeasure {

    @Override
    public String getDisplayName() {
        return "Euclidian distance";
    }

    @Override
    public double distance(double[] elem1, double[] elem2) {
        if (elem1.length != elem2.length) {
            throw new IllegalArgumentException("Observations should be of the same size!");
        }
        
        double sum = 0;

        for (int i = 0; i < elem2.length; i++) {
            sum+=Math.pow(elem2[i]-elem1[i], 2);
        }

        return sum;
    }

    
}
