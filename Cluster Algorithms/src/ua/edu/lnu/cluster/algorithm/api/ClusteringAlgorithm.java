/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.api;

import java.util.List;
import ua.edu.lnu.cluster.measures.api.ProximityMatrix;
import ua.edu.lnu.cluster.measures.api.ProximityMeasure;

/**
 *
 * @author pif
 */
public interface ClusteringAlgorithm {
    ClusterInfo calculate(List<double[]> observations, ProximityMatrix matrix, ProximityMeasure measure, int clusterCount);
}
