/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.edu.lnu.cluster.algorithm.api;

import java.util.Arrays;

/**
 *
 * @author pif
 */
public class ClusterInfo {

    int[] results = new int[0];

    public ClusterInfo(int size) {
        this.results = new int[size];
    }

    public ClusterInfo(int[] results) {
        this.results = results;
    }

    public int[] getResults() {
        return results;
    }
    
    public int getClusterNumber(int obsIndex) {
        return results[obsIndex];
    }

    public void setClusterNumber(int observation, int clusterNo) {
        results[observation] = clusterNo;
    }

    @Override
    public String toString() {
        return Arrays.toString(results);
    }
}
