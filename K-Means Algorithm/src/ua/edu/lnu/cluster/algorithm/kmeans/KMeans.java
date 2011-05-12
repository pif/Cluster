/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.kmeans;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import ua.edu.lnu.cluster.algorithm.api.ClusterInfo;
import ua.edu.lnu.cluster.algorithm.api.PartitionalClustering;
import ua.edu.lnu.cluster.measures.api.ProximityMeasure;

/**
 *
 * @author pif
 */
@ServiceProvider(service=PartitionalClustering.class)
public class KMeans implements PartitionalClustering {
    public KMeans(){
    }
    
    /**
     * 0.   randomly initialise means.
     *      to achive this assign each observation to the randomly
     *      chosen class. then calculate means, according to the chosen values.
     *
     * 1.   assign each observation to the cluster with the closest mean.
     *
     * 2.   update means
     *
     * 3.   if observations don't change their class -> stop
     *
     * @return 
     */
    @Override
    public ClusterInfo calculate(List<double[]> observations, double[][] matrix, ProximityMeasure measure, int clusterCount) {
        int observationsCount = matrix.length;
        int featureCount = observations.get(0).length;
        ClusterInfo result = new ClusterInfo(observationsCount);
        double[][] clusterMeans = new double[clusterCount][featureCount];
        result = initMeans(observations, result);
        clusterMeans = updateMeans(result);

        
        return result;
    }
    
    private ClusterInfo initMeans(List<double[]> observations, ClusterInfo info) {
        Random r = new Random();
        for (int i = 0; i < data.length; i++) {
            info.setClusterNumber(i, r.nextInt(clusterCount));
        }
        return info;
    }

    private double[][] updateMeans(ClusterInfo info, double[][] clMeans) {
        for (int i = 0; i < clMeans.length; i++) {
            double[] mean = clMeans[i];
            int elements = 0;
            for (int j = 0; j < data.length; j++) {
                if (info.getClusterNumber(j) == i) {
                    elements++;
                    for (int k = 0; k < data[j].length; k++) {
                        mean[k] += data[j][k];
                    }
                }
            }
            if (elements > 0) {
                for (int j = 0; j < mean.length; j++) {
                    mean[j] /= elements;
                }
            }
        }
        return clMeans;
    }

    
    public int[] clusterData() {

        boolean movedElements = true;
        while(movedElements) {
            System.out.println(Arrays.toString(resultingSet));
            movedElements = moveObservations();
            updateMeans();
        }

        return resultingSet;
    }

    private boolean moveObservations() {
        boolean movedElements = false;
        for (int i = 0; i < data.length; i++) {
            double[] observation = data[i];
            double distance = getDistance(observation, clusterMeans[resultingSet[i]]);
            for (int j = 0; j < clusterMeans.length; j++) {
                double length = getDistance(observation, clusterMeans[j]);
                if (length < distance) {
                    distance = length;
                    if (resultingSet[i] != j) {
                        movedElements = true;
                        resultingSet[i] = j;
                    }
                }
            }
        }
        return movedElements;
    }



    /**
     * move this into Distance Interface: Euclidian,...
     * @param observation
     * @param cluster
     * @return distance from observation to cluster mean
     */
    private double getDistance(double[] observation, double[] cluster) {
        if (observation.length != cluster.length) {
            throw new IllegalArgumentException("Observation and cluster mean should be of the same length");
        }
        double sum = 0;

        for (int i = 0; i < cluster.length; i++) {
            sum+=Math.pow(cluster[i]-observation[i], 2);
        }

        return sum;
    }
    
}