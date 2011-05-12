/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.kmeans;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
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
    private List<double[]> data;
    private int clusterCount;
    private double[][] clusterMeans;
    /**
     * array, where i-th element of the dataset is moved to a[i] cluster
     */
    private int[] resultingSet;
    private ProximityMeasure measure;

    public KMeans() {
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
    private void clusterData(ProgressHandle handle) {
        initMeans();

        boolean movedElements = true;
        while(movedElements) {
            System.out.println(Arrays.toString(resultingSet));
            movedElements = moveObservations();
            updateMeans();
            
            try {Thread.sleep(1000);} catch (InterruptedException ex) {
}

            handle.progress("Calculating...");
        }
    }

    private boolean moveObservations() {
        boolean movedElements = false;
        for (int i = 0; i < data.size(); i++) {
            double[] observation = data.get(i);
            double distance = measure.distance(observation, clusterMeans[resultingSet[i]]);
            for (int j = 0; j < clusterMeans.length; j++) {
                double length = measure.distance(observation, clusterMeans[j]);
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

    private void initMeans() {
        Random r = new Random();
        for (int i = 0; i < data.size(); i++) {
            resultingSet[i] = r.nextInt(clusterCount);
        }
        updateMeans();
    }

    private void updateMeans() {
        for (int i = 0; i < clusterMeans.length; i++) {
            double[] mean = clusterMeans[i];
            int elements = 0;
            for (int j = 0; j < data.size(); j++) {
                if (resultingSet[j] == i) {
                    elements++;
                    double[] d = data.get(j);
                    for (int k = 0; k < d.length; k++) {
                        mean[k] += d[k];
                    }
                }
            }
            if (elements > 0) {
                for (int j = 0; j < mean.length; j++) {
                    mean[j] /= elements;
                }
            }
        }
    }

    @Override
    public ClusterInfo calculate(List<double[]> observations, double[][] matrix, ProximityMeasure measure, int clusterCount) {
        
        this.clusterCount = clusterCount;
        this.data = observations;
        this.measure = measure;
        this.clusterMeans = new double[clusterCount][data.get(0).length];
        this.resultingSet = new int[data.size()];

        ProgressHandle handle = ProgressHandleFactory.createHandle("Calculating K-Means partitions...");
        handle.start();
        
        this.clusterData(handle);
        
        handle.finish();
        
        ClusterInfo info = new ClusterInfo(resultingSet);
        return info;
    }

    @Override
    public String toString() {
        return "K-Means algorithm";
    }
 
    
}