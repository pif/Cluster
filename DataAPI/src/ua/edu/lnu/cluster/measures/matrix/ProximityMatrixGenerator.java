/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.measures.matrix;

import java.util.Arrays;
import java.util.List;
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

        List<double[]> observations = model.getPreparedCalculationData();
        for (int i = 0; i < size; i++) {
            double[] element1 = observations.get(i);
            for (int j = 0; j < i; j++) {
                res[i][j] = measure.distance(element1, observations.get(j));
            }
        }

        System.out.println("Begin proximity matrix");
        for (double[] ds : res) {
            System.out.println(Arrays.toString(ds));
        }
        System.out.println("End proximity matrix");
        
        return res;
    }
}
