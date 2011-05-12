/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.api;

/**
 *
 * @author pif
 */
public class ClusterInfo {
    
    int[] results = new int[0];

    public ClusterInfo(int size) {
        this.results = new int[size];
    }
    
    public int getClusterNumber(int obsIndex) {
        return results[obsIndex];
    }
    
    public void setClusterNumber(int observation, int clusterNo) {
        results[observation] = clusterNo;
    }
}
