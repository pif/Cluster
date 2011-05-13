/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.api;

/**
 *
 * @author Taras
 */
public interface HierarchicalClustering extends ClusteringAlgorithm {
    ClusterTree clusterData(double[][] matrix);
}
