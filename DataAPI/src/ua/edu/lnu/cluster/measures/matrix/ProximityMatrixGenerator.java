/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.measures.matrix;

import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.DataModel;
import ua.edu.lnu.cluster.measures.api.ProximityMatrix;
import ua.edu.lnu.cluster.measures.api.ProximityMeasure;

/**
 *
 * @author pif
 */
@ServiceProvider(service=ProximityMatrix.class)
public class ProximityMatrixGenerator implements ProximityMatrix {

    @Override
    public double[][] calculate(DataModel model, ProximityMeasure measure) {
        int size = model.getObservationCount();
        double[][] res = new double[size][size];

        for (int i = 0; i < size; i++) {
            double[] element1 = model.getObservation(i);
            for (int j = i + 1; j < size; j++) {
                res[i][j] = measure.distance(element1, model.getObservation(j));
            }
        }

        return res;
    }
}
