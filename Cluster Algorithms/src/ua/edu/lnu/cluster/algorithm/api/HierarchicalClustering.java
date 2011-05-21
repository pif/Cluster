/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.api;

import javax.swing.JTree;

/**
 *
 * @author Taras
 */
public interface HierarchicalClustering extends ClusteringAlgorithm {
    ClusterTree clusterData(double[][] matrix);
    JTree getResultTree(double[][] matrix);
}
